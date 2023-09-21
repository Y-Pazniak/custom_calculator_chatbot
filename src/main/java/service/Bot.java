package service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

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
        if (update.hasMessage() && update.getMessage().hasText()) {
            //take the message - it is the object which contains all information about message (id, type (text, video, sticker), date and so on - all info is here)
            Message inputMessage = update.getMessage();
            //take the chat id where we must send the answer
            String chatId = inputMessage.getChatId().toString();
            //take the user's message
            String response = inputMessage.getText() + " - hello!";
            //create the response object - we need this object to execute it, we can put there whatever we want to send
            SendMessage outputMessage = new SendMessage();
            outputMessage.setChatId(chatId);
            outputMessage.setText(response);
            try {
                //this method sends our object to the user's chat
                execute(outputMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public static Bot getBot() {
        return BotHolder.bot;
    }
}
