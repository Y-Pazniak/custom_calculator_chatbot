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

        switch (requestingCommand) {
            case M1 -> userProgress.setGeneralTransportType(GeneralTransportType.M1);
            case EXCEPT_M1 -> userProgress.setGeneralTransportType(GeneralTransportType.EXCEPT_M1);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processN1_N3TransportType(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        switch (requestingCommand) {
            case N1_N3 -> userProgress.setExceptM1TransportType(ExceptM1TransportType.N1_N3);
            case M2_M3 -> userProgress.setExceptM1TransportType(ExceptM1TransportType.M2_M3);
            case TRUCK_UNITS -> userProgress.setExceptM1TransportType(ExceptM1TransportType.TRUCK_UNITS);
            case TRAILERS -> userProgress.setExceptM1TransportType(ExceptM1TransportType.TRAILERS_O4);
        }

        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processN1_N3TransportWeight(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress = userProgressStorage.get(chatID);
        switch (requestingCommand) {
            case LESS_2_TONS -> userProgress.setTransportWeightN1N2N3(TransportWeightN1N2N3.LESS_2_TONS);
            case BETWEEN_2_5_AND_3_5_TONS ->
                    userProgress.setTransportWeightN1N2N3(TransportWeightN1N2N3.BETWEEN_2_5_AND_3_5);
            case BETWEEN_3_5_AND_5_TONS ->
                    userProgress.setTransportWeightN1N2N3(TransportWeightN1N2N3.BETWEEN_3_5_AND_5);
            case BETWEEN_5_AND_8_TONS -> userProgress.setTransportWeightN1N2N3(TransportWeightN1N2N3.BETWEEN_5_AND_8);
            case BETWEEN_8_AND_12_TONS -> userProgress.setTransportWeightN1N2N3(TransportWeightN1N2N3.BETWEEN_8_AND_12);
            case BETWEEN_12_AND_20_TONS ->
                    userProgress.setTransportWeightN1N2N3(TransportWeightN1N2N3.BETWEEN_12_AND_20);
            case BETWEEN_20_AND_50_TONS ->
                    userProgress.setTransportWeightN1N2N3(TransportWeightN1N2N3.BETWEEN_20_AND_50);
        }
        userProgressStorage.save(userProgress);
        return messagesCreator.getSummaryAnswer(userProgress);
    }

    public String processCarOrigin(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);
        final String message;

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

        final String message;

        if (Objects.equals(requestingCommand, Command.JURIDICAL_PERSON)) {
            userProgress.setOwnersType(OwnersType.JURIDICAL);
        } else {
            if (Objects.equals(requestingCommand, Command.PHYSICAL_PERSON)) {
                userProgress.setOwnersType(OwnersType.PHYSICAL);
            }
        }

        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);

        return message;
    }

    public String processCarAge(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(Command.AGE, userProgress)) {
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

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }
        if (Objects.equals(requestingCommand, Command.GASOLINE_TYPE_ENGINE)) {
            switch (userProgress.getGeneralTransportType()) {
                case M1 -> userProgress.setTypeOfEngineM1(TypeOfEngineM1.GASOLINE);
                case EXCEPT_M1 -> userProgress.setEngineTypeM2M3(EngineTypeM2M3.GASOLINE);
            }
        } else {
            if (Objects.equals(requestingCommand, Command.ELECTRIC_TYPE_ENGINE)) {
                switch (userProgress.getGeneralTransportType()) {
                    case M1 -> userProgress.setTypeOfEngineM1(TypeOfEngineM1.ELECTRIC);
                    case EXCEPT_M1 -> userProgress.setEngineTypeM2M3(EngineTypeM2M3.ELECTRIC);
                }
            }
        }
        final String message;
        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    public String processEngineVolume(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(Command.VOLUME, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        final String message;

        switch (requestingCommand) {
            case Command.VOLUME_LESS_1000_CM -> userProgress.setVolumeOfEngine(VolumeOfEngine.LESS_1000);
            case Command.VOLUME_BETWEEN_1000_2000_CM ->
                    userProgress.setVolumeOfEngine(VolumeOfEngine.BETWEEN_1000_AND_2000);
            case Command.VOLUME_BETWEEN_2000_3000_CM ->
                    userProgress.setVolumeOfEngine(VolumeOfEngine.BETWEEN_2000_AND_3000);
            case Command.VOLUME_BETWEEN_3000_3500_CM ->
                    userProgress.setVolumeOfEngine(VolumeOfEngine.BETWEEN_3000_AND_3500);
            case VOLUME_MORE_3500_CM -> userProgress.setVolumeOfEngine(VolumeOfEngine.MORE_3500);
        }
        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    private static class BotFieldsManagerHolder {
        private static final UserProgressManager USER_PROGRESS_MANAGER = new UserProgressManager();
    }
}
