package service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import repository.steps.*;
import repository.strings.Storage;

public class Bot extends TelegramLongPollingBot {
    private CountryOrigin countryOrigin = null;
    private OwnersType ownersType = null;
    private CarAge carAge = null;
    private TypeOfEngine typeOfEngine = null;
    private VolumeOfEngine volumeOfEngine = null;

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
            case Storage.GASOLINE, Storage.ELECTRIC -> sendMessage = getEngineTypeMessage(message);
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
            case Storage.JURIDICAL -> ownersType = OwnersType.JURIDICAL;
            case Storage.PHYSICAL -> ownersType = OwnersType.PHYSICAL;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage getAutoAgeMessage(Message inputMessage) {
        cleanBooleans(3);
        switch (inputMessage.getText()) {
            case Storage.LESS_3_YEARS -> carAge = CarAge.LESS_3_YEARS;
            case Storage.BETWEEN_3_AND_7_YEARS -> carAge = CarAge.BETWEEN_3_AND_7_YEARS;
            case Storage.MORE_7_YEARS -> carAge = CarAge.MORE_7_YEARS;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage getEngineTypeMessage(Message message) {
        cleanBooleans(4);
        switch (message.getText()) {
            case Storage.GASOLINE -> typeOfEngine = TypeOfEngine.GASOLINE;
            case Storage.ELECTRIC -> typeOfEngine = TypeOfEngine.ELECTRIC;
        }
        return createSendMessage(message.getChatId().toString());
    }

    private SendMessage getEngineVolumeMessage(Message message) {
        cleanBooleans(5);
        switch (message.getText()) {
            case Storage.VOLUME_LESS_1000 -> volumeOfEngine = VolumeOfEngine.LESS_1000;
            case Storage.VOLUME_BETWEEN_1000_2000 -> volumeOfEngine = VolumeOfEngine.BETWEEN_1000_AND_2000;
            case Storage.VOLUME_BETWEEN_2000_3000 -> volumeOfEngine = VolumeOfEngine.BETWEEN_2000_AND_3000;
            case Storage.VOLUME_BETWEEN_3000_3500 -> volumeOfEngine = VolumeOfEngine.BETWEEN_3000_AND_3500;
            case Storage.VOLUME_MORE_3500 -> volumeOfEngine = VolumeOfEngine.MORE_3500;
        }
        return createSendMessage(message.getChatId().toString());
    }

    private String createSummaryString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createUsersChoiceString());

        if (CountryOrigin.EAES.equals(countryOrigin)) {
            if (ownersType == null) {
                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PHYSICAL_OR_JURIDICAL));
            } else {
                if (carAge == null) {
                    stringBuilder.append(stringBuilderAppender(".", "\n", Storage.AGE_OF_AUTO));
                } else {
                    stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PRICE_STRING));
                }
            }
        }

        if (CountryOrigin.OTHER.equals(countryOrigin)) {
            if (ownersType == null) {
                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PHYSICAL_OR_JURIDICAL));
            } else {
                if (ownersType.equals(OwnersType.PHYSICAL)) {
                    if (carAge == null) {
                        stringBuilder.append(stringBuilderAppender(".", "\n", Storage.AGE_OF_AUTO));
                    } else {
                        stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PRICE_STRING));
                    }
                }
                if (ownersType.equals(OwnersType.JURIDICAL)) {
                    if (typeOfEngine != null) {
                        stringBuilder.append(stringBuilderAppender(".", "\n", Storage.GAS_OR_ELECTRIC_ENGINE));
                    } else {
                        if (typeOfEngine.equals(TypeOfEngine.GASOLINE)) {
                            if (carAge == null) {
                                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.AGE_OF_AUTO));
                            } else {
                                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.PRICE_STRING));
                            }
                        } else {
                            if (volumeOfEngine == null) {
                                stringBuilder.append(stringBuilderAppender(".", "\n", Storage.GAS_ENGINE_VOLUME));
                            } else {
                                if (carAge == null) {
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
        if (countryOrigin != null) {
            if (CountryOrigin.EAES.equals(countryOrigin)) {
                sb.append(Storage.EAES_STRING);
            } else {
                sb.append(Storage.OTHER_COUNTRIES_STRING);
            }

            if (ownersType.equals(OwnersType.PHYSICAL)) {
                sb.append(Storage.PHYSICAL_STRING);
            } else if (ownersType.equals(OwnersType.JURIDICAL)) {
                sb.append(Storage.JURIDICAL_STRING);
            }


            if (carAge != null) {
                if (carAge.equals(CarAge.LESS_3_YEARS)) {
                    sb.append(Storage.LESS_3_YEARS_STRING);
                } else {
                    if (carAge.equals(CarAge.BETWEEN_3_AND_7_YEARS)) {
                        sb.append(Storage.BETWEEN_3_AND_7_YEARS_STRING);
                    } else {
                        sb.append(Storage.MORE_7_YEARS_STRING);
                    }
                }
            }

            if (typeOfEngine != null) {
                if (typeOfEngine.equals(TypeOfEngine.ELECTRIC)) {
                    sb.append(Storage.ELECTRIC_STRING);
                } else {
                    sb.append(Storage.GAS_STRING);
                }
            }

            if (volumeOfEngine != null) {
                if (volumeOfEngine.equals(VolumeOfEngine.LESS_1000)) {
                    sb.append(Storage.VOLUME_LESS_1000_STRING);
                } else {
                    if (volumeOfEngine.equals(VolumeOfEngine.BETWEEN_1000_AND_2000)) {
                        sb.append(Storage.VOLUME_BETWEEN_1000_2000_STRING);
                    } else {
                        if (volumeOfEngine.equals(VolumeOfEngine.BETWEEN_2000_AND_3000)) {
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
//        if (stepCleaner >= 1) {
//            countryOrigin = null;
//        }
//        if (stepCleaner >= 2) {
//            isPhysical = false;
//            isJuridical = false;
//        }
//        if (stepCleaner >= 3) {
//            is3Years = false;
//            is3And7Years = false;
//            isMore7Years = false;
//        }
//
//        if (stepCleaner >= 4) {
//            isElectric = false;
//            isGas = false;
//            isLess1000 = false;
//            isBetween1000and2000 = false;
//            isBetween2000and3000 = false;
//            isBetween3000and3500 = false;
//            isMore3500 = false;
//        }
//
//        if (stepCleaner >= 5) {
//            isLess1000 = false;
//            isBetween1000and2000 = false;
//            isBetween2000and3000 = false;
//            isBetween3000and3500 = false;
//            isMore3500 = false;
//            is3Years = false;
//            is3And7Years = false;
//            isMore7Years = false;
//        }
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
