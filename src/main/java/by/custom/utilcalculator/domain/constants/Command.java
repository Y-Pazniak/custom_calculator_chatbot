package by.custom.utilcalculator.domain.constants;

import javax.annotation.Nullable;

public enum Command {
    //command families - not commands actually, just markers
    AGE("age", null),
    ENGINE_VOLUME("volume", null),
    WEIGHT("weight", null),
    TRAILERS_O4_TYPE("trailers_o4_type", null),
    SELF_PROPELLED_TYPE("self_propelled_type", null),

    //main command - start the calculator
    START("/start", null),

    //general type of vehicle
    M1("/cars", null),
    BUSES_AND_TRUCKS("/buses_and_trucks", null),
    SELF_PROPELLED_VEHICLES("/self_propelled_vehicles", null),

    //type of except m1 vehicle - buses and trucks
    N1_N3("/n1_n3", null),
    M2_M3("/m2_m3", null),
    TRUCK_UNITS("/truck_units", null),
    TRAILERS_O4("/trailers_o4", null),

    //vehicle's weight for "exceptM1 -> n1, n2, n3" branch
    LESS_2_TONS("/less_2p5", WEIGHT),
    BETWEEN_2_5_AND_3_5_TONS("/2p5_3p5", WEIGHT),
    BETWEEN_3_5_AND_5_TONS("/3p5_5", WEIGHT),
    BETWEEN_5_AND_8_TONS("/5_8", WEIGHT),
    BETWEEN_8_AND_12_TONS("/8_12", WEIGHT),
    BETWEEN_12_AND_20_TONS("/12_20", WEIGHT),
    BETWEEN_20_AND_50_TONS("/20_50", WEIGHT),

    //country command
    EAES("/eaes", null),
    OTHER_COUNTRIES("/other", null),

    PHYSICAL("/physical", null),
    JURIDICAL("/juridical", null),

    //age command
    LESS_3_YEARS_AGE("/3_or_less_years", Command.AGE),
    MORE_THAN_3_YEARS_AGE("/more_3_years", Command.AGE),

    //type of engine command for m1
    GASOLINE("/gasoline", null),
    ELECTRIC("/electric", null),

    //m1 engine volume command
    VOLUME_LESS_1000_CM("/less_1000", ENGINE_VOLUME),
    VOLUME_BETWEEN_1000_2000_CM("/1000_2000", ENGINE_VOLUME),
    VOLUME_BETWEEN_2000_3000_CM("/2000_3000", ENGINE_VOLUME),
    VOLUME_BETWEEN_3000_3500_CM("/3000_3500", ENGINE_VOLUME),
    VOLUME_MORE_3500_CM("/more_3500", ENGINE_VOLUME),

    //m2, m3 engine volume command
    VOLUME_LESS_2500_CM("/less_2500", ENGINE_VOLUME),
    VOLUME_BETWEEN_2500_5000_CM("/2500_5000", ENGINE_VOLUME),
    VOLUME_BETWEEN_5000_10000_CM("/5000_10000", ENGINE_VOLUME),
    VOLUME_MORE_10000_CM("/more_10000", ENGINE_VOLUME),

    //truck units class
    TRUCK_UNITS_6_CLASS("/6_class", null),
    TRUCK_UNITS_OTHER("/except_6_class", null),

    //truck units weight
    TRUCK_UNITS_12_20_TONS("/12_20_tons", WEIGHT),
    TRUCK_UNITS_20_50_TONS("/20_50_tons", WEIGHT),

    //trailers O4 types
    TRAILERS_04_TYPE("/trailers_o4_type", TRAILERS_O4_TYPE),
    HALF_TRAILERS_04_TYPE("/semi_trailers_o4_type", TRAILERS_O4_TYPE),

    //other vehicles
    HELP("/help", null),
    GRADERS("/graders", SELF_PROPELLED_TYPE),
    BULLDOZERS("/bulldozers", SELF_PROPELLED_TYPE),
    EXCAVATORS("/excavators", SELF_PROPELLED_TYPE),
    WHEEL_LOADERS("/wheel_loaders", SELF_PROPELLED_TYPE),
    TAMPING_MACHINES("/tamping_machines", SELF_PROPELLED_TYPE),
    FRONT_LOADERS("/front_loaders", SELF_PROPELLED_TYPE),
    WHEELED_CRANES("/wheeled_cranes", SELF_PROPELLED_TYPE),
    PIPELAYERS("/pipelayers", SELF_PROPELLED_TYPE),
    TRAILERS_OTHER("/trailers_other", SELF_PROPELLED_TYPE),
    ROAD_MAINTENANCE("/road_maintenance", SELF_PROPELLED_TYPE),
    FORESTRY("/forestry", SELF_PROPELLED_TYPE),
    FORWADERS("/forwaders", SELF_PROPELLED_TYPE),
    TIMBER_LOADERS("/timber_loaders", SELF_PROPELLED_TYPE),
    WHEELED_TRACTORS("/wheeled_tractors", SELF_PROPELLED_TYPE),
    CRAWLER_TRACTORS("/crawler_tractors", SELF_PROPELLED_TYPE),
    COMBINE_HARVESTERS("/combine_harvesters", SELF_PROPELLED_TYPE),
    FORAGE_HARVESTERS("/forage_harvesters", SELF_PROPELLED_TYPE),
    AGRICULTURAL_VEHICLES("/agricultural_vehicles", SELF_PROPELLED_TYPE),
    OFF_ROAD_DUMP_TRUCKS("/dump_trucks", SELF_PROPELLED_TYPE),

