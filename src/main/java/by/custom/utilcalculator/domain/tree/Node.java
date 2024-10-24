package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.Step;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Node {
    @JsonIgnore
    private Node parent;
    private List<Node> children;
    private final Command key;
    private final Step nextStep;
    private final String nextMessage;
    private final String price;

    public Node(@JsonProperty("key") final Command key,
                @JsonProperty("description") final String description,
                @JsonProperty("nextStep") final Step nextStep,
                @JsonProperty("nextMessage") final String nextMessage,
                @JsonProperty("price") @Nullable final String price) {
        this.key = key;
        children = new ArrayList<>();
        this.nextStep = nextStep;
        this.nextMessage = nextMessage;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public Step getNextStep() {
        return nextStep;
    }

    public String getNextMessage(){
        return nextMessage;
    }
}
