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
            case SELF_PROPELLED_VEHICLES ->
                    userProgress.setGeneralTransportType(GeneralTransportType.SELF_PROPELLED_VEHICLES);
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
            case COMBINE_HARVESTERS ->
                    userProgress.setParticularTransportType(ParticularTransportType.COMBINE_HARVESTERS);
            case FORAGE_HARVESTERS ->
                    userProgress.setParticularTransportType(ParticularTransportType.FORAGE_HARVESTERS);
            case AGRICULTURAL_VEHICLES ->
                    userProgress.setParticularTransportType(ParticularTransportType.AGRICULTURAL_VEHICLES);
            case OFF_ROAD_DUMP_TRUCKS ->
                    userProgress.setParticularTransportType(ParticularTransportType.OFF_ROAD_DUMP_TRUCKS);
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
            case LESS_2P5_TONS -> userProgress.setWeight(Weight.LESS_2P5_TONS);
            case BETWEEN_2_5_AND_3_5_TONS -> userProgress.setWeight(Weight.BETWEEN_2_5_AND_3_5);
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

    public String processEngineVolumeOrPower(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);
        final String message;

        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        processEngineVolumeOrPower(userProgress, requestingCommand, chatID);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    private void processEngineVolumeOrPower(final UserProgress userProgress, final Command requestingCommand, final String chatID) throws UtilsborException {
        if (!CommandTree.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        switch (requestingCommand) {
            case Command.VOLUME_LESS_1000_CM -> userProgress.setVolumeOrPower(EngineVolumeOrPower.LESS_1000);
            case Command.VOLUME_BETWEEN_1000_2000_CM -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_1000_AND_2000);
            case Command.VOLUME_BETWEEN_2000_3000_CM -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_2000_AND_3000);
            case Command.VOLUME_BETWEEN_3000_3500_CM -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_3000_AND_3500);
            case Command.VOLUME_MORE_3500_CM -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_3500);
            case Command.VOLUME_LESS_2500_CM -> userProgress.setVolumeOrPower(EngineVolumeOrPower.LESS_2500);
            case Command.VOLUME_BETWEEN_2500_5000_CM -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_2500_AND_5000);
            case Command.VOLUME_BETWEEN_5000_10000_CM -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_5000_AND_10000);
            case Command.VOLUME_MORE_10000_CM -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_10000);

            case Command.POWER_LESS_100 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.LESS_100);
            case Command.POWER_100_140 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_100_140);
            case Command.POWER_140_200 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_140_200);
            case Command.POWER_MORE_200 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_200);

            case Command.POWER_100_200 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_100_200);
            case Command.POWER_200_300 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_200_300);
            case Command.POWER_300_400 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_300_400);
            case Command.POWER_MORE_400 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_400);

            case Command.POWER_LESS_170 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.LESS_170);
            case Command.POWER_170_250 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_170_250);
            case Command.POWER_MORE_250 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_250);

            case Command.POWER_100_125 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_100_125);
            case Command.POWER_125_150 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_125_150);
            case Command.POWER_MORE_150 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_150);

            case Command.POWER_LESS_40 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.LESS_40);
            case Command.POWER_40_80 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_40_80);
            case Command.POWER_MORE_80 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_80);

            case Command.POWER_5_50 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_5_50);
            case Command.POWER_50_100 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_50_100);
            case Command.POWER_200_250 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_200_250);
            case Command.POWER_250_300 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_250_300);

            case Command.POWER_LESS_130 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.LESS_130);
            case Command.POWER_130_200 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_130_200);
            case Command.POWER_MORE_300 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_300);

            case TRAILERS_OTHER_FULL -> userProgress.setVolumeOrPower(EngineVolumeOrPower.TRAILERS_OTHER_FULL);
            case TRAILERS_OTHER_HALF -> userProgress.setVolumeOrPower(EngineVolumeOrPower.TRAILERS_OTHER_HALF);

            case POWER_BETWEEN_100_220 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_100_220);
            case POWER_MORE_220 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_220);

            case POWER_BETWEEN_20_100 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_20_100);
            case POWER_BETWEEN_100_300 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_100_300);

            case POWER_BETWEEN_5p5_30 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_5p5_30);
            case POWER_BETWEEN_30_60 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_30_60);
            case POWER_BETWEEN_60_90 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_60_90);
            case POWER_BETWEEN_90_130 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_90_130);
            case POWER_BETWEEN_130_180 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_130_180);
            case POWER_BETWEEN_180_220 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_180_220);
            case POWER_BETWEEN_220_280 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_220_280);
            case POWER_BETWEEN_280_340 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_280_340);
            case POWER_BETWEEN_340_380 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_340_380);
            case POWER_MORE_380 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_380);

            case POWER_BETWEEN_25_160 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_25_160);
            case POWER_BETWEEN_160_220 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_160_220);
            case POWER_BETWEEN_220_255 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_220_255);
            case POWER_BETWEEN_255_325 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_255_325);
            case POWER_BETWEEN_325_400 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_325_400);

            case POWER_LESS_295 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.LESS_295);
            case POWER_BETWEEN_295_401 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_295_401);
            case POWER_MORE_401 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_401);

            case POWER_BETWEEN_100_120 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_100_120);
            case POWER_BETWEEN_120_300 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_120_300);
            case SELF_PROPELLED_MOWERS -> userProgress.setVolumeOrPower(EngineVolumeOrPower.SELF_PROPELLED_MOWERS);

            case POWER_LESS_200 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.LESS_200);
            case POWER_BETWEEN_200_650 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_200_650);
            case POWER_BETWEEN_650_1750 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.BETWEEN_650_1750);
            case POWER_MORE_1750 -> userProgress.setVolumeOrPower(EngineVolumeOrPower.MORE_1750);
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

    public String processHelpRequest(final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);
        if (userProgress.getParticularTransportType() == ParticularTransportType.TRAILERS_OTHER) {
            return messagesCreator.getTrailersOtherHelp();
        }
        if (userProgress.getNextStep() == Step.ENGINE_VOLUME) {
            return messagesCreator.getSelfPropelledPowerHelp();
        }
        if (userProgress.getGeneralTransportType() == GeneralTransportType.SELF_PROPELLED_VEHICLES) {
            return messagesCreator.getSelfPropelledHelp();
        }
        return "help request denied";
    }

    private static class BotFieldsManagerHolder {
        private static final UserProgressManager USER_PROGRESS_MANAGER = new UserProgressManager();
    }
}