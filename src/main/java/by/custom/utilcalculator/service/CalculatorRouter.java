package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;

public class CalculatorRouter {
    public static String calculate(final UserProgress userProgress) {
        switch (userProgress.getGeneralTransportType()) {
            case M1 -> {
                return CalculatorM1.getInstance().calculate(userProgress);
            }
            case EXCEPT_M1 -> {
                return CalculatorExceptM1.getInstance().calculate(userProgress);
            }
            case SELF_PROPELLED_VEHICLES -> {
                return "Self-propelled vehicle";
            }
            case null, default -> {
                return "unknown type of vehicle";
            }
        }
    }
}
