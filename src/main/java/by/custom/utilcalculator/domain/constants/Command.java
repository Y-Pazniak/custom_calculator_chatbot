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
    LESS_2_TONS("/less_2"),
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

    //engine volume command
    VOLUME_LESS_1000_CM("/less_1000"),
    VOLUME_BETWEEN_1000_2000_CM("/1000_2000"),
    VOLUME_BETWEEN_2000_3000_CM("/2000_3000"),
    VOLUME_BETWEEN_3000_3500_CM("/3000_3500"),
    VOLUME_MORE_3500_CM("/more_3500"),

    //command not for user - just for path checking (not command actually, just a marker to make code shorter)
    AGE("age"),
    VOLUME("volume");

    private final String command;

    Command(final String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Command getCommandByKey(final String key) {
        for (Command command : Command.values()) {
            if (command.getCommand().equals(key)) {
                return command;
            }
        }
        throw new IllegalArgumentException("No such key in Command: " + key);
    }
}
