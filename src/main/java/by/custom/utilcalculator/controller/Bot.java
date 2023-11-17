package by.custom.utilcalculator.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private final MessageRouter messageRouter;

    //singleton Bot creation
    private static class BotHolder {
        private static final Bot bot = new Bot();
    }

    public static Bot getBot() {
        return BotHolder.bot;
    }

    //bot's name "Бесплатный калькулятор утильсбора РБ"
    private static final String BOT_NAME = "Бесплатный калькулятор утильсбора РБ";

    //private constructor to avoid wrong bot's creation
    private Bot() {
        messageRouter = MessageRouter.getInstance();
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
    public void onUpdateReceived(final Update update) {
        //if user send us smth
        try {
            execute(messageRouter.route(update));
        } catch (TelegramApiException e) {
            System.out.println("Fail to process user's request.");
            e.printStackTrace();
        }
    }
}
