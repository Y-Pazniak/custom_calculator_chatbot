package by.custom.utilcalculator.service;

import by.custom.utilcalculator.repository.resources.Answers;
import by.custom.utilcalculator.repository.resources.Commands;
import by.custom.utilcalculator.repository.resources.Questions;
import by.custom.utilcalculator.repository.steps.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        return
                //System.getenv("custom_by_utilsbor_bot");
                "6490861114:AAHVFOpQANIn_EQr1A1PcLrCMNzVlGkJIig";
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
            case Commands.START -> sendMessage = getGreetingMessage(message);
            case Commands.EAES, Commands.OTHER_COUNTRIES -> sendMessage = getOriginCarMessage(message);
            case Commands.PHYSICAL_PERSON, Commands.JURIDICAL_PERSON -> sendMessage = getTypeOfOwnerMessage(message);
            case Commands.LESS_3_YEARS_AGE, Commands.BETWEEN_3_AND_7_YEARS_AGE, Commands.MORE_7_YEARS_AGE ->
                    sendMessage = getAutoAgeMessage(message);
            case Commands.GASOLINE_TYPE_ENGINE, Commands.ELECTRIC_TYPE_ENGINE -> sendMessage = getEngineTypeMessage(message);
            case Commands.VOLUME_LESS_1000_CM, Commands.VOLUME_BETWEEN_1000_2000_CM, Commands.VOLUME_BETWEEN_2000_3000_CM,
                    Commands.VOLUME_BETWEEN_3000_3500_CM, Commands.VOLUME_MORE_3500_CM ->
                    sendMessage = getEngineVolumeMessage(message);
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
        sendMessage.setText(Questions.GREETING_TEXT);
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
            case Commands.EAES -> countryOrigin = CountryOrigin.EAES;
            case Commands.OTHER_COUNTRIES -> countryOrigin = CountryOrigin.OTHER;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage getTypeOfOwnerMessage(Message inputMessage) {
        cleanBooleans(2);
        switch (inputMessage.getText()) {
            case Commands.JURIDICAL_PERSON -> ownersType = OwnersType.JURIDICAL;
            case Commands.PHYSICAL_PERSON -> ownersType = OwnersType.PHYSICAL;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage getAutoAgeMessage(Message inputMessage) {
        cleanBooleans(5);
        switch (inputMessage.getText()) {
            case Commands.LESS_3_YEARS_AGE -> carAge = CarAge.LESS_3_YEARS;
            case Commands.BETWEEN_3_AND_7_YEARS_AGE -> carAge = CarAge.BETWEEN_3_AND_7_YEARS;
            case Commands.MORE_7_YEARS_AGE -> carAge = CarAge.MORE_7_YEARS;
        }
        return createSendMessage(inputMessage.getChatId().toString());
    }

    private SendMessage getEngineTypeMessage(Message message) {
        cleanBooleans(3);
        switch (message.getText()) {
            case Commands.GASOLINE_TYPE_ENGINE -> typeOfEngine = TypeOfEngine.GASOLINE;
            case Commands.ELECTRIC_TYPE_ENGINE -> typeOfEngine = TypeOfEngine.ELECTRIC;
        }
        return createSendMessage(message.getChatId().toString());
    }

    private SendMessage getEngineVolumeMessage(Message message) {
        cleanBooleans(4);
        switch (message.getText()) {
            case Commands.VOLUME_LESS_1000_CM -> volumeOfEngine = VolumeOfEngine.LESS_1000;
            case Commands.VOLUME_BETWEEN_1000_2000_CM -> volumeOfEngine = VolumeOfEngine.BETWEEN_1000_AND_2000;
            case Commands.VOLUME_BETWEEN_2000_3000_CM -> volumeOfEngine = VolumeOfEngine.BETWEEN_2000_AND_3000;
            case Commands.VOLUME_BETWEEN_3000_3500_CM -> volumeOfEngine = VolumeOfEngine.BETWEEN_3000_AND_3500;
            case Commands.VOLUME_MORE_3500_CM -> volumeOfEngine = VolumeOfEngine.MORE_3500;
        }
        return createSendMessage(message.getChatId().toString());
    }

    private String getCountryOrigin() {
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
            case null -> sb.append(stringBuilderAppender(".", "\n", Questions.GAS_OR_ELECTRIC_ENGINE));
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
            sb.append(stringBuilderAppender(".", "\n", Questions.GAS_ENGINE_VOLUME));
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
                Questions.AGE_OF_AUTO;
    }

    private String getStringOptionsForPrice() {
        return "." +
                "\n" +
                Answers.PRICE + calculator.calculate(
                countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge
        ) +
                Answers.BYN +
                Answers.FAREWELL;
    }

    private String getStringOptionsTypeOfOwner() {
        return "." +
                "\n" +
                Questions.PHYSICAL_OR_JURIDICAL;
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
        sb.append(Answers.YOUR_CHOICE);

        switch (countryOrigin) {
            case null -> {
            }
            case EAES -> sb.append(Answers.EAES);
            case OTHER -> sb.append(Answers.OTHER_COUNTRIES);
        }

        switch (ownersType) {
            case null -> {
            }
            case PHYSICAL -> sb.append(Answers.PHYSICAL_PERSON);
            case JURIDICAL -> sb.append(Answers.JURIDICAL_PERSON);
        }

        switch (carAge) {
            case null -> {
            }
            case LESS_3_YEARS -> sb.append(Answers.LESS_3_YEARS_OLD);
            case BETWEEN_3_AND_7_YEARS -> sb.append(Answers.BETWEEN_3_AND_7_YEARS_OLD);
            case MORE_7_YEARS -> sb.append(Answers.MORE_7_YEARS_OLD);
        }

        switch (typeOfEngine) {
            case null -> {
            }
            case GASOLINE -> sb.append(Answers.GASOLINE_OR_HYBRID_ENGINE);
            case ELECTRIC -> sb.append(Answers.ELECTRIC_ENGINE);
        }

        switch (volumeOfEngine) {
            case null -> {
            }
            case LESS_1000 -> {
                sb.append(",");
                sb.append(Answers.VOLUME_LESS_1000_CM);
            }
            case BETWEEN_1000_AND_2000 -> {
                sb.append(",");
                sb.append(Answers.VOLUME_BETWEEN_1000_2000_CM);
            }
            case BETWEEN_2000_AND_3000 -> {
                sb.append(",");
                sb.append(Answers.VOLUME_BETWEEN_2000_3000_CM);
            }
            case BETWEEN_3000_AND_3500 -> {
                sb.append(",");
                sb.append(Answers.VOLUME_BETWEEN_3000_3500_CM);
            }
            case MORE_3500 -> {
                sb.append(",");
                sb.append(Answers.VOLUME_MORE_3500_CM);
            }
        }
        return sb.toString();
    }

    private SendMessage createSendMessage(String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(getCountryOrigin());
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
