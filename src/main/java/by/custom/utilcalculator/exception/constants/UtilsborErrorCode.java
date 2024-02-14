package by.custom.utilcalculator.exception.constants;

public enum UtilsborErrorCode {
    USER_FILE_NOT_FOUND ("user_file_not_found"),
    READING_FROM_FILE_EXCEPTION ("file_reading_error"),
    SAVING_INTO_FILE_EXCEPTION ("file_saving_error"),
    WRONG_QUEUE_EXCEPTION ("wrong_users_queue_steps"),
    ;

    private final String errorCode;
    UtilsborErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTitle(){
        return errorCode;
    }
}