    //power of graders
    POWER_LESS_100("/less_100", ENGINE_VOLUME),
    POWER_100_140("/between_100_140", ENGINE_VOLUME),
    POWER_140_200("/between_140_200", ENGINE_VOLUME),
    POWER_MORE_200("/more_200", ENGINE_VOLUME),

    //power of bulldozers
    POWER_100_200("/between_100_200", ENGINE_VOLUME),
    POWER_200_300("/between_200_300", ENGINE_VOLUME),
    POWER_300_400("/between_300_400", ENGINE_VOLUME),
    POWER_MORE_400("/more_400", ENGINE_VOLUME),

    //power of excavators
    POWER_LESS_170("/less_170", ENGINE_VOLUME),
    POWER_170_250("/between_170_250", ENGINE_VOLUME),
    POWER_MORE_250("/more_250", ENGINE_VOLUME),

    //power of wheel loaders
    POWER_100_125("/between_100_125", ENGINE_VOLUME),
    POWER_125_150("/between_125_150", ENGINE_VOLUME),
    POWER_MORE_150("/more_150", ENGINE_VOLUME),

    //power of tamping machines
    POWER_LESS_40("/less_40", ENGINE_VOLUME),
    POWER_40_80("/between_40_80", ENGINE_VOLUME),
    POWER_MORE_80("/more_80", ENGINE_VOLUME),

    //power of front loaders
    POWER_5_50("/between_5p5_50", ENGINE_VOLUME),
    POWER_50_100("/between_50_100", ENGINE_VOLUME),
    POWER_200_250("/between_200_250", ENGINE_VOLUME),
    POWER_250_300("/between_250_300", ENGINE_VOLUME),

    //power of pipelayers
    POWER_LESS_130("/less_130", ENGINE_VOLUME),
    POWER_130_200("/between_130_200", ENGINE_VOLUME),
    POWER_MORE_300("/more_300", ENGINE_VOLUME),

    //type of trailers
    TRAILERS_OTHER_FULL("/trailers", ENGINE_VOLUME),
    TRAILERS_OTHER_HALF("/half_trailers", ENGINE_VOLUME),

    //power of road maintenance
    POWER_BETWEEN_100_220("/between_100_220", ENGINE_VOLUME),
    POWER_MORE_220("/more_220", ENGINE_VOLUME),

    //power of forestry
    POWER_BETWEEN_20_100("/between_20_100",ENGINE_VOLUME),
    POWER_BETWEEN_100_300("/between_100_300", ENGINE_VOLUME),

    //power of wheeled tractors
    POWER_BETWEEN_5p5_30("/between_5p5_30",ENGINE_VOLUME),
    POWER_BETWEEN_30_60("/between_30_60", ENGINE_VOLUME),
    POWER_BETWEEN_60_90("/between_60_90",ENGINE_VOLUME),
    POWER_BETWEEN_90_130("/between_90_130", ENGINE_VOLUME),
    POWER_BETWEEN_130_180("/between_130_180",ENGINE_VOLUME),
    POWER_BETWEEN_180_220("/between_180_220", ENGINE_VOLUME),
    POWER_BETWEEN_220_280("/between_220_280",ENGINE_VOLUME),
    POWER_BETWEEN_280_340("/between_280_340", ENGINE_VOLUME),
    POWER_BETWEEN_340_380("/between_340_380",ENGINE_VOLUME),
    POWER_MORE_380("/more_380", ENGINE_VOLUME),

    //power of combine harvesters
    POWER_BETWEEN_25_160("/between_25_160",ENGINE_VOLUME),
    POWER_BETWEEN_160_220("/between_160_220",ENGINE_VOLUME),
    POWER_BETWEEN_220_255("/between_220_255",ENGINE_VOLUME),
    POWER_BETWEEN_255_325("/between_255_325",ENGINE_VOLUME),
    POWER_BETWEEN_325_400("/between_325_400",ENGINE_VOLUME),

    //power of forage harvesters
    POWER_LESS_295("/less_295",ENGINE_VOLUME),
    POWER_BETWEEN_295_401("/between_295_401",ENGINE_VOLUME),
    POWER_MORE_401("/more_401",ENGINE_VOLUME),

    //power of agricultural vehicles
    POWER_BETWEEN_100_120("/between_100_120",ENGINE_VOLUME),
    POWER_BETWEEN_120_300("/between_120_300",ENGINE_VOLUME),
    SELF_PROPELLED_MOWERS("/self_propelled_mowers",ENGINE_VOLUME),

    //power of off-road dump trucks
    POWER_LESS_200("/less_200",ENGINE_VOLUME),
    POWER_BETWEEN_200_650("/between_200_650",ENGINE_VOLUME),
    POWER_BETWEEN_650_1750("/between_650_1750",ENGINE_VOLUME),
    POWER_MORE_1750("/more_1750",ENGINE_VOLUME);

    private final String command;
    private final Command family;

    Command(final String command, @Nullable final Command family) {
        this.command = command;
        this.family = family;
    }

    public String getCommand() {
        return command;
    }

    public Command getFamily() {
        return family;
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