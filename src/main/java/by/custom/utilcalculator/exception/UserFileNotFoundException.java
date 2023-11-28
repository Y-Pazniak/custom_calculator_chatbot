package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.service.BundleResourcesServant;
import org.telegram.telegrambots.meta.api.objects.Message;

public class UserFileNotFoundException extends UtilsborException {
    private static final String USER_FILE_NOT_FOUND = "user_file_not_found";
    private static final String errorDescription = "find the file";

    public UserFileNotFoundException(final String chatID, final String fileName) {
        super(USER_FILE_NOT_FOUND, createStringForStackTrace(errorDescription, chatID, fileName));
    }
}
