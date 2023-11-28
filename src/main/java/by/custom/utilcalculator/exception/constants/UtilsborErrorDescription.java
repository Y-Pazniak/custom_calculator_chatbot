package by.custom.utilcalculator.exception.constants;

public enum UtilsborErrorDescription {
    READING_FROM_FILE_ERROR_DESCRIPTION ("read from file"),
    FILE_NOT_FOUND_ERROR_DESCRIPTION ("find the file"),
    WRITING_INTO_FILE_ERROR_DESCRIPTION ("save into the file")
    ;

    private final String errorDescription;
    UtilsborErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getTitle(){
        return errorDescription;
    }
}
