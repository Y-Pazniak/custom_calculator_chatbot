package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.Step;
import by.custom.utilcalculator.exception.UtilsborCommandTreeReadingException;

public class FillerTree {

    public static void fillTreeJson() throws UtilsborCommandTreeReadingException {
        ModifierTree.addNode(null, Command.AGE, "test", Step.FAREWELL);
        ModifierTree.addNode(null, Command.AGE, "test", Step.FAREWELL);
        ModifierTree.addNode(null, Command.AGE, "test", Step.FAREWELL);
        ModifierTree.addNode(null, Command.AGE, "test", Step.FAREWELL);
        ModifierTree.addNode(null, Command.AGE, "test", Step.FAREWELL);
        ModifierTree.addNode(null, Command.AGE, "test", Step.FAREWELL);
        ModifierTree.addNode(null, Command.AGE, "test", Step.FAREWELL);
        ModifierTree.addNode(null, Command.AGE, "test", Step.FAREWELL);
        ModifierTree.addNode(null, Command.AGE, "test", Step.FAREWELL);
        ModifierTree.addNode(null, Command.AGE, "test", Step.FAREWELL);
    }
}
