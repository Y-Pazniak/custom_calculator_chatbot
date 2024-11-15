package by.custom.utilcalculator.domain.constants.steps;

public enum TrailerO4Type implements StepsIndicator {
    TRAILERS, HALF_TRAILERS;

    @Override
    public String getName() {
        return this.name();
    }
}
