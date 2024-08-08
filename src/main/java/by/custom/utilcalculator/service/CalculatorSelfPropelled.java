package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Price;
import by.custom.utilcalculator.domain.constants.steps.CarAge;

public class CalculatorSelfPropelled {
    private static class CalculatorSelfPropelledHolder {
        private static final CalculatorSelfPropelled CALCULATOR_SELF_PROPELLED = new CalculatorSelfPropelled();
    }

    private CalculatorSelfPropelled() {
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
            case null, default -> {
                System.out.println("unknown self-propelled type during calculation");
                return "unknown self-propelled type during calculation";
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
