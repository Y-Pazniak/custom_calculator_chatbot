package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.domain.tree.CommandTree;

import java.io.Serializable;
import java.util.Map;

public class UserProgress implements Serializable {
    private GeneralTransportType generalTransportType = null;
    private CountryOrigin countryOrigin = null;
    private OwnersType ownersType = null;
    private CarAge carAge = null;
    private TypeOfEngine typeOfEngine = null;
    private VolumeOfEngine volumeOfEngine = null;
    private ExceptM1TransportType exceptM1TransportType = null;
    private TransportWeightN1N2N3 transportWeightN1N2N3 = null;
    private final String chatID;
    private Step currentQuestion;

    public UserProgress(final String chatID) {
        this.chatID = chatID;
    }

    public GeneralTransportType getGeneralTransportType() {
        return generalTransportType;
    }

    public CountryOrigin getCountryOrigin() {
        return countryOrigin;
    }

    public Command[] getUserPath() {
        final Command[] userPath = new Command[8];
        final Map<StepsIndicator, Command> fieldsToCommands = CommandTree.getInstance().getFieldsToCommands();
        userPath[0] = fieldsToCommands.get(getGeneralTransportType()); //0 cell contains general type of transport
        userPath[1] = fieldsToCommands.get(getCountryOrigin()); //1 cell contains country origin
        userPath[2] = fieldsToCommands.get(getOwnersType()); //2 cell contains owners type
        userPath[3] = fieldsToCommands.get(getCarAge()); //3 cell contains car age
        userPath[4] = fieldsToCommands.get(getTypeOfEngine()); //4 cell contains type of engine
        userPath[5] = fieldsToCommands.get(getVolumeOfEngine()); //5 cell contains engine volume
        userPath[6] = fieldsToCommands.get(getExceptM1TransportType()); //6 cell contains transport type for except M1 branch
        userPath[7] = fieldsToCommands.get(getTransportWeightN1N2N3()); //7 cell contains transport weight for "except M1 -> N1-N3 branch"
        return userPath;
    }

    public void setGeneralTransportType(final GeneralTransportType transportType) {
        cleanStepsAfterCurrentM1Branch(0);
        currentQuestion = Step.GENERAL_TRANSPORT_TYPE;
        this.generalTransportType = transportType;
    }

    public ExceptM1TransportType getExceptM1TransportType() {
        return exceptM1TransportType;
    }

    public void setExceptM1TransportType(final ExceptM1TransportType exceptM1TransportType) {
        cleanStepsAfterCurrentExceptM1Branch(0);
        currentQuestion = Step.EXCEPT_M1_TRANSPORT_TYPE;
        this.exceptM1TransportType = exceptM1TransportType;
    }

    public void setTransportWeightN1N2N3(final TransportWeightN1N2N3 transportWeightN1N2N3) {
        cleanStepsAfterCurrentExceptM1Branch(1);
        currentQuestion = Step.N1_N3_WEIGHT;
        this.transportWeightN1N2N3 = transportWeightN1N2N3;
    }

    public TransportWeightN1N2N3 getTransportWeightN1N2N3() {
        return transportWeightN1N2N3;
    }

    public void setCountryOrigin(final CountryOrigin countryOrigin) {
        final int stepID = 1;
        cleanStepsAfterCurrentM1Branch(stepID);
        currentQuestion = Step.COUNTRY_ORIGIN;
        this.countryOrigin = countryOrigin;
    }

    public OwnersType getOwnersType() {
        return ownersType;
    }

    public void setOwnersType(final OwnersType ownersType) {
        cleanStepsAfterCurrentM1Branch(2);
        currentQuestion = Step.OWNERS_TYPE;
        this.ownersType = ownersType;
    }

    public CarAge getCarAge() {
        return carAge;
    }

    public void setCarAge(final CarAge carAge) {
        cleanStepsAfterCurrentM1Branch(4);
        currentQuestion = Step.CAR_AGE;
        this.carAge = carAge;
    }

    public TypeOfEngine getTypeOfEngine() {
        return typeOfEngine;
    }

    public void setTypeOfEngine(final TypeOfEngine typeOfEngine) {
        cleanStepsAfterCurrentM1Branch(3);
        currentQuestion = Step.TYPE_OF_ENGINE;
        this.typeOfEngine = typeOfEngine;
    }

    public VolumeOfEngine getVolumeOfEngine() {
        return volumeOfEngine;
    }

    public void setVolumeOfEngine(final VolumeOfEngine volumeOfEngine) {
        cleanStepsAfterCurrentM1Branch(4);
        currentQuestion = Step.VOLUME_OF_ENGINE;
        this.volumeOfEngine = volumeOfEngine;
    }

    public String getChatID() {
        return chatID;
    }

    //method checks for last answered question and sends next step, which depends on current user's answers
    public Step getNextStep() {
        switch (currentQuestion) {
            case GENERAL_TRANSPORT_TYPE -> {
                if (generalTransportType == GeneralTransportType.M1) {
                    return Step.COUNTRY_ORIGIN;
                } else {
                    if (generalTransportType == GeneralTransportType.EXCEPT_M1) {
                        return Step.EXCEPT_M1_TRANSPORT_TYPE;
                    }
                }
            }

            case EXCEPT_M1_TRANSPORT_TYPE -> {
                if (exceptM1TransportType == ExceptM1TransportType.N1_N3) {
                    return Step.N1_N3_WEIGHT;
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
                return Step.GENERAL_TRANSPORT_TYPE;
            }
        }
        return Step.GENERAL_TRANSPORT_TYPE;
    }

    private void cleanStepsAfterCurrentM1Branch(final int stepCleaner) {
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

    private void cleanStepsAfterCurrentExceptM1Branch(final int stepCleaner) {
        if (stepCleaner <= 0) {
            this.exceptM1TransportType = null;
        }
        if (stepCleaner <= 1) {
            this.transportWeightN1N2N3 = null;
        }
    }
}
