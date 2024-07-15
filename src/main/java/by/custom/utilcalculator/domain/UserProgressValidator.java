package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.tree.CommandTree;

public class UserProgressValidator {
    private static final CommandTree commandTree = CommandTree.getInstance();

    public static boolean validateCommand(final Command requestingCommand, final UserProgress userProgress) {
        return commandTree.validateCommand(requestingCommand, userProgress);
    }
}
