package by.custom.utilcalculator.domain.constants;

import by.custom.utilcalculator.domain.constants.steps.Step;

import javax.annotation.Nullable;

public enum Command {
    //command families - not commands actually, just markers
    AGE("age", null, Step.FAREWELL),
    M1_GASOLINE_ENGINE_VOLUME("volume", null, Step.CAR_AGE),
    N1_N3_WEIGHT("n1_n3_weight", null, Step.CAR_AGE),
    M2_M3_GASOLINE_ENGINE_VOLUME("m2_m3_volume", null, Step.CAR_AGE),
    TRUCK_UNIT_WEIGHT("truck_unit_weight", null, Step.CAR_AGE),
    TRAILERS_O4_TYPE("trailers_o4_type", null, Step.CAR_AGE),
    SELF_PROPELLED_TYPE("self_propelled_type", null, Step.SELF_PROPELLED_POWER),
    SELF_PROPELLED_POWER("self_propelled_power", null, Step.CAR_AGE),

    //main command - start the calculator
    START("/start", null, Step.GENERAL_TRANSPORT_TYPE),

    //general type of vehicle
    M1("/cars", null, Step.COUNTRY_ORIGIN),
    BUSES_AND_TRUCKS("/buses_and_trucks", null, Step.BUSES_AND_TRUCKS_TYPES),
    SELF_PROPELLED_VEHICLES("/self_propelled_vehicles", null, Step.SELF_PROPELLED_TYPES),

    //type of except m1 vehicle - buses and trucks
    N1_N3("/n1_n3", null, Step.N1_N3_WEIGHT),
    M2_M3("/m2_m3", null, Step.M2_M3_ENGINE_TYPE),
    TRUCK_UNITS("/truck_units", null, Step.TRUCK_UNIT_CLASS),
    TRAILERS_O4("/trailers_o4", null, Step.TRAILERS_O4_TYPE),

    //vehicle's weight for "exceptM1 -> n1, n2, n3" branch
    LESS_2_TONS("/less_2p5", N1_N3_WEIGHT, Step.CAR_AGE),
    BETWEEN_2_5_AND_3_5_TONS("/2p5_3p5", N1_N3_WEIGHT, Step.CAR_AGE),
    BETWEEN_3_5_AND_5_TONS("/3p5_5", N1_N3_WEIGHT, Step.CAR_AGE),
    BETWEEN_5_AND_8_TONS("/5_8", N1_N3_WEIGHT, Step.CAR_AGE),
    BETWEEN_8_AND_12_TONS("/8_12", N1_N3_WEIGHT, Step.CAR_AGE),
    BETWEEN_12_AND_20_TONS("/12_20", N1_N3_WEIGHT, Step.CAR_AGE),
    BETWEEN_20_AND_50_TONS("/20_50", N1_N3_WEIGHT, Step.CAR_AGE),

    //country command
    EAES("/eaes", null, Step.OWNERS_TYPE),
    OTHER_COUNTRIES("/other", null, Step.OWNERS_TYPE),

    PHYSICAL_PERSON("/physical", null, Step.CAR_AGE),
    JURIDICAL_PERSON_EAES("/juridical_eaes", null, Step.CAR_AGE),
    JURIDICAL_PERSON_OTHER("/juridical_other", null, Step.M1_TYPE_OF_ENGINE),

    //age command
    LESS_3_YEARS_AGE("/less_3_years", Command.AGE, Step.FAREWELL),
    MORE_THAN_3_YEARS_AGE("/3_and_more_years", Command.AGE, Step.FAREWELL),

    //type of engine command for m1
    GASOLINE_TYPE_ENGINE_M1("/gasoline_m1", null, Step.M1_VOLUME_OF_ENGINE),
    ELECTRIC_TYPE_ENGINE_M1("/electric_m1", null, Step.CAR_AGE),

    //type of engine command for buses and trucks
    GASOLINE_TYPE_ENGINE_BUSES("/gasoline_buses", null, Step.M2_M3_ENGINE_VOLUME),
    ELECTRIC_TYPE_ENGINE_BUSES("/electric_buses", null, Step.CAR_AGE),

    //m1 engine volume command
    M1_VOLUME_LESS_1000_CM("/less_1000", M1_GASOLINE_ENGINE_VOLUME, Step.CAR_AGE),
    M1_VOLUME_BETWEEN_1000_2000_CM("/1000_2000", M1_GASOLINE_ENGINE_VOLUME, Step.CAR_AGE),
    M1_VOLUME_BETWEEN_2000_3000_CM("/2000_3000", M1_GASOLINE_ENGINE_VOLUME, Step.CAR_AGE),
    M1_VOLUME_BETWEEN_3000_3500_CM("/3000_3500", M1_GASOLINE_ENGINE_VOLUME, Step.CAR_AGE),
    M1_VOLUME_MORE_3500_CM("/more_3500", M1_GASOLINE_ENGINE_VOLUME, Step.CAR_AGE),

    //m2, m3 engine volume command
    M2_VOLUME_LESS_2500_CM("/less_2500", M2_M3_GASOLINE_ENGINE_VOLUME, Step.CAR_AGE),
    M2_VOLUME_BETWEEN_2500_5000_CM("/2500_5000", M2_M3_GASOLINE_ENGINE_VOLUME, Step.CAR_AGE),
    M2_VOLUME_BETWEEN_5000_10000_CM("/5000_10000", M2_M3_GASOLINE_ENGINE_VOLUME, Step.CAR_AGE),
    M2_VOLUME_MORE_10000_CM("/more_10000", M2_M3_GASOLINE_ENGINE_VOLUME, Step.CAR_AGE),

