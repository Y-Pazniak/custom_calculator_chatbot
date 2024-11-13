package by.custom.utilcalculator.domain.constants.steps;

public enum CountryOrigin implements StepsIndicator {
    EAES, OTHER;

    @Override
    public String getName() {
        return this.name();
    }
}
