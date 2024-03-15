package by.custom.utilcalculator.exception.constants;

public enum UtilsborErrorCode {
    USER_FILE_NOT_FOUND ("user_file_not_found"),
    READING_FROM_FILE_EXCEPTION ("file_reading_error"),
    SAVING_INTO_FILE_EXCEPTION ("file_saving_error"),
    //WRONG_QUEUE_EXCEPTION ("wrong_users_queue_steps"),

    INVALID_OWNER_TYPE ("invalid_owner_type"),
    INVALID_CAR_AGE ("invalid_car_age"),
    INVALID_ENGINE_TYPE ("invalid_engine_type"),
    INVALID_ENGINE_VOLUME ("invalid_engine_volume"),
    ;

    private final String errorCode;
    UtilsborErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getTitle(){
        return errorCode;
    }
}
