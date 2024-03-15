package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

public class CarAgeException extends UtilsborException {
    private final String chatID;
    private final String wrongStep;

    public CarAgeException(final String chatID, String wrongStep) {
        super(UtilsborErrorCode.INVALID_CAR_AGE.getTitle(),
                createStringForStackTrace(UtilsborErrorDescription.INVALID_CAR_AGE_DESCRIPTION.getTitle(), chatID, wrongStep));
        this.chatID = chatID;
        this.wrongStep = wrongStep;
    }

    public String getChatID() {
        return chatID;
    }

    public String getWrongStep() {
        return wrongStep;
    }
}
