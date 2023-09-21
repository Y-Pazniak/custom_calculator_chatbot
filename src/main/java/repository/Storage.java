package repository;

public class Storage {
    public static final String GREETING_TEXT = "Добрый день. Я чат-бот, который поможет вам бесплатно рассчитать стоимость утильсбора в Беларуси " +
            "для легкового транспорта (категория М1).\n" +
            "Пожалуйста, выберите тип транспортного средства:\n" +
            Storage.EAES + " - для автомобилей из России и ЕАЭС;\n" +
            Storage.OTHER_COUNTRIES + " - для автомобилей из других стран.";

    public static final String PHYSICAL_OR_JURIDICAL = "Кто везет автомобиль: физическое или юридическое лицо?" +
            Storage.PHYSICAL + " - если физлицо; \n" +
            Storage.JURIDICAL + " - если юрлицо.\n";

    public static final String AGE_OF_AUTO = "Какой возраст автомобиля?\n" +
            Storage.LESS_3_YEARS + " - до 3 лет; \n" +
            Storage.BETWEEN_3_AND_7_YEARS + " - от 3 до 7 лет;\n" +
            Storage.MORE_7_YEARS + " - старше 7 лет.\n";

    public static final String YOUR_CHOICE = "Вы выбрали: ";
    public static final String EAES_STRING = "автомобили из ЕАЭС";
    public static final String OTHER_COUNTRIES_STRING = "автомобили из иных стран";
    public static final String PHYSICAL_STRING = ", физическое лицо";
    public static final String JURIDICAL_STRING = ", юридическое лицо";
    public static final String LESS_3_YEARS_STRING = ", автомобиль младше 3 лет";
    public static final String BETWEEN_3_AND_7_YEARS_STRING = ", автомобиль от 3 до 7 лет";
    public static final String MORE_7_YEARS_STRING = ", автомобиль старше 7 лет";
    public static final String PRICE_STRING = "Цена: ";

    public static final String START = "/start";
    public static final String EAES = "/eaes";
    public static final String OTHER_COUNTRIES = "/other";
    public static final String PHYSICAL = "/physical";
    public static final String JURIDICAL = "/juridical";
    public static final String LESS_3_YEARS = "/less_3";
    public static final String BETWEEN_3_AND_7_YEARS = "/3_7_years";
    public static final String MORE_7_YEARS = "/more_7";
}
