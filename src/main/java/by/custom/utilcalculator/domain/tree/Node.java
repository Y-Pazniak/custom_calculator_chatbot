package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.Step;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Node {
    @JsonIgnore
    private Node parent;
    private List<Node> children;
    private final Command key;
    private final Step nextStep;
    private final String description;

    public Node(@JsonProperty("key") final Command key,
                @JsonProperty("description") final String description,
                @JsonProperty("nextStep") final Step nextStep) {
        this.key = key;
        children = new ArrayList<>();
        this.nextStep = nextStep;
        this.description = description;
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

    public void setChildren(final List<Node> children) {
        this.children = children;
    }

    public void addKid(final Node kid) {
        kid.setParent(this);
        children.add(kid);
    }

    public String getDescription() {
        return description;
    }
}
