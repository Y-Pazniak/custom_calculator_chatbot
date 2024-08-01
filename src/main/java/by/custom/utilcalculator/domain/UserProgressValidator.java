package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.tree.CommandTree;

public class UserProgressValidator {
    public static boolean validateCommand(final Command requestingCommand, final UserProgress userProgress) {
        Command command = requestingCommand.getFamily() == null ? requestingCommand : requestingCommand.getFamily();
        return CommandTree.getInstance().validateCommandFromNode(command, userProgress);
    }
}
