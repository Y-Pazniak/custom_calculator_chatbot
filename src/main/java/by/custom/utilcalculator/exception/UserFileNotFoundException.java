package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

public class UserFileNotFoundException extends UtilsborException {
    public UserFileNotFoundException(final String chatID, final String fileName) {
        super(UtilsborErrorCode.USER_FILE_NOT_FOUND.getTitle(),
                createStringForStackTrace(UtilsborErrorDescription.FILE_NOT_FOUND_ERROR_DESCRIPTION.getTitle(), chatID, fileName));
    }
}
