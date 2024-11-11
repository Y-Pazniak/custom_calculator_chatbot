package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.CurrencyType;

public class Price {
    private final double amount;
    private final CurrencyType currencyType;

    public Price(final double amount, final CurrencyType currencyType) {
        this.amount = amount;
        this.currencyType = currencyType;
    }

    public double getAmount() {
        return amount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    @Override
    public String toString() {
        return "Price {" + "amount=" + amount + "; currency=" + currencyType + "}";
    }
}
