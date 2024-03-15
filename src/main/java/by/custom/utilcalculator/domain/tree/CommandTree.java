package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.UserProgress;

import java.util.Objects;

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

    private boolean existsCommandInNodeParent(String requestingCommand, Node node) {
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

    //hello! even I can hardly understand what is going on, so there are comments
    public Node getNode(final UserProgress userProgress) { //method for extracting user's step node
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

    private boolean existCommandInNodeChildren(final String requestingCommand, final Node currentNode) { //method to check is it permissible to get next node
        boolean requestingCommandOk = false;
        for (Node kidNode : currentNode.getChildren()) { //taking node's kids
            if (kidNode.getKey().equals(requestingCommand)) { //if node's kid contains requesting command
                requestingCommandOk = true; //we can approve requesting command, add it to the list and show to a user next question
                break;
            }
        }
        return requestingCommandOk;
    }

    private static class TreeHolder {
        private static final CommandTree TREE_HOLDER = new CommandTree();
    }
}