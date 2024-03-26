package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

public class InvalidOrderCommandException extends UtilsborException {
    private final String chatID;
    private final String wrongStep;

    public InvalidOrderCommandException(final String chatID, String wrongStep) {
        super(UtilsborErrorCode.INVALID_ORDER_EXCEPTION.getTitle(),
                createStringForStackTrace(UtilsborErrorDescription.INVALID_ORDER_EXCEPTION.getTitle(), chatID, wrongStep));
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
