package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.steps.*;

import java.io.Serializable;

public class UserProgress implements Serializable {
    private CountryOrigin countryOrigin = null;
    private OwnersType ownersType = null;
    private CarAge carAge = null;
    private TypeOfEngine typeOfEngine = null;
    private VolumeOfEngine volumeOfEngine = null;
    private final String chatID;

    public UserProgress(final String chatID) {
        this.chatID = chatID;
    }

    public CountryOrigin getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(final CountryOrigin countryOrigin) {
        cleanStepsAfterCurrent(1);
        this.countryOrigin = countryOrigin;
    }

    public OwnersType getOwnersType() {
        return ownersType;
    }

    public void setOwnersType(final OwnersType ownersType) {
        cleanStepsAfterCurrent(2);
        this.ownersType = ownersType;
    }

    public CarAge getCarAge() {
        return carAge;
    }

    public void setCarAge(final CarAge carAge) {
        cleanStepsAfterCurrent(5);
        this.carAge = carAge;
    }

    public TypeOfEngine getTypeOfEngine() {
        return typeOfEngine;
    }

    public void setTypeOfEngine(final TypeOfEngine typeOfEngine) {
        cleanStepsAfterCurrent(3);
        this.typeOfEngine = typeOfEngine;
    }

    public VolumeOfEngine getVolumeOfEngine() {
        return volumeOfEngine;
    }

    public void setVolumeOfEngine(final VolumeOfEngine volumeOfEngine) {
        cleanStepsAfterCurrent(4);
        this.volumeOfEngine = volumeOfEngine;
    }

    public String getChatID() {
        return chatID;
    }

    private void cleanStepsAfterCurrent(final int stepCleaner) {
        if (stepCleaner <= 1) {
            this.countryOrigin = null;
        }
        if (stepCleaner <= 2) {
            this.ownersType = null;
        }
        if (stepCleaner <= 3) {
            this.typeOfEngine = null;
        }
        if (stepCleaner <= 4) {
            this.volumeOfEngine = null;
        }
        if (stepCleaner <= 5) {
            this.carAge = null;
        }
    }
}
