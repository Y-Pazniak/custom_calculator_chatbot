package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.UserProgressStorage;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import org.telegram.telegrambots.meta.api.objects.Message;

public class UserProgressManager {
    private final MessagesCreator messagesCreator;
    //private final UserProgress userProgress;
    private final UserProgressStorage userProgressStorage;

    public UserProgressManager() {
        messagesCreator = MessagesCreator.getInstance();
        userProgressStorage = UserProgressStorage.getInstance();
    }

    public static UserProgressManager getInstance() {
        return BotFieldsManagerHolder.BOT_FIELDS_MANAGER;
    }

    public String processCarOrigin(String command, Message message) {
        UserProgress userProgress = getUserProgress(message);
        switch (command) {
            case Command.EAES -> userProgress.setCountryOrigin(CountryOrigin.EAES);
            case Command.OTHER_COUNTRIES -> userProgress.setCountryOrigin(CountryOrigin.OTHER);
        }
        return messagesCreator.getCountryOrigin(userProgress);
    }

    public String processOwnerType(String command, Message message) {
        UserProgress userProgress = getUserProgress(message);
        switch (command) {
            case Command.JURIDICAL_PERSON -> userProgress.setOwnersType(OwnersType.JURIDICAL);
            case Command.PHYSICAL_PERSON -> userProgress.setOwnersType(OwnersType.PHYSICAL);
        }
        return messagesCreator.getCountryOrigin(userProgress);
    }

    public String processCarAge(String command, Message message) {
        UserProgress userProgress = getUserProgress(message);
        switch (command) {
            case Command.LESS_3_YEARS_AGE -> userProgress.setCarAge(CarAge.LESS_3_YEARS);
            case Command.BETWEEN_3_AND_7_YEARS_AGE -> userProgress.setCarAge(CarAge.BETWEEN_3_AND_7_YEARS);
            case Command.MORE_7_YEARS_AGE -> userProgress.setCarAge(CarAge.MORE_7_YEARS);
        }
        return messagesCreator.getCountryOrigin(userProgress);
    }

    public String processEngineType(String command, Message message) {
        UserProgress userProgress = getUserProgress(message);
        switch (command) {
            case Command.GASOLINE_TYPE_ENGINE -> userProgress.setTypeOfEngine(TypeOfEngine.GASOLINE);
            case Command.ELECTRIC_TYPE_ENGINE -> userProgress.setTypeOfEngine(TypeOfEngine.ELECTRIC);
        }
        return messagesCreator.getCountryOrigin(userProgress);
    }

    public String processEngineVolume(String command, Message message) {
        UserProgress userProgress = getUserProgress(message);
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
        return messagesCreator.getCountryOrigin(userProgress);
    }

    private UserProgress getUserProgress(Message message){
        return userProgressStorage.getUser(message.getChatId().toString());
    }

    private static class BotFieldsManagerHolder {
        private static final UserProgressManager BOT_FIELDS_MANAGER = new UserProgressManager();
    }
}
