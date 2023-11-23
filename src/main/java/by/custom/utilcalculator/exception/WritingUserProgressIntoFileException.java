package by.custom.utilcalculator.exception;

public class WritingUserProgressIntoFileException extends UtilsborException {
    @Override
    public String getExceptionCode() {
        return SAVING_INTO_FILE_EXCEPTION;
    }

    @Override
    public String getFileNameExceptionDetails(String filename) {
        return "Impossible to save into: " + filename;
    }

    @Override
    public String getUserChatIDExceptionDetails(String chatID) {
        return  "Impossible to save user: " + chatID;
    }

    @Override
    public String getUserCommandExceptionDetails(String userCommand) {
        return "Impossible to save for command: " + userCommand;
    }
}
