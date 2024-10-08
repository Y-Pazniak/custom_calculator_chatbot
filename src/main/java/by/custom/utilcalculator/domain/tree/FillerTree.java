package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.Price;
import by.custom.utilcalculator.domain.constants.steps.Step;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FillerTree {
    public static void fillTreeJson(final ObjectMapper mapper, final File file) {
        //root node - start
        Node root = new Node(Command.START, "base node to start - runs automatically", Step.GENERAL_TRANSPORT_TYPE, null);
        //general transport type
        Node m1 = new Node(Command.M1, "M1 - passenger's vehicles", Step.COUNTRY_ORIGIN, null);
        root.addKid(m1);
        //country origin
        Node eaes = new Node(Command.EAES, "branch for M1 vehicles from EAES (Armenia, Belarus, Kazakhstan, Kyrgyzstan, Russia)", Step.OWNERS_TYPE, null);
        Node otherCountries = new Node(Command.OTHER_COUNTRIES, "it means any country except EAES countries", Step.OWNERS_TYPE, null);
        m1.addKids(eaes, otherCountries);
        //owners type - eaes
        Node physical = new Node(Command.PHYSICAL, "common person, who does not represent any company", Step.AGE, null);
        Node juridical = new Node(Command.JURIDICAL, "branch for companies who want to purchase a passenger M1 car from EAES", Step.AGE, null);
        eaes.addKids(physical, juridical);
        //owners type - other countries
        Node otherCountriesJuridical = new Node(Command.JURIDICAL, "branch for companies who want to purchase a passenger M1 car except EAES", Step.ENGINE_TYPE, null);
        otherCountries.addKids(physical, otherCountriesJuridical);
        //m1 - other countries - juridical - type of engine
        Node electricTypeEngine = new Node(Command.ELECTRIC, "if car has pure electric engine, it will be different branch", Step.AGE, null);
        Node gasolineTypeEngine = new Node(Command.GASOLINE, "if car has gasoline or hybrid engine, it will be different branch", Step.ENGINE_VOLUME, null);
        otherCountriesJuridical.addKids(electricTypeEngine, gasolineTypeEngine);
        //m1 - other - juridical - gasoline - engine volume
        Node gasolineM1Less1000 = new Node(Command.VOLUME_LESS_1000_CM, "", Step.AGE, null);
        Node gasolineM1Between1000_2000 = new Node(Command.VOLUME_BETWEEN_1000_2000_CM, "", Step.AGE, null);
        Node gasolineM1Between2000_3000 = new Node(Command.VOLUME_BETWEEN_2000_3000_CM, "", Step.AGE, null);
        Node gasolineM1Between3000_3500 = new Node(Command.VOLUME_BETWEEN_3000_3500_CM, "", Step.AGE, null);
        Node gasolineM1more3500 = new Node(Command.VOLUME_MORE_3500_CM, "", Step.AGE, null);
        gasolineTypeEngine.addKids(gasolineM1Less1000, gasolineM1Between1000_2000, gasolineM1Between2000_3000, gasolineM1Between3000_3500, gasolineM1more3500);
        //age eaes
        Node threeYearsAndLessM1 = new Node(Command.LESS_3_YEARS_AGE, "three and less years old car", Step.FAREWELL, Price.PASSENGER_3_OR_LESS_YEARS);
        Node moreThanThreeYearsM1 = new Node(Command.MORE_THAN_3_YEARS_AGE, "more than three years old car", Step.FAREWELL, Price.PASSENGER_MORE_3_YEARS);
        physical.addKids(threeYearsAndLessM1, moreThanThreeYearsM1);
        juridical.addKids(threeYearsAndLessM1, moreThanThreeYearsM1);
        //age other
        Node gasolineM1Less1000AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_1000_LESS_OR_3_YEARS);
        Node gasolineM1Less1000AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_1000_MORE_3_YEARS);
        Node gasolineM1Between1000And2000AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_1000_2000_LESS_OR_3_YEARS);
        Node gasolineM1Between1000And2000AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_1000_2000_MORE_3_YEARS);
        Node gasolineM1Between2000And3000AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_2000_3000_LESS_OR_3_YEARS);
        Node gasolineM1Between2000And3000AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_2000_3000_MORE_3_YEARS);
        Node gasolineM1Between3000And3500AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_3000_3500_LESS_OR_3_YEARS);
        Node gasolineM1Between3000And3500AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_3000_3500_LESS_OR_3_YEARS);
        Node gasolineM1More3500AgeLessThreeYears = new Node(Command.LESS_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_3500_LESS_OR_3_YEARS);
        Node gasolineM1More3500AgeMoreThreeYears = new Node(Command.MORE_THAN_3_YEARS_AGE, "", Step.FAREWELL, Price.PASSENGER_OTHER_GASOLINE_3500_MORE_3_YEARS);
        electricTypeEngine.addKids(threeYearsAndLessM1, moreThanThreeYearsM1);
        gasolineM1Less1000.addKids(gasolineM1Less1000AgeLessThreeYears, gasolineM1Less1000AgeMoreThreeYears);
        gasolineM1Between1000_2000.addKids(gasolineM1Between1000And2000AgeLessThreeYears, gasolineM1Between1000And2000AgeMoreThreeYears);
        gasolineM1Between2000_3000.addKids(gasolineM1Between2000And3000AgeLessThreeYears, gasolineM1Between2000And3000AgeMoreThreeYears);
        gasolineM1Between3000_3500.addKids(gasolineM1Between3000And3500AgeLessThreeYears, gasolineM1Between3000And3500AgeMoreThreeYears);
        gasolineM1more3500.addKids(gasolineM1More3500AgeLessThreeYears, gasolineM1More3500AgeMoreThreeYears);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, root);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
