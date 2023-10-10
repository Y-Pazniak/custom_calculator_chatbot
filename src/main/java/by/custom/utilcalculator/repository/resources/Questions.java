package by.custom.utilcalculator.repository.resources;

public class Questions {
    //main question - starts calculator
    public static final String GREETING_TEXT = "Бесплатно рассчитаю стоимость утильсбора в Беларуси " +
            "для легкового транспорта (категория М1).\n" +
            "Выберите, откуда везете авто:\n" +
            Commands.EAES + " - для автомобилей из России и ЕАЭС;\n" +
            Commands.OTHER_COUNTRIES + " - для автомобилей из других стран.";

    //always second question
    public static final String PHYSICAL_OR_JURIDICAL = "Кто везет автомобиль: физическое или юридическое лицо?\n" +
            Commands.PHYSICAL_PERSON + " - если физлицо; \n" +
            Commands.JURIDICAL_PERSON + " - если юрлицо.\n";

    //last question - calls result
    public static final String AGE_OF_AUTO = "Какой возраст автомобиля?\n" +
            Commands.LESS_3_YEARS_AGE + " - до 3 лет; \n" +
            Commands.BETWEEN_3_AND_7_YEARS_AGE + " - от 3 до 7 лет;\n" +
            Commands.MORE_7_YEARS_AGE + " - старше 7 лет.\n";

    //only for other countries juridical
    public static final String GAS_OR_ELECTRIC_ENGINE = "Какой тип двигателя?\n" +
            Commands.GASOLINE_TYPE_ENGINE + " - если бензиновый или гибридный двигатель; \n" +
            Commands.ELECTRIC_TYPE_ENGINE + " - если электрический двигатель.\n";

    //only for another countries juridical gasoline engines
    public static final String GAS_ENGINE_VOLUME = "Какой объем двигателя?\n" +
            Commands.VOLUME_LESS_1000_CM + " - " + Answers.VOLUME_LESS_1000_CM + "\n" +
            Commands.VOLUME_BETWEEN_1000_2000_CM + " - " + Answers.VOLUME_BETWEEN_1000_2000_CM + "\n" +
            Commands.VOLUME_BETWEEN_2000_3000_CM + " - " + Answers.VOLUME_BETWEEN_2000_3000_CM + "\n" +
            Commands.VOLUME_BETWEEN_3000_3500_CM + " - " + Answers.VOLUME_BETWEEN_3000_3500_CM + "\n" +
            Commands.VOLUME_MORE_3500_CM + " - " + Answers.VOLUME_MORE_3500_CM;
}
