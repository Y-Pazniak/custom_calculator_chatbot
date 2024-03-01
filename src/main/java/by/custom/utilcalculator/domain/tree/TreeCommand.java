package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.UserProgress;

import java.util.List;
import java.util.Objects;

public class TreeCommand {
    private final List<Node> nodes;

    private TreeCommand() {
        nodes = NodeStorage.getInstance().getNodes();
    }

    public static TreeCommand getInstance() {
        return TreeHolder.TREE_HOLDER;
    }

    public boolean validateCommand(final String requestingCommand, final UserProgress userProgress) {
        return isRequestingCommandAcceptable(requestingCommand, currentNodeCreation(userProgress));
    }

    private boolean isRequestingCommandAcceptable(String requestingCommand, Node node) {
        if (isRequestingCommandInCurrentNodeKids(requestingCommand, node)) {
            return true;
        } else {
            if (isRequestingCommandInCurrentNodeParents(requestingCommand, node)){
                return true;
            } else {
                return isRequestingCommandEqualsCurrentNode(requestingCommand, node);
            }
        }
    }

    private boolean isRequestingCommandEqualsCurrentNode(String requestingCommand, Node node) {
        return node.getKey().equals(requestingCommand);
    }

    private boolean isRequestingCommandInCurrentNodeParents(String requestingCommand, Node node) {
        Node parent = node.getParent();
        while (!Objects.isNull(parent)) {
            if (requestingCommand.equals(parent.getKey())) {
                return true;
            } else {
                if (isRequestingCommandInCurrentNodeKids(requestingCommand, parent)) {
                    return true;
                } else {
                    parent = parent.getParent();
                }
            }
        }
        return false;
    }

    //hello! even I can hardly understand what is going on, so there are comments
    private Node currentNodeCreation(final UserProgress userProgress) { //method for creating current user's step node
        Node currentNode = nodes.getFirst();
        String[] userPath = userProgress.getUserPath();
        for (String userPassedStep : userPath) {
            for (Node node : nodes) { //to recreate path we need to check every node
                if (node.getKey().equals(userPassedStep)) { //if we find node with key equals user's command
                    if (currentNode.equals(node.getParent())) { //we check parent of this node - if next node parent equals current node
                        currentNode = node; //we update current node to the new node
                    }
                }
            }
        }
        return currentNode;
    }

    private boolean isRequestingCommandInCurrentNodeKids(final String requestingCommand, final Node currentNode) { //method to check is it permissible to get next node
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
        private static final TreeCommand TREE_HOLDER = new TreeCommand();
    }
}