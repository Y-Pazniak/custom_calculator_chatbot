package by.custom.utilcalculator.domain.constants;

import javax.annotation.Nullable;

public enum Command {
    //command families - not commands actually, just markers
    AGE("age", null),
    ENGINE_VOLUME("volume", null),
    WEIGHT("weight", null),
    TRAILERS_O4_TYPE("trailers_o4_type", null),
    SELF_PROPELLED_TYPE("self_propelled_type", null),
    SELF_PROPELLED_POWER("self_propelled_power", null),

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
    OFF_ROAD_DUMP_TRUCKS("/dump_trucks", SELF_PROPELLED_TYPE);

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