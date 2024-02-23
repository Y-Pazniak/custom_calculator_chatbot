package by.custom.utilcalculator.domain.tree;

import java.util.List;

public class Tree {
    private final List<Node> nodes;

    private Tree() {
        nodes = NodeStorage.getInstance().getNodes();
    }

    public static Tree getInstance() {
        return TreeHolder.TREE_HOLDER;
    }

    public boolean validateNode(final Node node, final String command, final List<Node> nodes) {
        for (Node nodeKid : node.getChildren()) {
            if (nodeKid.getKey().equals(command)) {
                return true;
            }
        }

        for (Node userNode : nodes) {
            for (Node userNodeKid : userNode.getChildren()) {
                if (userNodeKid.getKey().equals(command)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static class TreeHolder {
        private static final Tree TREE_HOLDER = new Tree();
    }
}
