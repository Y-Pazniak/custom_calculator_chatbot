package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.domain.tree.CommandTree;

import java.io.Serializable;
import java.util.Map;

import static by.custom.utilcalculator.domain.constants.steps.Step.M2_M3_ENGINE_TYPE;

public class UserProgress implements Serializable {
    private GeneralTransportType generalTransportType = null;
    private CountryOrigin countryOrigin = null;
    private OwnersType ownersType = null;
    private CarAge carAge = null;
    private M1TypeOfEngine typeOfEngine = null;
    private M1EngineVolume m1EngineVolume = null;
    private ExceptM1TransportType exceptM1TransportType = null;
    private N1N3TransportWeight transportWeightN1N2N3 = null;
    private M2M3EngineType engineTypeM2M3 = null;
    private M2EngineVolume m2EngineVolume = null;
    private TruckUnitClass truckUnitClass = null;
    private TruckUnitWeight truckUnitWeight = null;
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
        final Command[] userPath = new Command[12];
        final Map<StepsIndicator, Command> fieldsToCommands = CommandTree.getInstance().getFieldsToCommands();
        userPath[0] = fieldsToCommands.get(getGeneralTransportType()); //0 cell contains general type of transport
        userPath[1] = fieldsToCommands.get(getCountryOrigin()); //1 cell contains country origin
        userPath[2] = fieldsToCommands.get(getOwnersType()); //2 cell contains owners type
        userPath[3] = fieldsToCommands.get(getCarAge()); //3 cell contains car age
        userPath[4] = fieldsToCommands.get(getTypeOfM1Engine()); //4 cell contains type of engine
        userPath[5] = fieldsToCommands.get(getM1EngineVolume()); //5 cell contains engine volume
        userPath[6] = fieldsToCommands.get(getExceptM1TransportType()); //6 cell contains transport type for except M1 branch
        userPath[7] = fieldsToCommands.get(getTransportWeightN1N2N3()); //7 cell contains transport weight for "except M1 -> N1-N3 branch"
        userPath[8] = fieldsToCommands.get(getEngineTypeM2M3()); //8 cell contains "except M1 -> M2-M3" type of engine
        userPath[9] = fieldsToCommands.get(getM2EngineVolume()); //9 cell contains "except M1 -> M2-M3 -> gasoline" volume of engine
        userPath[10] = fieldsToCommands.get(getTruckUnitType()); //10 cell contains "except  M1 -> truck units" truck unit class
        userPath[11] = fieldsToCommands.get(getTruckUnitWeight()); //11 cell contains "except  M1 -> truck units -> truck unit weight" truck unit weight
        return userPath;
    }

    public TruckUnitWeight getTruckUnitWeight() {
        return truckUnitWeight;
    }

    public void setTruckUnitWeight(final TruckUnitWeight truckUnitWeight) {
        cleanStepsAfterCurrentM1Branch(4);
        currentQuestion = Step.TRUCK_UNIT_WEIGHT;
        this.truckUnitWeight = truckUnitWeight;
    }

    public void setGeneralTransportType(final GeneralTransportType transportType) {
        cleanStepsAfterCurrentM1Branch(0);
        currentQuestion = Step.GENERAL_TRANSPORT_TYPE;
        this.generalTransportType = transportType;
    }

    public M2M3EngineType getEngineTypeM2M3() {
        return engineTypeM2M3;
    }

    public M2EngineVolume getM2EngineVolume() {
        return m2EngineVolume;
    }

    public void setEngineTypeM2M3(final M2M3EngineType engineTypeM2M3) {
        cleanStepsAfterCurrentExceptM1Branch(1);
        currentQuestion = M2_M3_ENGINE_TYPE;
        this.engineTypeM2M3 = engineTypeM2M3;
    }

    public ExceptM1TransportType getExceptM1TransportType() {
        return exceptM1TransportType;
    }

    public void setExceptM1TransportType(final ExceptM1TransportType exceptM1TransportType) {
        cleanStepsAfterCurrentExceptM1Branch(0);
        currentQuestion = Step.EXCEPT_M1_TRANSPORT_TYPE;
        this.exceptM1TransportType = exceptM1TransportType;
    }

    public void setTransportWeightN1N2N3(final N1N3TransportWeight transportWeightN1N2N3) {
        cleanStepsAfterCurrentExceptM1Branch(1);
        currentQuestion = Step.N1_N3_WEIGHT;
        this.transportWeightN1N2N3 = transportWeightN1N2N3;
    }

    public N1N3TransportWeight getTransportWeightN1N2N3() {
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

    public M1TypeOfEngine getTypeOfM1Engine() {
        return typeOfEngine;
    }

    public void setTypeOfEngineM1(final M1TypeOfEngine typeOfEngine) {
        cleanStepsAfterCurrentM1Branch(3);
        currentQuestion = Step.M1_TYPE_OF_ENGINE;
        this.typeOfEngine = typeOfEngine;
    }

    public M1EngineVolume getM1EngineVolume() {
        return m1EngineVolume;
    }

    public void setM1Volume(final M1EngineVolume volumeOfEngine) {
        cleanStepsAfterCurrentM1Branch(4);
        currentQuestion = Step.M1_VOLUME_OF_ENGINE;
        this.m1EngineVolume = volumeOfEngine;
    }

    public void setM2Volume(final M2EngineVolume volumeOfEngine) {
        cleanStepsAfterCurrentExceptM1Branch(2);
        currentQuestion = Step.M2_M3_ENGINE_VOLUME;
        this.m2EngineVolume = volumeOfEngine;
    }

    public void setTruckUnitType(final TruckUnitClass truckUnit) {
        cleanStepsAfterCurrentExceptM1Branch(3);
        currentQuestion = Step.TRUCK_UNIT_CLASS;
        this.truckUnitClass = truckUnit;
    }

    public TruckUnitClass getTruckUnitType() {
        return this.truckUnitClass;
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
                } else {
                    if (exceptM1TransportType == ExceptM1TransportType.M2_M3) {
                        return Step.M2_M3_ENGINE_TYPE;
                    } else {
                        if (exceptM1TransportType == ExceptM1TransportType.TRUCK_UNITS) {
                            return Step.TRUCK_UNIT_CLASS;
                        }
                    }
                }
            }

            case M2_M3_ENGINE_TYPE -> {
                if (engineTypeM2M3 == M2M3EngineType.ELECTRIC) {
                    return Step.CAR_AGE;
                } else {
                    if (engineTypeM2M3 == M2M3EngineType.GASOLINE) {
                        return Step.M2_M3_ENGINE_VOLUME;
                    }
                }
            }

            case COUNTRY_ORIGIN -> {
                return Step.OWNERS_TYPE;
            }
            case OWNERS_TYPE -> {
                if (ownersType == OwnersType.PHYSICAL || countryOrigin == CountryOrigin.EAES) {
                    return Step.CAR_AGE;
                } else {
                    return Step.M1_TYPE_OF_ENGINE;
                }
            }
            case M1_TYPE_OF_ENGINE -> {
                if (typeOfEngine == M1TypeOfEngine.GASOLINE) {
                    return Step.M1_VOLUME_OF_ENGINE;
                } else {
                    return Step.CAR_AGE;
                }
            }

            case N1_N3_WEIGHT, M1_VOLUME_OF_ENGINE, M2_M3_ENGINE_VOLUME, TRUCK_UNIT_WEIGHT -> {
                return Step.CAR_AGE;
            }

            case TRUCK_UNIT_CLASS -> {
                return Step.TRUCK_UNIT_WEIGHT;
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
            this.m1EngineVolume = null;
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
            this.engineTypeM2M3 = null;
        }
        if (stepCleaner <= 2) {
            this.m2EngineVolume = null;
        }
        if (stepCleaner <= 3) {
            this.truckUnitClass = null;
        }
        if (stepCleaner <= 4) {
            this.truckUnitWeight = null;
        }
    }
}
