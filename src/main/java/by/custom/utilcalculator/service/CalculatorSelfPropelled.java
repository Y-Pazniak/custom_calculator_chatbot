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
            case ROAD_MAINTENANCE -> {
                return countPriceForRoadMaintenance(userProgress);
            }
            case FORESTRY -> {
                return countPriceForForestry(userProgress);
            }
            case FORWADERS -> {
                return countPriceForForwaders(userProgress);
            }
            case TIMBER_LOADERS -> {
                return countPriceForTimberLoaders(userProgress);
            }
            case WHEELED_TRACTORS -> {
                return countPriceForWheeledTractors(userProgress);
            }
            case CRAWLER_TRACTORS -> {
                return countPriceForCrawlerTractors(userProgress);
            }
            case COMBINE_HARVESTERS -> {
                return countPriceForCombineHarvesters(userProgress);
            }
            case FORAGE_HARVESTERS -> {
                return countForForageHarvesters(userProgress);
            }
            case AGRICULTURAL_VEHICLES -> {
                return countForAgriculturalVehicles(userProgress);
            }
            case null, default -> {
                return "unknown self-propelled type during calculation";
            }
        }
    }

    private String countForAgriculturalVehicles(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case BETWEEN_100_120 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_AGRICULTURAL_VEHICLES_BETWEEN_100_AND_120HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_AGRICULTURAL_VEHICLES_BETWEEN_100_AND_120HP_MORE_3_YEARS;
            }
            case BETWEEN_120_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_AGRICULTURAL_VEHICLES_BETWEEN_120_AND_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_AGRICULTURAL_VEHICLES_BETWEEN_120_AND_300HP_MORE_3_YEARS;
            }
            case MORE_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_AGRICULTURAL_VEHICLES_MORE_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_AGRICULTURAL_VEHICLES_MORE_300HP_MORE_3_YEARS;
            }
            case SELF_PROPELLED_MOWERS -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_AGRICULTURAL_VEHICLES_SELF_PROPELLED_MOWERS_LESS_OR_3_YEARS : Price.SELF_PROPELLED_AGRICULTURAL_VEHICLES_SELF_PROPELLED_MOWERS_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown agricultural type during calculation";
            }
        }
    }

    private String countForForageHarvesters(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case LESS_295 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FORAGE_HARVESTERS_LESS_295HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FORAGE_HARVESTERS_LESS_295HP_MORE_3_YEARS;
            }
            case BETWEEN_295_401 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FORAGE_HARVESTERS_BETWEEN_295_AND_401HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FORAGE_HARVESTERS_BETWEEN_295_AND_401HP_MORE_3_YEARS;
            }
            case MORE_401 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FORAGE_HARVESTERS_MORE_401HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FORAGE_HARVESTERS_MORE_401HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown forage type during calculation";
            }
        }
    }

    private String countPriceForCombineHarvesters(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case BETWEEN_25_160 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_COMBINE_HARVESTERS_BETWEEN_25_AND_160HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_COMBINE_HARVESTERS_BETWEEN_25_AND_160HP_MORE_3_YEARS;
            }
            case BETWEEN_160_220 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_COMBINE_HARVESTERS_BETWEEN_160_AND_220HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_COMBINE_HARVESTERS_BETWEEN_160_AND_220HP_MORE_3_YEARS;
            }
            case BETWEEN_220_255 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_COMBINE_HARVESTERS_BETWEEN_220_AND_255HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_COMBINE_HARVESTERS_BETWEEN_220_AND_255HP_MORE_3_YEARS;
            }
            case BETWEEN_255_325 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_COMBINE_HARVESTERS_BETWEEN_255_AND_325HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_COMBINE_HARVESTERS_BETWEEN_255_AND_325HP_MORE_3_YEARS;
            }
            case BETWEEN_325_400 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_COMBINE_HARVESTERS_BETWEEN_325_AND_400HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_COMBINE_HARVESTERS_BETWEEN_325_AND_400HP_MORE_3_YEARS;
            }
            case MORE_400 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_COMBINE_HARVESTERS_MORE_400HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_COMBINE_HARVESTERS_MORE_400HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown combine harvester type during calculation";
            }
        }
    }

    private String countPriceForCrawlerTractors(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case LESS_100 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_CRAWLER_TRACTORS_LESS_100HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_CRAWLER_TRACTORS_LESS_100HP_MORE_3_YEARS;
            }
            case BETWEEN_100_200 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_CRAWLER_TRACTORS_BETWEEN_100_AND_200HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_CRAWLER_TRACTORS_BETWEEN_100_AND_200HP_MORE_3_YEARS;
            }
            case MORE_200 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_CRAWLER_TRACTORS_MORE_200HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_CRAWLER_TRACTORS_MORE_200HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown crawler tractor type during calculation";
            }
        }
    }

    private String countPriceForWheeledTractors(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case BETWEEN_5p5_30 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_5p5_AND_30HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_5p5_AND_30HP_MORE_3_YEARS;
            }
            case BETWEEN_30_60 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_30_AND_60HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_30_AND_60HP_MORE_3_YEARS;
            }
            case BETWEEN_60_90 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_60_AND_90HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_60_AND_90HP_MORE_3_YEARS;
            }
            case BETWEEN_90_130 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_90_AND_130HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_90_AND_130HP_MORE_3_YEARS;
            }
            case BETWEEN_130_180 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_130_AND_180HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_130_AND_180HP_MORE_3_YEARS;
            }
            case BETWEEN_180_220 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_180_AND_220HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_180_AND_220HP_MORE_3_YEARS;
            }
            case BETWEEN_220_280 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_220_AND_280HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_220_AND_280HP_MORE_3_YEARS;
            }
            case BETWEEN_280_340 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_280_AND_340HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_280_AND_340HP_MORE_3_YEARS;
            }
            case BETWEEN_340_380 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_340_AND_380HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_TRACTORS_BETWEEN_340_AND_380HP_MORE_3_YEARS;
            }
            case MORE_380 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_WHEELED_TRACTORS_MORE_380HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_WHEELED_TRACTORS_MORE_380HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown wheeled tractor type during calculation";
            }
        }
    }

    private String countPriceForTimberLoaders(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case BETWEEN_20_100 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_TIMBER_LOADERS_BETWEEN_20_AND_100HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_TIMBER_LOADERS_BETWEEN_20_AND_100HP_MORE_3_YEARS;
            }
            case BETWEEN_100_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_TIMBER_LOADERS_BETWEEN_100_AND_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_TIMBER_LOADERS_BETWEEN_100_AND_300HP_MORE_3_YEARS;
            }
            case MORE_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_TIMBER_LOADERS_MORE_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_TIMBER_LOADERS_MORE_300HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown timber loader type during calculation";
            }
        }
    }

    private String countPriceForForwaders(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case BETWEEN_20_100 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FORWADERS_BETWEEN_20_AND_100HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FORWADERS_BETWEEN_20_AND_100HP_MORE_3_YEARS;
            }
            case BETWEEN_100_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FORWADERS_BETWEEN_100_AND_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FORWADERS_BETWEEN_100_AND_300HP_MORE_3_YEARS;
            }
            case MORE_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_FORWADERS_MORE_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_FORWADERS_MORE_300HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown forwader type during calculation";
            }
        }
    }

    private String countPriceForForestry(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case BETWEEN_20_100 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_ROAD_MAINTENANCE_BETWEEN_20_AND_100HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_ROAD_MAINTENANCE_BETWEEN_20_AND_100HP_MORE_3_YEARS;
            }
            case BETWEEN_100_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_ROAD_MAINTENANCE_BETWEEN_100_AND_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_ROAD_MAINTENANCE_BETWEEN_100_AND_300HP_MORE_3_YEARS;
            }
            case MORE_300 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_ROAD_MAINTENANCE_MORE_300HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_ROAD_MAINTENANCE_MORE_300HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown forestry vehicle type during calculation";
            }
        }
    }

    private String countPriceForRoadMaintenance(final UserProgress userProgress) {
        switch (userProgress.getSelfPropelledPower()) {
            case LESS_100 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_ROAD_MAINTENANCE_LESS_100HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_ROAD_MAINTENANCE_LESS_100HP_MORE_3_YEARS;
            }
            case BETWEEN_100_220 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_ROAD_MAINTENANCE_BETWEEN_100_AND_220HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_ROAD_MAINTENANCE_BETWEEN_100_AND_220HP_MORE_3_YEARS;
            }
            case MORE_220 -> {
                return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_ROAD_MAINTENANCE_MORE_220HP_LESS_OR_3_YEARS : Price.SELF_PROPELLED_ROAD_MAINTENANCE_MORE_220HP_MORE_3_YEARS;
            }
            case null, default -> {
                return "unknown road maintanance type during calculation";
            }
        }
    }

    private String countPriceForTrailers(final UserProgress userProgress) {
        return userProgress.getCarAge() == CarAge.LESS_OR_3_YEARS ? Price.SELF_PROPELLED_TRAILERS_LESS_OR_3_YEARS : Price.SELF_PROPELLED_TRAILERS_MORE_3_YEARS;
    }

    private String countPriceForPipelayers(final UserProgress userProgress) {
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
                return "unknown pipelayer type during calculation";
            }
        }
    }

    private String countPriceForWheelCranes(final UserProgress userProgress) {
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
