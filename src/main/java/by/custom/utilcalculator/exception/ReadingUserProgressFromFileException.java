package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.service.BundleResourcesServant;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ReadingUserProgressFromFileException extends UtilsborException {
    private static final String READING_FROM_FILE_EXCEPTION = "file_reading_error";
    private static final String errorDescription = "read from file";

    public ReadingUserProgressFromFileException(final String chatID, final String fileName) {
        super(READING_FROM_FILE_EXCEPTION, createStringForStackTrace(errorDescription, chatID, fileName));
    }
}
