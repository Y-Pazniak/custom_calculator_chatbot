package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.steps.*;

public class UserProgress {
    private CountryOrigin countryOrigin = null;
    private OwnersType ownersType = null;
    private CarAge carAge = null;
    private TypeOfEngine typeOfEngine = null;
    private VolumeOfEngine volumeOfEngine = null;

    private UserProgress() {

    }

    public static UserProgress getInstance() {
        return UserProgressHolder.BOT_ENTITY;
    }

    public CountryOrigin getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(CountryOrigin countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public OwnersType getOwnersType() {
        return ownersType;
    }

    public void setOwnersType(OwnersType ownersType) {
        this.ownersType = ownersType;
    }

    public CarAge getCarAge() {
        return carAge;
    }

    public void setCarAge(CarAge carAge) {
        this.carAge = carAge;
    }

    public TypeOfEngine getTypeOfEngine() {
        return typeOfEngine;
    }

    public void setTypeOfEngine(TypeOfEngine typeOfEngine) {
        this.typeOfEngine = typeOfEngine;
    }

    public VolumeOfEngine getVolumeOfEngine() {
        return volumeOfEngine;
    }

    public void setVolumeOfEngine(VolumeOfEngine volumeOfEngine) {
        this.volumeOfEngine = volumeOfEngine;
    }

    private static class UserProgressHolder {
        private static final UserProgress BOT_ENTITY = new UserProgress();
    }
}
