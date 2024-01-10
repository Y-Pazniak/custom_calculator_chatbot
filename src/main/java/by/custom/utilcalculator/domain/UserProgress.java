package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.steps.*;

import java.io.Serializable;

public class UserProgress implements Serializable {
    private CountryOrigin countryOrigin = null;
    private OwnersType ownersType = null;
    private CarAge carAge = null;
    private TypeOfEngine typeOfEngine = null;
    private VolumeOfEngine volumeOfEngine = null;
    private final String chatID;
    private CurrentQuestion currentQuestion;

    public UserProgress(final String chatID) {
        this.chatID = chatID;
    }

    public CountryOrigin getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(final CountryOrigin countryOrigin) {
        cleanStepsAfterCurrent(1);
        currentQuestion = CurrentQuestion.COUNTRY_ORIGIN;
        this.countryOrigin = countryOrigin;
    }

    public OwnersType getOwnersType() {
        return ownersType;
    }

    public void setOwnersType(final OwnersType ownersType) {
        cleanStepsAfterCurrent(2);
        currentQuestion = CurrentQuestion.OWNERS_TYPE;
        this.ownersType = ownersType;
    }

    public CarAge getCarAge() {
        return carAge;
    }

    public void setCarAge(final CarAge carAge) {
        cleanStepsAfterCurrent(5);
        currentQuestion = CurrentQuestion.CAR_AGE;
        this.carAge = carAge;
    }

    public TypeOfEngine getTypeOfEngine() {
        return typeOfEngine;
    }

    public void setTypeOfEngine(final TypeOfEngine typeOfEngine) {
        cleanStepsAfterCurrent(3);
        currentQuestion = CurrentQuestion.TYPE_OF_ENGINE;
        this.typeOfEngine = typeOfEngine;
    }

    public VolumeOfEngine getVolumeOfEngine() {
        return volumeOfEngine;
    }

    public void setVolumeOfEngine(final VolumeOfEngine volumeOfEngine) {
        cleanStepsAfterCurrent(4);
        currentQuestion = CurrentQuestion.VOLUME_OF_ENGINE;
        this.volumeOfEngine = volumeOfEngine;
    }

    public String getChatID() {
        return chatID;
    }

    //method checks for last answered question and sends next step, which depends on current user's answers
    public CurrentQuestion getNextStep() {
        switch (currentQuestion) {
            case COUNTRY_ORIGIN -> {
                return CurrentQuestion.OWNERS_TYPE;
            }
            case OWNERS_TYPE -> {
                if (ownersType == OwnersType.PHYSICAL || countryOrigin == CountryOrigin.EAES) {
                    return CurrentQuestion.CAR_AGE;
                } else {
                    return CurrentQuestion.TYPE_OF_ENGINE;
                }
            }
            case TYPE_OF_ENGINE -> {
                if (typeOfEngine == TypeOfEngine.GASOLINE) {
                    return CurrentQuestion.VOLUME_OF_ENGINE;
                } else {
                    return CurrentQuestion.CAR_AGE;
                }
            }
            case VOLUME_OF_ENGINE -> {
                return CurrentQuestion.CAR_AGE;
            }
            case CAR_AGE, FAREWELL -> {
                return CurrentQuestion.FAREWELL;
            }
            case null -> {
                return CurrentQuestion.COUNTRY_ORIGIN;
            }
        }
    }

    private void cleanStepsAfterCurrent(final int stepCleaner) {
        if (stepCleaner <= 1) {
            this.countryOrigin = null;
        }
        if (stepCleaner <= 2) {
            this.ownersType = null;
        }
        if (stepCleaner <= 3) {
            this.typeOfEngine = null;
        }
        if (stepCleaner <= 4) {
            this.volumeOfEngine = null;
        }
        if (stepCleaner <= 5) {
            this.carAge = null;
        }
    }
}
