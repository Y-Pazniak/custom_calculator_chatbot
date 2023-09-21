package service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import repository.Storage;

public class Bot extends TelegramLongPollingBot {
    private boolean isEaes = false;
    private boolean isOther = false;

    //singleton Bot creation
    public static class BotHolder {
        private static final Bot bot = new Bot();
    }

    //bot's name
    private static final String BOT_NAME = "Бесплатный калькулятор утильсбора РБ";

    //private constructor to avoid wrong bot's creation
    private Bot() {
    }

    //bot's token takes from the environmental variables
    @Override
    public String getBotToken() {
        return System.getenv("custom_by_utilsbor_bot");
    }

    //method describes what to do after receiving message
    @Override
    public void onUpdateReceived(Update update) {
        //if user send us smth
        SendMessage sendMessage;
        Message inputMessage = update.getMessage();
        try {
            if (update.hasMessage() && inputMessage.hasText()) {
                sendMessage = checkInputMessage(inputMessage);
            } else sendMessage = sorryMessage(inputMessage);
            execute(sendMessage);
        } catch (
                TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendMessage checkInputMessage(Message message) {
        String usersMessage = message.getText();
        SendMessage sendMessage;
        switch (usersMessage) {
            case Storage.START -> sendMessage = greetingStep(message);
            case Storage.EAES, Storage.OTHER_COUNTRIES -> sendMessage = whereAutoStepChecker(message);
            default -> sendMessage = sorryMessage(message);
        }
        return sendMessage;
    }

    private SendMessage sorryMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("sorry, wrong data, try again");
        sendMessage.setChatId(message.getChatId().toString());
        return sendMessage;
    }

    private SendMessage greetingStep(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(Storage.GREETING_TEXT);
        sendMessage.setChatId(message.getChatId().toString());
        return sendMessage;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public static Bot getBot() {
        return BotHolder.bot;
    }

    private SendMessage whereAutoStepChecker(Message inputMessage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(inputMessage.getChatId().toString());
        switch (inputMessage.getText()) {
            case Storage.EAES -> {
                sendMessage.setText("/eaes" + " your choise");
                isEaes = true;
            }
            case Storage.OTHER_COUNTRIES -> {
                sendMessage.setText("/other" + " your choise");
                isOther = true;
            }
            default -> sendMessage.setText("input error");
        }
        return sendMessage;
    }
}
