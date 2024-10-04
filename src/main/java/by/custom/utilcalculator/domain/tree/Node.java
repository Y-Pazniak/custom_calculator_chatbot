package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.Step;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private final List<Node> children;
    private final Command key;
    private final Step nextStep;


    public Node(@JsonProperty("parent") final Node parent,
                @JsonProperty("key") final Command key,
                @JsonProperty("description") final String description,
                @JsonProperty("nextStep") final Step nextStep) {
        this.parent = parent;
        this.key = key;
        children = new ArrayList<>();
        this.nextStep = nextStep;
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
        return key;
    }

    public Step getNextStep() {
        return nextStep;
    }
}
