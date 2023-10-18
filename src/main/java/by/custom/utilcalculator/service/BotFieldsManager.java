package by.custom.utilcalculator.service;

import by.custom.utilcalculator.directory.BotEntity;
import by.custom.utilcalculator.directory.resources.Commands;
import by.custom.utilcalculator.directory.steps.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class BotFieldsManager {
    private final BotEntity botEntity;
    private final MessagesManager messagesManager;

    public BotFieldsManager() {
        botEntity = BotEntity.getInstance();
        messagesManager = MessagesManager.getInstance();
    }

    public static BotFieldsManager getInstance() {
        return BotFieldsManagerHolder.BOT_FIELDS_MANAGER;
    }

    public SendMessage setCarOriginFieldAndGetNextMessageForUser(Message inputMessage) {
        cleanStepsAfterCurrent(1);
        switch (inputMessage.getText()) {
            case Commands.EAES -> botEntity.setCountryOrigin(CountryOrigin.EAES);
            case Commands.OTHER_COUNTRIES -> botEntity.setCountryOrigin(CountryOrigin.OTHER);
        }
        return messagesManager.createSendMessage(inputMessage.getChatId().toString(),
                botEntity.getCountryOrigin(), botEntity.getOwnersType(), botEntity.getTypeOfEngine(), botEntity.getVolumeOfEngine(), botEntity.getCarAge());
    }

    public SendMessage setTypeOfOwnerFieldAndGetNextMessageForUser(Message inputMessage) {
        cleanStepsAfterCurrent(2);
        switch (inputMessage.getText()) {
            case Commands.JURIDICAL_PERSON -> botEntity.setOwnersType(OwnersType.JURIDICAL);
            case Commands.PHYSICAL_PERSON -> botEntity.setOwnersType(OwnersType.PHYSICAL);
        }
        return messagesManager.createSendMessage(inputMessage.getChatId().toString(),
                botEntity.getCountryOrigin(), botEntity.getOwnersType(), botEntity.getTypeOfEngine(), botEntity.getVolumeOfEngine(), botEntity.getCarAge());
    }

    public SendMessage setAgeAutoFieldAndGetTheResultMessageForUser(Message inputMessage) {
        cleanStepsAfterCurrent(5);
        switch (inputMessage.getText()) {
            case Commands.LESS_3_YEARS_AGE -> botEntity.setCarAge(CarAge.LESS_3_YEARS);
            case Commands.BETWEEN_3_AND_7_YEARS_AGE -> botEntity.setCarAge(CarAge.BETWEEN_3_AND_7_YEARS);
            case Commands.MORE_7_YEARS_AGE -> botEntity.setCarAge(CarAge.MORE_7_YEARS);
        }
        return messagesManager.createSendMessage(inputMessage.getChatId().toString(),
                botEntity.getCountryOrigin(), botEntity.getOwnersType(), botEntity.getTypeOfEngine(), botEntity.getVolumeOfEngine(), botEntity.getCarAge());
    }

    public SendMessage setEngineTypeFieldAndGetNextMessageForUser(Message inputMessage) {
        cleanStepsAfterCurrent(3);
        switch (inputMessage.getText()) {
            case Commands.GASOLINE_TYPE_ENGINE -> botEntity.setTypeOfEngine(TypeOfEngine.GASOLINE);
            case Commands.ELECTRIC_TYPE_ENGINE -> botEntity.setTypeOfEngine(TypeOfEngine.ELECTRIC);
        }
        return messagesManager.createSendMessage(inputMessage.getChatId().toString(),
                botEntity.getCountryOrigin(), botEntity.getOwnersType(), botEntity.getTypeOfEngine(), botEntity.getVolumeOfEngine(), botEntity.getCarAge());
    }

    public SendMessage setEngineVolumeFieldAndGetNextMessageForUser(Message inputMessage) {
        cleanStepsAfterCurrent(4);
        switch (inputMessage.getText()) {
            case Commands.VOLUME_LESS_1000_CM -> botEntity.setVolumeOfEngine(VolumeOfEngine.LESS_1000);
            case Commands.VOLUME_BETWEEN_1000_2000_CM ->
                    botEntity.setVolumeOfEngine(VolumeOfEngine.BETWEEN_1000_AND_2000);
            case Commands.VOLUME_BETWEEN_2000_3000_CM ->
                    botEntity.setVolumeOfEngine(VolumeOfEngine.BETWEEN_2000_AND_3000);
            case Commands.VOLUME_BETWEEN_3000_3500_CM ->
                    botEntity.setVolumeOfEngine(VolumeOfEngine.BETWEEN_3000_AND_3500);
            case Commands.VOLUME_MORE_3500_CM -> botEntity.setVolumeOfEngine(VolumeOfEngine.MORE_3500);
        }
        return messagesManager.createSendMessage(inputMessage.getChatId().toString(),
                botEntity.getCountryOrigin(), botEntity.getOwnersType(), botEntity.getTypeOfEngine(), botEntity.getVolumeOfEngine(), botEntity.getCarAge());
    }

    private void cleanStepsAfterCurrent(int stepCleaner) {
        if (stepCleaner <= 1) {
            botEntity.setCountryOrigin(null);
        }
        if (stepCleaner <= 2) {
            botEntity.setOwnersType(null);
        }
        if (stepCleaner <= 3) {
            botEntity.setTypeOfEngine(null);
        }
        if (stepCleaner <= 4) {
            botEntity.setVolumeOfEngine(null);
        }
        if (stepCleaner <= 5) {
            botEntity.setCarAge(null);
        }
    }

    private static class BotFieldsManagerHolder {
        private static final BotFieldsManager BOT_FIELDS_MANAGER = new BotFieldsManager();
    }
}
