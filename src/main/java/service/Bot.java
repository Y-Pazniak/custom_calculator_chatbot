package service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import repository.CountryOrigin;
import repository.Storage;

public class Bot extends TelegramLongPollingBot {
    private CountryOrigin countryOrigin = null;
    private boolean isPhysical = false;
    private boolean isJuridical = false;
    private boolean is3Years = false;
    private boolean is3And7Years = false;
    private boolean isMore7Years = false;
    private boolean isGas = false;
    private boolean isElectric = false;
    private boolean isLess1000 = false;
    private boolean isBetween1000and2000 = false;
    private boolean isBetween2000and3000 = false;
    private boolean isBetween3000and3500 = false;
    private boolean isMore3500 = false;

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
            } else sendMessage = getSorryMessage(inputMessage);
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
            case Storage.START -> sendMessage = getGreetingMessage(message);
            case Storage.EAES, Storage.OTHER_COUNTRIES -> sendMessage = getOriginCarMessage(message);
            case Storage.PHYSICAL, Storage.JURIDICAL -> sendMessage = getTypeOfOwnerMessage(message);
            case Storage.LESS_3_YEARS, Storage.BETWEEN_3_AND_7_YEARS, Storage.MORE_7_YEARS -> sendMessage = getAutoAgeMessage(message);
            case Storage.GAS, Storage.ELECTRIC -> sendMessage = getEngineTypeMessage(message);
            case Storage.VOLUME_LESS_1000, Storage.VOLUME_BETWEEN_1000_2000, Storage.VOLUME_BETWEEN_2000_3000,
                    Storage.VOLUME_BETWEEN_3000_3500, Storage.VOLUME_MORE_3500 -> sendMessage = getEngineVolumeMessage(message);
            default -> sendMessage = getSorryMessage(message);
        }
        return sendMessage;
    }

    private SendMessage getSorryMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("sorry, wrong data, try again");
        sendMessage.setChatId(message.getChatId().toString());
        return sendMessage;
    }

    private SendMessage getGreetingMessage(Message message) {
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

    private SendMessage getOriginCarMessage(Message inputMessage) {
        cleanBooleans(1);
        switch (inputMessage.getText()) {
            case Storage.EAES -> countryOrigin = CountryOrigin.EAES;
            case Storage.OTHER_COUNTRIES -> countryOrigin = CountryOrigin.OTHER;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage getTypeOfOwnerMessage(Message inputMessage) {
        cleanBooleans(2);
        switch (inputMessage.getText()) {
            case Storage.JURIDICAL -> isJuridical = true;
            case Storage.PHYSICAL -> isPhysical = true;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage getAutoAgeMessage(Message inputMessage) {
        cleanBooleans(3);
        switch (inputMessage.getText()) {
            case Storage.LESS_3_YEARS -> is3Years = true;
            case Storage.BETWEEN_3_AND_7_YEARS -> is3And7Years = true;
            case Storage.MORE_7_YEARS -> isMore7Years = true;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage getEngineTypeMessage(Message message) {
        cleanBooleans(4);
        switch (message.getText()) {
            case Storage.GAS -> isGas = true;
            case Storage.ELECTRIC -> isElectric = true;
        }
        return createSendMessage(message.getChatId().toString());
    }

    private SendMessage getEngineVolumeMessage(Message message) {
        cleanBooleans(5);
        switch (message.getText()) {
            case Storage.VOLUME_LESS_1000 -> isLess1000 = true;
            case Storage.VOLUME_BETWEEN_1000_2000 -> isBetween1000and2000 = true;
            case Storage.VOLUME_BETWEEN_2000_3000 -> isBetween2000and3000 = true;
            case Storage.VOLUME_BETWEEN_3000_3500 -> isBetween3000and3500 = true;
            case Storage.VOLUME_MORE_3500 -> isMore3500 = true;
        }
        return createSendMessage(message.getChatId().toString());
    }

    private String createSummaryString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createUsersChoiceString());

        if (CountryOrigin.EAES.equals(countryOrigin)) {
            if (!isPhysical && !isJuridical) {
                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PHYSICAL_OR_JURIDICAL));
            } else {
                if (!is3Years && !is3And7Years && !isMore7Years) {
                    stringBuilder.append(stringBuilderAppender(".", "\n", Storage.AGE_OF_AUTO));
                } else {
                    stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PRICE_STRING));
                }
            }
        }

        if (CountryOrigin.OTHER.equals(countryOrigin)) {
            if (!isPhysical && !isJuridical) {
                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PHYSICAL_OR_JURIDICAL));
            } else {
                if (isPhysical) {
                    if (!is3Years && !is3And7Years && !isMore7Years) {
                        stringBuilder.append(stringBuilderAppender(".", "\n", Storage.AGE_OF_AUTO));
                    }
                    if (is3Years || is3And7Years || isMore7Years) {
                        stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PRICE_STRING));
                    }
                }
                if (isJuridical) {
                    if (!isElectric && !isGas) {
                        stringBuilder.append(stringBuilderAppender(".", "\n", Storage.GAS_OR_ELECTRIC_ENGINE));
                    } else {
                        if (isElectric) {
                            if (!is3Years && !is3And7Years && !isMore7Years) {
                                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.AGE_OF_AUTO));
                            } else {
                                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PRICE_STRING));
                            }
                        } else {
                            if (!isLess1000 && !isBetween1000and2000 && !isBetween2000and3000 && !isBetween3000and3500 && !isMore3500) {
                                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.GAS_ENGINE_VOLUME));
                            } else {
                                if (!is3Years && !is3And7Years && !isMore7Years) {
                                    stringBuilder.append(stringBuilderAppender(".", "\n", Storage.AGE_OF_AUTO));
                                } else stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PRICE_STRING));
                            }
                        }
                    }
                }
            }
        }

        return stringBuilder.toString();
    }

    private String createUsersChoiceString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Storage.YOUR_CHOICE);
        if (countryOrigin == null) {
            if (CountryOrigin.EAES.equals(countryOrigin)) {
                sb.append(Storage.EAES_STRING);
            } else {
                sb.append(Storage.OTHER_COUNTRIES_STRING);
            }

            if (isPhysical) {
                sb.append(Storage.PHYSICAL_STRING);
            } else if (isJuridical){
                sb.append(Storage.JURIDICAL_STRING);
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

            if (isElectric || isGas) {
                if (isElectric) {
                    sb.append(Storage.ELECTRIC_STRING);
                } else {
                    sb.append(Storage.GAS_STRING);
                }
            }

            if (isLess1000 || isBetween1000and2000 || isBetween2000and3000 || isBetween3000and3500 || isMore3500) {
                if (isLess1000) {
                    sb.append(Storage.VOLUME_LESS_1000_STRING);
                } else {
                    if (isBetween1000and2000) {
                        sb.append(Storage.VOLUME_BETWEEN_1000_2000_STRING);
                    } else {
                        if (isBetween2000and3000) {
                            sb.append(Storage.VOLUME_BETWEEN_2000_3000_STRING);
                        } else {
                            sb.append(Storage.VOLUME_MORE_3500_STRING);
                        }
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

    private void cleanBooleans(int stepCleaner) {
        if (stepCleaner <= 1) {
            countryOrigin = null;
        }
        if (stepCleaner <= 2) {
            isPhysical = false;
            isJuridical = false;
        }
        if (stepCleaner <= 3) {
            is3Years = false;
            is3And7Years = false;
            isMore7Years = false;
        }

        if (stepCleaner <= 4) {
            isElectric = false;
            isGas = false;
            isLess1000 = false;
            isBetween1000and2000 = false;
            isBetween2000and3000 = false;
            isBetween3000and3500 = false;
            isMore3500 = false;
        }

        if (stepCleaner <= 5) {
            isLess1000 = false;
            isBetween1000and2000 = false;
            isBetween2000and3000 = false;
            isBetween3000and3500 = false;
            isMore3500 = false;
            is3Years = false;
            is3And7Years = false;
            isMore7Years = false;
        }

//        if (stepCleaner <= 5) {
//            isElectric = false;
//            isGas = false;
//            isLess1000 = false;
//            isBetween1000and2000 = false;
//            isBetween2000and3000 = false;
//            isBetween3000and3500 = false;
//            isMore3500 = false;
//        }
//        switch (stepCleaner) {
//            case 1 -> {
//                isEaes = false;
//                isOther = false;
//                isPhysical = false;
//                isJuridical = false;
//                is3Years = false;
//                is3And7Years = false;
//                isMore7Years = false;
//                isElectric = false;
//                isGas = false;
//                isLess1000 = false;
//                isBetween1000and2000 = false;
//                isBetween2000and3000 = false;
//                isBetween3000and3500 = false;
//                isMore3500 = false;
//            }
//            case 2 -> {
//                isJuridical = false;
//                isPhysical = false;
//                is3Years = false;
//                is3And7Years = false;
//                isMore7Years = false;
//                isElectric = false;
//                isGas = false;
//                isLess1000 = false;
//                isBetween1000and2000 = false;
//                isBetween2000and3000 = false;
//                isBetween3000and3500 = false;
//                isMore3500 = false;
//            }
//            case 3 -> {
//                is3Years = false;
//                is3And7Years = false;
//                isMore7Years = false;
//            }
//            case 4 -> {
//                isElectric = false;
//                isGas = false;
//                is3Years = false;
//                is3And7Years = false;
//                isMore7Years = false;
//                isLess1000 = false;
//                isBetween1000and2000 = false;
//                isBetween2000and3000 = false;
//                isBetween3000and3500 = false;
//                isMore3500 = false;
//            }
//
//            case 5 -> {
//                isLess1000 = false;
//                isBetween1000and2000 = false;
//                isBetween2000and3000 = false;
//                isBetween3000and3500 = false;
//                isMore3500 = false;
//                is3Years = false;
//                is3And7Years = false;
//                isMore7Years = false;
//            }
//        }
    }

    private String stringBuilderAppender(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}
