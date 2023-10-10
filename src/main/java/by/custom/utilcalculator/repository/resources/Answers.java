package by.custom.utilcalculator.repository.resources;

public class Answers {
    //to build introduction of summary user choice string
    public static final String YOUR_CHOICE = "Вы выбрали: ";

    //first step answers
    public static final String EAES = "автомобили из ЕАЭС";
    public static final String OTHER_COUNTRIES = "автомобили из иных стран";

    //second step answers
    public static final String PHYSICAL_PERSON = ", физическое лицо";
    public static final String JURIDICAL_PERSON = ", юридическое лицо";

    //the last step answers
    public static final String LESS_3_YEARS_OLD = ", автомобиль младше 3 лет";
    public static final String BETWEEN_3_AND_7_YEARS_OLD = ", автомобиль от 3 до 7 лет";
    public static final String MORE_7_YEARS_OLD = ", автомобиль старше 7 лет";

    //other juridical third step answers
    public static final String GASOLINE_OR_HYBRID_ENGINE = ", бензиновый или гибридный двигатель";
    public static final String ELECTRIC_ENGINE = ", электродвигатель";

    //other juridical fourth step command
    public static final String VOLUME_LESS_1000_CM = " объем не более 1000 куб. см";
    public static final String VOLUME_BETWEEN_1000_2000_CM = " объем от 1000 куб. см до 2000 куб. см";
    public static final String VOLUME_BETWEEN_2000_3000_CM = " объем от 2000 куб. см до 3000 куб. см";
    public static final String VOLUME_BETWEEN_3000_3500_CM = " объем от 3000 куб. см до 3500 куб. см";
    public static final String VOLUME_MORE_3500_CM = " объем свыше 3500 куб. см";

    //answer to build string with price
    public static final String PRICE = "Цена: ";
    public static final String BYN = " BYN\n";

    //after price string with advices
    public static final String FAREWELL = "\nДополнительная информация.\n custom.by - оформление утильсбора;  \n /start - перезапустить калькулятор.";
}
