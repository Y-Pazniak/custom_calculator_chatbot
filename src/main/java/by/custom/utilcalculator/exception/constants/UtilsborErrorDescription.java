package by.custom.utilcalculator.exception.constants;

public enum UtilsborErrorDescription {
    READING_FROM_FILE_ERROR_DESCRIPTION ("read from file"),
    FILE_NOT_FOUND_ERROR_DESCRIPTION ("find the file"),
    WRITING_INTO_FILE_ERROR_DESCRIPTION ("save into the file"),
    NEXT_STEP_USER_ERROR_DESCRIPTION ("user's input is incorrect - wrong queue")
    ;

    private final String errorDescription;
    UtilsborErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getTitle(){
        return errorDescription;
    }
}
