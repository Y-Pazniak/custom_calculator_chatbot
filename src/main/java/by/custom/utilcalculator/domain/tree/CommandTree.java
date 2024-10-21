package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.Step;
import by.custom.utilcalculator.domain.constants.steps.StepsIndicator;
import by.custom.utilcalculator.exception.UtilsborCommandTreeReadingException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandTree {
    private final Map<StepsIndicator, Command> fieldsToCommands;
    private final Map<Command, List<StepsIndicator>> commandsToFields;
    private final Node treeRoot;

    private CommandTree() throws UtilsborCommandTreeReadingException {
        fieldsToCommands = HelperTree.fillFieldsToCommandsMap();
        commandsToFields = HelperTree.fillCommandsToFields(fieldsToCommands);
        treeRoot = HelperTree.buildTree();
    }

    public static CommandTree getInstance() {
        return TreeHolder.TREE_HOLDER;
    }

    public Map<StepsIndicator, Command> getFieldsToCommands() {
        return fieldsToCommands;
    }

    public Map<Command, List<StepsIndicator>> getCommandsToFields() {
        return commandsToFields;
    }

    public boolean validateCommandFromNode(final Command requestingCommand, final UserProgress userProgress) {
        return isRequestingCommandAcceptable(requestingCommand, getNode(userProgress));
    }

    public static boolean validateCommand(final Command requestingCommand, final UserProgress userProgress) {
        Command command = requestingCommand.getFamily() == null ? requestingCommand : requestingCommand.getFamily();
        return CommandTree.getInstance().validateCommandFromNode(command, userProgress);
    }

    public static Step getNextStep(final UserProgress userProgress) {
        return CommandTree.getInstance().getNextStepFromNode(userProgress);
    }

    public Step getNextStepFromNode(final UserProgress userProgress) {
        return getNode(userProgress).getNextStep();
    }

    public boolean isRequestingCommandAcceptable(final Command requestingCommand, final Node node) {
        final boolean isRequestingCommandInParent = existsCommandInNodeParent(requestingCommand, node);
        final boolean isRequestingCommandInKids = existCommandInNodeChildren(requestingCommand, node);
        final boolean isRequestingCommandEqualsNode = doesRequestingCommandEqualsCurrentNode(requestingCommand, node);

        return isRequestingCommandInParent || isRequestingCommandInKids || isRequestingCommandEqualsNode;
    }

    private boolean doesRequestingCommandEqualsCurrentNode(final Command requestingCommand, final Node node) {
        return node.getKey().equals(requestingCommand);
    }

    private boolean existsCommandInNodeParent(final Command requestingCommand, final Node node) { //checking is there such command in node parents
        Node parent = node.getParent();
        while (!Objects.isNull(parent)) {
            if (requestingCommand.equals(parent.getKey())) {
                return true;
            } else {
                if (existCommandInNodeChildren(requestingCommand, parent)) {
                    return true;
                } else {
                    parent = parent.getParent();
                }
            }
        }
        return false;
    }

    public Node getNode(final UserProgress userProgress) {
        Node node = treeRoot;
        final List<Command> userPath = userProgress.getUserPath();
        for (Command userStep : userPath) {
            for (Node kid : node.getChildren()) {
                if (kid.getKey().equals(userStep)) {
                    node = kid;
                    break;
                }
            }
        }

        return node;
    }

    private boolean existCommandInNodeChildren(final Command requestingCommand, final Node currentNode) {
        final Set<Command> childrenKeys = currentNode.getChildren().stream().map(Node::getKey).collect(Collectors.toSet()); //I literally have no idea what is it and how it works O_O
        return childrenKeys.contains(requestingCommand);
    }

    private static class TreeHolder {
        private static final CommandTree TREE_HOLDER;

        static {
            try {
                TREE_HOLDER = new CommandTree();
            } catch (UtilsborCommandTreeReadingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}