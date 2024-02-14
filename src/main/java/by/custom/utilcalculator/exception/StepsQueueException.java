package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

public class StepsQueueException extends UtilsborException {
    private final String chatID;
    private final String wrongStep;

    public StepsQueueException(final String chatID, String wrongStep) {
        super(UtilsborErrorCode.WRONG_QUEUE_EXCEPTION.getTitle(),
                createStringForStackTrace(UtilsborErrorDescription.NEXT_STEP_USER_ERROR_DESCRIPTION.getTitle(), chatID, wrongStep));
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
