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
            case null, default -> {
                return "unknown self-propelled error";
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
                return "unknown grader error";
            }
        }
    }
}
