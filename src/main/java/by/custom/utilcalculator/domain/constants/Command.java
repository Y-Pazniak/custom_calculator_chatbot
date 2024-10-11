package by.custom.utilcalculator.domain.constants;


public enum Command {
    //command families - not commands actually, just markers
    ENGINE_VOLUME("volume"),
    SELF_PROPELLED_TYPE("self_propelled_type"),

    //main command - start the calculator
    START("/start"),

    //general type of vehicle
    M1("/cars"),
    BUSES_AND_TRUCKS("/buses_and_trucks"),
    SELF_PROPELLED_VEHICLES("/self_propelled_vehicles"),

    //type of except m1 vehicle - buses and trucks
    N1_N3("/n1_n3"),
    M2_M3("/m2_m3"),
    TRUCK_UNITS("/truck_units"),
    TRAILERS_O4("/trailers_o4"),

    //vehicle's weight for "exceptM1 -> n1, n2, n3" branch
    LESS_2P5_TONS("/less_2p5"),
    BETWEEN_2_5_AND_3_5_TONS("/2p5_3p5"),
    BETWEEN_3_5_AND_5_TONS("/3p5_5"),
    BETWEEN_5_AND_8_TONS("/5_8"),
    BETWEEN_8_AND_12_TONS("/8_12"),
    BETWEEN_12_AND_20_TONS("/12_20"),
    BETWEEN_20_AND_50_TONS("/20_50"),

    //country command
    EAES("/eaes"),
    OTHER_COUNTRIES("/other"),

    PHYSICAL("/physical"),
    JURIDICAL("/juridical"),

    //age command
    LESS_3_YEARS_AGE("/3_or_less_years"),
    MORE_THAN_3_YEARS_AGE("/more_3_years"),

    //type of engine command for m1
    GASOLINE("/gasoline"),
    ELECTRIC("/electric"),

    //m1 engine volume command
    VOLUME_LESS_1000_CM("/less_1000"),
    VOLUME_BETWEEN_1000_2000_CM("/1000_2000"),
    VOLUME_BETWEEN_2000_3000_CM("/2000_3000"),
    VOLUME_BETWEEN_3000_3500_CM("/3000_3500"),
    VOLUME_MORE_3500_CM("/more_3500"),

    //m2, m3 engine volume command
    VOLUME_LESS_2500_CM("/less_2500"),
    VOLUME_BETWEEN_2500_5000_CM("/2500_5000"),
    VOLUME_BETWEEN_5000_10000_CM("/5000_10000"),
    VOLUME_MORE_10000_CM("/more_10000"),

    //truck units class
    TRUCK_UNITS_6_CLASS("/6_class"),
    TRUCK_UNITS_OTHER("/except_6_class"),

    //truck units weight
    TRUCK_UNITS_12_20_TONS("/12_20_tons"),
    TRUCK_UNITS_20_50_TONS("/20_50_tons"),

    //trailers O4 types
    TRAILERS_04_TYPE("/trailers_o4_type"),
    HALF_TRAILERS_04_TYPE("/semi_trailers_o4_type"),

    //other vehicles
    HELP("/help"),
    GRADERS("/graders"),
    BULLDOZERS("/bulldozers"),
    EXCAVATORS("/excavators"),
    WHEEL_LOADERS("/wheel_loaders"),
    TAMPING_MACHINES("/tamping_machines"),
    FRONT_LOADERS("/front_loaders"),
    WHEELED_CRANES("/wheeled_cranes"),
    PIPELAYERS("/pipelayers"),
    TRAILERS_OTHER("/trailers_other"),
    ROAD_MAINTENANCE("/road_maintenance"),
    FORESTRY("/forestry"),
    FORWADERS("/forwaders"),
    TIMBER_LOADERS("/timber_loaders"),
    WHEELED_TRACTORS("/wheeled_tractors"),
    CRAWLER_TRACTORS("/crawler_tractors"),
    COMBINE_HARVESTERS("/combine_harvesters"),
    FORAGE_HARVESTERS("/forage_harvesters"),
    AGRICULTURAL_VEHICLES("/agricultural_vehicles"),
    OFF_ROAD_DUMP_TRUCKS("/dump_trucks"),

    //power of graders
    POWER_LESS_100("/less_100"),
    POWER_100_140("/between_100_140"),
    POWER_140_200("/between_140_200"),
    POWER_MORE_200("/more_200"),

    //power of bulldozers
    POWER_100_200("/between_100_200"),
    POWER_200_300("/between_200_300"),
    POWER_300_400("/between_300_400"),
    POWER_MORE_400("/more_400"),

    //power of excavators
    POWER_LESS_170("/less_170"),
    POWER_170_250("/between_170_250"),
    POWER_MORE_250("/more_250"),

    //power of wheel loaders
    POWER_100_125("/between_100_125"),
    POWER_125_150("/between_125_150"),
    POWER_MORE_150("/more_150"),

    //power of tamping machines
    POWER_LESS_40("/less_40"),
    POWER_40_80("/between_40_80"),
    POWER_MORE_80("/more_80"),

    //power of front loaders
    POWER_5_50("/between_5p5_50"),
    POWER_50_100("/between_50_100"),
    POWER_200_250("/between_200_250"),
    POWER_250_300("/between_250_300"),

    //power of pipelayers
    POWER_LESS_130("/less_130"),
    POWER_130_200("/between_130_200"),
    POWER_MORE_300("/more_300"),

    //type of trailers
    TRAILERS_OTHER_FULL("/trailers"),
    TRAILERS_OTHER_HALF("/half_trailers"),

    //power of road maintenance
    POWER_BETWEEN_100_220("/between_100_220"),
    POWER_MORE_220("/more_220"),

    //power of forestry
    POWER_BETWEEN_20_100("/between_20_100"),
    POWER_BETWEEN_100_300("/between_100_300"),

    //power of wheeled tractors
    POWER_BETWEEN_5p5_30("/between_5p5_30"),
    POWER_BETWEEN_30_60("/between_30_60"),
    POWER_BETWEEN_60_90("/between_60_90"),
    POWER_BETWEEN_90_130("/between_90_130"),
    POWER_BETWEEN_130_180("/between_130_180"),
    POWER_BETWEEN_180_220("/between_180_220"),
    POWER_BETWEEN_220_280("/between_220_280"),
    POWER_BETWEEN_280_340("/between_280_340"),
    POWER_BETWEEN_340_380("/between_340_380"),
    POWER_MORE_380("/more_380"),

    //power of combine harvesters
    POWER_BETWEEN_25_160("/between_25_160"),
    POWER_BETWEEN_160_220("/between_160_220"),
    POWER_BETWEEN_220_255("/between_220_255"),
    POWER_BETWEEN_255_325("/between_255_325"),
    POWER_BETWEEN_325_400("/between_325_400"),

    //power of forage harvesters
    POWER_LESS_295("/less_295"),
    POWER_BETWEEN_295_401("/between_295_401"),
    POWER_MORE_401("/more_401"),

    //power of agricultural vehicles
    POWER_BETWEEN_100_120("/between_100_120"),
    POWER_BETWEEN_120_300("/between_120_300"),
    SELF_PROPELLED_MOWERS("/self_propelled_mowers"),

    //power of off-road dump trucks
    POWER_LESS_200("/less_200"),
    POWER_BETWEEN_200_650("/between_200_650"),
    POWER_BETWEEN_650_1750("/between_650_1750"),
    POWER_MORE_1750("/more_1750");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
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
        return null; //111
    }
}