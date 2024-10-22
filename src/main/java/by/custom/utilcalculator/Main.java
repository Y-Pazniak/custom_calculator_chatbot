package by.custom.utilcalculator;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import by.custom.utilcalculator.controller.Bot;

public class Main {
    //branch for customers edits
    public static void main(final String[] args) {
        final Bot localBot = Bot.getBot();

        try {
            final TelegramBotsApi telegramBot = new TelegramBotsApi(DefaultBotSession.class);
            telegramBot.registerBot(localBot);
        } catch (final TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
