package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.exception.constants.UtilsborErrorCode;
import by.custom.utilcalculator.exception.constants.UtilsborErrorDescription;

public class InvalidOrderCommandException extends UtilsborException {
    private final String chatID;
    private final Command command;

    public InvalidOrderCommandException(final String chatID, final Command command) {
        super(UtilsborErrorCode.INVALID_ORDER_EXCEPTION.getTitle(),
                createStringForStackTrace(UtilsborErrorDescription.INVALID_ORDER_EXCEPTION.getTitle(), chatID, command.getCommand()));
        this.chatID = chatID;
        this.command = command;
    }

    public String getChatID() {
        return chatID;
    }

    public String getWrongStep() {
        return command.getCommand();
    }
}
