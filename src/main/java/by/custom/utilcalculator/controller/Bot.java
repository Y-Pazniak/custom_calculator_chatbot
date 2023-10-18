package by.custom.utilcalculator.controller;

import by.custom.utilcalculator.repository.resources.Commands;
import by.custom.utilcalculator.repository.steps.*;
import by.custom.utilcalculator.service.MessagesChecker;
import by.custom.utilcalculator.service.MessagesCreator;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private CountryOrigin countryOrigin = null;
    private OwnersType ownersType = null;
    private CarAge carAge = null;
    private TypeOfEngine typeOfEngine = null;
    private VolumeOfEngine volumeOfEngine = null;
    private final MessagesCreator messagesCreator;
    private final MessagesChecker messagesChecker;

    //singleton Bot creation
    private static class BotHolder {
        private static final Bot bot = new Bot();
    }

    //bot's name
    private static final String BOT_NAME = "Бесплатный калькулятор утильсбора РБ";

    //private constructor to avoid wrong bot's creation
    private Bot() {
        messagesCreator = MessagesCreator.getInstance();
        messagesChecker = MessagesChecker.getInstance();
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    //bot's token takes from the environmental variables
    @Override
    public String getBotToken() {
        return
                //System.getenv("custom_by_utilsbor_bot");
                "6490861114:AAHVFOpQANIn_EQr1A1PcLrCMNzVlGkJIig";
    }

    //method describes what to do after receiving message
    @Override
    public void onUpdateReceived(Update update) {
        //if user send us smth
        try {
            execute(messagesChecker.checkMessageBeforeExecutionAndGetResult(update));
        } catch (TelegramApiException e) {
            System.out.println("Fail to process use request.");
            e.printStackTrace();
        }
    }

    public static Bot getBot() {
        return BotHolder.bot;
    }

    public SendMessage getOriginCarMessage(Message inputMessage) {
        cleanStepsAfterCurrent(1);
        switch (inputMessage.getText()) {
            case Commands.EAES -> countryOrigin = CountryOrigin.EAES;
            case Commands.OTHER_COUNTRIES -> countryOrigin = CountryOrigin.OTHER;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }


    public SendMessage getTypeOfOwnerMessage(Message inputMessage) {
        cleanStepsAfterCurrent(2);
        switch (inputMessage.getText()) {
            case Commands.JURIDICAL_PERSON -> ownersType = OwnersType.JURIDICAL;
            case Commands.PHYSICAL_PERSON -> ownersType = OwnersType.PHYSICAL;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    public SendMessage getAutoAgeMessage(Message inputMessage) {
        cleanStepsAfterCurrent(5);
        switch (inputMessage.getText()) {
            case Commands.LESS_3_YEARS_AGE -> carAge = CarAge.LESS_3_YEARS;
            case Commands.BETWEEN_3_AND_7_YEARS_AGE -> carAge = CarAge.BETWEEN_3_AND_7_YEARS;
            case Commands.MORE_7_YEARS_AGE -> carAge = CarAge.MORE_7_YEARS;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    public SendMessage getEngineTypeMessage(Message message) {
        cleanStepsAfterCurrent(3);
        switch (message.getText()) {
            case Commands.GASOLINE_TYPE_ENGINE -> typeOfEngine = TypeOfEngine.GASOLINE;
            case Commands.ELECTRIC_TYPE_ENGINE -> typeOfEngine = TypeOfEngine.ELECTRIC;
        }
        return createSendMessage(message.getChatId().toString());
    }

    public SendMessage getEngineVolumeMessage(Message message) {
        cleanStepsAfterCurrent(4);
        switch (message.getText()) {
            case Commands.VOLUME_LESS_1000_CM -> volumeOfEngine = VolumeOfEngine.LESS_1000;
            case Commands.VOLUME_BETWEEN_1000_2000_CM -> volumeOfEngine = VolumeOfEngine.BETWEEN_1000_AND_2000;
            case Commands.VOLUME_BETWEEN_2000_3000_CM -> volumeOfEngine = VolumeOfEngine.BETWEEN_2000_AND_3000;
            case Commands.VOLUME_BETWEEN_3000_3500_CM -> volumeOfEngine = VolumeOfEngine.BETWEEN_3000_AND_3500;
            case Commands.VOLUME_MORE_3500_CM -> volumeOfEngine = VolumeOfEngine.MORE_3500;
        }
        return createSendMessage(message.getChatId().toString());
    }

    private SendMessage createSendMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messagesCreator.getProperMessage(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge));
        return sendMessage;
    }

    private void cleanStepsAfterCurrent(int stepCleaner) {
        if (stepCleaner <= 1) {
            countryOrigin = null;
        }
        if (stepCleaner <= 2) {
            ownersType = null;
        }
        if (stepCleaner <= 3) {
            typeOfEngine = null;
        }
        if (stepCleaner <= 4) {
            volumeOfEngine = null;
        }
        if (stepCleaner <= 5) {
            carAge = null;
        }
    }
}
