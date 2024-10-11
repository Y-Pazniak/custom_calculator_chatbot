package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.Price;
import by.custom.utilcalculator.domain.constants.steps.Step;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FillerTree {
    public static void fillTreeJson(final ObjectMapper mapper, final File file) {
        //root node (start)
        Node root = new Node(Command.START, "base node to start - runs automatically", Step.GENERAL_TRANSPORT_TYPE, null);
        //root -> general transport type
        Node m1 = new Node(Command.M1, "M1 - passenger's vehicles", Step.COUNTRY_ORIGIN, null);
        Node busesAndTrucks = new Node(Command.BUSES_AND_TRUCKS, "any vehicle, except M1 and self-propelled vehicles and trailers for them (buses, trucks, trailers for trucks)", Step.PARTICULAR_TRANSPORT_TYPE, null);
        Node selfPropelledVehicles = new Node(Command.SELF_PROPELLED_VEHICLES, "other vehicles, except m1, buses and trucks", Step.PARTICULAR_TRANSPORT_TYPE, null);
        root.addKids(m1, busesAndTrucks, selfPropelledVehicles);

        /*start m1*/
        //root -> m1 -> country origin
        Node eaes = new Node(Command.EAES, "branch for M1 vehicles from EAES (Armenia, Belarus, Kazakhstan, Kyrgyzstan, Russia)", Step.OWNERS_TYPE, null);
        Node otherCountries = new Node(Command.OTHER_COUNTRIES, "it means any country except EAES countries", Step.OWNERS_TYPE, null);
        m1.addKids(eaes, otherCountries);
        //root -> m1 -> country origin -> owners type
        Node physical = new Node(Command.PHYSICAL, "common person, who does not represent any company", Step.AGE, null);
        Node juridical = new Node(Command.JURIDICAL, "branch for companies who want to purchase a passenger M1 car from EAES", Step.AGE, null);
        eaes.addKids(physical, juridical);
        //root -> m1 -> owners type -> other countries
        Node otherCountriesJuridical = new Node(Command.JURIDICAL, "branch for companies who want to purchase a passenger M1 car except EAES", Step.ENGINE_TYPE, null);
        otherCountries.addKids(physical, otherCountriesJuridical);
        //root -> m1 -> other countries -> juridical -> type of engine
        Node electricTypeEngine = new Node(Command.ELECTRIC, "if car has pure electric engine, it will be different branch", Step.AGE, null);
        Node gasolineTypeEngine = new Node(Command.GASOLINE, "if car has gasoline or hybrid engine, it will be different branch", Step.ENGINE_VOLUME_POWER, null);
        otherCountriesJuridical.addKids(electricTypeEngine, gasolineTypeEngine);
        //root -> m1 -> other -> juridical -> gasoline -> engine volume
        Node gasolineM1Less1000 = new Node(Command.VOLUME_LESS_1000_CM, "", Step.AGE, null);
        Node gasolineM1Between1000_2000 = new Node(Command.VOLUME_BETWEEN_1000_2000_CM, "", Step.AGE, null);
        Node gasolineM1Between2000_3000 = new Node(Command.VOLUME_BETWEEN_2000_3000_CM, "", Step.AGE, null);
        Node gasolineM1Between3000_3500 = new Node(Command.VOLUME_BETWEEN_3000_3500_CM, "", Step.AGE, null);
        Node gasolineM1more3500 = new Node(Command.VOLUME_MORE_3500_CM, "", Step.AGE, null);
        gasolineTypeEngine.addKids(gasolineM1Less1000, gasolineM1Between1000_2000, gasolineM1Between2000_3000, gasolineM1Between3000_3500, gasolineM1more3500);
        //root -> m1 -> eaes/other countries+physical -> age
        Node threeYearsAndLessM1 = new Node(Command.LESS_3_YEARS_AGE, "three and less years old car", Step.FAREWELL, Price.PASSENGER_3_OR_LESS_YEARS);
        Node moreThanThreeYearsM1 = new Node(Command.MORE_THAN_3_YEARS_AGE, "more than three years old car", Step.FAREWELL, Price.PASSENGER_MORE_3_YEARS);
        physical.addKids(threeYearsAndLessM1, moreThanThreeYearsM1);
        juridical.addKids(threeYearsAndLessM1, moreThanThreeYearsM1);
        //root -> m1 -> other countries -> juridical -> age
        Node gasolineM1Less1000AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_1000_LESS_OR_3_YEARS);
        Node gasolineM1Less1000AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_1000_MORE_3_YEARS);
        Node gasolineM1Between1000And2000AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_1000_2000_LESS_OR_3_YEARS);
        Node gasolineM1Between1000And2000AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_1000_2000_MORE_3_YEARS);
        Node gasolineM1Between2000And3000AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_2000_3000_LESS_OR_3_YEARS);
        Node gasolineM1Between2000And3000AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_2000_3000_MORE_3_YEARS);
        Node gasolineM1Between3000And3500AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_3000_3500_LESS_OR_3_YEARS);
        Node gasolineM1Between3000And3500AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_3000_3500_MORE_3_YEARS);
        Node gasolineM1More3500AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_3500_LESS_OR_3_YEARS);
        Node gasolineM1More3500AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_3500_MORE_3_YEARS);
        electricTypeEngine.addKids(threeYearsAndLessM1, moreThanThreeYearsM1);
        gasolineM1Less1000.addKids(gasolineM1Less1000AgeLessThreeYears, gasolineM1Less1000AgeMoreThreeYears);
        gasolineM1Between1000_2000.addKids(gasolineM1Between1000And2000AgeLessThreeYears, gasolineM1Between1000And2000AgeMoreThreeYears);
        gasolineM1Between2000_3000.addKids(gasolineM1Between2000And3000AgeLessThreeYears, gasolineM1Between2000And3000AgeMoreThreeYears);
        gasolineM1Between3000_3500.addKids(gasolineM1Between3000And3500AgeLessThreeYears, gasolineM1Between3000And3500AgeMoreThreeYears);
        gasolineM1more3500.addKids(gasolineM1More3500AgeLessThreeYears, gasolineM1More3500AgeMoreThreeYears);
        /*end m1*/

        /*start buses and trucks*/
        //root -> buses and trucks
        Node truckN1N2N3 = new Node(Command.N1_N3, "trucks", Step.WEIGHT, null);
        Node busM2M3 = new Node(Command.M2_M3, "trucks", Step.ENGINE_TYPE, null);
        Node truckUnit = new Node(Command.TRUCK_UNITS, "truck units - do not confuse with trucks", Step.TRUCK_UNIT_CLASS, null);
        Node trailerO4 = new Node(Command.TRAILERS_O4, "truck units - do not confuse with trucks", Step.TRAILERS_O4_TYPE, null);
        busesAndTrucks.addKids(truckN1N2N3, busM2M3, truckUnit, trailerO4);
        //root -> buses and trucks -> trucks -> weight
        Node less2p5tons = new Node(Command.LESS_2P5_TONS, "", Step.AGE, null);
        Node between2p5and3p5tons = new Node(Command.BETWEEN_2_5_AND_3_5_TONS, "", Step.AGE, null);
        Node between3p5and5tons = new Node(Command.BETWEEN_3_5_AND_5_TONS, "", Step.AGE, null);
        Node between5and8tons = new Node(Command.BETWEEN_5_AND_8_TONS, "", Step.AGE, null);
        Node between8and12tons = new Node(Command.BETWEEN_8_AND_12_TONS, "", Step.AGE, null);
        Node between12and20tons = new Node(Command.BETWEEN_12_AND_20_TONS, "", Step.AGE, null);
        Node between20and50tons = new Node(Command.BETWEEN_20_AND_50_TONS, "", Step.AGE, null);
        truckN1N2N3.addKids(less2p5tons, between2p5and3p5tons, between3p5and5tons, between5and8tons, between8and12tons, between12and20tons, between20and50tons);
        //root -> buses and trucks -> trucks -> weight -> age
        Node trucksLess2p5tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_LESS_2P5_LESS_OR_3_YEARS);
        Node trucksLess2p5tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_LESS_2P5_MORE_3_YEARS);
        Node trucksBetween2p5and3p5tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_2P5_3P5_LESS_OR_3_YEARS);
        Node trucksBetween2p5and3p5tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_2P5_3P5_MORE_3_YEARS);
        Node trucksBetween3p5and5tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_3P5_5_LESS_OR_3_YEARS);
        Node trucksBetween3p5and5tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_3P5_5_MORE_3_YEARS);
        Node trucksBetween5and8tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_5_8_LESS_OR_3_YEARS);
        Node trucksBetween5and8tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_5_8_MORE_3_YEARS);
        Node trucksBetween8and12tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_8_12_LESS_OR_3_YEARS);
        Node trucksBetween8and12tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_8_12_MORE_3_YEARS);
        Node trucksBetween12and20tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_12_20_LESS_OR_3_YEARS);
        Node trucksBetween12and20tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_12_20_MORE_3_YEARS);
        Node trucksBetween20and50tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_20_50_LESS_OR_3_YEARS);
        Node trucksBetween20and50tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_N1_N3_BETWEEN_20_50_MORE_3_YEARS);
        less2p5tons.addKids(trucksLess2p5tonsAgeLessThreeYears, trucksLess2p5tonsAgeMoreThreeYears);
        between2p5and3p5tons.addKids(trucksBetween2p5and3p5tonsAgeLessThreeYears, trucksBetween2p5and3p5tonsAgeMoreThreeYears);
        between3p5and5tons.addKids(trucksBetween3p5and5tonsAgeLessThreeYears, trucksBetween3p5and5tonsAgeMoreThreeYears);
        between5and8tons.addKids(trucksBetween5and8tonsAgeLessThreeYears, trucksBetween5and8tonsAgeMoreThreeYears);
        between8and12tons.addKids(trucksBetween8and12tonsAgeLessThreeYears, trucksBetween8and12tonsAgeMoreThreeYears);
        between12and20tons.addKids(trucksBetween12and20tonsAgeLessThreeYears, trucksBetween12and20tonsAgeMoreThreeYears);
        between20and50tons.addKids(trucksBetween20and50tonsAgeLessThreeYears, trucksBetween20and50tonsAgeMoreThreeYears);
        //root -> buses and trucks -> buses -> engine type
        Node busesElectricTypeEngine = new Node(Command.ELECTRIC, "if bus has pure electric engine, it will be different branch", Step.AGE, null);
        Node busesGasolineTypeEngine = new Node(Command.GASOLINE, "if bus has gasoline or hybrid engine, it will be different branch", Step.ENGINE_VOLUME_POWER, null);
        busM2M3.addKids(busesElectricTypeEngine, busesGasolineTypeEngine);
        //root -> buses and trucks -> buses -> engine type -> electric -> age
        Node busesElectricAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_M2_M3_ELECTRIC_LESS_OR_3_YEARS);
        Node busesElectricAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_M2_M3_ELECTRIC_MORE_3_YEARS);
        busesElectricTypeEngine.addKids(busesElectricAgeLessThreeYears, busesElectricAgeMoreThreeYears);
        //root -> buses and trucks -> buses -> engine type -> gasoline
        Node busesGasolineLess2500volume = new Node(Command.VOLUME_LESS_2500_CM, "", Step.AGE, null);
        Node busesGasolineBetween2500and5000volume = new Node(Command.VOLUME_BETWEEN_2500_5000_CM, "", Step.AGE, null);
        Node busesGasolineBetween5000and10000volume = new Node(Command.VOLUME_BETWEEN_5000_10000_CM, "", Step.AGE, null);
        Node busesGasolineMore10000volume = new Node(Command.VOLUME_MORE_10000_CM, "", Step.AGE, null);
        busesGasolineTypeEngine.addKids(busesGasolineLess2500volume, busesGasolineBetween2500and5000volume, busesGasolineBetween5000and10000volume, busesGasolineMore10000volume);
        //root -> buses and trucks -> buses -> engine type -> gasoline -> age
        Node busesGasolineLess2500volumeAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_2500_LESS_OR_3_YEARS);
        Node busesGasolineLess2500volumeAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_2500_MORE_3_YEARS);
        Node busesGasolineBetween2500and5000volumeAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_2500_5000_LESS_OR_3_YEARS);
        Node busesGasolineBetween2500and5000volumeAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_2500_5000_MORE_3_YEARS);
        Node busesGasolineBetween5000and10000volumeAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_5000_10000_LESS_OR_3_YEARS);
        Node busesGasolineBetween5000and10000volumeAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_5000_10000_MORE_3_YEARS);
        Node busesGasolineMore10000volumeAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_MORE_10000_LESS_OR_3_YEARS);
        Node busesGasolineMore10000volumeAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_M2_M3_GASOLINE_MORE_10000_MORE_3_YEARS);
        busesGasolineLess2500volume.addKids(busesGasolineLess2500volumeAgeLessThreeYears, busesGasolineLess2500volumeAgeMoreThreeYears);
        busesGasolineBetween2500and5000volume.addKids(busesGasolineBetween2500and5000volumeAgeLessThreeYears, busesGasolineBetween2500and5000volumeAgeMoreThreeYears);
        busesGasolineBetween5000and10000volume.addKids(busesGasolineBetween5000and10000volumeAgeLessThreeYears, busesGasolineBetween5000and10000volumeAgeMoreThreeYears);
        busesGasolineMore10000volume.addKids(busesGasolineMore10000volumeAgeLessThreeYears, busesGasolineMore10000volumeAgeMoreThreeYears);
        //root -> buses and trucks -> truck units
        Node truckUnitsOther = new Node(Command.TRUCK_UNITS_OTHER, "truck units except eco class 6", Step.WEIGHT, null);
        Node truckUnits6class = new Node(Command.TRUCK_UNITS_6_CLASS, "truck units with eco class 6", Step.WEIGHT, null);
        truckUnit.addKids(truckUnitsOther, truckUnits6class);
        //root -> buses and trucks -> truck units -> other except 6 class
        Node truckUnitOtherBetween12and20tons = new Node(Command.TRUCK_UNITS_12_20_TONS, "", Step.AGE, null);
        Node truckUnitOtherBetween20and50tons = new Node(Command.TRUCK_UNITS_20_50_TONS, "", Step.AGE, null);
        truckUnitsOther.addKids(truckUnitOtherBetween12and20tons, truckUnitOtherBetween20and50tons);
        //root -> buses and trucks -> truck units -> 6 class
        Node truckUnit6classBetween12and20tons = new Node(Command.TRUCK_UNITS_12_20_TONS, "", Step.AGE, null);
        Node truckUnit6classBetween20and50tons = new Node(Command.TRUCK_UNITS_20_50_TONS, "", Step.AGE, null);
        truckUnits6class.addKids(truckUnit6classBetween12and20tons, truckUnit6classBetween20and50tons);
        //root -> buses and trucks -> truck units -> other except 6 class -> age
        Node truckUnitOtherBetween12and20tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRUCK_UNITS_EXCEPT_6_CLASS_12_20_TONS_LESS_OR_3_YEARS);
        Node truckUnitOtherBetween12and20tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRUCK_UNITS_EXCEPT_6_CLASS_12_20_TONS_MORE_3_YEARS);
        Node truckUnitOtherBetween20and50tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRUCK_UNITS_EXCEPT_6_CLASS_20_50_TONS_LESS_OR_3_YEARS);
        Node truckUnitOtherBetween20and50tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRUCK_UNITS_EXCEPT_6_CLASS_20_50_TONS_MORE_3_YEARS);
        truckUnitOtherBetween12and20tons.addKids(truckUnitOtherBetween12and20tonsAgeLessThreeYears, truckUnitOtherBetween12and20tonsAgeMoreThreeYears);
        truckUnitOtherBetween20and50tons.addKids(truckUnitOtherBetween20and50tonsAgeLessThreeYears, truckUnitOtherBetween20and50tonsAgeMoreThreeYears);
        //root -> buses and trucks -> truck units -> 6 class -> age
        Node truckUnit6classBetween12and20tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRUCK_UNITS_6_CLASS_12_20_TONS_LESS_OR_3_YEARS);
        Node truckUnit6classBetween12and20tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRUCK_UNITS_6_CLASS_12_20_TONS_MORE_3_YEARS);
        Node truckUnit6classBetween20and50tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRUCK_UNITS_6_CLASS_20_50_TONS_LESS_OR_3_YEARS);
        Node truckUnit6classBetween20and50tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRUCK_UNITS_6_CLASS_20_50_TONS_MORE_3_YEARS);
        truckUnit6classBetween12and20tons.addKids(truckUnit6classBetween12and20tonsAgeLessThreeYears, truckUnit6classBetween12and20tonsAgeMoreThreeYears);
        truckUnit6classBetween20and50tons.addKids(truckUnit6classBetween20and50tonsAgeLessThreeYears, truckUnit6classBetween20and50tonsAgeMoreThreeYears);
        //root -> buses and trucks -> trailers
        Node trailersMassMore10tons = new Node(Command.TRAILERS_04_TYPE, "there are two types of O4 trailers: trailers and semitrailers", Step.AGE, null);
        Node halfTrailersMassMore10tons = new Node(Command.HALF_TRAILERS_04_TYPE, "there are two types of O4 trailers: trailers and semitrailers", Step.AGE, null);
        trailerO4.addKids(trailersMassMore10tons, halfTrailersMassMore10tons);
        //root -> buses and trucks -> trailers o4 -> age
        Node trailersMassMore10tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRAILERS_TRAILERS_AND_HALF_TRAILERS_LESS_OR_3_YEARS);
        Node trailersMassMore10tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRAILERS_TRAILERS_AND_HALF_TRAILERS_MORE_3_YEARS);
        Node halfTrailersMassMore10tonsAgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRAILERS_TRAILERS_AND_HALF_TRAILERS_LESS_OR_3_YEARS);
        Node halfTrailersMassMore10tonsAgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.EXCEPT_PASSENGER_TRAILERS_TRAILERS_AND_HALF_TRAILERS_MORE_3_YEARS);
        trailersMassMore10tons.addKids(trailersMassMore10tonsAgeLessThreeYears, trailersMassMore10tonsAgeMoreThreeYears);
        halfTrailersMassMore10tons.addKids(halfTrailersMassMore10tonsAgeLessThreeYears, halfTrailersMassMore10tonsAgeMoreThreeYears);
        /*end buses and trucks*/

        /*start self-propelled vehicles*/
        //root -> self-propelled -> help
        Node help = new Node(Command.HELP, "help reques from user", Step.PARTICULAR_TRANSPORT_TYPE, null);
        //root -> self-propelled -> graders
        Node graders = new Node(Command.GRADERS, "graders and planners", Step.ENGINE_VOLUME_POWER, null);
        //root -> self-propelled -> graders -> power
        Node gradersPowerLess100 = new Node(Command.POWER_LESS_100, "graders less 100 power", Step.AGE, null);
        Node gradersPowerBetween100and140 = new Node(Command.POWER_100_140, "graders power 100-140 hp", Step.AGE, null);
        Node gradersPowerBetween140and200 = new Node(Command.POWER_140_200, "graders power 140-200 hp", Step.AGE, null);
        Node gradersPowerMore200 = new Node(Command.POWER_MORE_200, "graders power more 200 hp", Step.AGE, null);
        graders.addKids(gradersPowerLess100, gradersPowerBetween100and140, gradersPowerBetween140and200, gradersPowerMore200);
        //root -> self-propelled -> graders -> power -> age
        Node gradersPowerLess100AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_GRADERS_LESS_100HP_LESS_OR_3_YEARS);
        Node gradersPowerLess100AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_GRADERS_LESS_100HP_MORE_3_YEARS);
        Node gradersPowerBetween100and140LessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_GRADERS_BETWEEN_100_AND_140HP_LESS_OR_3_YEARS);
        Node gradersPowerBetween100and140MoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_GRADERS_BETWEEN_100_AND_140HP_MORE_3_YEARS);
        Node gradersPowerBetween140and200LessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_GRADERS_BETWEEN_140_AND_200HP_LESS_OR_3_YEARS);
        Node gradersPowerBetween140and200MoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_GRADERS_BETWEEN_140_AND_200HP_MORE_3_YEARS);
        Node gradersPowerMore200LessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_GRADERS_MORE_200HP_LESS_OR_3_YEARS);
        Node gradersPowerMore200MoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_GRADERS_MORE_200HP_MORE_3_YEARS);
        gradersPowerLess100.addKids(gradersPowerLess100AgeLessThreeYears, gradersPowerLess100AgeMoreThreeYears);
        gradersPowerBetween100and140.addKids(gradersPowerBetween100and140LessThreeYears, gradersPowerBetween100and140MoreThreeYears);
        gradersPowerBetween140and200.addKids(gradersPowerBetween140and200LessThreeYears, gradersPowerBetween140and200MoreThreeYears);
        gradersPowerMore200.addKids(gradersPowerMore200LessThreeYears, gradersPowerMore200MoreThreeYears);
        //root -> self-propelled -> bulldozers
        Node bulldozers = new Node(Command.BULLDOZERS, "bulldozers with power", Step.ENGINE_VOLUME_POWER, null);
        //root -> self-propelled -> bulldozers -> power
        Node bulldozersPowerLess100 = new Node(Command.POWER_LESS_100, "", Step.AGE, null);
        Node bulldozersPowerBetween100and200 = new Node(Command.POWER_100_200, "", Step.AGE, null);
        Node bulldozersPowerBetween200and300 = new Node(Command.POWER_200_300, "", Step.AGE, null);
        Node bulldozersPowerBetween300and400 = new Node(Command.POWER_300_400, "", Step.AGE, null);
        Node bulldozersPowerMore400 = new Node(Command.POWER_MORE_400, "", Step.AGE, null);
        bulldozers.addKids(bulldozersPowerLess100, bulldozersPowerBetween100and200, bulldozersPowerBetween200and300, bulldozersPowerBetween300and400, bulldozersPowerMore400);
        //root -> self-propelled -> bulldozers -> power -> age
        Node bulldozersPowerLess100AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_BULLDOZERS_LESS_100HP_LESS_OR_3_YEARS);
        Node bulldozersPowerLess100AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_BULLDOZERS_LESS_100HP_MORE_3_YEARS);
        Node bulldozersPowerBetween100and200LessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_100_AND_200HP_LESS_OR_3_YEARS);
        Node bulldozersPowerBetween100and200MoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_100_AND_200HP_MORE_3_YEARS);
        Node bulldozersPowerBetween200and300LessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_200_AND_300HP_LESS_OR_3_YEARS);
        Node bulldozersPowerBetween200and300MoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_200_AND_300HP_MORE_3_YEARS);
        Node bulldozersPowerBetween300and400LessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_300_AND_400HP_LESS_OR_3_YEARS);
        Node bulldozersPowerBetween300and400MoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_BULLDOZERS_BETWEEN_300_AND_400HP_MORE_3_YEARS);
        Node bulldozersPowerMore400LessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_BULLDOZERS_MORE_400HP_LESS_OR_3_YEARS);
        Node bulldozersPowerMore400MoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.SELF_PROPELLED_BULLDOZERS_MORE_400HP_MORE_3_YEARS);
        bulldozersPowerLess100.addKids(bulldozersPowerLess100AgeLessThreeYears, bulldozersPowerLess100AgeMoreThreeYears);
        bulldozersPowerBetween100and200.addKids(bulldozersPowerBetween100and200LessThreeYears, bulldozersPowerBetween100and200MoreThreeYears);
        bulldozersPowerBetween200and300.addKids(bulldozersPowerBetween200and300LessThreeYears, bulldozersPowerBetween200and300MoreThreeYears);
        bulldozersPowerBetween300and400.addKids(bulldozersPowerBetween300and400LessThreeYears, bulldozersPowerBetween300and400MoreThreeYears);
        bulldozersPowerMore400.addKids(bulldozersPowerMore400LessThreeYears, bulldozersPowerMore400MoreThreeYears);

        selfPropelledVehicles.addKids(help, graders, bulldozers);
        /*end self-propelled vehicles */

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, root);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
