package by.custom.utilcalculator.service;

import by.custom.utilcalculator.repository.resources.Commands;
import by.custom.utilcalculator.repository.steps.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessagesCreator {
    private final BundleResourcesServant bundle;
    private final CalculatorPassenger calculator;

    private MessagesCreator() {
        bundle = BundleResourcesServant.getInstance();
        calculator = CalculatorPassenger.getInstance();
    }

    public static MessagesCreator getInstance() {
        return MessagesCreatorHolder.MESSAGES_CREATOR;
    }

    public String getGreeting() {
        return stringBuilderAppender(bundle.getString("GREETING_TEXT"), "\n",
                Commands.EAES, " ", bundle.getString("EAES_DETAILS"), "\n",
                Commands.OTHER_COUNTRIES, " ", bundle.getString("OTHER_COUNTRIES_DETAILS"));
    }

    public String getTypeOfEngine() {
        return stringBuilderAppender(".", "\n", bundle.getString("GAS_OR_ELECTRIC_ENGINE"),
                Commands.GASOLINE_TYPE_ENGINE, " ", bundle.getString("GASOLINE_ENGINE_DETAILS"),
                Commands.ELECTRIC_TYPE_ENGINE, " ", bundle.getString("ELECTRIC_ENGINE_DETAILS"));
    }

    public String getEngineVolume() {
        return stringBuilderAppender(".", "\n", bundle.getString("GAS_ENGINE_VOLUME"), "\n",
                Commands.VOLUME_LESS_1000_CM, " ", bundle.getString("VOLUME_LESS_1000_DETAILS"), "\n",
                Commands.VOLUME_BETWEEN_1000_2000_CM, " ", bundle.getString("VOLUME_BETWEEN_1000_2000_DETAILS"), "\n",
                Commands.VOLUME_BETWEEN_2000_3000_CM, " ", bundle.getString("VOLUME_BETWEEN_2000_3000_DETAILS"), "\n",
                Commands.VOLUME_BETWEEN_3000_3500_CM, " ", bundle.getString("VOLUME_BETWEEN_3000_3500_DETAILS"), "\n",
                Commands.VOLUME_MORE_3500_CM, " ", bundle.getString("VOLUME_MORE_3500_DETAILS"));
    }

    public String getAgeAuto() {
        return stringBuilderAppender(".", "\n", bundle.getString("AGE_OF_AUTO"),
                Commands.LESS_3_YEARS_AGE, " ", bundle.getString("LESS_3_YEARS_DETAILS"),
                Commands.BETWEEN_3_AND_7_YEARS_AGE, " ", bundle.getString("BETWEEN_3_AND_7_YEARS_DETAILS"),
                Commands.MORE_7_YEARS_AGE, " ", bundle.getString("MORE_7_YEARS_DETAILS"));
    }

    public String getResultAndFarewell(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        return stringBuilderAppender("." +
                "\n" +
                bundle.getString("PRICE") + calculator.calculate(
                countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge
        ) +
                bundle.getString("BYN") +
                bundle.getString("FAREWELL"));
    }

    public String getTypeOfOwner() {
        return stringBuilderAppender(".", "\n", bundle.getString("PHYSICAL_OR_JURIDICAL"),
                Commands.PHYSICAL_PERSON, " ", bundle.getString("PHYSICAL_PERSON_DETAILS"),
                Commands.JURIDICAL_PERSON, " ", bundle.getString("JURIDICAL_PERSON_DETAILS"));
    }

    public String getSorry() {
        return bundle.getString("SORRY");
    }

    public String getUserChoice(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        StringBuilder sb = new StringBuilder();
        sb.append(bundle.getString("YOUR_CHOICE"));

        switch (countryOrigin) {
            case null -> {
            }
            case EAES -> sb.append(bundle.getString("EAES"));
            case OTHER -> sb.append(bundle.getString("OTHER_COUNTRIES"));
        }

        switch (ownersType) {
            case null -> {
            }
            case PHYSICAL -> sb.append(bundle.getString("PHYSICAL_PERSON"));
            case JURIDICAL -> sb.append(bundle.getString("JURIDICAL_PERSON"));
        }

        switch (carAge) {
            case null -> {
            }
            case LESS_3_YEARS -> sb.append(bundle.getString("LESS_3_YEARS_OLD"));
            case BETWEEN_3_AND_7_YEARS -> sb.append(bundle.getString("BETWEEN_3_AND_7_YEARS_OLD"));
            case MORE_7_YEARS -> sb.append(bundle.getString("MORE_7_YEARS_OLD"));
        }

        switch (typeOfEngine) {
            case null -> {
            }
            case GASOLINE -> sb.append(bundle.getString("GASOLINE_OR_HYBRID_ENGINE"));
            case ELECTRIC -> sb.append(bundle.getString("ELECTRIC_ENGINE"));
        }

        switch (volumeOfEngine) {
            case null -> {
            }
            case LESS_1000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("VOLUME_LESS_1000_DETAILS")));
            }
            case BETWEEN_1000_AND_2000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("VOLUME_BETWEEN_1000_2000_DETAILS")));
            }
            case BETWEEN_2000_AND_3000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("VOLUME_BETWEEN_2000_3000_DETAILS")));
            }
            case BETWEEN_3000_AND_3500 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("VOLUME_BETWEEN_3000_3500_DETAILS")));
            }
            case MORE_3500 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("VOLUME_MORE_3500_DETAILS")));
            }
        }
        return sb.toString();
    }

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
            case GASOLINE -> resultString = checkForGasolineAuoAge(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
            case ELECTRIC -> resultString = checkForElectricAutoAge(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
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

    public String getProperMessage(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        return getCountryOrigin(countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge);
    }

    private static class MessagesCreatorHolder {
        private static final MessagesCreator MESSAGES_CREATOR = new MessagesCreator();
    }
}
