package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Price;
import by.custom.utilcalculator.domain.constants.steps.CarAge;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalculatorSelfPropelled {
    private final DateTimeFormatter formatter;

    private static class CalculatorSelfPropelledHolder {
        private static final CalculatorSelfPropelled CALCULATOR_SELF_PROPELLED = new CalculatorSelfPropelled();
    }

    private CalculatorSelfPropelled() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH-mm-ss");
    }

    public static CalculatorSelfPropelled getInstance() {
        return CalculatorSelfPropelledHolder.CALCULATOR_SELF_PROPELLED;
    }

    public String calculate(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledType()) {
            case GRADER -> {
                return countPriceForGraders(userProgress);
            }
            case BULLDOZER -> {
                return countPriceForBulldozers(userProgress);
            }
            case EXCAVATOR -> {
                return countPriceForExcavators(userProgress);
            }
            case WHEEL_LOADER -> {
                return countPriceForWheelLoaders(userProgress);
            }
            case TAMPING_MACHINE -> {
                return countPriceForTampingMachines(userProgress);
            }
            case FRONT_LOADER -> {
                return countPriceForFrontLoaders(userProgress);
            }
            case WHEELED_CRANES -> {
                return countPriceForWheelCranes(userProgress);
            }
            case PIPELAYERS -> {
                return countPriceForPipelayers(userProgress);
            }
            case TRAILERS_OTHER -> {
                return countPriceForTrailers(userProgress);
            }
            case null, default -> {
                System.out.println(LocalDateTime.now().format(formatter) + ": unknown self-propelled type during calculation");
                return "unknown self-propelled type during calculation";
            }
        }
    }

    private String countPriceForTrailers(UserProgress userProgress) {
        return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_TRAILERS_LESS_OR_3_YEARS : Price.SELF_PROPELLED_TRAILERS_MORE_3_YEARS;
    }

    private String countPriceForPipelayers(UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case LESS_130 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_PIPELAYERS_LESS_130HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_PIPELAYERS_LESS_130HP_MORE_3_YEARS;
            }
            case BETWEEN_130_200 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_PIPELAYERS_BETWEEN_130_AND_200HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_PIPELAYERS_BETWEEN_130_AND_200HP_MORE_3_YEARS;
            }
            case BETWEEN_200_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_PIPELAYERS_BETWEEN_200_AND_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_PIPELAYERS_BETWEEN_200_AND_300HP_MORE_3_YEARS;
            }
            case MORE_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_PIPELAYERS_MORE_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_PIPELAYERS_MORE_300HP_MORE_3_YEARS;
            }
            case null, default -> {
                System.out.println(LocalDateTime.now().format(formatter) + ": unknown pipelayer type during calculation");
                return "unknown pipelayer type during calculation";
            }
        }
    }

    private String countPriceForWheelCranes(UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case LESS_170 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_CRANES_LESS_170HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_CRANES_LESS_170HP_MORE_3_YEARS;
            }
            case BETWEEN_170_250 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_CRANES_BETWEEN_170_AND_250HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_CRANES_BETWEEN_170_AND_250HP_MORE_3_YEARS;
            }
            case MORE_250 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_CRANES_MORE_250HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_CRANES_MORE_250HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown wheel crane error during calculation";
            }
        }
    }

    private String countPriceForFrontLoaders(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case BETWEEN_5_50 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_5_AND_50HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_5_AND_50HP_MORE_3_YEARS;
            }
            case BETWEEN_50_100 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_50_AND_100HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_50_AND_100HP_MORE_3_YEARS;
            }
            case BETWEEN_100_200 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_100_AND_200HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_100_AND_200HP_MORE_3_YEARS;
            }
            case BETWEEN_200_250 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_200_AND_250HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_200_AND_250HP_MORE_3_YEARS;
            }
            case BETWEEN_250_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_250_AND_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_250_AND_300HP_MORE_3_YEARS;
            }
            case BETWEEN_300_400 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_300_AND_400HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FRONT_LOADERS_BETWEEN_300_AND_400HP_MORE_3_YEARS;
            }
            case MORE_400 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FRONT_LOADERS_MORE_400HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FRONT_LOADERS_MORE_400HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown tamping machine error during calculation";
            }
        }
    }

    private String countPriceForTampingMachines(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case LESS_40 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_TAMPING_MACHINES_LESS_40HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_TAMPING_MACHINES_LESS_40HP_MORE_3_YEARS;
            }
            case BETWEEN_40_80 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_TAMPING_MACHINES_BETWEEN_40_AND_80HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_TAMPING_MACHINES_BETWEEN_40_AND_80HP_MORE_3_YEARS;
            }
            case MORE_80 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_TAMPING_MACHINES_MORE_80HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_TAMPING_MACHINES_MORE_80HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown tamping machine error during calculation";
            }
        }
    }

    private String countPriceForWheelLoaders(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case LESS_100 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEEL_LOADERS_LESS_100HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEEL_LOADERS_LESS_100HP_MORE_3_YEARS;
            }
            case BETWEEN_100_125 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEEL_LOADERS_BETWEEN_100_AND_125HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEEL_LOADERS_BETWEEN_100_AND_125HP_MORE_3_YEARS;
            }
            case BETWEEN_125_150 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEEL_LOADERS_BETWEEN_125_AND_150HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEEL_LOADERS_BETWEEN_125_AND_150HP_MORE_3_YEARS;
            }
            case MORE_150 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEEL_LOADERS_MORE_150HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEEL_LOADERS_MORE_150HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown loader error during calculation";
            }
        }
    }

    private String countPriceForExcavators(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case LESS_170 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_EXCAVATORS_LESS_170HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_EXCAVATORS_LESS_170HP_MORE_3_YEARS;
            }
            case BETWEEN_170_250 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_EXCAVATORS_BETWEEN_170_AND_250HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_EXCAVATORS_BETWEEN_170_AND_250HP_MORE_3_YEARS;
            }
            case MORE_250 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_EXCAVATORS_MORE_250HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_EXCAVATORS_MORE_250HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown excavator error during calculation";
            }
        }
    }

    private String countPriceForBulldozers(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case LESS_100 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_BULLDOZERS_LESS_100HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_BULLDOZERS_LESS_100HP_MORE_3_YEARS;
            }
            case BETWEEN_100_200 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_100_AND_200HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_100_AND_200HP_MORE_3_YEARS;
            }
            case BETWEEN_200_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_200_AND_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_200_AND_300HP_MORE_3_YEARS;
            }
            case BETWEEN_300_400 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_300_AND_400HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_300_AND_400HP_MORE_3_YEARS;
            }
            case MORE_400 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_BULLDOZERS_MORE_400HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_BULLDOZERS_MORE_400HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown bulldozer error during calculation";
            }
        }
    }

    private String countPriceForGraders(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case LESS_100 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_GRADERS_LESS_100HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_GRADERS_LESS_100HP_MORE_3_YEARS;
            }
            case BETWEEN_100_140 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_GRADERS_BETWEEN_100_AND_140HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_GRADERS_BETWEEN_100_AND_140HP_MORE_3_YEARS;
            }
            case BETWEEN_140_200 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_GRADERS_BETWEEN_140_AND_200HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_GRADERS_BETWEEN_140_AND_200HP_MORE_3_YEARS;
            }
            case MORE_200 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_GRADERS_MORE_200HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_GRADERS_MORE_200HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown grader error during calculation";
            }
        }
    }
}
