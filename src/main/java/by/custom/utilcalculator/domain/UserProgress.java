package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.exception.StepsQueueException;

import java.io.Serializable;
import java.nio.file.FileSystemNotFoundException;

public class UserProgress implements Serializable {
    private CountryOrigin countryOrigin = null;
    private OwnersType ownersType = null;
    private CarAge carAge = null;
    private TypeOfEngine typeOfEngine = null;
    private VolumeOfEngine volumeOfEngine = null;
    private final String chatID;
    private Step currentQuestion;
    private Enum[] stepsData;
    private PassengerStepsChecker passengerStepsChecker;

    public UserProgress(final String chatID) {
        this.chatID = chatID;
        stepsData = new Enum[5];
        passengerStepsChecker = PassengerStepsChecker.getInstance();
    }

    public CountryOrigin getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(final CountryOrigin countryOrigin) {
        int stepID = 0;
        cleanStepsAfterCurrent(stepID);
        stepsData[stepID] = countryOrigin;
        currentQuestion = Step.COUNTRY_ORIGIN;
        this.countryOrigin = countryOrigin;
    }

    public OwnersType getOwnersType() {
        return ownersType;
    }

    public void setOwnersType(final OwnersType ownersType) throws StepsQueueException {
        int stepID = 1;
        cleanStepsAfterCurrent(stepID);
        stepsData[stepID] = ownersType;
        if (!passengerStepsChecker.isStepOk(stepsData)) {
            throw new StepsQueueException(chatID, "owners type is wrong");
        }
        currentQuestion = Step.OWNERS_TYPE;
        this.ownersType = ownersType;
    }

    public CarAge getCarAge() {
        return carAge;
    }

    public void setCarAge(final CarAge carAge) throws StepsQueueException {
        cleanStepsAfterCurrent(4);
        stepsData[2] = carAge;
        if (!passengerStepsChecker.isStepOk(stepsData)) {
            throw new StepsQueueException(chatID, "car age is wrong");
        }
        currentQuestion = Step.CAR_AGE;
        this.carAge = carAge;
    }

    public TypeOfEngine getTypeOfEngine() {
        return typeOfEngine;
    }

    public void setTypeOfEngine(final TypeOfEngine typeOfEngine) throws StepsQueueException {
        cleanStepsAfterCurrent(2);
        stepsData[4] = typeOfEngine;
        if (!passengerStepsChecker.isStepOk(stepsData)) {
            throw new StepsQueueException(chatID, "car engine is wrong");
        }
        currentQuestion = Step.TYPE_OF_ENGINE;
        this.typeOfEngine = typeOfEngine;
    }

    public VolumeOfEngine getVolumeOfEngine() {
        return volumeOfEngine;
    }

    public void setVolumeOfEngine(final VolumeOfEngine volumeOfEngine) throws StepsQueueException {
        int stepID = 3;
        cleanStepsAfterCurrent(stepID);
        stepsData[stepID] = volumeOfEngine;
        if (!passengerStepsChecker.isStepOk(stepsData)) {
            throw new StepsQueueException(chatID, "engine volume is wrong");
        }
        currentQuestion = Step.VOLUME_OF_ENGINE;
        this.volumeOfEngine = volumeOfEngine;
    }

    public String getChatID() {
        return chatID;
    }

    //method checks for last answered question and sends next step, which depends on current user's answers
    public Step getNextStep() {
        switch (currentQuestion) {
            case COUNTRY_ORIGIN -> {
                return Step.OWNERS_TYPE;
            }
            case OWNERS_TYPE -> {
                if (ownersType == OwnersType.PHYSICAL || countryOrigin == CountryOrigin.EAES) {
                    return Step.CAR_AGE;
                } else {
                    return Step.TYPE_OF_ENGINE;
                }
            }
            case TYPE_OF_ENGINE -> {
                if (typeOfEngine == TypeOfEngine.GASOLINE) {
                    return Step.VOLUME_OF_ENGINE;
                } else {
                    return Step.CAR_AGE;
                }
            }
            case VOLUME_OF_ENGINE -> {
                return Step.CAR_AGE;
            }
            case CAR_AGE, FAREWELL -> {
                return Step.FAREWELL;
            }
            case null -> {
                return Step.COUNTRY_ORIGIN;
            }
        }
    }

    private void cleanStepsAfterCurrent(final int stepCleaner) {
        if (stepCleaner <= 0) {
            this.countryOrigin = null;
            stepsData[0] = null;
        }
        if (stepCleaner <= 1) {
            this.ownersType = null;
            stepsData[1] = null;
        }
        if (stepCleaner <= 2) {
            this.typeOfEngine = null;
            stepsData[4] = null;
        }
        if (stepCleaner <= 3) {
            this.volumeOfEngine = null;
            stepsData[3] = null;
        }
        if (stepCleaner <= 4) {
            this.carAge = null;
            stepsData[2] = null;
        }
    }
}