    //truck units class
    TRUCK_UNITS_6_CLASS("/6_class", null, Step.TRUCK_UNIT_WEIGHT),
    TRUCK_UNITS_OTHER("/except_6_class", null, Step.TRUCK_UNIT_WEIGHT),

    //truck units weight
    TRUCK_UNITS_12_20_TONS("/12_20_tons", TRUCK_UNIT_WEIGHT, Step.CAR_AGE),
    TRUCK_UNITS_20_50_TONS("/20_50_tons", TRUCK_UNIT_WEIGHT, Step.CAR_AGE),

    //trailers O4 types
    TRAILERS_04_TYPE("/trailers_o4_type", TRAILERS_O4_TYPE, Step.CAR_AGE),
    HALF_TRAILERS_04_TYPE("/semi_trailers_o4_type", TRAILERS_O4_TYPE, Step.CAR_AGE),

    //other vehicles
    HELP("/help", null, null),
    GRADERS("/graders", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    BULLDOZERS("/bulldozers", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    EXCAVATORS("/excavators", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    WHEEL_LOADERS("/wheel_loaders", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    TAMPING_MACHINES("/tamping_machines", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    FRONT_LOADERS("/front_loaders", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    WHEELED_CRANES("/wheeled_cranes", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    PIPELAYERS("/pipelayers", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    TRAILERS_OTHER("/trailers_other", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    ROAD_MAINTENANCE("/road_maintenance", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    FORESTRY("/forestry", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    FORWADERS("/forwaders", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    TIMBER_LOADERS("/timber_loaders", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    WHEELED_TRACTORS("/wheeled_tractors", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    CRAWLER_TRACTORS("/crawler_tractors", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    COMBINE_HARVESTERS("/combine_harvesters", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    FORAGE_HARVESTERS("/forage_harvesters", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    AGRICULTURAL_VEHICLES("/agricultural_vehicles", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),
    OFF_ROAD_DUMP_TRUCKS("/dump_trucks", SELF_PROPELLED_TYPE, Step.SELF_PROPELLED_POWER),

    //power of graders
    POWER_LESS_100("/less_100", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_100_140("/between_100_140", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_140_200("/between_140_200", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_MORE_200("/more_200", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of bulldozers
    POWER_100_200("/between_100_200", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_200_300("/between_200_300", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_300_400("/between_300_400", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_MORE_400("/more_400", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of excavators
    POWER_LESS_170("/less_170", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_170_250("/between_170_250", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_MORE_250("/more_250", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of wheel loaders
    POWER_100_125("/between_100_125", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_125_150("/between_125_150", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_MORE_150("/more_150", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of tamping machines
    POWER_LESS_40("/less_40", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_40_80("/between_40_80", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_MORE_80("/more_80", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of front loaders
    POWER_5_50("/between_5p5_50", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_50_100("/between_50_100", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_200_250("/between_200_250", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_250_300("/between_250_300", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of pipelayers
    POWER_LESS_130("/less_130", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_130_200("/between_130_200", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_MORE_300("/more_300", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //type of trailers
    TRAILERS_OTHER_FULL("/trailers", SELF_PROPELLED_POWER, Step.CAR_AGE),
    TRAILERS_OTHER_HALF("/half_trailers", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of road maintenance
    POWER_BETWEEN_100_220("/between_100_220", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_MORE_220("/more_220", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of forestry
    POWER_BETWEEN_20_100("/between_20_100",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_100_300("/between_100_300", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of wheeled tractors
    POWER_BETWEEN_5p5_30("/between_5p5_30",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_30_60("/between_30_60", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_60_90("/between_60_90",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_90_130("/between_90_130", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_130_180("/between_130_180",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_180_220("/between_180_220", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_220_280("/between_220_280",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_280_340("/between_280_340", SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_340_380("/between_340_380",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_MORE_380("/more_380", SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of combine harvesters
    POWER_BETWEEN_25_160("/between_25_160",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_160_220("/between_160_220",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_220_255("/between_220_255",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_255_325("/between_255_325",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_325_400("/between_325_400",SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of forage harvesters
    POWER_LESS_295("/less_295",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_295_401("/between_295_401",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_MORE_401("/more_401",SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of agricultural vehicles
    POWER_BETWEEN_100_120("/between_100_120",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_120_300("/between_120_300",SELF_PROPELLED_POWER, Step.CAR_AGE),
    SELF_PROPELLED_MOWERS("/self_propelled_mowers",SELF_PROPELLED_POWER, Step.CAR_AGE),

    //power of off-road dump trucks
    POWER_LESS_200("/less_200",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_200_650("/between_200_650",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_BETWEEN_650_1750("/between_650_1750",SELF_PROPELLED_POWER, Step.CAR_AGE),
    POWER_MORE_1750("/more_1750",SELF_PROPELLED_POWER, Step.CAR_AGE);

    private final String command;
    private final Command family;
    private final Step nextStep;

    Command(final String command, final @Nullable Command family, final Step step) {
        this.command = command;
        this.family = family;
        this.nextStep = step;
    }

    public String getCommand() {
        return command;
    }

    public Command getFamily() {
        return family;
    }

    public Step getNextStep() {
        return nextStep;
    }

    public static Command getCommandByKey(final String key) {
        try {
            for (Command command : Command.values()) {
                if (command.getCommand().equals(key)) {
                    return command;
                }
            }
            throw new IllegalArgumentException("No such key in Command: " + key);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}