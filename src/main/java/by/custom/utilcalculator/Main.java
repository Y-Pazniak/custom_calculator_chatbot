package by.custom.utilcalculator;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import by.custom.utilcalculator.controller.Bot;

public class Main {
    public static void main(final String[] args) {
        Bot localBot = Bot.getBot();
        try {
            TelegramBotsApi telegramBot = new TelegramBotsApi(DefaultBotSession.class);
            telegramBot.registerBot(localBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
