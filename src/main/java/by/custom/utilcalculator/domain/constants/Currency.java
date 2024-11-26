package by.custom.utilcalculator.domain.constants;

public enum Currency {
    BYN("BYN");

    private final String currencyName;

    Currency(final String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }
}
