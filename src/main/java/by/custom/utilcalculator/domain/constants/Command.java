package by.custom.utilcalculator.domain.constants;

public enum Command {
    //main command - start the calculator
    START("/start"),

    //general type of vehicle
    M1("/m1"),
    EXCEPT_M1("/except_m1"),
    TRAILERS("/trailers"),

    //type of except m1 vehicle
    N1_N3("/n1_n3"),
    M2_M3("/m2_m3"),
    TRUCK_UNITS("/truck_units"),
    TRAILERS_O4("/trailers_o4"),

    //vehicle's weight for "exceptM1 -> n1, n2, n3" branch
    LESS_2_TONS("/less_2p5"),
    BETWEEN_2_5_AND_3_5_TONS("/2p5_3p5"),
    BETWEEN_3_5_AND_5_TONS("/3p5_5"),
    BETWEEN_5_AND_8_TONS("/5_8"),
    BETWEEN_8_AND_12_TONS("/8_12"),
    BETWEEN_12_AND_20_TONS("/12_20"),
    BETWEEN_20_AND_50_TONS("/20_50"),

    //country command
    EAES("/eaes"),
    OTHER_COUNTRIES("/other"),

    PHYSICAL_PERSON("/physical"),
    JURIDICAL_PERSON("/juridical"),

    //age command
    LESS_3_YEARS_AGE("/3_or_less_years"),
    MORE_THAN_3_YEARS_AGE("/more_3_years"),

    //type of engine command
    GASOLINE_TYPE_ENGINE("/gasoline"),
    ELECTRIC_TYPE_ENGINE("/electric"),

    //m1 engine volume command
    M1_VOLUME_LESS_1000_CM("/less_1000"),
    M1_VOLUME_BETWEEN_1000_2000_CM("/1000_2000"),
    M1_VOLUME_BETWEEN_2000_3000_CM("/2000_3000"),
    M1_VOLUME_BETWEEN_3000_3500_CM("/3000_3500"),
    M1_VOLUME_MORE_3500_CM("/more_3500"),

    //m2 engine volume command
    M2_VOLUME_LESS_2500_CM("/less_2500"),
    M2_VOLUME_BETWEEN_2500_5000_CM("/2500_5000"),
    M2_VOLUME_BETWEEN_5000_10000_CM("/5000_10000"),
    M2_VOLUME_MORE_10000_CM("/more_10000"),

    //truck units class
    TRUCK_UNITS_6_CLASS("/6_class"),
    TRUCK_UNITS_EXCEPT_6_CLASS("/except_6_class"),

    //truck units weight
    TRUCK_UNITS_12_20_TONS("/12_20_tons"),
    TRUCK_UNITS_20_50_TONS("/20_50_tons"),

    //trailers O4 types
    TRAILERS_04_TYPE("/trailers_o4_type"),
    HALF_TRAILERS_04_TYPE("/half_trailers_o4_type"),

    //command not for user - just for path checking (not command actually, just a marker to make code in tree shorter)
    AGE("age"),
    M1_GASOLINE_ENGINE_VOLUME("volume"),
    N1_N3_WEIGHT("n1_n3_weight"),
    M2_M3_GASOLINE_ENGINE_VOLUME("m2_m3_volume"),
    TRUCK_UNIT_WEIGHT("truck_unit_weight"),
    TRAILERS_O4_TYPE("trailers_o4_weight");

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
        return null;
    }
}
