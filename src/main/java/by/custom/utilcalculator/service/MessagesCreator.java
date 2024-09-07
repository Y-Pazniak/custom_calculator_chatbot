package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.EngineType;
import by.custom.utilcalculator.domain.constants.steps.Step;

import static by.custom.utilcalculator.domain.constants.steps.EngineType.ELECTRIC;
import static by.custom.utilcalculator.domain.constants.steps.EngineType.GASOLINE;

public class MessagesCreator {
    private final BundleResourcesServant bundle;

    private MessagesCreator() {
        bundle = BundleResourcesServant.getInstance();
    }

    public static MessagesCreator getInstance() {
        return MessagesCreatorHolder.MESSAGES_CREATOR;
    }

    //this method builds next questions for user to interact with chatbot
    public String buildNextStepQuestion(final UserProgress userProgress) {
        final Step step = userProgress.getNextStep();
        switch (step) {
            case GENERAL_TRANSPORT_TYPE -> {
                return getGreeting();
            }
            case PARTICULAR_TRANSPORT_TYPE -> {
                return getTransportType();
            }
            case WEIGHT -> {
                return getTransportWeight(userProgress);
            }
            case ENGINE_TYPE -> {
                return getTypeOfEngine();
            }
            case ENGINE_VOLUME -> {
                return getEngineVolume(userProgress);
            }
            case COUNTRY_ORIGIN -> {
                return getCountryOrigin();
            }
            case OWNERS_TYPE -> {
                return getTypeOfOwner();
            }
            case TRUCK_UNIT_CLASS -> {
                return getTruckUnitClass();
            }

            case TRAILERS_O4_TYPE -> {
                return getO4TrailersTypes();
            }
            case AGE -> {
                return getAgeAuto();
            }
            case FAREWELL -> {
                return getResultAndFarewell(userProgress);
            }
        }
        return bundle.getString("answers.sorry");
    }

