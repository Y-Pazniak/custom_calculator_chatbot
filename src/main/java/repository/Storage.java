package repository;

public class Storage {
    public static final String GREETING_TEXT = "Добрый день. Я чат-бот, который поможет вам бесплатно рассчитать стоимость утильсбора в Беларуси " +
            "для легкового транспорта (категория М1).\n" +
            "Пожалуйста, выберите тип транспортного средства:\n" +
            Storage.EAES + " - для автомобилей из России и ЕАЭС;\n" +
            Storage.OTHER_COUNTRIES + " - для автомобилей из других стран.";

    public static final String START = "/start";
    public static final String EAES = "/eaes";
    public static final String OTHER_COUNTRIES = "/other";
}
