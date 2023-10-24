package by.custom.utilcalculator.service;

import by.custom.utilcalculator.directory.UserProgress;
import by.custom.utilcalculator.directory.resources.Command;
import by.custom.utilcalculator.directory.steps.*;

public class MessagesCreator {
    private final BundleResourcesServant bundle;
    private final CalculatorPassenger calculator;
    private final UserProgress botEntity;

    private MessagesCreator() {
        bundle = BundleResourcesServant.getInstance();
        calculator = CalculatorPassenger.getInstance();
        botEntity = UserProgress.getInstance();
    }

    public static MessagesCreator getInstance() {
        return MessagesCreatorHolder.MESSAGES_CREATOR;
    }

    public String getGreeting() {
        return stringBuilderAppender(bundle.getString("questions.users.greeting"), "\n",
                Command.EAES, " ", bundle.getString("answers.details.eaes"), "\n",
                Command.OTHER_COUNTRIES, " ", bundle.getString("answers.details.other"));
    }

    public String getTypeOfEngine() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.type.engine"), "\n",
                Command.GASOLINE_TYPE_ENGINE, " ", bundle.getString("answers.details.gas.engine"), "\n",
                Command.ELECTRIC_TYPE_ENGINE, " ", bundle.getString("answers.details.electric.engine"));
    }

    public String getEngineVolume() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.volume.engine"), "\n",
                Command.VOLUME_LESS_1000_CM, " ", bundle.getString("answers.details.less.1000"), "\n",
                Command.VOLUME_BETWEEN_1000_2000_CM, " ", bundle.getString("answers.details.between.1000.2000"), "\n",
                Command.VOLUME_BETWEEN_2000_3000_CM, " ", bundle.getString("answers.details.between.2000.3000"), "\n",
                Command.VOLUME_BETWEEN_3000_3500_CM, " ", bundle.getString("answers.details.between.3000.3500"), "\n",
                Command.VOLUME_MORE_3500_CM, " ", bundle.getString("answers.details.more.3500"));
    }

    public String getAgeAuto() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.age.auto"), "\n",
                Command.LESS_3_YEARS_AGE, " ", bundle.getString("answers.details.before.3"), "\n",
                Command.BETWEEN_3_AND_7_YEARS_AGE, " ", bundle.getString("answers.details.between.3.and.7"), "\n",
                Command.MORE_7_YEARS_AGE, " ", bundle.getString("answers.details.more.7"));
    }

    public String getResultAndFarewell(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        return stringBuilderAppender("." +
                        "\n" +
                        bundle.getString("answers.summary.price") + " " + calculator.calculate(
                        countryOrigin, ownersType, typeOfEngine, volumeOfEngine, carAge
                ) + " " +
                        bundle.getString("answers.summary.byn") + "\n",
                bundle.getString("answers.summary.goodbye.add.info"));
    }

    public String getTypeOfOwner() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.physical.or.juridical"), "\n",
                Command.PHYSICAL_PERSON, " ", bundle.getString("answers.details.physical"), "\n",
                Command.JURIDICAL_PERSON, " ", bundle.getString("answers.details.juridical"));
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
    public String getCountryOrigin() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getUserChoice(botEntity.getCountryOrigin(), botEntity.getOwnersType(), botEntity.getTypeOfEngine(), botEntity.getVolumeOfEngine(), botEntity.getCarAge()));
        switch (botEntity.getCountryOrigin()) {
            case EAES ->
                    stringBuilder.append(getOptionsForEaes(botEntity.getCountryOrigin(), botEntity.getOwnersType(), botEntity.getTypeOfEngine(), botEntity.getVolumeOfEngine(), botEntity.getCarAge()));
            case OTHER ->
                    stringBuilder.append(getOptionsForOtherCountries(botEntity.getCountryOrigin(), botEntity.getOwnersType(), botEntity.getTypeOfEngine(), botEntity.getVolumeOfEngine(), botEntity.getCarAge()));
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
        private static final MessagesCreator MESSAGES_CREATOR = new MessagesCreator();
    }
}
