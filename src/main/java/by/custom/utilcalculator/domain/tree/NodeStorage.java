package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;

import java.util.*;

public class NodeStorage {
    private List<Node> nodes;

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
        eaes.addChildren(new ArrayList<>(Arrays.asList(eaesPhysical, eaesJuridical)));
        Node eaesPhysicalEnd = new Node(eaesPhysical, Command.AGE);
        eaesPhysical.addChildren(new ArrayList<>(List.of(eaesPhysicalEnd)));
        Node eaesJuridicalEnd = new Node(eaesJuridical, Command.AGE);
        eaesJuridical.addChildren(new ArrayList<>(List.of(eaesJuridicalEnd)));
        Collections.addAll(nodes, eaes, eaesPhysical, eaesJuridical, eaesPhysicalEnd, eaesJuridicalEnd);

        //other physical nodes queue
        Node other = new Node(start, Command.OTHER_COUNTRIES);
        start.addChildren(new ArrayList<>(Arrays.asList(eaes, other)));
        Node otherPhysical = new Node(other, Command.PHYSICAL_PERSON);
        Node otherPhysicalEnd = new Node(otherPhysical, Command.AGE);
        Collections.addAll(nodes, other, otherPhysical, otherPhysicalEnd);

        //other juridical electric nodes queue
        Node otherJuridical = new Node(other, Command.JURIDICAL_PERSON + "_other");
        other.addChildren(new ArrayList<>(Arrays.asList(otherPhysical, otherJuridical)));
        Node otherElectricEngine = new Node(otherJuridical, Command.ELECTRIC_TYPE_ENGINE);
        Node otherElectricEnd = new Node(otherElectricEngine, Command.AGE);
        otherElectricEngine.addChildren(new ArrayList<>(List.of(otherElectricEnd)));
        Collections.addAll(nodes, otherJuridical, otherElectricEngine, otherElectricEnd);

        //other juridical gasoline nodes queue
        Node otherGasolineEngine = new Node(otherJuridical, Command.GASOLINE_TYPE_ENGINE);
        otherJuridical.addChildren(new ArrayList<>(List.of(otherGasolineEngine, otherElectricEngine)));
        Node otherGasolineVolume = new Node(otherGasolineEngine, Command.VOLUME);
        otherGasolineEngine.addChildren(new ArrayList<>(List.of(otherGasolineVolume)));
        Node otherGasolineEnd = new Node(otherGasolineVolume, Command.AGE);
        otherGasolineVolume.addChildren(new ArrayList<>(List.of(otherGasolineEnd)));
        Collections.addAll(nodes, otherGasolineEngine, otherGasolineVolume, otherGasolineEnd);
    }

    public Node updateUserNode(final String key) {
        for (Node node : nodes) {
            if (node.getKey().equals(key)) {
                return node;
            }
        }
        return nodes.getFirst();
    }

    private static class NodeStorageHolder {
        private static final NodeStorage NODE_STORAGE = new NodeStorage();
    }
}
