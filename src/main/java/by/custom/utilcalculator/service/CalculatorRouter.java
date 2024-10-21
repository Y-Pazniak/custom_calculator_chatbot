package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;

public class CalculatorRouter {
    public static String calculate(final UserProgress userProgress) {
        switch (userProgress.getGeneralTransportType()) {
            case M1 -> {
                return CalculatorM1.getInstance().calculate(userProgress);
            }
            case BUSES_AND_TRUCKS -> {
                return CalculatorExceptM1.getInstance().calculate(userProgress);
            }
            case SELF_PROPELLED_VEHICLES -> {
                return CalculatorSelfPropelled.getInstance().calculate(userProgress);
            }
            case null, default -> {
                return "unknown type of vehicle";
            }
        }
    }
}
