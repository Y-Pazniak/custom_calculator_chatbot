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
    //private final CommandTree treeCommand;

    private UserProgressManager() {
        messagesCreator = MessagesCreator.getInstance();
        userProgressStorage = FileUserProgressStorage.getInstance();
        //userProgressValidator = CommandTree.getInstance();
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

    public String processCarOrigin(final String requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);
        final String message;

        if (Objects.equals(requestingCommand, Command.EAES.getCommand())) {
            userProgress.setCountryOrigin(CountryOrigin.EAES);
        } else {
            if (Objects.equals(requestingCommand, Command.OTHER_COUNTRIES.getCommand())) {
                userProgress.setCountryOrigin(CountryOrigin.OTHER);
            }
        }

        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    public String processOwnerType(final String requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        final String message;

        if (Objects.equals(requestingCommand, Command.JURIDICAL_PERSON.getCommand())) {
            userProgress.setOwnersType(OwnersType.JURIDICAL);
        } else {
            if (Objects.equals(requestingCommand, Command.PHYSICAL_PERSON.getCommand())) {
                userProgress.setOwnersType(OwnersType.PHYSICAL);
            }
        }

        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);

        return message;
    }

    public String processCarAge(final String requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(Command.AGE.getCommand(), userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        final String message;

        if (Objects.equals(requestingCommand, Command.LESS_3_YEARS_AGE.getCommand())) {
            userProgress.setCarAge(CarAge.LESS_OR_3_YEARS);
        } else {
            if (Objects.equals(requestingCommand, Command.MORE_THAN_3_YEARS_AGE.getCommand())) {
                userProgress.setCarAge(CarAge.MORE_3_YEARS);
            }
        }

        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    public String processEngineType(final String requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(requestingCommand, userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        if (Objects.equals(requestingCommand, Command.GASOLINE_TYPE_ENGINE.getCommand())) {
            userProgress.setTypeOfEngine(TypeOfEngine.GASOLINE);
        } else {
            if (Objects.equals(requestingCommand, Command.ELECTRIC_TYPE_ENGINE.getCommand())) {
                userProgress.setTypeOfEngine(TypeOfEngine.ELECTRIC);
            }
        }
        final String message;
        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    public String processEngineVolume(final String requestingCommand, final String chatID) throws UtilsborException {
        final UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!UserProgressValidator.validateCommand(Command.VOLUME.getCommand(), userProgress)) {
            throw new InvalidOrderCommandException(chatID, requestingCommand);
        }

        final String message;

        while (true) {
            if (Objects.equals(requestingCommand, Command.VOLUME_LESS_1000_CM.getCommand())) {
                userProgress.setVolumeOfEngine(VolumeOfEngine.LESS_1000);
                break;
            }
            if (Objects.equals(requestingCommand, Command.VOLUME_BETWEEN_1000_2000_CM.getCommand())) {
                userProgress.setVolumeOfEngine(VolumeOfEngine.BETWEEN_1000_AND_2000);
                break;
            }
            if (Objects.equals(requestingCommand, Command.VOLUME_BETWEEN_2000_3000_CM.getCommand())) {
                userProgress.setVolumeOfEngine(VolumeOfEngine.BETWEEN_2000_AND_3000);
                break;
            }
            if (Objects.equals(requestingCommand, Command.VOLUME_BETWEEN_3000_3500_CM.getCommand())) {
                userProgress.setVolumeOfEngine(VolumeOfEngine.BETWEEN_3000_AND_3500);
                break;
            }
            if (Objects.equals(requestingCommand, Command.VOLUME_MORE_3500_CM.getCommand())) {
                userProgress.setVolumeOfEngine(VolumeOfEngine.MORE_3500);
                break;
            }
            break;
        }
        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    private static class BotFieldsManagerHolder {
        private static final UserProgressManager USER_PROGRESS_MANAGER = new UserProgressManager();
    }
}
