package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.FileUserProgressStorage;
import by.custom.utilcalculator.domain.IUserProgressStorage;
import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.domain.tree.CommandTree;
import by.custom.utilcalculator.exception.*;

import java.util.Objects;

public class UserProgressManager {
    private final MessagesCreator messagesCreator;
    private final IUserProgressStorage userProgressStorage;

    private UserProgressManager() {
        messagesCreator = MessagesCreator.getInstance();
        userProgressStorage = FileUserProgressStorage.getInstance();
    }

    public static UserProgressManager getInstance() {
        return BotFieldsManagerHolder.USER_PROGRESS_MANAGER;
    }

    public void createNewUserProgress(final String chatID) throws UtilsborException {
        final UserProgress userProgress = new UserProgress(chatID);
        userProgressStorage.save(userProgress);
    }

    public String processGeneralTransportType(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        switch (requestingCommand) {
            case M1 -> userProgress.setGeneralTransportType(GeneralTransportType.M1);
            case BUSES_AND_TRUCKS -> userProgress.setGeneralTransportType(GeneralTransportType.BUSES_AND_TRUCKS);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processParticularTransportType(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        switch (requestingCommand) {
            //buses and trucks
            case N1_N3 -> userProgress.setParticularTransportType(ParticularTransportType.N1_N3);
            case M2_M3 -> userProgress.setParticularTransportType(ParticularTransportType.M2_M3);
            case TRUCK_UNITS -> userProgress.setParticularTransportType(ParticularTransportType.TRUCK_UNITS);
            case TRAILERS_O4 -> userProgress.setParticularTransportType(ParticularTransportType.TRAILERS_O4);
            //self-propelled vehicles
            case GRADERS -> userProgress.setParticularTransportType(ParticularTransportType.GRADER);
            case BULLDOZERS -> userProgress.setParticularTransportType(ParticularTransportType.BULLDOZER);
            case EXCAVATORS -> userProgress.setParticularTransportType(ParticularTransportType.EXCAVATOR);
            case WHEEL_LOADERS -> userProgress.setParticularTransportType(ParticularTransportType.WHEEL_LOADER);
            case TAMPING_MACHINES -> userProgress.setParticularTransportType(ParticularTransportType.TAMPING_MACHINE);
            case FRONT_LOADERS -> userProgress.setParticularTransportType(ParticularTransportType.FRONT_LOADER);
            case WHEELED_CRANES -> userProgress.setParticularTransportType(ParticularTransportType.WHEELED_CRANES);
            case PIPELAYERS -> userProgress.setParticularTransportType(ParticularTransportType.PIPELAYERS);
            case TRAILERS_OTHER -> userProgress.setParticularTransportType(ParticularTransportType.TRAILERS_OTHER);
            case ROAD_MAINTENANCE -> userProgress.setParticularTransportType(ParticularTransportType.ROAD_MAINTENANCE);
            case FORESTRY -> userProgress.setParticularTransportType(ParticularTransportType.FORESTRY);
            case FORWADERS -> userProgress.setParticularTransportType(ParticularTransportType.FORWADERS);
            case TIMBER_LOADERS -> userProgress.setParticularTransportType(ParticularTransportType.TIMBER_LOADERS);
            case WHEELED_TRACTORS -> userProgress.setParticularTransportType(ParticularTransportType.WHEELED_TRACTORS);
            case CRAWLER_TRACTORS -> userProgress.setParticularTransportType(ParticularTransportType.CRAWLER_TRACTORS);
            case COMBINE_HARVESTERS -> userProgress.setParticularTransportType(ParticularTransportType.COMBINE_HARVESTERS);
            case FORAGE_HARVESTERS -> userProgress.setParticularTransportType(ParticularTransportType.FORAGE_HARVESTERS);
            case AGRICULTURAL_VEHICLES -> userProgress.setParticularTransportType(ParticularTransportType.AGRICULTURAL_VEHICLES);
            case OFF_ROAD_DUMP_TRUCKS -> userProgress.setParticularTransportType(ParticularTransportType.OFF_ROAD_DUMP_TRUCKS);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processN1_N3TransportWeight(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);

        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        switch (requestingCommand) {
            case LESS_2_TONS -> userProgress.setWeight(Weight.LESS_2_TONS);
            case BETWEEN_2_5_AND_3_5_TONS ->
                    userProgress.setWeight(Weight.BETWEEN_2_5_AND_3_5);
            case BETWEEN_3_5_AND_5_TONS -> userProgress.setWeight(Weight.BETWEEN_3_5_AND_5);
            case BETWEEN_5_AND_8_TONS -> userProgress.setWeight(Weight.BETWEEN_5_AND_8);
            case BETWEEN_8_AND_12_TONS -> userProgress.setWeight(Weight.BETWEEN_8_AND_12);
            case BETWEEN_12_AND_20_TONS -> userProgress.setWeight(Weight.BETWEEN_12_AND_20);
            case BETWEEN_20_AND_50_TONS -> userProgress.setWeight(Weight.BETWEEN_20_AND_50);
        }
        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processCarOrigin(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);
        final String message;

        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        if (Objects.equals(requestingCommand, Command.EAES)) {
            userProgress.setCountryOrigin(CountryOrigin.EAES);
        } else if (Objects.equals(requestingCommand, Command.OTHER_COUNTRIES)) {
            userProgress.setCountryOrigin(CountryOrigin.OTHER);
        }


        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    public String processOwnerType(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        final String message;

        switch (requestingCommand) {
            case Command.PHYSICAL -> userProgress.setOwnersType(OwnersType.PHYSICAL);
            case Command.JURIDICAL -> userProgress.setOwnersType(OwnersType.JURIDICAL);
        }

        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);

        return message;
    }

    public String processCarAge(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        final String message;

        if (Objects.equals(requestingCommand, Command.LESS_3_YEARS_AGE)) {
            userProgress.setCarAge(CarAge.LESS_OR_3_YEARS);
        } else {
            if (Objects.equals(requestingCommand, Command.MORE_THAN_3_YEARS_AGE)) {
                userProgress.setCarAge(CarAge.MORE_3_YEARS);
            }
        }

        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    public String processEngineType(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        if (Objects.equals(requestingCommand, Command.GASOLINE)) {
            userProgress.setEngineType(EngineType.GASOLINE);
        } else {
            userProgress.setEngineType(EngineType.ELECTRIC);
        }
        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processEngineVolume(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);
        final String message;

        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        processEngineVolume(userProgress, requestingCommand, chatID);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    private void processEngineVolume(final UserProgress userProgress, final Command requestingCommand, final String chatID) throws UtilsborException {
        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        switch (requestingCommand) {
            case Command.VOLUME_LESS_1000_CM -> userProgress.setVolume(EngineVolume.LESS_1000);
            case Command.VOLUME_BETWEEN_1000_2000_CM -> userProgress.setVolume(EngineVolume.BETWEEN_1000_AND_2000);
            case Command.VOLUME_BETWEEN_2000_3000_CM -> userProgress.setVolume(EngineVolume.BETWEEN_2000_AND_3000);
            case Command.VOLUME_BETWEEN_3000_3500_CM -> userProgress.setVolume(EngineVolume.BETWEEN_3000_AND_3500);
            case VOLUME_MORE_3500_CM -> userProgress.setVolume(EngineVolume.MORE_3500);
            case Command.VOLUME_LESS_2500_CM -> userProgress.setVolume(EngineVolume.LESS_2500);
            case Command.VOLUME_BETWEEN_2500_5000_CM -> userProgress.setVolume(EngineVolume.BETWEEN_2500_AND_5000);
            case Command.VOLUME_BETWEEN_5000_10000_CM -> userProgress.setVolume(EngineVolume.BETWEEN_5000_AND_10000);
            case Command.VOLUME_MORE_10000_CM -> userProgress.setVolume(EngineVolume.MORE_10000);
        }
        userProgressStorage.save(userProgress);
    }

    public String processTruckUnitClass(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);

        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        switch (requestingCommand) {
            case TRUCK_UNITS_6_CLASS -> userProgress.setTruckUnitType(TruckUnitClass.TRUCK_UNITS_6_CLASS);
            case TRUCK_UNITS_OTHER -> userProgress.setTruckUnitType(TruckUnitClass.TRUCK_UNITS_EXCEPT_6_CLASS);
        }
        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processTruckUnitWeight(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);
        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        switch (requestingCommand) {
            case TRUCK_UNITS_12_20_TONS -> userProgress.setWeight(Weight.FROM_12_TILL_20_TONS);
            case TRUCK_UNITS_20_50_TONS -> userProgress.setWeight(Weight.FROM_20_TILL_50_TONS);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processTrailersO4Type(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);
        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        switch (requestingCommand) {
            case TRAILERS_04_TYPE -> userProgress.setTrailerO4Type(TrailerO4Type.TRAILERS);
            case HALF_TRAILERS_04_TYPE -> userProgress.setTrailerO4Type(TrailerO4Type.HALF_TRAILERS);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    private static class BotFieldsManagerHolder {
        private static final UserProgressManager USER_PROGRESS_MANAGER = new UserProgressManager();
    }
}
