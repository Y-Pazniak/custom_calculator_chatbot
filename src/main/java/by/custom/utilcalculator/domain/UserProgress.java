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
    private EngineType engineType = null;
    private EngineVolumeOrPower engineVolumeOrPower = null;
    private ParticularTransportType particularTransportType = null;
    private TruckUnitClass truckUnitClass = null;
    private Weight weight = null;
    private TrailerO4Type trailersO4Type = null;
    private final String chatID;
    private final List<Command> userPath;

    public UserProgress(final String chatID) {
        this.chatID = chatID;
        userPath = new ArrayList<>();
    }

    private void addUserStatusToPath(final StepsIndicator stepsIndicator) {
        final Map<StepsIndicator, Command> fieldsToCommands = CommandTree.getInstance().getFieldsToCommands();
        Command command = fieldsToCommands.get(stepsIndicator);
        Class<? extends StepsIndicator> stepsIndicatorClass = stepsIndicator.getClass();
        boolean needAddCommand = true;
        int lastNumber = 0;

        for (int i = 0; i < userPath.size(); i++) {
            Command localCommand = userPath.get(i);
            for (Map.Entry<StepsIndicator, Command> entry : fieldsToCommands.entrySet()) {
                if (entry.getKey().getClass().equals(stepsIndicatorClass) && localCommand.equals(entry.getValue())) {
                    needAddCommand = false;
                    userPath.set(i, command);
                    lastNumber = i;
                    break;
                }
            }
            if (!needAddCommand) {
                break;
            }
        }

        if (needAddCommand) {
            userPath.add(command);
        } else {
            userPath.subList(lastNumber + 1, userPath.size()).clear();
        }

    }

    public Step getNextStep() {
        return CommandTree.getNextStep(this);
    }

    public GeneralTransportType getGeneralTransportType() {
        return generalTransportType;
    }

    public CountryOrigin getCountryOrigin() {
        return countryOrigin;
    }

    public List<Command> getUserPath() {
        return userPath;
    }

    public TrailerO4Type getTrailerO4Type() {
        return trailersO4Type;
    }

    public void setTrailerO4Type(final TrailerO4Type trailersO4Type) {
        this.trailersO4Type = trailersO4Type;
        addUserStatusToPath(trailersO4Type);
    }

    public void setGeneralTransportType(final GeneralTransportType transportType) {
        this.generalTransportType = transportType;
        addUserStatusToPath(transportType);
    }

    public ParticularTransportType getParticularTransportType() {
        return particularTransportType;
    }

    public void setParticularTransportType(final ParticularTransportType particularTransportType) {
        this.particularTransportType = particularTransportType;
        addUserStatusToPath(particularTransportType);
    }

    public void setWeight(final Weight weight) {
        this.weight = weight;
        addUserStatusToPath(weight);
    }

    public Weight getWeight() {
        return weight;
    }

    public void setCountryOrigin(final CountryOrigin countryOrigin) {
        this.countryOrigin = countryOrigin;
        addUserStatusToPath(countryOrigin);
    }

    public OwnersType getOwnersType() {
        return ownersType;
    }

    public void setOwnersType(final OwnersType ownersType) {
        this.ownersType = ownersType;
        addUserStatusToPath(ownersType);
    }

    public CarAge getCarAge() {
        return carAge;
    }

    public void setCarAge(final CarAge carAge) {
        this.carAge = carAge;
        addUserStatusToPath(carAge);
    }

    public void setEngineType(final EngineType typeOfEngine) {
        this.engineType = typeOfEngine;
        addUserStatusToPath(typeOfEngine);
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setVolumeOrPower(final EngineVolumeOrPower volumeOfEngine) {
        this.engineVolumeOrPower = volumeOfEngine;
        addUserStatusToPath(volumeOfEngine);
    }

    public EngineVolumeOrPower getVolumeOrPower() {
        return engineVolumeOrPower;
    }

    public void setTruckUnitType(final TruckUnitClass truckUnit) {
        this.truckUnitClass = truckUnit;
        addUserStatusToPath(truckUnit);
    }

    public TruckUnitClass getTruckUnitClass() {
        return this.truckUnitClass;
    }

    public String getChatID() {
        return chatID;
    }
}
