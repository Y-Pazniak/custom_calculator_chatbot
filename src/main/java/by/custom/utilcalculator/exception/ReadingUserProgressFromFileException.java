package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

public class ReadingUserProgressFromFileException extends UtilsborException {
    public ReadingUserProgressFromFileException(final String chatID, final String fileName) {
        super(UtilsborErrorCode.READING_FROM_FILE_EXCEPTION.getTitle(),
                createStringForStackTrace(UtilsborErrorDescription.READING_FROM_FILE_ERROR_DESCRIPTION.getTitle(), chatID, fileName));
    }
}
