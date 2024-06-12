package by.custom.utilcalculator.exception;

public abstract class UtilsborException extends Exception {
    private final String errorCode;

    public UtilsborException(final String errorCode) {
        this.errorCode = errorCode;
    }

    public UtilsborException(final String errorCode, final String errorDetails) {
        super(errorDetails);
        this.errorCode = errorCode;
    }

    public UtilsborException(final String errorCode, final String detailsAboutError, final Throwable cause) {
        super(detailsAboutError, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static String createStringForStackTrace(final String errorDescription, final String chatID, final String placeOfError) {
        return String.format("Failed to %s for %s in chat %s", errorDescription, placeOfError, chatID);
    }
}