    private String getO4TrailersTypes() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.weight.n1_n3"), "\n",
                Command.TRAILERS_04_TYPE.getCommand(), " ", bundle.getString("answers.details.trailersO4"), "\n",
                Command.HALF_TRAILERS_04_TYPE.getCommand(), " ", bundle.getString("answers.details.halftrailersO4"));
    }

    private String getTruckUnitClass() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.truck.type"), "\n",
                Command.TRUCK_UNITS_6_CLASS.getCommand(), " ", bundle.getString("answers.details.truck.6_class"), "\n",
                Command.TRUCK_UNITS_OTHER.getCommand(), " ", bundle.getString("answers.details.truck.except_6"));
    }

    public String getGreeting() {
        return stringBuilderAppender(bundle.getString("questions.users.greeting"), "\n",
                Command.M1.getCommand(), " ", bundle.getString("answers.details.m1"), "\n",
                Command.BUSES_AND_TRUCKS.getCommand(), " ", bundle.getString("answers.details.except_m1"), "\n",
                Command.SELF_PROPELLED_VEHICLES.getCommand(), " ", bundle.getString("answers.details.trailers"));
    }

    public String getCountryOrigin() {
        return stringBuilderAppender("\n", bundle.getString("questions.users.country"), "\n",
                Command.EAES.getCommand(), " ", bundle.getString("answers.details.eaes"), "\n",
                Command.OTHER_COUNTRIES.getCommand(), " ", bundle.getString("answers.details.other"));
    }

    public String getTransportType() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.type.exceptM1"), "\n",
                Command.N1_N3.getCommand(), " ", bundle.getString("answers.details.n1_n3"), "\n",
                Command.M2_M3.getCommand(), " ", bundle.getString("answers.details.m2_m3"), "\n",
                Command.TRUCK_UNITS.getCommand(), " ", bundle.getString("answers.details.truck_units"), "\n",
                Command.TRAILERS_O4.getCommand(), " ", bundle.getString("answers.details.trailers04"));
    }

    public String getTransportWeight(final UserProgress userProgress) {
        switch (userProgress.getBusesOrTrucksType()) {
            case N1_N3 -> {
                return stringBuilderAppender(".", "\n", bundle.getString("questions.users.weight.n1_n3"), "\n",
                        Command.LESS_2_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.less_2_tons"), "\n",
                        Command.BETWEEN_2_5_AND_3_5_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_2d5_and_3d5_tons"), "\n",
                        Command.BETWEEN_3_5_AND_5_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_3d5_and_5_tons"), "\n",
                        Command.BETWEEN_5_AND_8_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_5_and_8_tons"), "\n",
                        Command.BETWEEN_8_AND_12_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_8_and_12_tons"), "\n",
                        Command.BETWEEN_12_AND_20_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_12_and_20_tons"), "\n",
                        Command.BETWEEN_20_AND_50_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_20_and_50_tons"));
            }
            case TRUCK_UNITS -> {
                return stringBuilderAppender(".", "\n", bundle.getString("questions.users.weight.n1_n3"), "\n",
                        Command.TRUCK_UNITS_12_20_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_12_and_20_tons"), "\n",
                        Command.TRUCK_UNITS_20_50_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_20_and_50_tons"));
            }
            case null, default -> {
            }
        }
        return "unknown weight or type";
    }

    public String getTypeOfEngine() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.type.engine"), "\n",
                Command.GASOLINE.getCommand(), " ", bundle.getString("answers.details.gas.engine"), "\n",
                Command.ELECTRIC.getCommand(), " ", bundle.getString("answers.details.electric.engine"));
    }

    public String getEngineVolume(final UserProgress userProgress) {
        switch (userProgress.getGeneralTransportType()) {
            case M1 -> {
                return stringBuilderAppender(".", "\n", bundle.getString("questions.users.volume.engine"), "\n",
                        Command.VOLUME_LESS_1000_CM.getCommand(), " ", bundle.getString("answers.details.less.1000"), "\n",
                        Command.VOLUME_BETWEEN_1000_2000_CM.getCommand(), " ", bundle.getString("answers.details.between.1000.2000"), "\n",
                        Command.VOLUME_BETWEEN_2000_3000_CM.getCommand(), " ", bundle.getString("answers.details.between.2000.3000"), "\n",
                        Command.VOLUME_BETWEEN_3000_3500_CM.getCommand(), " ", bundle.getString("answers.details.between.3000.3500"), "\n",
                        Command.VOLUME_MORE_3500_CM.getCommand(), " ", bundle.getString("answers.details.more.3500"));
            }
            case BUSES_AND_TRUCKS -> {
                return stringBuilderAppender(".", "\n", bundle.getString("questions.users.volume.engine"), "\n",
                        Command.VOLUME_LESS_2500_CM.getCommand(), " ", bundle.getString("answers.summary.volume.m2_m3.less_2500"), "\n",
                        Command.VOLUME_BETWEEN_2500_5000_CM.getCommand(), " ", bundle.getString("answers.summary.volume.m2_m3.between_2500_5000"), "\n",
                        Command.VOLUME_BETWEEN_5000_10000_CM.getCommand(), " ", bundle.getString("answers.summary.volume.m2_m3.between_5000_10000"), "\n",
                        Command.VOLUME_MORE_10000_CM.getCommand(), " ", bundle.getString("answers.summary.volume.m2_m3.more_10000"));
            }
            case null, default -> {
                return "unknown general transport type";
            }
        }
    }

    public String getAgeAuto() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.age.auto"), "\n",
                Command.LESS_3_YEARS_AGE.getCommand(), " ", bundle.getString("answers.details.before.3"), "\n",
                Command.MORE_THAN_3_YEARS_AGE.getCommand(), " ", bundle.getString("answers.details.between.3.and.7"), "\n");
    }

    public String getResultAndFarewell(final UserProgress userProgress) {
        return stringBuilderAppender("." +
                        "\n" +
                        bundle.getString("answers.summary.price") + " " + CalculatorRouter.calculate(userProgress) + " " +
                        bundle.getString("answers.summary.byn") + "\n",
                bundle.getString("answers.summary.goodbye.add.info"));
    }

    public String getTypeOfOwner() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.physical.or.juridical"), "\n",
                Command.PHYSICAL.getCommand(), " ", bundle.getString("answers.details.physical"), "\n",
                Command.JURIDICAL.getCommand(), " ", bundle.getString("answers.details.juridical"));
    }

    public String getSorry() {
        return bundle.getString("answers.sorry");
    }

    //this method builds summary string with user choices (just to show the user's path)
    public String getUserChoiceSequence(final UserProgress userProgress) {
        final StringBuilder sb = new StringBuilder();
        sb.append(bundle.getString("answers.summary.beginning"));

        switch (userProgress.getGeneralTransportType()) {
            case null -> {
            }
            case M1 -> sb.append(bundle.getString("answers.summary.m1"));
            case BUSES_AND_TRUCKS -> sb.append(bundle.getString("answers.summary.except_m1"));
            case SELF_PROPELLED_VEHICLES -> {
            }
        }

        switch (userProgress.getBusesOrTrucksType()) {
            case null -> {
            }
            case N1_N3 -> sb.append(bundle.getString("answers.summary.n1_n3"));
            case M2_M3 -> sb.append(bundle.getString("answers.summary.m2_m3"));
            case TRUCK_UNITS -> sb.append(bundle.getString("answers.summary.truck_unit"));
            case TRAILERS_O4 -> sb.append(bundle.getString("answers.summary.trailersO4"));
        }

        switch (userProgress.getWeight()) {
            case null -> {
            }
            case LESS_2_TONS -> sb.append(bundle.getString("answers.summary.weight.n1_n3.less_2_tons"));
            case BETWEEN_2_5_AND_3_5 ->
                    sb.append(bundle.getString("answers.summary.weight.n1_n3.between_2d5_and_3d5_tons"));
            case BETWEEN_3_5_AND_5 ->
                    sb.append(bundle.getString("answers.summary.weight.n1_n3.between_3d5_and_5_tons"));
            case BETWEEN_5_AND_8 -> sb.append(bundle.getString("answers.summary.weight.n1_n3.between_5_and_8_tons"));
            case BETWEEN_8_AND_12 -> sb.append(bundle.getString("answers.summary.weight.n1_n3.between_8_and_12_tons"));
            case BETWEEN_12_AND_20 ->
                    sb.append(bundle.getString("answers.summary.weight.n1_n3.between_12_and_20_tons"));
            case BETWEEN_20_AND_50 ->
                    sb.append(bundle.getString("answers.summary.weight.n1_n3.between_20_and_50_tons"));
            case FROM_12_TILL_20_TONS ->
                    sb.append(bundle.getString("answers.summary.weight.n1_n3.between_12_and_20_tons"));
            case FROM_20_TILL_50_TONS ->
                    sb.append(bundle.getString("answers.summary.weight.n1_n3.between_20_and_50_tons"));
        }

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
            case LESS_OR_3_YEARS -> sb.append(bundle.getString("answers.summary.less.3"));
            case MORE_3_YEARS -> sb.append(bundle.getString("answers.summary.between.3.and.7"));
        }

        switch (userProgress.getTruckUnitClass()) {
            case null -> {
            }
            case TRUCK_UNITS_6_CLASS -> sb.append(bundle.getString("answers.summary.truck.6_class"));
            case TRUCK_UNITS_EXCEPT_6_CLASS -> sb.append(bundle.getString("answers.summary.truck.except_6"));
        }

        switch (userProgress.getTrailerO4Type()) {
            case null -> {
            }
            case TRAILERS -> sb.append(bundle.getString("answers.summary.trailersO4details"));
            case HALF_TRAILERS -> sb.append(bundle.getString("answers.summary.halftrailersO4details"));
        }

        if (userProgress.getEngineType() == ELECTRIC) {
            sb.append(bundle.getString("answers.summary.electro"));
        } else {
            if (userProgress.getEngineType() == GASOLINE) {
                sb.append(bundle.getString("answers.summary.gas"));
            }
        }

        switch (userProgress.getVolume()) {
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
            case LESS_2500 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.volume.m2_m3.less_2500")));
            }
            case BETWEEN_2500_AND_5000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.volume.m2_m3.between_2500_5000")));
            }
            case BETWEEN_5000_AND_10000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.volume.m2_m3.between_5000_10000")));
            }
            case MORE_10000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.volume.m2_m3.more_10000")));
            }
        }
        return sb.toString();
    }

    private String stringBuilderAppender(final String... strings) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    public String getSummaryAnswer(final UserProgress userProgress) {
        return getUserChoiceSequence(userProgress) + buildNextStepQuestion(userProgress);
    }

    private String trimFirstAndLastLetters(final String toTrim) {
        return toTrim.substring(1, toTrim.length() - 1);
    }

    private static class MessagesCreatorHolder {
        private static final MessagesCreator MESSAGES_CREATOR = new MessagesCreator();
    }
}