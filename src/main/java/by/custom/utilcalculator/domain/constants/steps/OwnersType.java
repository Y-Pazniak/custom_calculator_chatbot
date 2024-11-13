package by.custom.utilcalculator.domain.constants.steps;

public enum OwnersType implements StepsIndicator {
    PHYSICAL, JURIDICAL;

    @Override
    public String getName() {
        return this.name();
    }
}
