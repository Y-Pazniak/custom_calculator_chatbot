package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

public class TypeEngineException extends UtilsborException {
    private final String chatID;
    private final String wrongStep;

    public TypeEngineException(final String chatID, String wrongStep) {
        super(UtilsborErrorCode.INVALID_ENGINE_TYPE.getTitle(),
                createStringForStackTrace(UtilsborErrorDescription.INVALID_TYPE_ENGINE_DESCRIPTION.getTitle(), chatID, wrongStep));
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
