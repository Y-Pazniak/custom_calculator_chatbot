package by.custom.utilcalculator;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import by.custom.utilcalculator.service.Bot;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        try {
            //this is the bot's registration - i don't know what is going here, i have just copied this from manual
            TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
            bot.registerBot(Bot.getBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
