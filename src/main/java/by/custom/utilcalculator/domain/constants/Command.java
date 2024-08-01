package by.custom.utilcalculator.domain.constants;

public enum Command {
    //command not for user - just for path checking (not command actually, just a marker to make code in tree shorter)
    AGE("age", null),
    M1_GASOLINE_ENGINE_VOLUME("volume", null),
    N1_N3_WEIGHT("n1_n3_weight", null),
    M2_M3_GASOLINE_ENGINE_VOLUME("m2_m3_volume", null),
    TRUCK_UNIT_WEIGHT("truck_unit_weight", null),
    TRAILERS_O4_TYPE("trailers_o4_type", null),

    //main command - start the calculator
    START("/start", null),

    //general type of vehicle
    M1("/cars", null),
    BUSES_AND_TRUCKS("/buses_and_trucks", null),
    SELF_PROPELLED_VEHICLES("/self_propelled_vehicles", null),

    //type of except m1 vehicle
    N1_N3("/n1_n3", null),
    M2_M3("/m2_m3", null),
    TRUCK_UNITS("/truck_units", null),
    TRAILERS_O4("/trailers_o4", null),

    //vehicle's weight for "exceptM1 -> n1, n2, n3" branch
    LESS_2_TONS("/less_2p5", N1_N3_WEIGHT),
    BETWEEN_2_5_AND_3_5_TONS("/2p5_3p5", N1_N3_WEIGHT),
    BETWEEN_3_5_AND_5_TONS("/3p5_5", N1_N3_WEIGHT),
    BETWEEN_5_AND_8_TONS("/5_8", N1_N3_WEIGHT),
    BETWEEN_8_AND_12_TONS("/8_12", N1_N3_WEIGHT),
    BETWEEN_12_AND_20_TONS("/12_20", N1_N3_WEIGHT),
    BETWEEN_20_AND_50_TONS("/20_50", N1_N3_WEIGHT),

    //country command
    EAES("/eaes", null),
    OTHER_COUNTRIES("/other", null),

    PHYSICAL_PERSON("/physical", null),
    JURIDICAL_PERSON("/juridical", null),

    //age command
    LESS_3_YEARS_AGE("/3_or_less_years", Command.AGE),
    MORE_THAN_3_YEARS_AGE("/more_3_years", Command.AGE),

    //type of engine command
    GASOLINE_TYPE_ENGINE("/gasoline", null),
    ELECTRIC_TYPE_ENGINE("/electric", null),

    //m1 engine volume command
    M1_VOLUME_LESS_1000_CM("/less_1000", M1_GASOLINE_ENGINE_VOLUME),
    M1_VOLUME_BETWEEN_1000_2000_CM("/1000_2000", M1_GASOLINE_ENGINE_VOLUME),
    M1_VOLUME_BETWEEN_2000_3000_CM("/2000_3000", M1_GASOLINE_ENGINE_VOLUME),
    M1_VOLUME_BETWEEN_3000_3500_CM("/3000_3500", M1_GASOLINE_ENGINE_VOLUME),
    M1_VOLUME_MORE_3500_CM("/more_3500", M1_GASOLINE_ENGINE_VOLUME),

    //m2 engine volume command
    M2_VOLUME_LESS_2500_CM("/less_2500", M2_M3_GASOLINE_ENGINE_VOLUME),
    M2_VOLUME_BETWEEN_2500_5000_CM("/2500_5000", M2_M3_GASOLINE_ENGINE_VOLUME),
    M2_VOLUME_BETWEEN_5000_10000_CM("/5000_10000", M2_M3_GASOLINE_ENGINE_VOLUME),
    M2_VOLUME_MORE_10000_CM("/more_10000", M2_M3_GASOLINE_ENGINE_VOLUME),

    //truck units class
    TRUCK_UNITS_6_CLASS("/6_class", null),
    TRUCK_UNITS_OTHER("/except_6_class", null),

    //truck units weight
    TRUCK_UNITS_12_20_TONS("/12_20_tons", TRUCK_UNIT_WEIGHT),
    TRUCK_UNITS_20_50_TONS("/20_50_tons", TRUCK_UNIT_WEIGHT),

    //trailers O4 types
    TRAILERS_04_TYPE("/trailers_o4_type", TRAILERS_O4_TYPE),
    HALF_TRAILERS_04_TYPE("/semi_trailers_o4_type", TRAILERS_O4_TYPE);

    private final String command;
    private final Command family;

    Command(final String command, final Command family) {
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
