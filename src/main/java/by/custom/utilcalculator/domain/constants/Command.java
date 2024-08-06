package by.custom.utilcalculator.domain.constants;

import by.custom.utilcalculator.domain.constants.steps.Step;

public enum Command {
    //command families - not commands actually, just markers
    AGE("age", null, Step.FAREWELL),
    M1_GASOLINE_ENGINE_VOLUME("volume", null, Step.CAR_AGE),
    N1_N3_WEIGHT("n1_n3_weight", null, Step.CAR_AGE),
    M2_M3_GASOLINE_ENGINE_VOLUME("m2_m3_volume", null, Step.CAR_AGE),
    TRUCK_UNIT_WEIGHT("truck_unit_weight", null, Step.CAR_AGE),
    TRAILERS_O4_TYPE("trailers_o4_type", null, Step.CAR_AGE),

    //main command - start the calculator
    START("/start", null, Step.GENERAL_TRANSPORT_TYPE),

    //general type of vehicle
    M1("/cars", null, Step.COUNTRY_ORIGIN),
    BUSES_AND_TRUCKS("/buses_and_trucks", null, Step.BUSES_AND_TRUCKS_TYPES),
    SELF_PROPELLED_VEHICLES("/self_propelled_vehicles", null, Step.SELF_PROPELLED_VEHICLES_TYPES),

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
    LESS_3_YEARS_AGE("/3_or_less_years", Command.AGE, Step.FAREWELL),
    MORE_THAN_3_YEARS_AGE("/more_3_years", Command.AGE, Step.FAREWELL),

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
    GRADERS("/graders", null, Step.CAR_AGE),
    BULLDOZERS("/bulldozers", null,Step.CAR_AGE),
    EXCAVATORS("/excavators", null,Step.CAR_AGE),
    WHEEL_LOADERS("/wheel_loaders", null,Step.CAR_AGE),
    TAMPING_MACHINES("/tamping_machines", null,Step.CAR_AGE),
    FRONT_LOADERS("/front_loaders", null,Step.CAR_AGE),
    WHEELED_CRANES("/wheeled_cranes", null,Step.CAR_AGE),
    PIPELAYERS("/pipelayers", null,Step.CAR_AGE),
    TRAILERS_OTHER("/trailers_other", null,Step.CAR_AGE),
    ROAD_MAINTENANCE_VEHICLES("/road_maintenance", null,Step.CAR_AGE),
    FORESTRY_VEHICLES("/forestry_vehicles", null,Step.CAR_AGE),
    FORWADERS("/forwaders", null,Step.CAR_AGE),
    TIMBER_LOADERS("/timber_loaders", null,Step.CAR_AGE),
    WHEELED_TRACTORS("/wheeled_tractors", null,Step.CAR_AGE),
    CRAWLER_TRACTORS("/crawler_tractors", null,Step.CAR_AGE),
    COMBINE_HARVESTERS("/combine_harvesters", null,Step.CAR_AGE),
    FORAGE_HARVESTERS("/forage_harvesters", null,Step.CAR_AGE),
    AGRICULTURAL_VEHICLES("/agricultural_vehicles", null,Step.CAR_AGE),
    OFF_ROAD_DUMP_TRUCKS("/dump_trucks", null,Step.CAR_AGE);

    private final String command;
    private final Command family;
    private final Step nextStep;

    Command(final String command, final Command family, final Step step) {
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