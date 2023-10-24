package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;

public class MessagesCreator {
    private final BundleResourcesServant bundle;
    private final CalculatorPassenger calculator;
    //private final UserProgress userProgress;

    private MessagesCreator() {
        bundle = BundleResourcesServant.getInstance();
        calculator = CalculatorPassenger.getInstance();
        //userProgress = UserProgress.getInstance();
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

    public String getResultAndFarewell(UserProgress userProgress) {
        return stringBuilderAppender("." +
                        "\n" +
                        bundle.getString("answers.summary.price") + " " + calculator.calculate(userProgress) + " " +
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

    public String getUserChoice(UserProgress userProgress) {
        StringBuilder sb = new StringBuilder();
        sb.append(bundle.getString("answers.summary.beginning"));

        switch (userProgress.getCountryOrigin()) {
            case null -> {
            }
            case EAES -> sb.append(bundle.getString("answers.summary.eaes"));
            case OTHER -> sb.append(bundle.getString("answers.summary.other"));
        }

        switch (userProgress.getOwnersType()) {
            case null -> {
            }
            case PHYSICAL -> sb.append(bundle.getString("answers.summary.physical"));
            case JURIDICAL -> sb.append(bundle.getString("answers.summary.juridical"));
        }

        switch (userProgress.getCarAge()) {
            case null -> {
            }
            case LESS_3_YEARS -> sb.append(bundle.getString("answers.summary.less.3"));
            case BETWEEN_3_AND_7_YEARS -> sb.append(bundle.getString("answers.summary.between.3.and.7"));
            case MORE_7_YEARS -> sb.append(bundle.getString("answers.summary.older.7"));
        }

        switch (userProgress.getTypeOfEngine()) {
            case null -> {
            }
            case GASOLINE -> sb.append(bundle.getString("answers.summary.gas"));
            case ELECTRIC -> sb.append(bundle.getString("answers.summary.electro"));
        }

        switch (userProgress.getVolumeOfEngine()) {
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
    public String getCountryOrigin(UserProgress userProgress) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getUserChoice(userProgress));
        switch (userProgress.getCountryOrigin()) {
            case EAES -> stringBuilder.append(getOptionsForEaes(userProgress));
            case OTHER -> stringBuilder.append(getOptionsForOtherCountries(userProgress));
        }
        return stringBuilder.toString();
    }

    private String getOptionsForOtherCountries(UserProgress userProgress) {
        String resultString;
        switch (userProgress.getOwnersType()) {
            case PHYSICAL ->
                    resultString = getOptionsForOtherCountriesPhysical(userProgress);
            case JURIDICAL ->
                    resultString = getOptionsForOtherCountriesJuridical(userProgress);
            case null -> resultString = getTypeOfOwner();
        }
        return resultString;
    }

    private String getOptionsForEaes(UserProgress userProgress) {
        String resultString;
        if (userProgress.getOwnersType() == null) {
            resultString = getTypeOfOwner();
        } else {
            if (userProgress.getCarAge() == null) {
                resultString = getAgeAuto();
            } else {
                resultString = getResultAndFarewell(userProgress);
            }
        }
        return resultString;
    }

    private String getOptionsForOtherCountriesPhysical(UserProgress userProgress) {
        return userProgress.getCarAge() == null ? getAgeAuto() : getResultAndFarewell(userProgress);
    }

    private String getOptionsForOtherCountriesJuridical(UserProgress userProgress) {
        String resultString;
        switch (userProgress.getTypeOfEngine()) {
            case null -> resultString = getTypeOfEngine();
            case GASOLINE ->
                    resultString = checkForGasolineAuoAge(userProgress);
            case ELECTRIC ->
                    resultString = checkForElectricAutoAge(userProgress);
        }
        return resultString;
    }

    private String checkForGasolineAuoAge(UserProgress userProgress) {
        String resultString;
        if (userProgress.getVolumeOfEngine() == null) {
            resultString = getEngineVolume();
        } else {
            if (userProgress.getCarAge() == null) {
                resultString = getAgeAuto();
            } else {
                resultString = getResultAndFarewell(userProgress);
            }
        }
        return resultString;
    }

    private String checkForElectricAutoAge(UserProgress userProgress) {
        return userProgress.getCarAge() == null ? getAgeAuto() : getResultAndFarewell(userProgress);
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
