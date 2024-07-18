package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Price;
import by.custom.utilcalculator.domain.constants.steps.CarAge;
import by.custom.utilcalculator.domain.constants.steps.EngineTypeM2M3;

public class CalculatorExceptM1 {
    private CalculatorExceptM1() {
    }

    private static class CalculatorExceptM1Holder {
        private static final CalculatorExceptM1 CALCULATOR_INSTANCE = new CalculatorExceptM1();
    }

    public static CalculatorExceptM1 getInstance() {
        return CalculatorExceptM1Holder.CALCULATOR_INSTANCE;
    }

    public String calculate(final UserProgress userProgress) {
        switch (userProgress.getExceptM1TransportType()) {
            case N1_N3 -> {
                return countForN1N3price(userProgress);
            }
            case M2_M3 -> {
                return countForM2M3Price(userProgress);
            }
            case TRAILERS_O4 -> {
                return "trailers";
            }
            case TRUCK_UNITS -> {
                return "track units";
            }
            case null, default -> {
                return "unknown type of vehicle cat. M1-M3";
            }
        }
    }

    private String countForM2M3Price(final UserProgress userProgress) {
        return userProgress.getEngineTypeM2M3() == EngineTypeM2M3.ELECTRIC ? countPriceForM2M3Electric(userProgress) : "fill calculator for gasoline";
    }

    private String countPriceForM2M3Electric(final UserProgress userProgress) {
        return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_M2_M3_ELECTRIC_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_M2_M3_ELECTRIC_MORE_3_YEARS;
    }

    private String countForN1N3price(final UserProgress userProgress) {
        switch (userProgress.getTransportWeightN1N2N3()) {
            case LESS_2_TONS -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_N1_N3_LESS_2P5_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_N1_N3_LESS_2P5_MORE_3_YEARS;
            }
            case BETWEEN_2_5_AND_3_5 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_2P5_3P5_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_2P5_3P5_MORE_3_YEARS;
            }
            case BETWEEN_3_5_AND_5 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_3P5_5_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_3P5_5_MORE_3_YEARS;
            }
            case BETWEEN_5_AND_8 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_5_8_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_5_8_MORE_3_YEARS;
            }
            case BETWEEN_8_AND_12 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_8_12_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_8_12_MORE_3_YEARS;
            }
            case BETWEEN_12_AND_20 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_12_20_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_12_20_MORE_3_YEARS;
            }
            case BETWEEN_20_AND_50 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_20_50_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_20_50_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown age";
            }
        }
    }
}
