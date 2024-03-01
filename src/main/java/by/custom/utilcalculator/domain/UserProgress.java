package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.domain.tree.Node;
import by.custom.utilcalculator.domain.tree.NodeStorage;
import by.custom.utilcalculator.exception.StepsQueueException;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class UserProgress implements Serializable {
    private CountryOrigin countryOrigin = null;
    private OwnersType ownersType = null;
    private CarAge carAge = null;
    private TypeOfEngine typeOfEngine = null;
    private VolumeOfEngine volumeOfEngine = null;
    private final String chatID;
    private Step currentQuestion;

    public UserProgress(final String chatID) {
        this.chatID = chatID;
    }

    public CountryOrigin getCountryOrigin() {
        return countryOrigin;
    }

    public String[] getUserPath() {
        String[] userPath = new String[5];

        if (getCountryOrigin() != null) {
            if (getCountryOrigin().equals(CountryOrigin.EAES)) {
                userPath[0] = Command.EAES;
            } else {
                userPath[0] = Command.OTHER_COUNTRIES;
            }
        } else {
            userPath[0] = null;
        }

        if (getOwnersType() != null) {
            if (getOwnersType().equals(OwnersType.PHYSICAL)) {
                userPath[1] = Command.PHYSICAL_PERSON;
            } else {
                userPath[1] = Command.JURIDICAL_PERSON;
            }
        } else {
            userPath[1] = null;
        }

        if (getCarAge() != null) {
            userPath[2] = Command.AGE;
        } else {
            userPath[2] = null;
        }

        if (getTypeOfEngine() != null) {
            if (getTypeOfEngine().equals(TypeOfEngine.ELECTRIC)) {
                userPath[3] = Command.ELECTRIC_TYPE_ENGINE;
            } else {
                userPath[3] = Command.GASOLINE_TYPE_ENGINE;
            }
        } else {
            userPath[3] = null;
        }

        if (getVolumeOfEngine() != null) {
            userPath[4] = Command.VOLUME;
        } else {
            userPath[4] = null;
        }
        return userPath;
    }

    public void setCountryOrigin(final CountryOrigin countryOrigin) {
        int stepID = 0;
        cleanStepsAfterCurrent(stepID);
        currentQuestion = Step.COUNTRY_ORIGIN;
        this.countryOrigin = countryOrigin;
    }

    public OwnersType getOwnersType() {
        return ownersType;
    }

    public void setOwnersType(final OwnersType ownersType) {
        int stepID = 1;
        cleanStepsAfterCurrent(stepID);
        currentQuestion = Step.OWNERS_TYPE;
        this.ownersType = ownersType;
    }

    public CarAge getCarAge() {
        return carAge;
    }

    public void setCarAge(final CarAge carAge) throws StepsQueueException {
        cleanStepsAfterCurrent(4);
        currentQuestion = Step.CAR_AGE;
        this.carAge = carAge;
    }

    public TypeOfEngine getTypeOfEngine() {
        return typeOfEngine;
    }

    public void setTypeOfEngine(final TypeOfEngine typeOfEngine) throws StepsQueueException {
        cleanStepsAfterCurrent(2);
        currentQuestion = Step.TYPE_OF_ENGINE;
        this.typeOfEngine = typeOfEngine;
    }

    public VolumeOfEngine getVolumeOfEngine() {
        return volumeOfEngine;
    }

    public void setVolumeOfEngine(final VolumeOfEngine volumeOfEngine) throws StepsQueueException {
        int stepID = 3;
        cleanStepsAfterCurrent(stepID);
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
        }
        if (stepCleaner <= 1) {
            this.ownersType = null;
        }
        if (stepCleaner <= 2) {
            this.typeOfEngine = null;
        }
        if (stepCleaner <= 3) {
            this.volumeOfEngine = null;
        }
        if (stepCleaner <= 4) {
            this.carAge = null;
        }
    }
}
