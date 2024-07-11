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

    public String processTransportType(final Command requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);
        final String message;

        if (Objects.equals(requestingCommand, Command.M1)) {
            userProgress.setTransportType(TransportType.M1);
        }

        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
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
            userProgress.setTypeOfEngine(TypeOfEngine.GASOLINE);
        } else {
            if (Objects.equals(requestingCommand, Command.ELECTRIC_TYPE_ENGINE)) {
                userProgress.setTypeOfEngine(TypeOfEngine.ELECTRIC);
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
