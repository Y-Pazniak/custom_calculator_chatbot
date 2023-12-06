package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

public class WritingUserProgressIntoFileException extends UtilsborException {
    private final String chatID;
    private final String fileName;

    public WritingUserProgressIntoFileException(final String chatID, final String fileName, Throwable cause) {
        super(UtilsborErrorCode.SAVING_INTO_FILE_EXCEPTION.getTitle(),
                createStringForStackTrace(UtilsborErrorDescription.WRITING_INTO_FILE_ERROR_DESCRIPTION.getTitle(), chatID, fileName));
        this.chatID = chatID;
        this.fileName = fileName;
    }

    public String getChatID() {
        return chatID;
    }

    public String getFileName() {
        return fileName;
    }
}
