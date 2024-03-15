package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;

import java.util.*;

public class NodeStorage {
    private final List<Node> nodes;

    private NodeStorage() {
        nodes = new ArrayList<>();
        fillNodes();
    }

    public static NodeStorage getInstance() {
        return NodeStorageHolder.NODE_STORAGE;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    private void fillNodes() {
        //start node
        Node start = new Node(null, Command.START);
        nodes.add(start);

        //eaes nodes queue
        Node eaes = new Node(start, Command.EAES);
        Node eaesPhysical = new Node(eaes, Command.PHYSICAL_PERSON);
        Node eaesJuridical = new Node(eaes, Command.JURIDICAL_PERSON);
        eaes.addChildren(Arrays.asList(eaesPhysical, eaesJuridical));
        Node eaesPhysicalAge = new Node(eaesPhysical, Command.AGE);
        eaesPhysical.addChildren(List.of(eaesPhysicalAge));
        Node eaesJuridicalAge = new Node(eaesJuridical, Command.AGE);
        eaesJuridical.addChildren(List.of(eaesJuridicalAge));
        Collections.addAll(nodes, eaes, eaesPhysical, eaesJuridical, eaesPhysicalAge, eaesJuridicalAge);

        //other physical nodes queue
        Node other = new Node(start, Command.OTHER_COUNTRIES);
        start.addChildren(Arrays.asList(eaes, other));
        Node otherPhysical = new Node(other, Command.PHYSICAL_PERSON);
        Node otherPhysicalAge = new Node(otherPhysical, Command.AGE);
        otherPhysical.addChildren(List.of(otherPhysicalAge));
        Collections.addAll(nodes, other, otherPhysical, otherPhysicalAge);

        //other juridical electric nodes queue
        Node otherJuridical = new Node(other, Command.JURIDICAL_PERSON);
        other.addChildren(Arrays.asList(otherPhysical, otherJuridical));
        Node otherElectricEngine = new Node(otherJuridical, Command.ELECTRIC_TYPE_ENGINE);
        Node otherElectricAge = new Node(otherElectricEngine, Command.AGE);
        otherElectricEngine.addChildren(List.of(otherElectricAge));
        Collections.addAll(nodes, otherJuridical, otherElectricEngine, otherElectricAge);

        //other juridical gasoline nodes queue
        Node otherGasolineEngine = new Node(otherJuridical, Command.GASOLINE_TYPE_ENGINE);
        otherJuridical.addChildren(List.of(otherGasolineEngine, otherElectricEngine));
        Node otherGasolineVolume = new Node(otherGasolineEngine, Command.VOLUME);
        otherGasolineEngine.addChildren(List.of(otherGasolineVolume));
        Node otherGasolineAge = new Node(otherGasolineVolume, Command.AGE);
        otherGasolineVolume.addChildren(List.of(otherGasolineAge));
        Collections.addAll(nodes, otherGasolineEngine, otherGasolineVolume, otherGasolineAge);
    }

    private static class NodeStorageHolder {
        private static final NodeStorage NODE_STORAGE = new NodeStorage();
    }
}
