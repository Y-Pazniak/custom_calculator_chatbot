package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.service.BundleResourcesServant;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ReadingUserProgressFromFileException extends UtilsborException {
    @Override
    public String getExceptionCode() {
        return READING_FROM_FILE_EXCEPTION;
    }


    @Override
    public String getFileNameExceptionDetails(final String filename) {
        return "Impossible to read the file. Filename: " + filename;
    }

    @Override
    public String getUserChatIDExceptionDetails(final String chatID) {
        return "Impossible to read the file for chatID: " + chatID;
    }

    @Override
    public String getUserCommandExceptionDetails(final String userCommand) {
        return "Impossible to read the file for command: " + userCommand;
    }
}
