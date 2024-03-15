package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

public class OwnerTypeException extends UtilsborException {
    private final String chatID;
    private final String wrongStep;

    public OwnerTypeException(final String chatID, String wrongStep) {
        super(UtilsborErrorCode.INVALID_OWNER_TYPE.getTitle(),
                createStringForStackTrace(UtilsborErrorDescription.INVALID_OWNER_TYPE_DESCRIPTION.getTitle(), chatID, wrongStep));
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
