package by.custom.utilcalculator.domain.constants;

public enum Command {
    //main command - start the calculator
    START("/start"),

    //type of vehicle
    M1("/m1"),
    EXCEPT_M1("/except_m1"),
    TRAILERS("/trailers"),


    EAES("/eaes"),
    OTHER_COUNTRIES("/other"),

    //second step command
//    public static final String PHYSICAL_PERSON = "/physical";
//    public static final String JURIDICAL_PERSON = "/juridical";
    PHYSICAL_PERSON("/physical"),
    JURIDICAL_PERSON("/juridical"),

    //the last step command
//    public static final String LESS_3_YEARS_AGE = "/3_or_less_years";
//    public static final String BETWEEN_3_AND_7_YEARS_AGE = "/more_3_years";
    LESS_3_YEARS_AGE("/3_or_less_years"),
    MORE_THAN_3_YEARS_AGE("/more_3_years"),

    //juridical other third step command
//    public static final String GASOLINE_TYPE_ENGINE = "/gasoline";
//    public static final String ELECTRIC_TYPE_ENGINE = "/electric";
    GASOLINE_TYPE_ENGINE("/gasoline"),
    ELECTRIC_TYPE_ENGINE("/electric"),

    //juridical other gasoline fourth step command
//    public static final String VOLUME_LESS_1000_CM = "/less_1000";
//    public static final String VOLUME_BETWEEN_1000_2000_CM = "/1000_2000";
//    public static final String VOLUME_BETWEEN_2000_3000_CM = "/2000_3000";
//    public static final String VOLUME_BETWEEN_3000_3500_CM = "/3000_3500";
//    public static final String VOLUME_MORE_3500_CM = "/more_3500";
    VOLUME_LESS_1000_CM("/less_1000"),
    VOLUME_BETWEEN_1000_2000_CM("/1000_2000"),
    VOLUME_BETWEEN_2000_3000_CM("/2000_3000"),
    VOLUME_BETWEEN_3000_3500_CM("/3000_3500"),
    VOLUME_MORE_3500_CM("/more_3500"),

    //command not for user - just for path checking
//    public static final String AGE = "age";
//    public static final String VOLUME = "volume";
    AGE("age"),
    VOLUME("volume");

    private final String command;

    Command (final String command) {
        this.command = command;
    }

    public String getCommand(){
        return command;
    }

    public static Command getCommandByKey(final String key){
        for (Command command : Command.values()) {
            if (command.getCommand().equals(key)){
                return command;
            }
        }
        throw new IllegalArgumentException("No such key in Command: " + key);
    }
}
