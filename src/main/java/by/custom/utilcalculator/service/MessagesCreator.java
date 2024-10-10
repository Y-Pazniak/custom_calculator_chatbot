package by.custom.utilcalculator.service;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.Step;
import by.custom.utilcalculator.domain.constants.steps.StepsIndicator;
import by.custom.utilcalculator.domain.tree.CommandTree;
import by.custom.utilcalculator.domain.tree.ModifierTree;
import by.custom.utilcalculator.exception.UtilsborCommandTreeReadingException;

import java.util.List;
import java.util.Map;

import static by.custom.utilcalculator.domain.constants.steps.ParticularTransportType.*;
import static by.custom.utilcalculator.domain.constants.steps.EngineType.*;
import static by.custom.utilcalculator.domain.constants.steps.GeneralTransportType.*;
import static by.custom.utilcalculator.domain.constants.steps.TrailerO4Type.*;
import static by.custom.utilcalculator.domain.constants.steps.Weight.*;
import static by.custom.utilcalculator.domain.constants.steps.CountryOrigin.*;
import static by.custom.utilcalculator.domain.constants.steps.OwnersType.*;
import static by.custom.utilcalculator.domain.constants.steps.CarAge.*;
import static by.custom.utilcalculator.domain.constants.steps.TruckUnitClass.*;
import static by.custom.utilcalculator.domain.constants.steps.EngineVolumeOrPower.*;

public class MessagesCreator {
    private final BundleResourcesServant bundle;

    private MessagesCreator() {
        bundle = BundleResourcesServant.getInstance();
    }

    public static MessagesCreator getInstance() {
        return MessagesCreatorHolder.MESSAGES_CREATOR;
    }

    //this method builds next questions for user to interact with chatbot
    public String buildNextStepQuestion(final UserProgress userProgress) throws UtilsborCommandTreeReadingException {
        final Step step = userProgress.getNextStep();
        switch (step) {
            case GENERAL_TRANSPORT_TYPE -> {
                return getGreeting();
            }
            case PARTICULAR_TRANSPORT_TYPE -> {
                return getTransportType(userProgress);
            }
            case WEIGHT -> {
                return getTransportWeight(userProgress);
            }
            case ENGINE_TYPE -> {
                return getTypeOfEngine();
            }
            case ENGINE_VOLUME -> {
                return getEngineVolumeOrPower(userProgress);
            }
            case COUNTRY_ORIGIN -> {
                return getCountryOrigin();
            }
            case OWNERS_TYPE -> {
                return getTypeOfOwner();
            }
            case TRUCK_UNIT_CLASS -> {
                return getTruckUnitClass();
            }
            case TRAILERS_O4_TYPE -> {
                return getO4TrailersTypes();
            }
            case AGE -> {
                return getAgeAuto();
            }
            case FAREWELL -> {
                return getResultAndFarewell(userProgress);
            }
        }
        return bundle.getString("answers.sorry");
    }

