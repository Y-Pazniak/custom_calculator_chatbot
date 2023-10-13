package repository.strings;

public class Storage {
    public static final String GREETING_TEXT = "Бесплатно рассчитаю стоимость утильсбора в Беларуси " +
            "для легкового транспорта (категория М1).\n" +
            "Выберите, откуда везете авто:\n" +
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
            Storage.GASOLINE + " - если бензиновый или гибридный двигатель; \n" +
            Storage.ELECTRIC + " - если электрический двигатель.\n";

    public static final String GAS_ENGINE_VOLUME = "Какой объем двигателя?\n" +
            Storage.VOLUME_LESS_1000 + " - " + Storage.VOLUME_LESS_1000_STRING + "\n" +
            Storage.VOLUME_BETWEEN_1000_2000 + " - " + Storage.VOLUME_BETWEEN_1000_2000_STRING + "\n" +
            Storage.VOLUME_BETWEEN_2000_3000 + " - " + Storage.VOLUME_BETWEEN_2000_3000_STRING + "\n" +
            Storage.VOLUME_BETWEEN_3000_3500 + " - " + Storage.VOLUME_BETWEEN_3000_3500_STRING + "\n" +
            Storage.VOLUME_MORE_3500 + " - " + Storage.VOLUME_MORE_3500_STRING;

    public static final String YOUR_CHOICE_STRING = "Вы выбрали: ";
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
    public static final String VOLUME_MORE_3500_STRING = " объем свыше 3500 куб. см";
    public static final String BYN_STRING = " BYN\n";
    public static final String FAREWELL_STRING = "\nДополнительная информация.\n custom.by - оформление утильсбора;  \n /start - перезапустить калькулятор.";

    public static final String START = "/start";
    public static final String EAES = "/eaes";
    public static final String OTHER_COUNTRIES = "/other";
    public static final String PHYSICAL = "/physical";
    public static final String JURIDICAL = "/juridical";
    public static final String LESS_3_YEARS = "/less_3";
    public static final String BETWEEN_3_AND_7_YEARS = "/3_7_years";
    public static final String MORE_7_YEARS = "/more_7";
    public static final String GASOLINE = "/gasoline";
    public static final String ELECTRIC = "/electric";
    public static final String VOLUME_LESS_1000 = "/less_1000";
    public static final String VOLUME_BETWEEN_1000_2000 = "/1000_2000";
    public static final String VOLUME_BETWEEN_2000_3000 = "/2000_3000";
    public static final String VOLUME_BETWEEN_3000_3500 = "/3000_3500";
    public static final String VOLUME_MORE_3500 = "/more_3500";

    //passenger cars - EAES or other countries, physical or juridical with electric engines
    public static final String PRICE_PASSENGER_LESS_3_YEARS = "544.5";
    public static final String PRICE_PASSENGER_3_TO_7_YEARS = "816.7";
    public static final String PRICE_PASSENGER_OLDER_7_YEARS = "1225.1";

    //other countries - gasoline engine prices
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_1000_LESS_3_YEARS = "1652.2";
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_1000_3_TO_7_YEARS = "5771.7";
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_1000_OLDER_7_YEARS = "8657.6";

    public static final String PRICE_PASSENGER_OTHER_GASOLINE_1000_2000_LESS_3_YEARS = "6115.2";
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_1000_2000_3_TO_7_YEARS = "8995.1";
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_1000_2000_OLDER_7_YEARS = "13492.7";

    public static final String PRICE_PASSENGER_OTHER_GASOLINE_2000_3000_LESS_3_YEARS = "9652.7";
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_2000_3000_3_TO_7_YEARS = "17554.7";
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_2000_3000_OLDER_7_YEARS = "26332.1";

    public static final String PRICE_PASSENGER_OTHER_GASOLINE_3000_3500_LESS_3_YEARS = "8898.6";
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_3000_3500_3_TO_7_YEARS = "31036.5";
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_3000_3500_OLDER_7_YEARS = "46554.8";

    public static final String PRICE_PASSENGER_OTHER_GASOLINE_3500_LESS_3_YEARS = "15253.7";
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_3500_3_TO_7_YEARS = "38125.9";
    public static final String PRICE_PASSENGER_OTHER_GASOLINE_3500_OLDER_7_YEARS = "57188.9";

}
