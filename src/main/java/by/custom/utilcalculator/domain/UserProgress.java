package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.domain.tree.CommandTree;

import java.io.Serializable;
import java.util.Map;

public class UserProgress implements Serializable {
    private TransportType transportType = null;
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

    public TransportType getTransportType(){
        return transportType;
    }

    public CountryOrigin getCountryOrigin() {
        return countryOrigin;
    }

    public Command[] getUserPath() {
        final Command[] userPath = new Command[6];
        final Map<StepsIndicator, Command> fieldsToCommands = CommandTree.getInstance().getFieldsToCommands();
        userPath[0] = fieldsToCommands.get(getTransportType()); //0 cell contains type of transport
        userPath[1] = fieldsToCommands.get(getCountryOrigin()); //1 cell contains country origin
        userPath[2] = fieldsToCommands.get(getOwnersType()); //2 cell contains owners type
        userPath[3] = fieldsToCommands.get(getCarAge()); //3 cell contains car age
        userPath[4] = fieldsToCommands.get(getTypeOfEngine()); //4 cell contains type of engine
        userPath[5] = fieldsToCommands.get(getVolumeOfEngine()); //5 cell contains engine volume
        return userPath;
    }

    public void setTransportType(final TransportType transportType) {
        final int stepID = 0;
        cleanStepsAfterCurrent(stepID);
        currentQuestion = Step.TRANSPORT_TYPE;
        this.transportType = transportType;
    }

    public void setCountryOrigin(final CountryOrigin countryOrigin) {
        final int stepID = 1;
        cleanStepsAfterCurrent(stepID);
        currentQuestion = Step.COUNTRY_ORIGIN;
        this.countryOrigin = countryOrigin;
    }

    public OwnersType getOwnersType() {
        return ownersType;
    }

    public void setOwnersType(final OwnersType ownersType) {
        final int stepID = 2;
        cleanStepsAfterCurrent(stepID);
        currentQuestion = Step.OWNERS_TYPE;
        this.ownersType = ownersType;
    }

    public CarAge getCarAge() {
        return carAge;
    }

    public void setCarAge(final CarAge carAge) {
        cleanStepsAfterCurrent(4);
        currentQuestion = Step.CAR_AGE;
        this.carAge = carAge;
    }

    public TypeOfEngine getTypeOfEngine() {
        return typeOfEngine;
    }

    public void setTypeOfEngine(final TypeOfEngine typeOfEngine) {
        cleanStepsAfterCurrent(3);
        currentQuestion = Step.TYPE_OF_ENGINE;
        this.typeOfEngine = typeOfEngine;
    }

    public VolumeOfEngine getVolumeOfEngine() {
        return volumeOfEngine;
    }

    public void setVolumeOfEngine(final VolumeOfEngine volumeOfEngine) {
        final int stepID = 4;
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
            case TRANSPORT_TYPE -> {
                if (transportType == TransportType.M1) {
                    return Step.COUNTRY_ORIGIN;
                }
            }
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
            case null, default -> {
                return Step.TRANSPORT_TYPE;
            }
        }
        return Step.TRANSPORT_TYPE;
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
