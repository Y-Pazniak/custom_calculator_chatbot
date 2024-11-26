package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.Price;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.Currency;
import by.custom.utilcalculator.domain.constants.steps.Step;
import by.custom.utilcalculator.domain.constants.steps.StepsIndicator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Node {
    @JsonIgnore
    private Node parent;
    private final List<Node> children;
    private final Command key;
    private final Step nextStep;
    private final String nextMessage;
    @Nullable
    private final Price price;
    @JsonIgnore
    private final StepsIndicator stepsIndicator;

    public Node(@JsonProperty("key") final Command key,
                @JsonProperty("description") final String description,
                @JsonProperty("nextStep") final Step nextStep,
                @JsonProperty("nextMessage") final String nextMessage,
                @JsonProperty("command") final String command,
                @JsonProperty("price") @Nullable final BigDecimal price) {
        this.key = key;
        children = new ArrayList<>();
        this.nextStep = nextStep;
        this.nextMessage = nextMessage;
        if (price != null) {
            this.price = new Price(price, Currency.BYN);
        } else {
            this.price = null;
        }
        stepsIndicator = Command.getStepIndicatorByCommand(key);
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

    public Price getPrice() {
        return price;
    }

    public Step getNextStep() {
        return nextStep;
    }

    public String getNextMessage() {
        return nextMessage;
    }

    public StepsIndicator getStepsIndicator() {
        return stepsIndicator;
    }
}