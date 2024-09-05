package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.domain.tree.CommandTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserProgress implements Serializable {
    private GeneralTransportType generalTransportType = null;
    private CountryOrigin countryOrigin = null;
    private OwnersType ownersType = null;
    private CarAge carAge = null;
    private M1TypeOfEngine typeOfEngine = null;
    private M1EngineVolume m1EngineVolume = null;
    private BusesAndTrucksTransportType exceptM1TransportType = null;
    private N1N3TransportWeight transportWeightN1N2N3 = null;
    private M2M3EngineType engineTypeM2M3 = null;
    private M2EngineVolume m2EngineVolume = null;
    private TruckUnitClass truckUnitClass = null;
    private TruckUnitWeight truckUnitWeight = null;
    private TrailerO4Type trailersO4Type = null;
    private SelfPropelledType selfPropelledType = null;
    private SelfPropelledPower selfPropelledPower = null;
    private final String chatID;
    private Step nextStep;
    private final List<Command> userPath;

    public UserProgress(final String chatID) {
        this.chatID = chatID;
        userPath = new ArrayList<>();
    }

    private void addUserStatusToPath(final StepsIndicator stepsIndicator) {
        final Map<StepsIndicator, Command> fieldsToCommands = CommandTree.getInstance().getFieldsToCommands();
        Command command = fieldsToCommands.get(stepsIndicator);
        Class stepsIndicatorClass = stepsIndicator.getClass();
        boolean needAddCommand = true;

        for (int i = 0; i < userPath.size(); i++) {
            Command localCommand = userPath.get(i);
            for (Map.Entry<StepsIndicator, Command> entry : fieldsToCommands.entrySet()) {
                if (entry.getValue().equals(localCommand)) {
                    if (entry.getKey().getClass().equals(stepsIndicatorClass)) {
                        needAddCommand = false;
                        userPath.set(i, command);
                        break;
                    }
                }
            }
        }

        if (needAddCommand) {
            userPath.add(command);
        }
    }

    public void setNextStep(final Step nextStep) {
        this.nextStep = nextStep;
    }

    public Step getNextStep() {
        return nextStep;
    }

    public GeneralTransportType getGeneralTransportType() {
        return generalTransportType;
    }

    public CountryOrigin getCountryOrigin() {
        return countryOrigin;
    }

    public List<Command> getUserPath() {
//        final Command[] userPath = new Command[15];
//        userPath[0] = fieldsToCommands.get(getGeneralTransportType()); //0 cell contains general type of transport
//        userPath[1] = fieldsToCommands.get(getCountryOrigin()); //1 cell contains country origin
//        userPath[2] = fieldsToCommands.get(getOwnersType()); //2 cell contains owners type
//        userPath[3] = fieldsToCommands.get(getCarAge()); //3 cell contains car age
//        userPath[4] = fieldsToCommands.get(getTypeOfM1Engine()); //4 cell contains type of engine
//        userPath[5] = fieldsToCommands.get(getM1EngineVolume()); //5 cell contains engine volume
//        userPath[6] = fieldsToCommands.get(getExceptM1TransportType()); //6 cell contains transport type for except M1 branch
//        userPath[7] = fieldsToCommands.get(getTransportWeightN1N2N3()); //7 cell contains transport weight for "except M1 -> N1-N3 branch"
//        userPath[8] = fieldsToCommands.get(getEngineTypeM2M3()); //8 cell contains "except M1 -> M2-M3" type of engine
//        userPath[9] = fieldsToCommands.get(getM2EngineVolume()); //9 cell contains "except M1 -> M2-M3 -> gasoline" volume of engine
//        userPath[10] = fieldsToCommands.get(getTruckUnitType()); //10 cell contains "except  M1 -> truck units" truck unit class
//        userPath[11] = fieldsToCommands.get(getTruckUnitWeight()); //11 cell contains "except  M1 -> truck units -> truck unit weight" truck unit weight
//        userPath[12] = fieldsToCommands.get(getTrailerO4Type()); //12 cell contains "except  M1 -> trailers O4 -> trailer type" type of trailer
//        userPath[13] = fieldsToCommands.get(getSelfPropelledType()); //13 cell contains "self-propelled vehicles -> type of vehicle"
//        userPath[14] = fieldsToCommands.get(getSelfPropelledPower()); //14 cell contains "self-propelled vehicles -> type of vehicle -> power of vehicle"
        return userPath;
    }

    public TruckUnitWeight getTruckUnitWeight() {
        return truckUnitWeight;
    }

    public TrailerO4Type getTrailerO4Type() {
        return trailersO4Type;
    }

    public void setTrailerO4Type(final TrailerO4Type trailersO4Type) {
        cleanStepsAfterCurrentM1Branch(3);
        this.trailersO4Type = trailersO4Type;
        addUserStatusToPath(trailersO4Type);
    }

    public void setTruckUnitWeight(final TruckUnitWeight truckUnitWeight) {
        cleanStepsAfterCurrentM1Branch(4);
        this.truckUnitWeight = truckUnitWeight;
        addUserStatusToPath(truckUnitWeight);
    }

    public void setGeneralTransportType(final GeneralTransportType transportType) {
        cleanStepsAfterCurrentM1Branch(0);
        this.generalTransportType = transportType;
        addUserStatusToPath(transportType);
    }

    public M2M3EngineType getEngineTypeM2M3() {
        return engineTypeM2M3;
    }

    public M2EngineVolume getM2EngineVolume() {
        return m2EngineVolume;
    }

    public void setEngineTypeM2M3(final M2M3EngineType engineTypeM2M3) {
        cleanStepsAfterCurrentExceptM1Branch(1);
        this.engineTypeM2M3 = engineTypeM2M3;
        addUserStatusToPath(engineTypeM2M3);
    }

    public BusesAndTrucksTransportType getExceptM1TransportType() {
        return exceptM1TransportType;
    }

    public void setExceptM1TransportType(final BusesAndTrucksTransportType exceptM1TransportType) {
        cleanStepsAfterCurrentExceptM1Branch(0);
        this.exceptM1TransportType = exceptM1TransportType;
        addUserStatusToPath(exceptM1TransportType);
    }

    public void setTransportWeightN1N2N3(final N1N3TransportWeight transportWeightN1N2N3) {
        cleanStepsAfterCurrentExceptM1Branch(1);
        this.transportWeightN1N2N3 = transportWeightN1N2N3;
        addUserStatusToPath(transportWeightN1N2N3);
    }

    public N1N3TransportWeight getTransportWeightN1N2N3() {
        return transportWeightN1N2N3;
    }

    public void setCountryOrigin(final CountryOrigin countryOrigin) {
        final int stepID = 1;
        cleanStepsAfterCurrentM1Branch(stepID);
        this.countryOrigin = countryOrigin;
        addUserStatusToPath(countryOrigin);
    }

    public OwnersType getOwnersType() {
        return ownersType;
    }

    public void setOwnersType(final OwnersType ownersType) {
        cleanStepsAfterCurrentM1Branch(2);
        this.ownersType = ownersType;
        addUserStatusToPath(ownersType);
    }

    public CarAge getCarAge() {
        return carAge;
    }

    public void setCarAge(final CarAge carAge) {
        cleanStepsAfterCurrentM1Branch(4);
        cleanStepsAfterCurrentExceptM1Branch(5);
        cleanStepsAfterCurrentSelfPropelledVehicles(2);
        this.carAge = carAge;
        addUserStatusToPath(carAge);
    }

    public M1TypeOfEngine getTypeOfM1Engine() {
        return typeOfEngine;
    }

    public void setTypeOfEngineM1(final M1TypeOfEngine typeOfEngine) {
        cleanStepsAfterCurrentM1Branch(3);
        this.typeOfEngine = typeOfEngine;
        addUserStatusToPath(typeOfEngine);
    }

    public M1EngineVolume getM1EngineVolume() {
        return m1EngineVolume;
    }

    public void setM1Volume(final M1EngineVolume volumeOfEngine) {
        cleanStepsAfterCurrentM1Branch(4);
        this.m1EngineVolume = volumeOfEngine;
        addUserStatusToPath(volumeOfEngine);
    }

    public void setM2Volume(final M2EngineVolume volumeOfEngine) {
        cleanStepsAfterCurrentExceptM1Branch(2);
        this.m2EngineVolume = volumeOfEngine;
        addUserStatusToPath(volumeOfEngine);
    }

    public void setTruckUnitType(final TruckUnitClass truckUnit) {
        cleanStepsAfterCurrentExceptM1Branch(3);
        this.truckUnitClass = truckUnit;
        addUserStatusToPath(truckUnit);
    }

    public void setSelfPropelledType(final SelfPropelledType selfPropelledType) {
        cleanStepsAfterCurrentSelfPropelledVehicles(0);
        this.selfPropelledType = selfPropelledType;
        addUserStatusToPath(selfPropelledType);
    }

    public SelfPropelledType getSelfPropelledType() {
        return selfPropelledType;
    }

    public SelfPropelledPower getSelfPropelledPower() {
        return selfPropelledPower;
    }

    public void setSelfPropelledPower(final SelfPropelledPower selfPropelledPower) {
        cleanStepsAfterCurrentSelfPropelledVehicles(1);
        this.selfPropelledPower = selfPropelledPower;
        addUserStatusToPath(selfPropelledPower);
    }

    public TruckUnitClass getTruckUnitType() {
        return this.truckUnitClass;
    }

    public String getChatID() {
        return chatID;
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
        if (stepCleaner <= 5) {
            this.carAge = null;
        }
    }

    private void cleanStepsAfterCurrentSelfPropelledVehicles(final int stepCleaner) {
        if (stepCleaner <= 0) {
            this.selfPropelledType = null;
        }
        if (stepCleaner <= 1) {
            this.selfPropelledPower = null;
        }
        if (stepCleaner <= 2) {
            this.carAge = null;
        }
    }
}
