package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.steps.StepsIndicator;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandTree {
    private final Map<StepsIndicator, String> fieldsToCommands;
    private final Node treeRoot;

    private CommandTree() {
        fieldsToCommands = HelperTree.fillFieldsToCommandsMap();
        treeRoot = HelperTree.buildTree();
    }

    public static CommandTree getInstance() {
        return TreeHolder.TREE_HOLDER;
    }

    public Map<StepsIndicator, String> getFieldsToCommands() {
        return fieldsToCommands;
    }

    public boolean validateCommand(final String requestingCommand, final UserProgress userProgress) {
        return isRequestingCommandAcceptable(requestingCommand, getNode(userProgress));
    }

    public boolean isRequestingCommandAcceptable(final String requestingCommand, final Node node) {
        final boolean isRequestingCommandInParent = existsCommandInNodeParent(requestingCommand, node);
        final boolean isRequestingCommandInKids = existCommandInNodeChildren(requestingCommand, node);
        final boolean isRequestingCommandEqualsNode = doesRequestingCommandEqualsCurrentNode(requestingCommand, node);

        return isRequestingCommandInParent || isRequestingCommandInKids || isRequestingCommandEqualsNode;
    }

    private boolean doesRequestingCommandEqualsCurrentNode(final String requestingCommand, final Node node) {
        return node.getKey().equals(requestingCommand);
    }

    private boolean existsCommandInNodeParent(final String requestingCommand, final Node node) { //checking is there such command in node parents
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
        final String[] userPath = userProgress.getUserPath();

        for (final String userStep : userPath) {
            if (!Objects.isNull(userStep)) {
                for (final Node kid : node.getChildren()) {
                    if (kid.getKey().equals(userStep)) {
                        node = kid;
                    }
                }
            }
        }
        return node;
    }

    private boolean existCommandInNodeChildren(final String requestingCommand, final Node currentNode) {
        final Set<String> childrenKeys = currentNode.getChildren().stream().map(Node::getKey).collect(Collectors.toSet()); //I literally have no idea what is it and how it works O_O
        return childrenKeys.contains(requestingCommand);
    }

    private static class TreeHolder {
        private static final CommandTree TREE_HOLDER = new CommandTree();
    }
}