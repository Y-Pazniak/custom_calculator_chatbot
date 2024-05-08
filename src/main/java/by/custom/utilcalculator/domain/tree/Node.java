package by.custom.utilcalculator.domain.tree;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private final List<Node> children;
    private final String key;

    public Node(@JsonProperty("parent") final Node parent, @JsonProperty("key") final String key) {
        this.parent = parent;
        this.key = key;
        children = new ArrayList<>();
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(final Node node) {
        this.parent = node;
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getKey() {
        return key;
    }
}
