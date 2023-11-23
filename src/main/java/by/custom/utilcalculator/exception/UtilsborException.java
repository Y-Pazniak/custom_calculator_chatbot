package by.custom.utilcalculator.exception;

import org.telegram.telegrambots.meta.api.objects.Message;

public abstract class UtilsborException extends Exception {
    public static final String USER_FILE_NOT_FOUND = "user_file_not_found";
    public static final String READING_FROM_FILE_EXCEPTION = "file_reading_error";
    public static final String SAVING_INTO_FILE_EXCEPTION = "file_saving_error";
    public abstract String getExceptionCode();

    public abstract String getFileNameExceptionDetails(final String filename);

    public abstract String getUserChatIDExceptionDetails(final String chatID);

    public abstract String getUserCommandExceptionDetails(final String userCommand);

    public String[] getMessageInfo(final Message message) {
        return message.toString().split(", ");
    }
}