    private String getO4TrailersTypes() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.weight.n1_n3"), "\n",
                Command.TRAILERS_04_TYPE.getCommand(), " ", bundle.getString("answers.details.trailersO4"), "\n",
                Command.HALF_TRAILERS_04_TYPE.getCommand(), " ", bundle.getString("answers.details.halftrailersO4"));
    }

    private String getTruckUnitClass() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.truck.type"), "\n",
                Command.TRUCK_UNITS_OTHER.getCommand(), " ", bundle.getString("answers.details.truck.except_6"), "\n",
                Command.TRUCK_UNITS_6_CLASS.getCommand(), " ", bundle.getString("answers.details.truck.6_class"));
    }

    public String getGreeting() {
        return stringBuilderAppender(bundle.getString("questions.users.greeting"), "\n",
                Command.M1.getCommand(), " ", bundle.getString("answers.details.m1"), "\n",
                Command.BUSES_AND_TRUCKS.getCommand(), " ", bundle.getString("answers.details.except_m1"), "\n",
                Command.SELF_PROPELLED_VEHICLES.getCommand(), " ", bundle.getString("answers.details.trailers"));
    }

    public String getCountryOrigin() {
        return stringBuilderAppender("\n", bundle.getString("questions.users.country"), "\n",
                Command.EAES.getCommand(), " ", bundle.getString("answers.details.eaes"), "\n",
                Command.OTHER_COUNTRIES.getCommand(), " ", bundle.getString("answers.details.other"));
    }

    public String getTransportType(final UserProgress userProgress) {
        switch (userProgress.getGeneralTransportType()) {
            case BUSES_AND_TRUCKS -> {
                return stringBuilderAppender(".", "\n", bundle.getString("questions.users.type_of_vehicle"), "\n",
                        Command.N1_N3.getCommand(), " ", bundle.getString("answers.details.n1_n3"), "\n",
                        Command.M2_M3.getCommand(), " ", bundle.getString("answers.details.m2_m3"), "\n",
                        Command.TRUCK_UNITS.getCommand(), " ", bundle.getString("answers.details.truck_units"), "\n",
                        Command.TRAILERS_O4.getCommand(), " ", bundle.getString("answers.details.trailers04"));
            }
            case SELF_PROPELLED_VEHICLES -> {
                return stringBuilderAppender(".", "\n", bundle.getString("questions.users.type_of_vehicle"), "\n",
                        Command.HELP.getCommand(), " ", bundle.getString("answers.details.self_propelled_help"), "\n",

                        Command.GRADERS.getCommand(), " ", bundle.getString("answers.details.graders"), "\n",
                        Command.BULLDOZERS.getCommand(), " ", bundle.getString("answers.details.bulldozers"), "\n",
                        Command.EXCAVATORS.getCommand(), " ", bundle.getString("answers.details.excavators"), "\n",
                        Command.WHEEL_LOADERS.getCommand(), " ", bundle.getString("answers.details.wheel_loaders"), "\n",
                        Command.TAMPING_MACHINES.getCommand(), " ", bundle.getString("answers.details.tamping_machines"), "\n",

                        Command.FRONT_LOADERS.getCommand(), " ", bundle.getString("answers.details.front_loaders"), "\n",
                        Command.WHEELED_CRANES.getCommand(), " ", bundle.getString("answers.details.wheeled_cranes"), "\n",
                        Command.PIPELAYERS.getCommand(), " ", bundle.getString("answers.details.pipelayers"), "\n",
                        Command.TRAILERS_OTHER.getCommand(), " ", bundle.getString("answers.details.trailers_other"), "\n",
                        Command.ROAD_MAINTENANCE.getCommand(), " ", bundle.getString("answers.details.road_maintenance_vehicles"), "\n",

                        Command.FORESTRY.getCommand(), " ", bundle.getString("answers.details.forestry_vehicles"), "\n",
                        Command.FORWADERS.getCommand(), " ", bundle.getString("answers.details.forwaders"), "\n",
                        Command.TIMBER_LOADERS.getCommand(), " ", bundle.getString("answers.details.timber_loaders"), "\n",
                        Command.WHEELED_TRACTORS.getCommand(), " ", bundle.getString("answers.details.wheeled_tractors"), "\n",
                        Command.CRAWLER_TRACTORS.getCommand(), " ", bundle.getString("answers.details.crawler_tractors"), "\n",

                        Command.COMBINE_HARVESTERS.getCommand(), " ", bundle.getString("answers.details.combine_harvesters"), "\n",
                        Command.FORAGE_HARVESTERS.getCommand(), " ", bundle.getString("answers.details.forage_harvesters"), "\n",
                        Command.AGRICULTURAL_VEHICLES.getCommand(), " ", bundle.getString("answers.details.agricultural_vehicles"), "\n",
                        Command.OFF_ROAD_DUMP_TRUCKS.getCommand(), " ", bundle.getString("answers.details.off_road_dump_trucks"));
            }
        }
        return "unknown general transport type";
    }

    public String getTransportWeight(final UserProgress userProgress) {
        switch (userProgress.getParticularTransportType()) {
            case N1_N3 -> {
                return stringBuilderAppender(".", "\n", bundle.getString("questions.users.weight.n1_n3"), "\n",
                        Command.LESS_2P5_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.less_2_tons"), "\n",
                        Command.BETWEEN_2_5_AND_3_5_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_2d5_and_3d5_tons"), "\n",
                        Command.BETWEEN_3_5_AND_5_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_3d5_and_5_tons"), "\n",
                        Command.BETWEEN_5_AND_8_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_5_and_8_tons"), "\n",
                        Command.BETWEEN_8_AND_12_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_8_and_12_tons"), "\n",
                        Command.BETWEEN_12_AND_20_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_12_and_20_tons"), "\n",
                        Command.BETWEEN_20_AND_50_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_20_and_50_tons"));
            }
            case TRUCK_UNITS -> {
                return stringBuilderAppender(".", "\n", bundle.getString("questions.users.weight.n1_n3"), "\n",
                        Command.TRUCK_UNITS_12_20_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_12_and_20_tons"), "\n",
                        Command.TRUCK_UNITS_20_50_TONS.getCommand(), " ", bundle.getString("answers.details.weight.n1_n3.between_20_and_50_tons"));
            }
            case null, default -> {
            }
        }
        return "unknown weight or type";
    }

    public String getTypeOfEngine() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.type.engine"), "\n",
                Command.GASOLINE.getCommand(), " ", bundle.getString("answers.details.gas.engine"), "\n",
                Command.ELECTRIC.getCommand(), " ", bundle.getString("answers.details.electric.engine"));
    }

    public String getEngineVolumeOrPower(final UserProgress userProgress) {
        switch (userProgress.getGeneralTransportType()) {
            case M1 -> {
                return stringBuilderAppender(".", "\n", bundle.getString("questions.users.volume.engine"), "\n",
                        Command.VOLUME_LESS_1000_CM.getCommand(), " ", bundle.getString("answers.details.less.1000"), "\n",
                        Command.VOLUME_BETWEEN_1000_2000_CM.getCommand(), " ", bundle.getString("answers.details.between.1000.2000"), "\n",
                        Command.VOLUME_BETWEEN_2000_3000_CM.getCommand(), " ", bundle.getString("answers.details.between.2000.3000"), "\n",
                        Command.VOLUME_BETWEEN_3000_3500_CM.getCommand(), " ", bundle.getString("answers.details.between.3000.3500"), "\n",
                        Command.VOLUME_MORE_3500_CM.getCommand(), " ", bundle.getString("answers.details.more.3500"));
            }
            case BUSES_AND_TRUCKS -> {
                return stringBuilderAppender(".", "\n", bundle.getString("questions.users.volume.engine"), "\n",
                        Command.VOLUME_LESS_2500_CM.getCommand(), " ", bundle.getString("answers.summary.volume.m2_m3.less_2500"), "\n",
                        Command.VOLUME_BETWEEN_2500_5000_CM.getCommand(), " ", bundle.getString("answers.summary.volume.m2_m3.between_2500_5000"), "\n",
                        Command.VOLUME_BETWEEN_5000_10000_CM.getCommand(), " ", bundle.getString("answers.summary.volume.m2_m3.between_5000_10000"), "\n",
                        Command.VOLUME_MORE_10000_CM.getCommand(), " ", bundle.getString("answers.summary.volume.m2_m3.more_10000"));
            }
            case SELF_PROPELLED_VEHICLES -> {
                switch (userProgress.getParticularTransportType()) {
                    case GRADER -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_LESS_100.getCommand(), " ", bundle.getString("answers.details.graders.100"), "\n",
                                Command.POWER_100_140.getCommand(), " ", bundle.getString("answers.details.graders.140"), "\n",
                                Command.POWER_140_200.getCommand(), " ", bundle.getString("answers.details.graders.200"), "\n",
                                Command.POWER_MORE_200.getCommand(), " ", bundle.getString("answers.details.graders.more_200"));
                    }
                    case BULLDOZER -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_LESS_100.getCommand(), " ", bundle.getString("answers.details.graders.100"), "\n",
                                Command.POWER_100_200.getCommand(), " ", bundle.getString("answers.details.bulldozers.200"), "\n",
                                Command.POWER_200_300.getCommand(), " ", bundle.getString("answers.details.bulldozers.300"), "\n",
                                Command.POWER_300_400.getCommand(), " ", bundle.getString("answers.details.bulldozers.400"), "\n",
                                Command.POWER_MORE_400.getCommand(), " ", bundle.getString("answers.details.bulldozers.more_400"));
                    }
                    case EXCAVATOR, WHEELED_CRANES -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_LESS_170.getCommand(), " ", bundle.getString("answers.details.excavators.170"), "\n",
                                Command.POWER_170_250.getCommand(), " ", bundle.getString("answers.details.excavators.250"), "\n",
                                Command.POWER_MORE_250.getCommand(), " ", bundle.getString("answers.details.excavators.more_250"));
                    }
                    case WHEEL_LOADER -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_LESS_100.getCommand(), " ", bundle.getString("answers.details.graders.100"), "\n",
                                Command.POWER_100_125.getCommand(), " ", bundle.getString("answers.details.wheel_loaders.100_125"), "\n",
                                Command.POWER_125_150.getCommand(), " ", bundle.getString("answers.details.wheel_loaders.125_150"), "\n",
                                Command.POWER_MORE_150.getCommand(), " ", bundle.getString("answers.details.excavators.more_150"));
                    }
                    case TAMPING_MACHINE -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_LESS_40.getCommand(), " ", bundle.getString("answers.details.power.less_40"), "\n",
                                Command.POWER_40_80.getCommand(), " ", bundle.getString("answers.details.power.40_80"), "\n",
                                Command.POWER_MORE_80.getCommand(), " ", bundle.getString("answers.details.power.more_80"));
                    }
                    case FRONT_LOADER -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_5_50.getCommand(), " ", bundle.getString("answers.details.power.5p5_50"), "\n",
                                Command.POWER_50_100.getCommand(), " ", bundle.getString("answers.details.power.50_100"), "\n",
                                Command.POWER_100_200.getCommand(), " ", bundle.getString("answers.details.bulldozers.200"), "\n",
                                Command.POWER_200_250.getCommand(), " ", bundle.getString("answers.details.power.200_250"), "\n",
                                Command.POWER_250_300.getCommand(), " ", bundle.getString("answers.details.power.250_300"), "\n",
                                Command.POWER_300_400.getCommand(), " ", bundle.getString("answers.details.bulldozers.400"), "\n",
                                Command.POWER_MORE_400.getCommand(), " ", bundle.getString("answers.details.bulldozers.more_400"));
                    }
                    case PIPELAYERS -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_LESS_130.getCommand(), " ", bundle.getString("answers.details.power.less_130"), "\n",
                                Command.POWER_130_200.getCommand(), " ", bundle.getString("answers.details.power.130_200"), "\n",
                                Command.POWER_200_300.getCommand(), " ", bundle.getString("answers.details.bulldozers.300"), "\n",
                                Command.POWER_MORE_300.getCommand(), " ", bundle.getString("answers.details.power.more_300"));
                    }
                    case TRAILERS_OTHER -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("answers.details.trailersOther_lifting_capacity"), "\n",
                                String.format("%s %s", Command.HELP.getCommand(), bundle.getString("answers.details.trailers_other_lifting_capacity")), "\n",
                                Command.TRAILERS_OTHER_FULL.getCommand(), " ", bundle.getString("answers.details.trailersOther"), "\n",
                                Command.TRAILERS_OTHER_HALF.getCommand(), " ", bundle.getString("answers.details.halftrailersOther"), "\n");
                    }
                    case ROAD_MAINTENANCE -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_LESS_100.getCommand(), " ", bundle.getString("answers.details.graders.100"), "\n",
                                Command.POWER_BETWEEN_100_220.getCommand(), " ", bundle.getString("answers.details.power.100_220"), "\n",
                                Command.POWER_MORE_220.getCommand(), " ", bundle.getString("answers.details.power.more_220"));
                    }
                    case FORESTRY, FORWADERS, TIMBER_LOADERS -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_BETWEEN_20_100.getCommand(), " ", bundle.getString("answers.details.power.20_100"), "\n",
                                Command.POWER_BETWEEN_100_300.getCommand(), " ", bundle.getString("answers.details.power.100_300"), "\n",
                                Command.POWER_MORE_300.getCommand(), " ", bundle.getString("answers.details.power.more_300"));
                    }
                    case WHEELED_TRACTORS -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_BETWEEN_5p5_30.getCommand(), " ", bundle.getString("answers.details.power.5p5_30"), "\n",
                                Command.POWER_BETWEEN_30_60.getCommand(), " ", bundle.getString("answers.details.power.30_60"), "\n",
                                Command.POWER_BETWEEN_60_90.getCommand(), " ", bundle.getString("answers.details.power.60_90"), "\n",
                                Command.POWER_BETWEEN_90_130.getCommand(), " ", bundle.getString("answers.details.power.90_130"), "\n",
                                Command.POWER_BETWEEN_130_180.getCommand(), " ", bundle.getString("answers.details.power.130_180"), "\n",
                                Command.POWER_BETWEEN_180_220.getCommand(), " ", bundle.getString("answers.details.power.180_220"), "\n",
                                Command.POWER_BETWEEN_220_280.getCommand(), " ", bundle.getString("answers.details.power.220_280"), "\n",
                                Command.POWER_BETWEEN_280_340.getCommand(), " ", bundle.getString("answers.details.power.280_340"), "\n",
                                Command.POWER_BETWEEN_340_380.getCommand(), " ", bundle.getString("answers.details.power.340_380"), "\n",
                                Command.POWER_MORE_380.getCommand(), " ", bundle.getString("answers.details.power.more_380"));
                    }
                    case CRAWLER_TRACTORS -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_LESS_100.getCommand(), " ", bundle.getString("answers.details.graders.100"), "\n",
                                Command.POWER_100_200.getCommand(), " ", bundle.getString("answers.details.bulldozers.200"), "\n",
                                Command.POWER_MORE_200.getCommand(), " ", bundle.getString("answers.details.graders.more_200"));
                    }
                    case COMBINE_HARVESTERS -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_BETWEEN_25_160.getCommand(), " ", bundle.getString("answers.details.power.25_160"), "\n",
                                Command.POWER_BETWEEN_160_220.getCommand(), " ", bundle.getString("answers.details.power.160_220"), "\n",
                                Command.POWER_BETWEEN_220_255.getCommand(), " ", bundle.getString("answers.details.power.220_255"), "\n",
                                Command.POWER_BETWEEN_255_325.getCommand(), " ", bundle.getString("answers.details.power.255_325"), "\n",
                                Command.POWER_BETWEEN_325_400.getCommand(), " ", bundle.getString("answers.details.power.325_400"), "\n",
                                Command.POWER_MORE_400.getCommand(), " ", bundle.getString("answers.details.bulldozers.more_400"));
                    }
                    case FORAGE_HARVESTERS -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_LESS_295.getCommand(), " ", bundle.getString("answers.details.power.less_295"), "\n",
                                Command.POWER_BETWEEN_295_401.getCommand(), " ", bundle.getString("answers.details.power.295_401"), "\n",
                                Command.POWER_MORE_401.getCommand(), " ", bundle.getString("answers.details.power.more_401"));
                    }
                    case AGRICULTURAL_VEHICLES -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_BETWEEN_100_120.getCommand(), " ", bundle.getString("answers.details.power.100_120"), "\n",
                                Command.POWER_BETWEEN_120_300.getCommand(), " ", bundle.getString("answers.details.power.120_300"), "\n",
                                Command.POWER_MORE_300.getCommand(), " ", bundle.getString("answers.details.power.more_300"), "\n",
                                Command.SELF_PROPELLED_MOWERS.getCommand(), " ", bundle.getString("answers.details.power.self_mowers"));
                    }
                    case OFF_ROAD_DUMP_TRUCKS -> {
                        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.power"), "\n",
                                getHelpSelfpropelledPowerString(), "\n",
                                Command.POWER_LESS_200.getCommand(), " ", bundle.getString("answers.details.power.less_200"), "\n",
                                Command.POWER_BETWEEN_200_650.getCommand(), " ", bundle.getString("answers.details.power.200_650"), "\n",
                                Command.POWER_BETWEEN_650_1750.getCommand(), " ", bundle.getString("answers.details.power.650_1750"), "\n",
                                Command.POWER_MORE_1750.getCommand(), " ", bundle.getString("answers.details.power.more_1750"));
                    }
                }
            }
        }
        return "unknown engine volume";
    }

    public String getAgeAuto() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.age.auto"), "\n",
                Command.LESS_3_YEARS_AGE.getCommand(), " ", bundle.getString("answers.details.before.3"), "\n",
                Command.MORE_THAN_3_YEARS_AGE.getCommand(), " ", bundle.getString("answers.details.between.3.and.7"), "\n");
    }

    public String getResultAndFarewell(final UserProgress userProgress) throws UtilsborCommandTreeReadingException {
        return stringBuilderAppender("." +
                        "\n" +
                        bundle.getString("answers.summary.price") + " " + ModifierTree.getPrice(userProgress) + " " +
                        bundle.getString("answers.summary.byn") + "\n",
                bundle.getString("answers.summary.goodbye.add.info"));
    }

    public String getTypeOfOwner() {
        return stringBuilderAppender(".", "\n", bundle.getString("questions.users.physical.or.juridical"), "\n",
                Command.PHYSICAL.getCommand(), " ", bundle.getString("answers.details.physical"), "\n",
                Command.JURIDICAL.getCommand(), " ", bundle.getString("answers.details.juridical"));
    }

    public String getSorry() {
        return bundle.getString("answers.sorry");
    }

    //this method builds summary string with user choices (just to show the user's path)
    public String getUserChoiceSequence(final UserProgress userProgress) {
        final StringBuilder sb = new StringBuilder();
        sb.append(bundle.getString("answers.summary.beginning"));
        List<Command> userPath = userProgress.getUserPath();

        for (int i = 0; i < userPath.size(); i++) {
            Command localCommand = userPath.get(i);
            for (Map.Entry<StepsIndicator, Command> entry : CommandTree.getInstance().getFieldsToCommands().entrySet()) {
                if (localCommand.equals(entry.getValue())) {
                    switch (entry.getKey()) {
                        case M1, BUSES_AND_TRUCKS, SELF_PROPELLED_VEHICLES ->
                                addSequenceGeneralTransportType(userProgress, sb);
                        case N1_N3, M2_M3, TRUCK_UNITS, TRAILERS_O4, GRADER, BULLDOZER, EXCAVATOR, WHEEL_LOADER,
                             TAMPING_MACHINE, FRONT_LOADER, WHEELED_CRANES, PIPELAYERS, TRAILERS_OTHER,
                             ROAD_MAINTENANCE, FORESTRY, FORWADERS, TIMBER_LOADERS, WHEELED_TRACTORS, CRAWLER_TRACTORS,
                             COMBINE_HARVESTERS, FORAGE_HARVESTERS, AGRICULTURAL_VEHICLES, OFF_ROAD_DUMP_TRUCKS ->
                                addSequenceParticularTransportType(userProgress, sb);
                        case LESS_2P5_TONS, BETWEEN_2_5_AND_3_5, BETWEEN_3_5_AND_5, BETWEEN_5_AND_8, BETWEEN_8_AND_12,
                             BETWEEN_12_AND_20, BETWEEN_20_AND_50, FROM_12_TILL_20_TONS, FROM_20_TILL_50_TONS ->
                                addSequenceWeight(userProgress, sb);
                        case EAES, OTHER -> addSequenceCountryOrigin(userProgress, sb);
                        case PHYSICAL, JURIDICAL -> addSequenceOwnerType(userProgress, sb);
                        case LESS_OR_3_YEARS, MORE_3_YEARS -> addSequenceAge(userProgress, sb);
                        case TRUCK_UNITS_EXCEPT_6_CLASS, TRUCK_UNITS_6_CLASS ->
                                addSequenceTruckUnitClass(userProgress, sb);
                        case TRAILERS, HALF_TRAILERS -> addSequenceTrailerType(userProgress, sb);
                        case GASOLINE, ELECTRIC -> addSequenceEngineType(userProgress, sb);
                        case LESS_1000, BETWEEN_1000_AND_2000, BETWEEN_2000_AND_3000, BETWEEN_3000_AND_3500, MORE_3500,
                             LESS_2500, BETWEEN_2500_AND_5000, BETWEEN_5000_AND_10000, MORE_10000, BETWEEN_5p5_30,
                             BETWEEN_30_60, BETWEEN_60_90, BETWEEN_90_130, BETWEEN_130_180, BETWEEN_180_220,
                             BETWEEN_220_280, BETWEEN_280_340, BETWEEN_340_380, MORE_380, BETWEEN_25_160,
                             BETWEEN_160_220, BETWEEN_220_255, BETWEEN_255_325, BETWEEN_325_400, BETWEEN_5_50, LESS_40,
                             BETWEEN_40_80, BETWEEN_50_100, MORE_80, LESS_100, BETWEEN_20_100, BETWEEN_100_125,
                             BETWEEN_100_140, BETWEEN_100_300, BETWEEN_125_150, LESS_130, MORE_150, BETWEEN_130_200,
                             BETWEEN_140_200, LESS_170, MORE_200, BETWEEN_170_250, MORE_250, BETWEEN_100_200,
                             BETWEEN_100_220, MORE_220, BETWEEN_200_250, BETWEEN_250_300, BETWEEN_200_300, MORE_300,
                             BETWEEN_300_400, MORE_400, TRAILERS_OTHER_FULL, TRAILERS_OTHER_HALF, LESS_295,
                             BETWEEN_295_401, MORE_401, BETWEEN_100_120, BETWEEN_120_300, LESS_200, BETWEEN_200_650,
                             BETWEEN_650_1750, MORE_1750, SELF_PROPELLED_MOWERS ->
                                addSequenceEngineVolumeOrPower(userProgress, sb);
                        case null, default -> {
                        }
                    }
                    break;
                }
            }
        }


        return sb.toString();
    }

    private void addSequenceGeneralTransportType(final UserProgress userProgress, final StringBuilder sb) {
        switch (userProgress.getGeneralTransportType()) {
            case null -> {
            }
            case M1 -> sb.append(bundle.getString("answers.summary.m1"));
            case BUSES_AND_TRUCKS -> sb.append(bundle.getString("answers.summary.buses_and_trucks"));
            case SELF_PROPELLED_VEHICLES -> sb.append(bundle.getString("answers.summary.self_propelled_vehicles"));
        }
    }

    private void addSequenceParticularTransportType(final UserProgress userProgress, final StringBuilder sb) {
        switch (userProgress.getParticularTransportType()) {
            case null -> {
            }
            case N1_N3 -> sb.append(bundle.getString("answers.summary.n1_n3"));
            case M2_M3 -> sb.append(bundle.getString("answers.summary.m2_m3"));
            case TRUCK_UNITS -> sb.append(bundle.getString("answers.summary.truck_unit"));
            case TRAILERS_O4 -> sb.append(bundle.getString("answers.summary.trailersO4"));

            case GRADER -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.grader")));
            }
            case BULLDOZER -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.bulldozer")));
            }
            case EXCAVATOR -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.excavator")));
            }
            case WHEEL_LOADER -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.wheel_loader")));
            }
            case TAMPING_MACHINE -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.tamping_machines")));
            }
            case FRONT_LOADER -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.front_loaders")));
            }
            case WHEELED_CRANES -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.wheeled_cranes")));
            }
            case PIPELAYERS -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.pipelayers")));
            }
            case TRAILERS_OTHER -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.trailers_other")));
            }
            case ROAD_MAINTENANCE -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.road_maintenance_vehicles")));
            }
            case FORESTRY -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.forestry_vehicles")));
            }
            case FORWADERS -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.forwaders")));
            }
            case TIMBER_LOADERS -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.timber_loaders")));
            }
            case WHEELED_TRACTORS -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.wheeled_tractors")));
            }
            case CRAWLER_TRACTORS -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.crawler_tractors")));
            }
            case COMBINE_HARVESTERS -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.combine_harvesters")));
            }
            case FORAGE_HARVESTERS -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.forage_harvesters")));
            }
            case AGRICULTURAL_VEHICLES -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.agricultural_vehicles")));
            }
            case OFF_ROAD_DUMP_TRUCKS -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.off_road_dump_trucks")));
            }
        }
    }

    private void addSequenceWeight(final UserProgress userProgress, final StringBuilder sb) {
        switch (userProgress.getWeight()) {
            case null -> {
            }
            case LESS_2P5_TONS -> sb.append(bundle.getString("answers.summary.weight.n1_n3.less_2_tons"));
            case BETWEEN_2_5_AND_3_5 ->
                    sb.append(bundle.getString("answers.summary.weight.n1_n3.between_2d5_and_3d5_tons"));
            case BETWEEN_3_5_AND_5 ->
                    sb.append(bundle.getString("answers.summary.weight.n1_n3.between_3d5_and_5_tons"));
            case BETWEEN_5_AND_8 -> sb.append(bundle.getString("answers.summary.weight.n1_n3.between_5_and_8_tons"));
            case BETWEEN_8_AND_12 -> sb.append(bundle.getString("answers.summary.weight.n1_n3.between_8_and_12_tons"));
            case BETWEEN_12_AND_20, FROM_12_TILL_20_TONS ->
                    sb.append(bundle.getString("answers.summary.weight.n1_n3.between_12_and_20_tons"));
            case BETWEEN_20_AND_50, FROM_20_TILL_50_TONS ->
                    sb.append(bundle.getString("answers.summary.weight.n1_n3.between_20_and_50_tons"));
        }
    }

    private void addSequenceCountryOrigin(final UserProgress userProgress, final StringBuilder sb) {
        switch (userProgress.getCountryOrigin()) {
            case null -> {
            }
            case EAES -> sb.append(bundle.getString("answers.summary.eaes"));
            case OTHER -> sb.append(bundle.getString("answers.summary.other"));
        }
    }

    private void addSequenceOwnerType(final UserProgress userProgress, final StringBuilder sb) {
        switch (userProgress.getOwnersType()) {
            case null -> {
            }
            case PHYSICAL -> sb.append(bundle.getString("answers.summary.physical"));
            case JURIDICAL -> sb.append(bundle.getString("answers.summary.juridical"));
        }
    }

    private void addSequenceAge(final UserProgress userProgress, final StringBuilder sb) {
        switch (userProgress.getCarAge()) {
            case null -> {
            }
            case LESS_OR_3_YEARS -> sb.append(bundle.getString("answers.summary.less.3"));
            case MORE_3_YEARS -> sb.append(bundle.getString("answers.summary.between.3.and.7"));
        }
    }

    private void addSequenceTruckUnitClass(final UserProgress userProgress, final StringBuilder sb) {
        switch (userProgress.getTruckUnitClass()) {
            case null -> {
            }
            case TRUCK_UNITS_6_CLASS -> sb.append(bundle.getString("answers.summary.truck.6_class"));
            case TRUCK_UNITS_EXCEPT_6_CLASS -> sb.append(bundle.getString("answers.summary.truck.except_6"));
        }
    }

    private void addSequenceTrailerType(final UserProgress userProgress, final StringBuilder sb) {
        switch (userProgress.getTrailerO4Type()) {
            case null -> {
            }
            case TRAILERS -> sb.append(bundle.getString("answers.summary.trailersO4details"));
            case HALF_TRAILERS -> sb.append(bundle.getString("answers.summary.halftrailersO4details"));
        }
    }

    private void addSequenceEngineType(final UserProgress userProgress, final StringBuilder sb) {
        String s = userProgress.getEngineType() == ELECTRIC ? bundle.getString("answers.summary.electro") : bundle.getString("answers.summary.gas");
        sb.append(s);
    }

    private void addSequenceEngineVolumeOrPower(final UserProgress userProgress, final StringBuilder sb) {
        switch (userProgress.getVolumeOrPower()) {
            case null -> {
            }
            case LESS_1000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.less.1000")));
            }
            case BETWEEN_1000_AND_2000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.between.1000.2000")));
            }
            case BETWEEN_2000_AND_3000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.between.2000.3000")));
            }
            case BETWEEN_3000_AND_3500 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.between.3000.3500")));
            }
            case MORE_3500 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.more.3500")));
            }
            case LESS_2500 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.volume.m2_m3.less_2500")));
            }
            case BETWEEN_2500_AND_5000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.volume.m2_m3.between_2500_5000")));
            }
            case BETWEEN_5000_AND_10000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.volume.m2_m3.between_5000_10000")));
            }
            case MORE_10000 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.summary.volume.m2_m3.more_10000")));
            }
            case LESS_40 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.less_40")));
            }
            case BETWEEN_40_80 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.40_80")));
            }
            case MORE_80 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.more_80")));
            }
            case LESS_100 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.graders.100")));
            }
            case BETWEEN_100_125 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.wheel_loaders.100_125")));
            }
            case BETWEEN_100_140 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.graders.140")));
            }
            case BETWEEN_125_150 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.wheel_loaders.125_150")));
            }
            case MORE_150 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.excavators.more_150")));
            }
            case BETWEEN_140_200 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.graders.200")));
            }
            case LESS_170 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.excavators.170")));
            }
            case MORE_200 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.graders.more_200")));
            }
            case BETWEEN_170_250 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.excavators.250")));
            }
            case MORE_250 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.excavators.more_250")));
            }
            case BETWEEN_100_200 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.bulldozers.200")));
            }
            case BETWEEN_200_300 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.bulldozers.300")));
            }
            case BETWEEN_300_400 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.bulldozers.400")));
            }
            case MORE_400 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.bulldozers.more_400")));
            }
            case BETWEEN_5_50 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.5p5_50")));
            }
            case BETWEEN_50_100 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.50_100")));
            }
            case BETWEEN_200_250 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.200_250")));
            }
            case BETWEEN_250_300 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.250_300")));
            }
            case LESS_130 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.less_130")));
            }
            case BETWEEN_130_200 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.130_200")));
            }
            case BETWEEN_100_220 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.100_220")));
            }
            case MORE_220 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.more_220")));
            }
            case MORE_300 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.more_300")));
            }
            case TRAILERS_OTHER_FULL -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.trailersOther")));
            }
            case TRAILERS_OTHER_HALF -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.halftrailersOther")));
            }
            case BETWEEN_20_100 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.20_100")));
            }
            case BETWEEN_100_300 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.100_300")));
            }
            case BETWEEN_5p5_30 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.5p5_30")));
            }
            case BETWEEN_30_60 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.30_60")));
            }
            case BETWEEN_60_90 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.60_90")));
            }
            case BETWEEN_90_130 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.90_130")));

            }
            case BETWEEN_130_180 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.130_180")));
            }
            case BETWEEN_180_220 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.180_220")));
            }
            case BETWEEN_220_280 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.220_280")));
            }
            case BETWEEN_280_340 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.280_340")));
            }
            case BETWEEN_340_380 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.340_380")));
            }
            case MORE_380 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.more_380")));
            }
            case BETWEEN_25_160 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.25_160")));
            }
            case BETWEEN_160_220 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.160_220")));
            }
            case BETWEEN_220_255 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.220_255")));
            }
            case BETWEEN_255_325 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.255_325")));
            }
            case BETWEEN_325_400 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.325_400")));
            }
            case LESS_295 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.less_295")));
            }
            case BETWEEN_295_401 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.295_401")));
            }
            case MORE_401 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.more_401")));
            }
            case BETWEEN_100_120 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.100_120")));
            }
            case BETWEEN_120_300 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.120_300")));
            }
            case SELF_PROPELLED_MOWERS -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.self_mowers")));
            }
            case LESS_200 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.less_200")));
            }
            case BETWEEN_200_650 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.200_650")));
            }
            case BETWEEN_650_1750 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.650_1750")));
            }
            case MORE_1750 -> {
                sb.append(",");
                sb.append(trimFirstAndLastLetters(bundle.getString("answers.details.power.more_1750")));
            }
        }
    }

    private String stringBuilderAppender(final String... strings) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    private String getHelpSelfpropelledPowerString() {
        return String.format("%s %s", Command.HELP.getCommand(), bundle.getString("answers.details.self_propelled_power"));
    }

    public String getSelfPropelledHelp() {
        return stringBuilderAppender(bundle.getString("answers.help.self_propelled"), "\n", "\n",
                bundle.getString("answers.help.trailers_for_self_propelled"), "\n", "\n",
                bundle.getString("answers.help.friendly_advice"));
    }

    public String getSelfPropelledPowerHelp() {
        return stringBuilderAppender(bundle.getString("answers.help.self_propelled_power"), "\n", "\n",
                bundle.getString("answers.help.friendly_advice"));
    }

    public String getTrailersOtherHelp() {
        return stringBuilderAppender(bundle.getString("answers.help.trailers_lifting_capacity"), "\n", "\n",
                bundle.getString("answers.help.friendly_advice"));
    }

    public String getSummaryAnswer(final UserProgress userProgress) {
        try {
            return getUserChoiceSequence(userProgress) + buildNextStepQuestion(userProgress);
        } catch (UtilsborCommandTreeReadingException e) {
            throw new RuntimeException(e);
        }
    }

    private String trimFirstAndLastLetters(final String toTrim) {
        return toTrim.substring(1, toTrim.length() - 1);
    }

    private static class MessagesCreatorHolder {
        private static final MessagesCreator MESSAGES_CREATOR = new MessagesCreator();
    }
}