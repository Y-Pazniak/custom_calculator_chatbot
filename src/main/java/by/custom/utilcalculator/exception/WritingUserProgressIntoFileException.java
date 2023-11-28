package by.custom.utilcalculator.exception;

public class WritingUserProgressIntoFileException extends UtilsborException {
    private static final String SAVING_INTO_FILE_EXCEPTION = "file_saving_error";
    private static final String errorDescription = "save into the file";

    public WritingUserProgressIntoFileException(final String chatID, final String fileName) {
        super(SAVING_INTO_FILE_EXCEPTION, createStringForStackTrace(errorDescription, chatID, fileName));
    }
}
