package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private final List<Node> children;
    private final Command key;


    public Node(@JsonProperty("parent") final Node parent, @JsonProperty("key") final Command key, @JsonProperty("description") final String description) {
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

    public Command getKey() {
//        Command myEnumValue = Command.valueOf(key);
//        return myEnumValue.getCommand();
        return key;
    }
}
