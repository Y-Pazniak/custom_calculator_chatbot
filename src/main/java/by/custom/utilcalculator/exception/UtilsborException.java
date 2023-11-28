package by.custom.utilcalculator.exception;

public abstract class UtilsborException extends Exception {
    private final String errorCode;

    public UtilsborException(final String errorCode, final String detailsAboutError) {
        super(detailsAboutError);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static String createStringForStackTrace(final String errorDescription, final String chatID, final String fileName) {
        return String.format("Failed to %s for file %s in chat %s", errorDescription, fileName, chatID);
    }
}
