package by.custom.utilcalculator.service;

import by.custom.utilcalculator.directory.BotEntity;
import by.custom.utilcalculator.directory.resources.Commands;
import by.custom.utilcalculator.directory.steps.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessagesManager {
    private final BundleResourcesServant bundle;
    private final CalculatorPassenger calculator;

    private MessagesManager() {
        bundle = BundleResourcesServant.getInstance();
        calculator = CalculatorPassenger.getInstance();
    }

    public static MessagesManager getInstance() {
        return MessagesCreatorHolder.MESSAGES_CREATOR;
    }

    public String getGreeting() {
        return stringBuilderAppender(bundle.getString("questions.users.greeting"), "\n",
                Commands.EAES, " ", bundle.getString("answers.details.eaes"), "\n",
                Commands.OTHER_COUNTRIES, " ", bundle.getString("answers.details.other"));
    }

    public String getTypeOfEngine() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.type.engine"), "\n",
                Commands.GASOLINE_TYPE_ENGINE, " ", bundle.getString("answers.details.gas.engine"),"\n",
                Commands.ELECTRIC_TYPE_ENGINE, " ", bundle.getString("answers.details.electric.engine"));
    }

    public String getEngineVolume() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.volume.engine"), "\n",
                Commands.VOLUME_LESS_1000_CM, " ", bundle.getString("answers.details.less.1000"), "\n",
                Commands.VOLUME_BETWEEN_1000_2000_CM, " ", bundle.getString("answers.details.between.1000.2000"), "\n",
                Commands.VOLUME_BETWEEN_2000_3000_CM, " ", bundle.getString("answers.details.between.2000.3000"), "\n",
                Commands.VOLUME_BETWEEN_3000_3500_CM, " ", bundle.getString("answers.details.between.3000.3500"), "\n",
                Commands.VOLUME_MORE_3500_CM, " ", bundle.getString("answers.details.more.3500"));
    }

    public String getAgeAuto() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.age.auto"),"\n",
                Commands.LESS_3_YEARS_AGE, " ", bundle.getString("answers.details.before.3"),"\n",
                Commands.BETWEEN_3_AND_7_YEARS_AGE, " ", bundle.getString("answers.details.between.3.and.7"),"\n",
                Commands.MORE_7_YEARS_AGE, " ", bundle.getString("answers.details.more.7"));
    }

    public String getResultAndFarewell(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        return stringBuilderAppender("." +
                "\n" +
                bundle.getString("answers.summary.price") + calculator.calculate(
                countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge
        ) + " " +
                bundle.getString("answers.summary.byn") + "\n",
                bundle.getString("answers.summary.goodbye.add.info"));
    }

    public String getTypeOfOwner() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.physical.or.juridical"),"\n",
                Commands.PHYSICAL_PERSON, " ", bundle.getString("answers.details.physical"),"\n",
                Commands.JURIDICAL_PERSON, " ", bundle.getString("answers.details.juridical"));
    }

    public String getSorry() {
        return bundle.getString("answers.sorry");
    }

    public String getUserChoice(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        StringBuilder sb = new StringBuilder();
        sb.append(bundle.getString("answers.summary.beginning"));

        switch (countryOrigin) {
            case null -> {
            }
            case EAES -> sb.append(bundle.getString("answers.summary.eaes"));
            case OTHER -> sb.append(bundle.getString("answers.summary.other"));
        }

        switch (ownersType) {
            case null -> {
            }
            case PHYSICAL -> sb.append(bundle.getString("answers.summary.physical"));
            case JURIDICAL -> sb.append(bundle.getString("answers.summary.juridical"));
        }

        switch (carAge) {
            case null -> {
            }
            case LESS_3_YEARS -> sb.append(bundle.getString("answers.summary.less.3"));
            case BETWEEN_3_AND_7_YEARS -> sb.append(bundle.getString("answers.summary.between.3.and.7"));
            case MORE_7_YEARS -> sb.append(bundle.getString("answers.summary.older.7"));
        }

        switch (typeOfEngine) {
            case null -> {
            }
            case GASOLINE -> sb.append(bundle.getString("answers.summary.gas"));
            case ELECTRIC -> sb.append(bundle.getString("answers.summary.electro"));
        }

        switch (volumeOfEngine) {
            case null -> {
            }
            case LESS_1000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.less.1000")));
            }
            case BETWEEN_1000_AND_2000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.between.1000.2000")));
            }
            case BETWEEN_2000_AND_3000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.between.2000.3000")));
            }
            case BETWEEN_3000_AND_3500 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.between.3000.3500")));
            }
            case MORE_3500 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.more.3500")));
            }
        }
        return sb.toString();
    }

    //the first method which starts checking user's commands and building the message for user
    public String getCountryOrigin(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getUserChoice(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge));
        switch (countryOrigin) {
            case EAES ->
                    stringBuilder.append(getOptionsForEaes(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge));
            case OTHER ->
                    stringBuilder.append(getOptionsForOtherCountries(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge));
        }
        return stringBuilder.toString();
    }

    private String getOptionsForOtherCountries(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        String resultString;
        switch (ownersType) {
            case PHYSICAL ->
                    resultString = getOptionsForOtherCountriesPhysical(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
            case JURIDICAL ->
                    resultString = getOptionsForOtherCountriesJuridical(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
            case null -> resultString = getTypeOfOwner();
        }
        return resultString;
    }

    private String getOptionsForEaes(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        String resultString;
        if (ownersType == null) {
            resultString = getTypeOfOwner();
        } else {
            if (carAge == null) {
                resultString = getAgeAuto();
            } else {
                resultString = getResultAndFarewell(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
            }
        }
        return resultString;
    }

    private String getOptionsForOtherCountriesPhysical(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        return carAge == null ? getAgeAuto() : getResultAndFarewell(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
    }

    private String getOptionsForOtherCountriesJuridical(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        String resultString;
        switch (typeOfEngine) {
            case null -> resultString = getTypeOfEngine();
            case GASOLINE ->
                    resultString = checkForGasolineAuoAge(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
            case ELECTRIC ->
                    resultString = checkForElectricAutoAge(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
        }
        return resultString;
    }

    private String checkForGasolineAuoAge(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        String resultString;
        if (volumeOfEngine == null) {
            resultString = getEngineVolume();
        } else {
            if (carAge == null) {
                resultString = getAgeAuto();
            } else {
                resultString = getResultAndFarewell(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
            }
        }
        return resultString;
    }

    private String checkForElectricAutoAge(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        return carAge == null ? getAgeAuto() : getResultAndFarewell(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
    }

    public SendMessage createSendMessage(String chatId, CountryOrigin countryOrigin,
                                         OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(getCountryOrigin(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge));
        return sendMessage;
    }

    SendMessage getGreetingMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(getGreeting());
        sendMessage.setChatId(message.getChatId().toString());
        return sendMessage;
    }

    public SendMessage getSorryMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(getSorry());
        sendMessage.setChatId(message.getChatId().toString());
        return sendMessage;
    }

    private String stringBuilderAppender(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    private String trimFirstAndLastLetters(String toTrim) {
        return toTrim.substring(1, toTrim.length() - 1);
    }

    private static class MessagesCreatorHolder {
        private static final MessagesManager MESSAGES_CREATOR = new MessagesManager();
    }
}
