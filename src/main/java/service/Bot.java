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
    private boolean isPhysical = false;
    private boolean isJuridical = false;
    private boolean is3Years = false;
    private boolean is3And7Years = false;
    private boolean isMore7Years = false;

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
            case Storage.PHYSICAL, Storage.JURIDICAL -> sendMessage = whoIsPersonChecker(message);
            case Storage.LESS_3_YEARS, Storage.BETWEEN_3_AND_7_YEARS, Storage.MORE_7_YEARS -> sendMessage = whatIsTheAgeAuto(message);
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
        booleanCleaner(1);
        switch (inputMessage.getText()) {
            case Storage.EAES -> isEaes = true;
            case Storage.OTHER_COUNTRIES -> isOther = true;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage whoIsPersonChecker(Message inputMessage) {
        booleanCleaner(2);
        switch (inputMessage.getText()) {
            case Storage.JURIDICAL -> isJuridical = true;
            case Storage.PHYSICAL -> isPhysical = true;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage whatIsTheAgeAuto(Message inputMessage) {
        booleanCleaner(3);
        switch (inputMessage.getText()) {
            case Storage.LESS_3_YEARS -> is3Years = true;
            case Storage.BETWEEN_3_AND_7_YEARS -> is3And7Years = true;
            case Storage.MORE_7_YEARS -> isMore7Years = true;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private String createSummaryString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createUsersChoiceString());

        if ((isEaes || isOther) && (!isPhysical && !isJuridical)) {
            stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PHYSICAL_OR_JURIDICAL));
        } else {
            if (isPhysical || isJuridical && (!is3Years && !is3And7Years && !isMore7Years)) {
                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.AGE_OF_AUTO));
            } else {
                if (is3Years || is3And7Years || isMore7Years) {
                    stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PRICE_STRING));
                }
            }
        }

        return stringBuilder.toString();
    }

    private String createUsersChoiceString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Storage.YOUR_CHOICE);
        if (isEaes || isOther) {
            if (isEaes) {
                sb.append(Storage.EAES_STRING);
            } else {
                sb.append(Storage.OTHER_COUNTRIES_STRING);
            }

            if (isPhysical || isJuridical) {
                if (isPhysical) {
                    sb.append(Storage.PHYSICAL_STRING);
                } else {
                    sb.append(Storage.JURIDICAL_STRING);
                }
            }

            if (is3Years || is3And7Years || isMore7Years) {
                if (is3Years) {
                    sb.append(Storage.LESS_3_YEARS_STRING);
                } else {
                    if (is3And7Years) {
                        sb.append(Storage.BETWEEN_3_AND_7_YEARS_STRING);
                    } else {
                        sb.append(Storage.MORE_7_YEARS_STRING);
                    }
                }
            }
        }
        return sb.toString();
    }

    private SendMessage createSendMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(createSummaryString());
        return sendMessage;
    }

    private void booleanCleaner(int stepCleaner) {
        switch (stepCleaner) {
            case 1 -> {
                isEaes = false;
                isOther = false;
                isPhysical = false;
                isJuridical = false;
                is3Years = false;
                is3And7Years = false;
                isMore7Years = false;
            }
            case 2 -> {
                isJuridical = false;
                isPhysical = false;
                is3Years = false;
                is3And7Years = false;
                isMore7Years = false;
            }
            case 3 -> {
                is3Years = false;
                is3And7Years = false;
                isMore7Years = false;
            }
        }
    }

    private String stringBuilderAppender(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}
