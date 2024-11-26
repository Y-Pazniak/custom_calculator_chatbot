package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.Currency;

import java.math.BigDecimal;

public class Price {
    private final BigDecimal amount;
    private final Currency currencyType;

    public Price(final BigDecimal amount, final Currency currencyType) {
        this.amount = amount;
        this.currencyType = currencyType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrencyType() {
        return currencyType;
    }

    @Override
    public String toString() {
        return "Price {" + "amount=" + amount + "; currency=" + currencyType + "}";
    }
}
