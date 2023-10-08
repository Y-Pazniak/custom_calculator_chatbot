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
    private final CalculatorPassenger calculator;

    //singleton Bot creation
    private static class BotHolder {
        private static final Bot bot = new Bot();
    }

    //bot's name
    private static final String BOT_NAME = "Бесплатный калькулятор утильсбора РБ";

    //private constructor to avoid wrong bot's creation
    private Bot() {
        calculator = CalculatorPassenger.getInstance();
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
        cleanBooleans(5);
        switch (inputMessage.getText()) {
            case Storage.LESS_3_YEARS -> carAge = CarAge.LESS_3_YEARS;
            case Storage.BETWEEN_3_AND_7_YEARS -> carAge = CarAge.BETWEEN_3_AND_7_YEARS;
            case Storage.MORE_7_YEARS -> carAge = CarAge.MORE_7_YEARS;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage getEngineTypeMessage(Message message) {
        cleanBooleans(3);
        switch (message.getText()) {
            case Storage.GASOLINE -> typeOfEngine = TypeOfEngine.GASOLINE;
            case Storage.ELECTRIC -> typeOfEngine = TypeOfEngine.ELECTRIC;
        }
        return createSendMessage(message.getChatId().toString());
    }

    private SendMessage getEngineVolumeMessage(Message message) {
        cleanBooleans(4);
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
        switch (countryOrigin) {
            case EAES -> stringBuilder.append(getStringOptionsForEaes());
            case OTHER -> stringBuilder.append(getStringOptionsForOtherCountries());
        }
        return stringBuilder.toString();
    }

    private String getStringOptionsForOtherCountries() {
        StringBuilder sb = new StringBuilder();
        switch (ownersType) {
            case PHYSICAL -> sb.append(getStringOptionsForOtherCountriesPhysical());
            case JURIDICAL -> sb.append(getStringOptionsForOtherCountriesJuridical());
            case null -> sb.append(stringBuilderAppender(getStringOptionsTypeOfOwner()));
        }

        return sb.toString();
    }

    private String getStringOptionsForOtherCountriesJuridical() {
        StringBuilder sb = new StringBuilder();
        switch (typeOfEngine) {
            case null -> sb.append(stringBuilderAppender(".", "\n", Storage.GAS_OR_ELECTRIC_ENGINE));
            case GASOLINE -> sb.append(getStringOptionsForEngineVolume());
            case ELECTRIC -> sb.append(checkForElectricAutoAge());
        }
        return sb.toString();
    }

    private String checkForElectricAutoAge() {
        if (carAge == null) {
            return getStringOptionsForAgeAuto();
        } else {
            return getStringOptionsForPrice();
        }
    }

    private String getStringOptionsForEngineVolume() {
        StringBuilder sb = new StringBuilder();
        if (volumeOfEngine == null) {
            sb.append(stringBuilderAppender(".", "\n", Storage.GAS_ENGINE_VOLUME));
        } else {
            if (carAge == null) {
                sb.append(getStringOptionsForAgeAuto());
            } else {
                sb.append(getStringOptionsForPrice());
            }
        }
        return sb.toString();
    }

    private String getStringOptionsForAgeAuto() {
        return "." +
                "\n" +
                Storage.AGE_OF_AUTO;
    }

    private String getStringOptionsForPrice() {
        return "." +
                "\n" +
                Storage.PRICE_STRING + calculator.calculate(
                countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge
        ) +
                Storage.BYN_STRING +
                Storage.FAREWELL_STRING;
    }

    private String getStringOptionsTypeOfOwner() {
        return "." +
                "\n" +
                Storage.PHYSICAL_OR_JURIDICAL;
    }

    private String getStringOptionsForOtherCountriesPhysical() {
        StringBuilder sb = new StringBuilder();
        if (carAge == null) {
            sb.append(stringBuilderAppender(getStringOptionsForAgeAuto()));
        } else {
            sb.append(stringBuilderAppender(getStringOptionsForPrice()));
        }
        return sb.toString();
    }

    private String getStringOptionsForEaes() {
        StringBuilder sb = new StringBuilder();
        if (ownersType == null) {
            sb.append(stringBuilderAppender(getStringOptionsTypeOfOwner()));
        } else {
            if (carAge == null) {
                sb.append(stringBuilderAppender(getStringOptionsForAgeAuto()));
            } else {
                sb.append(stringBuilderAppender(getStringOptionsForPrice()));
            }
        }
        return sb.toString();
    }

    private String createUsersChoiceString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Storage.YOUR_CHOICE_STRING);

        switch (countryOrigin) {
            case null -> {
            }
            case EAES -> sb.append(Storage.EAES_STRING);
            case OTHER -> sb.append(Storage.OTHER_COUNTRIES_STRING);
        }

        switch (ownersType) {
            case null -> {
            }
            case PHYSICAL -> sb.append(Storage.PHYSICAL_STRING);
            case JURIDICAL -> sb.append(Storage.JURIDICAL_STRING);
        }

        switch (carAge) {
            case null -> {
            }
            case LESS_3_YEARS -> sb.append(Storage.LESS_3_YEARS_STRING);
            case BETWEEN_3_AND_7_YEARS -> sb.append(Storage.BETWEEN_3_AND_7_YEARS_STRING);
            case MORE_7_YEARS -> sb.append(Storage.MORE_7_YEARS_STRING);
        }

        switch (typeOfEngine) {
            case null -> {
            }
            case GASOLINE -> sb.append(Storage.GAS_STRING);
            case ELECTRIC -> sb.append(Storage.ELECTRIC_STRING);
        }

        switch (volumeOfEngine) {
            case null -> {
            }
            case LESS_1000 -> {
                sb.append(",");
                sb.append(Storage.VOLUME_LESS_1000_STRING);
            }
            case BETWEEN_1000_AND_2000 -> {
                sb.append(",");
                sb.append(Storage.VOLUME_BETWEEN_1000_2000_STRING);
            }
            case BETWEEN_2000_AND_3000 -> {
                sb.append(",");
                sb.append(Storage.VOLUME_BETWEEN_2000_3000_STRING);
            }
            case BETWEEN_3000_AND_3500 -> {
                sb.append(",");
                sb.append(Storage.VOLUME_BETWEEN_3000_3500_STRING);
            }
            case MORE_3500 -> {
                sb.append(",");
                sb.append(Storage.VOLUME_MORE_3500_STRING);
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

    private String stringBuilderAppender(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
}
