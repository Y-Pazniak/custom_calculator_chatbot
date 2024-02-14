package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.steps.CountryOrigin;
import by.custom.utilcalculator.domain.constants.steps.OwnersType;
import by.custom.utilcalculator.domain.constants.steps.TypeOfEngine;
import by.custom.utilcalculator.domain.constants.steps.VolumeOfEngine;
import by.custom.utilcalculator.service.CalculatorPassenger;

import java.util.List;

public class StepsCheckerContainer {
    private Enum[] eaesArray = {CountryOrigin.EAES, OwnersType.PHYSICAL};
    private Enum[] otherPhysicalArray = {CountryOrigin.OTHER, OwnersType.PHYSICAL};
    private Enum[] otherElectricArray = {CountryOrigin.OTHER, OwnersType.PHYSICAL, TypeOfEngine.ELECTRIC};
    private Enum[] otherGasolineArray = {CountryOrigin.OTHER, OwnersType.JURIDICAL, TypeOfEngine.GASOLINE};

    private static class StepsCheckerContainerHolder {
        private static final StepsCheckerContainer CONTAINER_INSTANCE = new StepsCheckerContainer();
    }

    private StepsCheckerContainer() {
    }

    public static StepsCheckerContainer getInstance() {
        return StepsCheckerContainerHolder.CONTAINER_INSTANCE;
    }

    public Enum[] getOtherPhysicalArray() {
        return otherPhysicalArray;
    }

    public Enum[] getOtherElectricArray() {
        return otherElectricArray;
    }

    public Enum[] getOtherGasolineArray() {
        return otherGasolineArray;
    }
}
