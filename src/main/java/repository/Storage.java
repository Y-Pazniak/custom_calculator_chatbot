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

    public static final String GAS_OR_ELECTRIC_ENGINE = "Какой тип двигателя?\n" +
            Storage.GAS + " - если бензиновый или гибридный двигатель; \n" +
            Storage.ELECTRIC + " - если электрический двигатель.\n";

    public static final String GAS_ENGINE_VOLUME = "Какой объем двигателя?\n" +
            Storage.VOLUME_LESS_1000 + " - " + Storage.VOLUME_LESS_1000_STRING + "\n" +
            Storage.VOLUME_BETWEEN_1000_2000 + " - " + Storage.VOLUME_BETWEEN_1000_2000_STRING + "\n" +
            Storage.VOLUME_BETWEEN_2000_3000 + " - " + Storage.VOLUME_BETWEEN_2000_3000_STRING + "\n" +
            Storage.VOLUME_BETWEEN_3000_3500 + " - " + Storage.VOLUME_BETWEEN_3000_3500_STRING + "\n" +
            Storage.VOLUME_MORE_3500 + " - " + Storage.VOLUME_MORE_3500_STRING;

    public static final String YOUR_CHOICE = "Вы выбрали: ";
    public static final String EAES_STRING = "автомобили из ЕАЭС";
    public static final String OTHER_COUNTRIES_STRING = "автомобили из иных стран";
    public static final String PHYSICAL_STRING = ", физическое лицо";
    public static final String JURIDICAL_STRING = ", юридическое лицо";
    public static final String LESS_3_YEARS_STRING = ", автомобиль младше 3 лет";
    public static final String BETWEEN_3_AND_7_YEARS_STRING = ", автомобиль от 3 до 7 лет";
    public static final String MORE_7_YEARS_STRING = ", автомобиль старше 7 лет";
    public static final String GAS_STRING = ", бензиновый или гибридный двигатель";
    public static final String ELECTRIC_STRING = ", электродвигатель";
    public static final String PRICE_STRING = "Цена: ";
    public static final String VOLUME_LESS_1000_STRING = " объем не более 1000 куб. см";
    public static final String VOLUME_BETWEEN_1000_2000_STRING = " объем от 1000 куб. см до 2000 куб. см";
    public static final String VOLUME_BETWEEN_2000_3000_STRING = " объем от 2000 куб. см до 3000 куб. см";
    public static final String VOLUME_BETWEEN_3000_3500_STRING = " объем от 3000 куб. см до 3500 куб. см";
    public static final String VOLUME_MORE_3500_STRING = " объем свыше 3500 куб. см.";

    public static final String START = "/start";
    public static final String EAES = "/eaes";
    public static final String OTHER_COUNTRIES = "/other";
    public static final String PHYSICAL = "/physical";
    public static final String JURIDICAL = "/juridical";
    public static final String LESS_3_YEARS = "/less_3";
    public static final String BETWEEN_3_AND_7_YEARS = "/3_7_years";
    public static final String MORE_7_YEARS = "/more_7";
    public static final String GAS = "/gas";
    public static final String ELECTRIC = "/electric";
    public static final String VOLUME_LESS_1000 = "/less_1000";
    public static final String VOLUME_BETWEEN_1000_2000 = "/1000_2000";
    public static final String VOLUME_BETWEEN_2000_3000 = "/2000_3000";
    public static final String VOLUME_BETWEEN_3000_3500 = "/3000_3500";
    public static final String VOLUME_MORE_3500 = "/more_3500";


}
