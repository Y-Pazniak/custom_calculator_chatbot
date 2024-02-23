package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.FileUserProgressStorage;
import by.custom.utilcalculator.domain.IUserProgressStorage;
import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.domain.tree.Node;
import by.custom.utilcalculator.domain.tree.NodeStorage;
import by.custom.utilcalculator.domain.tree.Tree;
import by.custom.utilcalculator.exception.StepsQueueException;
import by.custom.utilcalculator.exception.UtilsborException;

public class UserProgressManager {
    private final MessagesCreator messagesCreator;
    private final IUserProgressStorage userProgressStorage;
    private final NodeStorage nodeStorage;

    private final Tree tree;

    private UserProgressManager() {
        messagesCreator = MessagesCreator.getInstance();
        userProgressStorage = FileUserProgressStorage.getInstance();
        nodeStorage = NodeStorage.getInstance();
        tree = Tree.getInstance();
    }

    public static UserProgressManager getInstance() {
        return BotFieldsManagerHolder.USER_PROGRESS_MANAGER;
    }

    public void createNewUserProgress(final String chatID) throws UtilsborException {
        UserProgress userProgress = new UserProgress(chatID);
        userProgress.resetNodes();
        userProgressStorage.save(userProgress);
//        IUserProgressStorage localUserProgressStorage = PostgresUserProgressStorage.getInstance();
//        UserProgress userProgress = new UserProgress(chatID);
//        localUserProgressStorage.save(userProgress);
//        userProgressStorage.save(userProgress);
    }

    public String processCarOrigin(final String command, final String chatID) throws UtilsborException {
        UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);
        userProgress.setNode(nodeStorage.updateUserNode(command));
        String message;
        switch (command) {
            case Command.EAES -> userProgress.setCountryOrigin(CountryOrigin.EAES);
            case Command.OTHER_COUNTRIES -> userProgress.setCountryOrigin(CountryOrigin.OTHER);

        }
        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }


    public String processOwnerType(final String command, final String chatID) throws UtilsborException {
        UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!tree.validateNode(userProgress.getCurrentNode(), command, userProgress.getNodes())) {
            throw new StepsQueueException(chatID, command);
        }
        userProgress.setNode(nodeStorage.updateUserNode(command));
        String message;
        switch (command) {
            case Command.JURIDICAL_PERSON -> userProgress.setOwnersType(OwnersType.JURIDICAL);
            case Command.PHYSICAL_PERSON -> userProgress.setOwnersType(OwnersType.PHYSICAL);
        }
        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);

        return message;
    }

    public String processCarAge(final String command, final String chatID) throws UtilsborException {
        UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!tree.validateNode(userProgress.getCurrentNode(), Command.AGE, userProgress.getNodes())) {
            throw new StepsQueueException(chatID, command);
        }

        String message;
        try {
            switch (command) {
                case Command.LESS_3_YEARS_AGE -> userProgress.setCarAge(CarAge.LESS_3_YEARS);
                case Command.BETWEEN_3_AND_7_YEARS_AGE -> userProgress.setCarAge(CarAge.BETWEEN_3_AND_7_YEARS);
                case Command.MORE_7_YEARS_AGE -> userProgress.setCarAge(CarAge.MORE_7_YEARS);
            }
            userProgressStorage.save(userProgress);
            message = messagesCreator.getSummaryAnswer(userProgress);
        } catch (StepsQueueException exception) {
            message = exception.getWrongStep();
        }
        return message;
    }

    public String processEngineType(final String command, final String chatID) throws UtilsborException {
        UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!tree.validateNode(userProgress.getCurrentNode(), command, userProgress.getNodes())) {
            throw new StepsQueueException(chatID, command);
        }
        userProgress.setNode(nodeStorage.updateUserNode(command));
        String message;
        switch (command) {
            case Command.GASOLINE_TYPE_ENGINE -> userProgress.setTypeOfEngine(TypeOfEngine.GASOLINE);
            case Command.ELECTRIC_TYPE_ENGINE -> userProgress.setTypeOfEngine(TypeOfEngine.ELECTRIC);
        }
        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    public String processEngineVolume(final String command, final String chatID) throws UtilsborException {
        UserProgress userProgress;
        userProgress = userProgressStorage.get(chatID);

        if (!tree.validateNode(userProgress.getCurrentNode(), Command.VOLUME, userProgress.getNodes())) {
            throw new StepsQueueException(chatID, command);
        }
        userProgress.setNode(nodeStorage.updateUserNode(command));
        String message;
        switch (command) {
            case Command.VOLUME_LESS_1000_CM -> userProgress.setVolumeOfEngine(VolumeOfEngine.LESS_1000);
            case Command.VOLUME_BETWEEN_1000_2000_CM ->
                    userProgress.setVolumeOfEngine(VolumeOfEngine.BETWEEN_1000_AND_2000);
            case Command.VOLUME_BETWEEN_2000_3000_CM ->
                    userProgress.setVolumeOfEngine(VolumeOfEngine.BETWEEN_2000_AND_3000);
            case Command.VOLUME_BETWEEN_3000_3500_CM ->
                    userProgress.setVolumeOfEngine(VolumeOfEngine.BETWEEN_3000_AND_3500);
            case Command.VOLUME_MORE_3500_CM -> userProgress.setVolumeOfEngine(VolumeOfEngine.MORE_3500);
        }
        userProgressStorage.save(userProgress);
        message = messagesCreator.getSummaryAnswer(userProgress);
        return message;
    }

    private static class BotFieldsManagerHolder {
        private static final UserProgressManager USER_PROGRESS_MANAGER = new UserProgressManager();
    }
}
