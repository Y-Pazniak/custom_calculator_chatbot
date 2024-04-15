package by.custom.utilcalculator.exception.constants;

public enum UtilsborErrorCode {
    USER_FILE_NOT_FOUND ("user_file_not_found"),
    READING_FROM_FILE_EXCEPTION ("file_reading_error"),
    SAVING_INTO_FILE_EXCEPTION ("file_saving_error"),
    INVALID_ORDER_EXCEPTION("invalid_order_exception"),
    ;

    private final String errorCode;
    UtilsborErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTitle(){
        return errorCode;
    }
}
