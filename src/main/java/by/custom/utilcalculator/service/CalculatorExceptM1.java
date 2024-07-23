package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Price;
import by.custom.utilcalculator.domain.constants.steps.CarAge;
import by.custom.utilcalculator.domain.constants.steps.M2EngineVolume;
import by.custom.utilcalculator.domain.constants.steps.M2M3EngineType;
import by.custom.utilcalculator.domain.constants.steps.TruckUnitClass;

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
            case TRUCK_UNITS -> {
                return countForTruckUnits(userProgress);
            }
            case TRAILERS_O4 -> {
                return countForTrailers(userProgress);
            }
            case null, default -> {
                return "unknown type of vehicle cat. M1-M3";
            }
        }
    }

    private String countForTrailers(final UserProgress userProgress) {
        return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_TRAILERS_TRAILERS_AND_HALF_TRAILERS_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_TRAILERS_TRAILERS_AND_HALF_TRAILERS_MORE_3_YEARS;
    }

    private String countForTruckUnits(final UserProgress userProgress) {
        return userProgress.getTruckUnitType() == TruckUnitClass.TRUCK_UNITS_6_CLASS ? countForTruckUnits6Class(userProgress) : countForExcept6ClassUnits(userProgress);
    }

    private String countForExcept6ClassUnits(final UserProgress userProgress) {
        switch (userProgress.getTruckUnitWeight()) {
            case FROM_12_TILL_20_TONS -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_TRUCK_UNITS_EXCEPT_6_CLASS_12_20_TONS_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_TRUCK_UNITS_EXCEPT_6_CLASS_12_20_TONS_MORE_3_YEARS;
            }
            case FROM_20_TILL_50_TONS -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_TRUCK_UNITS_EXCEPT_6_CLASS_20_50_TONS_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_TRUCK_UNITS_EXCEPT_6_CLASS_20_50_TONS_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown except 6 class type";
            }
        }
    }

    private String countForTruckUnits6Class(final UserProgress userProgress) {
        switch (userProgress.getTruckUnitWeight()) {
            case FROM_12_TILL_20_TONS -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_TRUCK_UNITS_6_CLASS_12_20_TONS_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_TRUCK_UNITS_6_CLASS_12_20_TONS_MORE_3_YEARS;
            }
            case FROM_20_TILL_50_TONS -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.EXCEPT_PASSENGER_TRUCK_UNITS_6_CLASS_20_50_TONS_LESS_OR_3_YEARS : Price.EXCEPT_PASSENGER_TRUCK_UNITS_6_CLASS_20_50_TONS_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown except 6 class type";
            }
        }
    }

    private String countForM2M3Price(final UserProgress userProgress) {
        return userProgress.getEngineTypeM2M3() == M2M3EngineType.ELECTRIC ? countPriceForM2M3Electric(userProgress) : countPriceForM2M3Gasoline(userProgress);
    }

    private String countPriceForM2M3Gasoline(final UserProgress userProgress) {
        return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? countM2M3GasolineLess3Years(userProgress.getM2EngineVolume()) : countM2M3GasolineMore3Years(userProgress.getM2EngineVolume());
    }

    private String countM2M3GasolineMore3Years(final M2EngineVolume m2EngineVolume) {
        switch (m2EngineVolume) {
            case LESS_2500 -> {
                return Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_2500_MORE_3_YEARS;
            }
            case BETWEEN_2500_AND_5000 -> {
                return Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_2500_5000_MORE_3_YEARS;
            }
            case BETWEEN_5000_AND_10000 -> {
                return Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_5000_10000_MORE_3_YEARS;
            }
            case MORE_10000 -> {
                return Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_MORE_10000_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown engine volume";
            }
        }
    }

    private String countM2M3GasolineLess3Years(final M2EngineVolume m2EngineVolume) {
        switch (m2EngineVolume) {
            case LESS_2500 -> {
                return Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_2500_LESS_OR_3_YEARS;
            }
            case BETWEEN_2500_AND_5000 -> {
                return Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_2500_5000_LESS_OR_3_YEARS;
            }
            case BETWEEN_5000_AND_10000 -> {
                return Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_5000_10000_LESS_OR_3_YEARS;
            }
            case MORE_10000 -> {
                return Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_MORE_10000_LESS_OR_3_YEARS;
            }
            case null, default -> {
                return "unknown engine volume";
            }
        }
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
