package by.custom.utilcalculator.repository.resources;

public class Questions {
    //main question - starts calculator
    public static final String GREETING_TEXT = "Бесплатно рассчитаю стоимость утильсбора в Беларуси " +
            "для легкового транспорта (категория М1).\n" +
            "Выберите, откуда везете авто:\n" +
            Commands.EAES_COMMAND + " - для автомобилей из России и ЕАЭС;\n" +
            Commands.OTHER_COUNTRIES_COMMAND + " - для автомобилей из других стран.";

    //always second question
    public static final String PHYSICAL_OR_JURIDICAL = "Кто везет автомобиль: физическое или юридическое лицо?\n" +
            Commands.PHYSICAL_PERSON_COMMAND + " - если физлицо; \n" +
            Commands.JURIDICAL_PERSON_COMMAND + " - если юрлицо.\n";

    //last question - calls result
    public static final String AGE_OF_AUTO = "Какой возраст автомобиля?\n" +
            Commands.LESS_3_YEARS_AGE_COMMAND + " - до 3 лет; \n" +
            Commands.BETWEEN_3_AND_7_YEARS_AGE_COMMAND + " - от 3 до 7 лет;\n" +
            Commands.MORE_7_YEARS_AGE_COMMAND + " - старше 7 лет.\n";

    //only for other countries juridical
    public static final String GAS_OR_ELECTRIC_ENGINE = "Какой тип двигателя?\n" +
            Commands.GASOLINE_TYPE_ENGINE_COMMAND + " - если бензиновый или гибридный двигатель; \n" +
            Commands.ELECTRIC_TYPE_ENGINE_COMMAND + " - если электрический двигатель.\n";

    //only for another countries juridical gasoline engines
    public static final String GAS_ENGINE_VOLUME = "Какой объем двигателя?\n" +
            Commands.VOLUME_LESS_1000_CM_COMMAND + " - " + Answers.VOLUME_LESS_1000_CM_ANSWER + "\n" +
            Commands.VOLUME_BETWEEN_1000_2000_CM_COMMAND + " - " + Answers.VOLUME_BETWEEN_1000_2000_CM_ANSWER + "\n" +
            Commands.VOLUME_BETWEEN_2000_3000_CM_COMMAND + " - " + Answers.VOLUME_BETWEEN_2000_3000_CM_ANSWER + "\n" +
            Commands.VOLUME_BETWEEN_3000_3500_CM_COMMAND + " - " + Answers.VOLUME_BETWEEN_3000_3500_CM_ANSWER + "\n" +
            Commands.VOLUME_MORE_3500_CM_COMMAND + " - " + Answers.VOLUME_MORE_3500_CM_ANSWER;
}
