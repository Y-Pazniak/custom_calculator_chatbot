package by.custom.utilcalculator.service;

import by.custom.utilcalculator.directory.UserProgress;
import by.custom.utilcalculator.directory.resources.Command;
import by.custom.utilcalculator.directory.steps.*;

public class BotFieldsManager {
    private final UserProgress userProgress;
    private final MessagesCreator messagesManager;

    public BotFieldsManager() {
        userProgress = UserProgress.getInstance();
        messagesManager = MessagesCreator.getInstance();
    }

    public static BotFieldsManager getInstance() {
        return BotFieldsManagerHolder.BOT_FIELDS_MANAGER;
    }

    public String processCarOrigin(String command) {
        cleanStepsAfterCurrent(1);
        switch (command) {
            case Command.EAES -> userProgress.setCountryOrigin(CountryOrigin.EAES);
            case Command.OTHER_COUNTRIES -> userProgress.setCountryOrigin(CountryOrigin.OTHER);
        }
        return messagesManager.getCountryOrigin();
    }

    public String processOwnerType(String command) {
        cleanStepsAfterCurrent(2);
        switch (command) {
            case Command.JURIDICAL_PERSON -> userProgress.setOwnersType(OwnersType.JURIDICAL);
            case Command.PHYSICAL_PERSON -> userProgress.setOwnersType(OwnersType.PHYSICAL);
        }
        return messagesManager.getCountryOrigin();
    }

    public String processCarAge(String command) {
        cleanStepsAfterCurrent(5);
        switch (command) {
            case Command.LESS_3_YEARS_AGE -> userProgress.setCarAge(CarAge.LESS_3_YEARS);
            case Command.BETWEEN_3_AND_7_YEARS_AGE -> userProgress.setCarAge(CarAge.BETWEEN_3_AND_7_YEARS);
            case Command.MORE_7_YEARS_AGE -> userProgress.setCarAge(CarAge.MORE_7_YEARS);
        }
        return messagesManager.getCountryOrigin();
    }

    public String processEngineType(String command) {
        cleanStepsAfterCurrent(3);
        switch (command) {
            case Command.GASOLINE_TYPE_ENGINE -> userProgress.setTypeOfEngine(TypeOfEngine.GASOLINE);
            case Command.ELECTRIC_TYPE_ENGINE -> userProgress.setTypeOfEngine(TypeOfEngine.ELECTRIC);
        }
        return messagesManager.getCountryOrigin();
    }

    public String processEngineVolume(String command) {
        cleanStepsAfterCurrent(4);
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
        return messagesManager.getCountryOrigin();
    }

    private void cleanStepsAfterCurrent(int stepCleaner) {
        if (stepCleaner <= 1) {
            userProgress.setCountryOrigin(null);
        }
        if (stepCleaner <= 2) {
            userProgress.setOwnersType(null);
        }
        if (stepCleaner <= 3) {
            userProgress.setTypeOfEngine(null);
        }
        if (stepCleaner <= 4) {
            userProgress.setVolumeOfEngine(null);
        }
        if (stepCleaner <= 5) {
            userProgress.setCarAge(null);
        }
    }

    private static class BotFieldsManagerHolder {
        private static final BotFieldsManager BOT_FIELDS_MANAGER = new BotFieldsManager();
    }
}
