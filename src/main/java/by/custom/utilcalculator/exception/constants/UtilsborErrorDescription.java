package by.custom.utilcalculator.exception.constants;

public enum UtilsborErrorDescription {
    READING_FROM_FILE_ERROR_DESCRIPTION ("read from file"),
    FILE_NOT_FOUND_ERROR_DESCRIPTION ("find the file"),
    WRITING_INTO_FILE_ERROR_DESCRIPTION ("save into the file"),
    INVALID_OWNER_TYPE_DESCRIPTION ("wrong queue - impossible to set an owner"),
    INVALID_CAR_AGE_DESCRIPTION ("wrong queue - impossible to set age"),
    INVALID_TYPE_ENGINE_DESCRIPTION ("wrong queue - impossible to set type of engine"),
    INVALID_VOLUME_ENGINE_DESCRIPTION ("wrong queue - impossible to set volume of engine"),
    ;

    private final String errorDescription;
    UtilsborErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getTitle(){
        return errorDescription;
    }
}
