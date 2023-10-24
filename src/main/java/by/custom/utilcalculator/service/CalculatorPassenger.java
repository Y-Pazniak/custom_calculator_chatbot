package by.custom.utilcalculator.service;

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

    public String calculate(CountryOrigin countryOrigin, OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        switch (countryOrigin) {
            case EAES -> {
                return countMostCommonPrice(carAge);
            }
            case OTHER -> {
                return countForOtherCountriesPrice(ownersType, typeOfEngine, volumeOfEngine, carAge);
            }
            case null -> {
                return "calculator: countryOrigin is null";
            }
            default -> {
                return "calculator: calculate() unknown error";
            }
        }
    }

    private String countForOtherCountriesPrice(OwnersType ownersType, TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        switch (ownersType) {
            case PHYSICAL -> {
                return countMostCommonPrice(carAge);
            }
            case JURIDICAL -> {
                return countForJuridicalPrice(typeOfEngine, volumeOfEngine, carAge);
            }
            case null -> {
                return "calculator: ownersType is null";
            }
            default -> {
                return "calculator: countForOtherCountriesPrice() unknown error";
            }
        }
    }

    private String countForJuridicalPrice(TypeOfEngine typeOfEngine, VolumeOfEngine volumeOfEngine, CarAge carAge) {
        switch (typeOfEngine) {
            case ELECTRIC -> {
                return countForElectricAutoPrice(carAge);
            }
            case GASOLINE -> {
                return countForGasolineAutoPrice(volumeOfEngine, carAge);
            }
            case null -> {
                return "calculator: typeOfEngine is null";
            }
            default -> {
                return "calculator: countForJuridicalPrice() unknown error";
            }
        }
    }

    private String countForElectricAutoPrice(CarAge carAge) {
        return carAge == CarAge.LESS_3_YEARS ? Price.PASSENGER_LESS_3_YEARS : Price.PASSENGER_3_TO_7_YEARS;
    }

    private String countForGasolineAutoPrice(VolumeOfEngine volumeOfEngine, CarAge carAge) {
        switch (carAge) {
            case LESS_3_YEARS -> {
                return getPriceForJuridicalGasolineLess3Years(volumeOfEngine);
            }
            case BETWEEN_3_AND_7_YEARS -> {
                return getPriceForJuridicalGasolineBetween3And7Years(volumeOfEngine);
            }
            case MORE_7_YEARS -> {
                return getPriceForJuridicalGasolineMore7Years(volumeOfEngine);
            }
            case null -> {
                return "calculator: carAge is null";
            }
            default -> {
                return "calculator: countForGasolineAutoPrice() unknown error";
            }
        }
    }

    private String getPriceForJuridicalGasolineMore7Years(VolumeOfEngine volumeOfEngine) {
        switch (volumeOfEngine) {
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

    private String getPriceForJuridicalGasolineBetween3And7Years(VolumeOfEngine volumeOfEngine) {
        switch (volumeOfEngine) {
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

    private String getPriceForJuridicalGasolineLess3Years(VolumeOfEngine volumeOfEngine) {
        switch (volumeOfEngine) {
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

    private String countMostCommonPrice(CarAge carAge) { //gives prices for all eaes and all physical owners + other countries juridical electric engines
        switch (carAge) {
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
