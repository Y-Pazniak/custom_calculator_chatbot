package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.FileUserProgressStorage;
import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;

public class UserProgressManager {
    private final MessagesCreator messagesCreator;
    private final FileUserProgressStorage fileUserProgressStorage;

    private UserProgressManager() {
        messagesCreator = MessagesCreator.getInstance();
        fileUserProgressStorage = FileUserProgressStorage.getInstance();
    }

    public static UserProgressManager getInstance() {
        return BotFieldsManagerHolder.USER_PROGRESS_MANAGER;
    }

    public void createNewUserProgress(String chatID) {
        fileUserProgressStorage.createNewUserFile(chatID);
    }

    public String processCarOrigin(String command, String chatID) {
        UserProgress userProgress = fileUserProgressStorage.get(chatID);
        switch (command) {
            case Command.EAES -> userProgress.setCountryOrigin(CountryOrigin.EAES);
            case Command.OTHER_COUNTRIES -> userProgress.setCountryOrigin(CountryOrigin.OTHER);
        }
        fileUserProgressStorage.save(userProgress);
        return messagesCreator.getCountryOrigin(userProgress);
    }

    public String processOwnerType(String command, String chatID) {
        UserProgress userProgress = fileUserProgressStorage.get(chatID);
        switch (command) {
            case Command.JURIDICAL_PERSON -> userProgress.setOwnersType(OwnersType.JURIDICAL);
            case Command.PHYSICAL_PERSON -> userProgress.setOwnersType(OwnersType.PHYSICAL);
        }
        fileUserProgressStorage.save(userProgress);
        return messagesCreator.getCountryOrigin(userProgress);
    }

    public String processCarAge(String command, String chatID) {
        UserProgress userProgress = fileUserProgressStorage.get(chatID);
        switch (command) {
            case Command.LESS_3_YEARS_AGE -> userProgress.setCarAge(CarAge.LESS_3_YEARS);
            case Command.BETWEEN_3_AND_7_YEARS_AGE -> userProgress.setCarAge(CarAge.BETWEEN_3_AND_7_YEARS);
            case Command.MORE_7_YEARS_AGE -> userProgress.setCarAge(CarAge.MORE_7_YEARS);
        }
        fileUserProgressStorage.save(userProgress);
        return messagesCreator.getCountryOrigin(userProgress);
    }

    public String processEngineType(String command, String chatID) {
        UserProgress userProgress = fileUserProgressStorage.get(chatID);
        switch (command) {
            case Command.GASOLINE_TYPE_ENGINE -> userProgress.setTypeOfEngine(TypeOfEngine.GASOLINE);
            case Command.ELECTRIC_TYPE_ENGINE -> userProgress.setTypeOfEngine(TypeOfEngine.ELECTRIC);
        }
        fileUserProgressStorage.save(userProgress);
        return messagesCreator.getCountryOrigin(userProgress);
    }

    public String processEngineVolume(String command, String chatID) {
        UserProgress userProgress = fileUserProgressStorage.get(chatID);
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
        fileUserProgressStorage.save(userProgress);
        return messagesCreator.getCountryOrigin(userProgress);
    }

    private static class BotFieldsManagerHolder {
        private static final UserProgressManager USER_PROGRESS_MANAGER = new UserProgressManager();
    }
}
