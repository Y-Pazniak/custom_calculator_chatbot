package by.custom.utilcalculator.repository.resources;

public class Commands {
    //main command - start the calculator
    public static final String START_COMMAND = "/start";

    //first step command
    public static final String EAES_COMMAND = "/eaes";
    public static final String OTHER_COUNTRIES_COMMAND = "/other";

    //second step command
    public static final String PHYSICAL_PERSON_COMMAND = "/physical";
    public static final String JURIDICAL_PERSON_COMMAND = "/juridical";

    //the last step command
    public static final String LESS_3_YEARS_AGE_COMMAND = "/less_3_years";
    public static final String BETWEEN_3_AND_7_YEARS_AGE_COMMAND = "/3_7_years";
    public static final String MORE_7_YEARS_AGE_COMMAND = "was /more_7_years";

    //juridical other third step command
    public static final String GASOLINE_TYPE_ENGINE_COMMAND = "/gasoline";
    public static final String ELECTRIC_TYPE_ENGINE_COMMAND = "/electric";

    //juridical other gasoline fourth step command
    public static final String VOLUME_LESS_1000_CM_COMMAND = "/less_1000";
    public static final String VOLUME_BETWEEN_1000_2000_CM_COMMAND = "/1000_2000";
    public static final String VOLUME_BETWEEN_2000_3000_CM_COMMAND = "/2000_3000";
    public static final String VOLUME_BETWEEN_3000_3500_CM_COMMAND = "/3000_3500";
    public static final String VOLUME_MORE_3500_CM_COMMAND = "/more_3500";
}
