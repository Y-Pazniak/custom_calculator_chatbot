package by.custom.utilcalculator.domain.constants;

public enum CurrencyType {
    BYN("BYN");

    private final String currencyName;

    CurrencyType(final String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }
}
