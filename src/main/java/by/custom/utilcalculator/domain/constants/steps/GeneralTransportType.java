package by.custom.utilcalculator.domain.constants.steps;

public enum GeneralTransportType implements StepsIndicator {
    START, M1, BUSES_AND_TRUCKS, SELF_PROPELLED_VEHICLES;

    @Override
    public String getName() {
        return this.name();
    }
}
