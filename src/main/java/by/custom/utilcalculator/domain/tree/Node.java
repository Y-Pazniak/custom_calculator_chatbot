package by.custom.utilcalculator.domain.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
    private final Node parent;
    private final List<Node> children;
    private final String key;

    public Node(Node parent, String key) {
        this.parent = parent;
        this.key = key;
        children = new ArrayList<>();
    }

    public Node getParent() {
        //return parent == null ? new Node(null, "zeroNode") : parent;
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getKey() {
        return key;
    }

    public void addChildren(List<Node> children) {
        this.children.addAll(children);
    }
}
