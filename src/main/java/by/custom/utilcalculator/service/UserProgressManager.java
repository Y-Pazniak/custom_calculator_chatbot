package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.FileUserProgressStorage;
import by.custom.utilcalculator.domain.IUserProgressStorage;
import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.UserProgressValidator;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
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
//        IUserProgressStorage localUserProgressStorage = PostgresUserProgressStorage.getInstance();
//        UserProgress userProgress = new UserProgress(chatID);
//        localUserProgressStorage.save(userProgress);
//        userProgressStorage.save(userProgress);
    }

    public String processGeneralTransportType(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        userProgress.setNextStep(requestingCommand.getNextStep());

        switch (requestingCommand) {
            case M1 -> userProgress.setGeneralTransportType(GeneralTransportType.M1);
            case BUSES_AND_TRUCKS -> userProgress.setGeneralTransportType(GeneralTransportType.BUSES_AND_TRUCKS);
            case SELF_PROPELLED_VEHICLES ->
                    userProgress.setGeneralTransportType(GeneralTransportType.SELF_PROPELLED_VEHICLES);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processN1_N3TransportType(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
        switch (requestingCommand) {
            case N1_N3 -> userProgress.setExceptM1TransportType(BusesAndTrucksTransportType.N1_N3);
            case M2_M3 -> userProgress.setExceptM1TransportType(BusesAndTrucksTransportType.M2_M3);
            case TRUCK_UNITS -> userProgress.setExceptM1TransportType(BusesAndTrucksTransportType.TRUCK_UNITS);
            case TRAILERS_O4 -> userProgress.setExceptM1TransportType(BusesAndTrucksTransportType.TRAILERS_O4);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processN1_N3TransportWeight(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());

        switch (requestingCommand) {
            case LESS_2_TONS -> userProgress.setTransportWeightN1N2N3(N1N3TransportWeight.LESS_2_TONS);
            case BETWEEN_2_5_AND_3_5_TONS ->
                    userProgress.setTransportWeightN1N2N3(N1N3TransportWeight.BETWEEN_2_5_AND_3_5);
            case BETWEEN_3_5_AND_5_TONS -> userProgress.setTransportWeightN1N2N3(N1N3TransportWeight.BETWEEN_3_5_AND_5);
            case BETWEEN_5_AND_8_TONS -> userProgress.setTransportWeightN1N2N3(N1N3TransportWeight.BETWEEN_5_AND_8);
            case BETWEEN_8_AND_12_TONS -> userProgress.setTransportWeightN1N2N3(N1N3TransportWeight.BETWEEN_8_AND_12);
            case BETWEEN_12_AND_20_TONS -> userProgress.setTransportWeightN1N2N3(N1N3TransportWeight.BETWEEN_12_AND_20);
            case BETWEEN_20_AND_50_TONS -> userProgress.setTransportWeightN1N2N3(N1N3TransportWeight.BETWEEN_20_AND_50);
        }
        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processCarOrigin(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);
        final String message;

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
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

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
        final String message;

        switch (requestingCommand) {
            case Command.PHYSICAL_PERSON -> userProgress.setOwnersType(OwnersType.PHYSICAL);
            case Command.JURIDICAL_PERSON_EAES -> userProgress.setOwnersType(OwnersType.JURIDICAL_EAES);
            case Command.JURIDICAL_PERSON_OTHER -> userProgress.setOwnersType(OwnersType.JURIDICAL_OTHER);
        }

        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);

        return message;
    }

    public String processCarAge(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
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

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        userProgress.setNextStep(requestingCommand.getNextStep());

        if (Objects.equals(requestingCommand, Command.GASOLINE_TYPE_ENGINE_M1) || Objects.equals(requestingCommand, Command.GASOLINE_TYPE_ENGINE_BUSES)) {
            switch (userProgress.getGeneralTransportType()) {
                case M1 -> userProgress.setTypeOfEngineM1(M1TypeOfEngine.GASOLINE);
                case BUSES_AND_TRUCKS -> userProgress.setEngineTypeM2M3(M2M3EngineType.GASOLINE);
            }
        } else {
            if (Objects.equals(requestingCommand, Command.ELECTRIC_TYPE_ENGINE_M1) || Objects.equals(requestingCommand, Command.ELECTRIC_TYPE_ENGINE_BUSES)) {
                switch (userProgress.getGeneralTransportType()) {
                    case M1 -> userProgress.setTypeOfEngineM1(M1TypeOfEngine.ELECTRIC);
                    case BUSES_AND_TRUCKS -> userProgress.setEngineTypeM2M3(M2M3EngineType.ELECTRIC);
                }
            }
        }
        final String message;
        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    public String processEngineVolumeOrPower(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);
        final String message;

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
        switch (userProgress.getGeneralTransportType()) {
            case M1 -> processM1EngineVolume(userProgress, requestingCommand, chatID);
            case BUSES_AND_TRUCKS -> processExceptM1EngineVolume(userProgress, requestingCommand, chatID);
            case SELF_PROPELLED_VEHICLES -> processSelfPropelledPower(userProgress, requestingCommand, chatID);
        }
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    private void processSelfPropelledPower(final UserProgress userProgress, final Command requestingCommand, final String chatID) throws UtilsborException {
        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
        switch (requestingCommand) {
            case Command.POWER_LESS_100 -> userProgress.setSelfPropelledPower(SelfPropelledPower.LESS_100);
            case Command.POWER_100_140 -> userProgress.setSelfPropelledPower(SelfPropelledPower.BETWEEN_100_140);
            case Command.POWER_140_200 -> userProgress.setSelfPropelledPower(SelfPropelledPower.BETWEEN_140_200);
            case Command.POWER_MORE_200 -> userProgress.setSelfPropelledPower(SelfPropelledPower.MORE_200);

            case Command.POWER_100_200 -> userProgress.setSelfPropelledPower(SelfPropelledPower.BETWEEN_100_200);
            case Command.POWER_200_300 -> userProgress.setSelfPropelledPower(SelfPropelledPower.BETWEEN_200_300);
            case Command.POWER_300_400 -> userProgress.setSelfPropelledPower(SelfPropelledPower.BETWEEN_300_400);
            case Command.POWER_MORE_400 -> userProgress.setSelfPropelledPower(SelfPropelledPower.MORE_400);
        }
        userProgressStorage.save(userProgress);
    }

    private void processExceptM1EngineVolume(final UserProgress userProgress, final Command requestingCommand, final String chatID) throws UtilsborException {
        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
        switch (requestingCommand) {
            case Command.M2_VOLUME_LESS_2500_CM -> userProgress.setM2Volume(M2EngineVolume.LESS_2500);
            case Command.M2_VOLUME_BETWEEN_2500_5000_CM ->
                    userProgress.setM2Volume(M2EngineVolume.BETWEEN_2500_AND_5000);
            case Command.M2_VOLUME_BETWEEN_5000_10000_CM ->
                    userProgress.setM2Volume(M2EngineVolume.BETWEEN_5000_AND_10000);
            case Command.M2_VOLUME_MORE_10000_CM -> userProgress.setM2Volume(M2EngineVolume.MORE_10000);
        }
        userProgressStorage.save(userProgress);
    }

    private void processM1EngineVolume(final UserProgress userProgress, final Command requestingCommand, final String chatID) throws UtilsborException {
        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
        switch (requestingCommand) {
            case Command.M1_VOLUME_LESS_1000_CM -> userProgress.setM1Volume(M1EngineVolume.LESS_1000);
            case Command.M1_VOLUME_BETWEEN_1000_2000_CM ->
                    userProgress.setM1Volume(M1EngineVolume.BETWEEN_1000_AND_2000);
            case Command.M1_VOLUME_BETWEEN_2000_3000_CM ->
                    userProgress.setM1Volume(M1EngineVolume.BETWEEN_2000_AND_3000);
            case Command.M1_VOLUME_BETWEEN_3000_3500_CM ->
                    userProgress.setM1Volume(M1EngineVolume.BETWEEN_3000_AND_3500);
            case M1_VOLUME_MORE_3500_CM -> userProgress.setM1Volume(M1EngineVolume.MORE_3500);
        }
        userProgressStorage.save(userProgress);
    }

    public String processTruckUnitClass(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
        switch (requestingCommand) {
            case TRUCK_UNITS_6_CLASS -> userProgress.setTruckUnitType(TruckUnitClass.TRUCK_UNITS_6_CLASS);
            case TRUCK_UNITS_OTHER -> userProgress.setTruckUnitType(TruckUnitClass.TRUCK_UNITS_EXCEPT_6_CLASS);
        }
        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processTruckUnitWeight(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);
        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
        switch (requestingCommand) {
            case TRUCK_UNITS_12_20_TONS -> userProgress.setTruckUnitWeight(TruckUnitWeight.FROM_12_TILL_20_TONS);
            case TRUCK_UNITS_20_50_TONS -> userProgress.setTruckUnitWeight(TruckUnitWeight.FROM_20_TILL_50_TONS);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processTrailersO4Type(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);
        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
        switch (requestingCommand) {
            case TRAILERS_04_TYPE -> userProgress.setTrailerO4Type(TrailerO4Type.TRAILERS);
            case HALF_TRAILERS_04_TYPE -> userProgress.setTrailerO4Type(TrailerO4Type.HALF_TRAILERS);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processSelfPropelledType(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);
        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        userProgress.setNextStep(requestingCommand.getNextStep());
        switch (requestingCommand) {
            case GRADERS -> userProgress.setSelfPropelledType(SelfPropelledType.GRADER);
            case BULLDOZERS -> userProgress.setSelfPropelledType(SelfPropelledType.BULLDOZER);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processHelpRequest(final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);
        if (userProgress.getNextStep() == Step.SELF_PROPELLED_POWER) {
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
