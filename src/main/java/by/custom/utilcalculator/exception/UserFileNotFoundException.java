package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.service.BundleResourcesServant;
import org.telegram.telegrambots.meta.api.objects.Message;

public class UserFileNotFoundException extends UtilsborException {
    @Override
    public String getExceptionCode() {
        return USER_FILE_NOT_FOUND;
    }

    @Override
    public String getFileNameExceptionDetails(final String filename) {
        return "File not found for filename: " + filename;
    }

    @Override
    public String getUserChatIDExceptionDetails(final String chatID) {
        return "File not found for chatID: " + chatID;
    }

    @Override
    public String getUserCommandExceptionDetails(final String userCommand) {
        return "File not found for user's command: " + userCommand;
    }
}
