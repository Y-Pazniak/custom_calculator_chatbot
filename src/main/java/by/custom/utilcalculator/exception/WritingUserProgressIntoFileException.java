package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

public class WritingUserProgressIntoFileException extends UtilsborException {
    public WritingUserProgressIntoFileException(final String chatID, final String fileName) {
        super(UtilsborErrorCode.SAVING_INTO_FILE_EXCEPTION.getTitle(),
                createStringForStackTrace(UtilsborErrorDescription.WRITING_INTO_FILE_ERROR_DESCRIPTION.getTitle(), chatID, fileName));
    }
}
