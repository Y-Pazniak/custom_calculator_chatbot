package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Price;
import by.custom.utilcalculator.domain.constants.steps.*;

public class CalculatorPassenger {
    private static class CalculatorHolder {
        private static final CalculatorPassenger CALCULATOR_INSTANCE = new CalculatorPassenger();
    }

    private CalculatorPassenger() {
    }

    public static CalculatorPassenger getInstance() {
        return CalculatorHolder.CALCULATOR_INSTANCE;
    }

    public String calculate(final UserProgress userProgress) {
        switch (userProgress.getCountryOrigin()) {
            case EAES -> {
                return countMostCommonPrice(userProgress);
            }
            case OTHER -> {
                return countForOtherCountriesPrice(userProgress);
            }
            case null -> {
                return "calculator: countryOrigin is null";
            }
            default -> {
                return "calculator: calculate() unknown error";
            }
        }
    }

    private String countForOtherCountriesPrice(final UserProgress userProgress) {
        switch (userProgress.getOwnersType()) {
            case PHYSICAL -> {
                return countMostCommonPrice(userProgress);
            }
            case JURIDICAL -> {
                return countForJuridicalPrice(userProgress);
            }
            case null -> {
                return "calculator: ownersType is null";
            }
            default -> {
                return "calculator: countForOtherCountriesPrice() unknown error";
            }
        }
    }

    private String countForJuridicalPrice(final UserProgress userProgress) {
        switch (userProgress.getTypeOfEngine()) {
            case ELECTRIC -> {
                return countForElectricAutoPrice(userProgress);
            }
            case GASOLINE -> {
                return countForGasolineAutoPrice(userProgress);
            }
            case null -> {
                return "calculator: typeOfEngine is null";
            }
            default -> {
                return "calculator: countForJuridicalPrice() unknown error";
            }
        }
    }

    private String countForElectricAutoPrice(final UserProgress userProgress) {
        return userProgress.getCarAge() == CarAge.LESS_3_YEARS ? Price.PASSENGER_LESS_3_YEARS : Price.PASSENGER_3_TO_7_YEARS;
    }

    private String countForGasolineAutoPrice(final UserProgress userProgress) {
        switch (userProgress.getCarAge()) {
            case LESS_3_YEARS -> {
                return getPriceForJuridicalGasolineLess3Years(userProgress);
            }
            case BETWEEN_3_AND_7_YEARS -> {
                return getPriceForJuridicalGasolineBetween3And7Years(userProgress);
            }
            case MORE_7_YEARS -> {
                return getPriceForJuridicalGasolineMore7Years(userProgress);
            }
            case null -> {
                return "calculator: carAge is null";
            }
            default -> {
                return "calculator: countForGasolineAutoPrice() unknown error";
            }
        }
    }

    private String getPriceForJuridicalGasolineMore7Years(final UserProgress userProgress) {
        switch (userProgress.getVolumeOfEngine()) {
            case LESS_1000 -> {
                return Price.PASSENGER_OTHER_GASOLINE_1000_OLDER_7_YEARS;
            }
            case BETWEEN_1000_AND_2000 -> {
                return Price.PASSENGER_OTHER_GASOLINE_1000_2000_OLDER_7_YEARS;
            }
            case BETWEEN_2000_AND_3000 -> {
                return Price.PASSENGER_OTHER_GASOLINE_2000_3000_OLDER_7_YEARS;
            }
            case BETWEEN_3000_AND_3500 -> {
                return Price.PASSENGER_OTHER_GASOLINE_3000_3500_OLDER_7_YEARS;
            }
            case MORE_3500 -> {
                return Price.PASSENGER_OTHER_GASOLINE_3500_OLDER_7_YEARS;
            }
            case null -> {
                return "calculator: volumeOfEngine is null";
            }
            default -> {
                return "calculator: getPriceForJuridicalGasolineMore7Years() unknown error";
            }
        }
    }

    private String getPriceForJuridicalGasolineBetween3And7Years(final UserProgress userProgress) {
        switch (userProgress.getVolumeOfEngine()) {
            case LESS_1000 -> {
                return Price.PASSENGER_OTHER_GASOLINE_1000_3_TO_7_YEARS;
            }
            case BETWEEN_1000_AND_2000 -> {
                return Price.PASSENGER_OTHER_GASOLINE_1000_2000_3_TO_7_YEARS;
            }
            case BETWEEN_2000_AND_3000 -> {
                return Price.PASSENGER_OTHER_GASOLINE_2000_3000_3_TO_7_YEARS;
            }
            case BETWEEN_3000_AND_3500 -> {
                return Price.PASSENGER_OTHER_GASOLINE_3000_3500_3_TO_7_YEARS;
            }
            case MORE_3500 -> {
                return Price.PASSENGER_OTHER_GASOLINE_3500_3_TO_7_YEARS;
            }
            case null -> {
                return "calculator: volumeOfEngine is null";
            }
            default -> {
                return "calculator: getPriceForJuridicalGasolineBetween3And7Years() unknown error";
            }
        }
    }

    private String getPriceForJuridicalGasolineLess3Years(final UserProgress userProgress) {
        switch (userProgress.getVolumeOfEngine()) {
            case LESS_1000 -> {
                return Price.PASSENGER_OTHER_GASOLINE_1000_LESS_3_YEARS;
            }
            case BETWEEN_1000_AND_2000 -> {
                return Price.PASSENGER_OTHER_GASOLINE_1000_2000_LESS_3_YEARS;
            }
            case BETWEEN_2000_AND_3000 -> {
                return Price.PASSENGER_OTHER_GASOLINE_2000_3000_LESS_3_YEARS;
            }
            case BETWEEN_3000_AND_3500 -> {
                return Price.PASSENGER_OTHER_GASOLINE_3000_3500_LESS_3_YEARS;
            }
            case MORE_3500 -> {
                return Price.PASSENGER_OTHER_GASOLINE_3500_LESS_3_YEARS;
            }
            case null -> {
                return "calculator: volumeOfEngine is null";
            }
            default -> {
                return "calculator: getPriceForJuridicalGasolineLess3Years() unknown error";
            }
        }
    }

    private String countMostCommonPrice(final UserProgress userProgress) { //gives prices for all eaes and all physical owners + other countries juridical electric engines
        switch (userProgress.getCarAge()) {
            case LESS_3_YEARS -> {
                return Price.PASSENGER_LESS_3_YEARS;
            }
            case BETWEEN_3_AND_7_YEARS -> {
                return Price.PASSENGER_3_TO_7_YEARS;
            }
            case MORE_7_YEARS -> {
                return Price.PASSENGER_OLDER_7_YEARS;
            }
            case null -> {
                return "calculator: carAge is null";
            }
            default -> {
                return "calculator: unknown error";
            }
        }
    }
}
