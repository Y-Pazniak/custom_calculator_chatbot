package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.tree.CommandTree;

public class UserProgressValidator {

    public static boolean validateCommand(final Command requestingCommand, final UserProgress userProgress) {
        return CommandTree.getInstance().validateCommand(requestingCommand, userProgress);
    }
}
