package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.UserProgress;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandTree {

    private CommandTree() {
    }

    public static CommandTree getInstance() {
        return TreeHolder.TREE_HOLDER;
    }


    public boolean validateCommand(final String requestingCommand, final UserProgress userProgress) {
        return isRequestingCommandAcceptable(requestingCommand, getNode(userProgress));
    }

    public boolean isRequestingCommandAcceptable(String requestingCommand, Node node) {
        boolean isRequestingCommandInParent = existsCommandInNodeParent(requestingCommand, node);
        boolean isRequestingCommandInKids = existCommandInNodeChildren(requestingCommand, node);
        boolean isRequestingCommandEqualsNode = isRequestingCommandEqualsCurrentNode(requestingCommand, node);

        return isRequestingCommandInParent || isRequestingCommandInKids || isRequestingCommandEqualsNode;
    }

    private boolean isRequestingCommandEqualsCurrentNode(String requestingCommand, Node node) {
        return node.getKey().equals(requestingCommand);
    }

    private boolean existsCommandInNodeParent(String requestingCommand, Node node) { //checking is there such command in node parents
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
        Node currentNode = NodeStorage.getInstance().getNodes().getFirst();
        String[] userPath = userProgress.getUserPath();

        for (String userStep : userPath) {
            if (!Objects.isNull(userStep)) {
                for (Node kid : currentNode.getChildren()) {
                    if (kid.getKey().equals(userStep)) {
                        currentNode = kid;
                    }
                }
            }
        }
        return currentNode;
    }

    private boolean existCommandInNodeChildren(final String requestingCommand, final Node currentNode) {
        Set<String> childrenKeys = currentNode.getChildren().stream().map(Node::getKey).collect(Collectors.toSet()); //I literally have no idea what is it and how it works O_O
        return childrenKeys.contains(requestingCommand);
    }

    private static class TreeHolder {
        private static final CommandTree TREE_HOLDER = new CommandTree();
    }
}