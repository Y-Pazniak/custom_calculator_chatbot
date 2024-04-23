package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class HelperTree {
    public static Map<StepsIndicator, String> fillFieldsToCommandsMap() {
        final Map<StepsIndicator, String> fieldsToCommands = new HashMap<>(14);
        //country step
        fieldsToCommands.put(CountryOrigin.EAES, Command.EAES);
        fieldsToCommands.put(CountryOrigin.OTHER, Command.OTHER_COUNTRIES);
        //type of person step
        fieldsToCommands.put(OwnersType.PHYSICAL, Command.PHYSICAL_PERSON);
        fieldsToCommands.put(OwnersType.JURIDICAL, Command.JURIDICAL_PERSON);
        //age step
        fieldsToCommands.put(CarAge.LESS_OR_3_YEARS, Command.AGE);
        fieldsToCommands.put(CarAge.MORE_3_YEARS, Command.AGE);
        //type of engine step
        fieldsToCommands.put(TypeOfEngine.ELECTRIC, Command.ELECTRIC_TYPE_ENGINE);
        fieldsToCommands.put(TypeOfEngine.GASOLINE, Command.GASOLINE_TYPE_ENGINE);
        //engine's volume step
        fieldsToCommands.put(VolumeOfEngine.LESS_1000, Command.VOLUME);
        fieldsToCommands.put(VolumeOfEngine.BETWEEN_1000_AND_2000, Command.VOLUME);
        fieldsToCommands.put(VolumeOfEngine.BETWEEN_2000_AND_3000, Command.VOLUME);
        fieldsToCommands.put(VolumeOfEngine.BETWEEN_3000_AND_3500, Command.VOLUME);
        fieldsToCommands.put(VolumeOfEngine.MORE_3500, Command.VOLUME);

        return fieldsToCommands;
    }

    public static Node buildTree() {
//        Node treeRoot = new Node(null, Command.START);
//        //eaes nodes queue
//        Node eaes = new Node(treeRoot, Command.EAES);
//        Node eaesPhysical = new Node(eaes, Command.PHYSICAL_PERSON);
//        Node eaesJuridical = new Node(eaes, Command.JURIDICAL_PERSON);
//        eaes.addChildren(Arrays.asList(eaesPhysical, eaesJuridical));
//        Node eaesPhysicalAge = new Node(eaesPhysical, Command.AGE);
//        eaesPhysical.addChildren(List.of(eaesPhysicalAge));
//        Node eaesJuridicalAge = new Node(eaesJuridical, Command.AGE);
//        eaesJuridical.addChildren(List.of(eaesJuridicalAge));
//
//        //other physical nodes queue
//        Node other = new Node(treeRoot, Command.OTHER_COUNTRIES);
//        treeRoot.addChildren(Arrays.asList(eaes, other));
//        Node otherPhysical = new Node(other, Command.PHYSICAL_PERSON);
//        Node otherPhysicalAge = new Node(otherPhysical, Command.AGE);
//        otherPhysical.addChildren(List.of(otherPhysicalAge));
//
//        //other juridical electric nodes queue
//        Node otherJuridical = new Node(other, Command.JURIDICAL_PERSON);
//        other.addChildren(Arrays.asList(otherPhysical, otherJuridical));
//        Node otherElectricEngine = new Node(otherJuridical, Command.ELECTRIC_TYPE_ENGINE);
//        Node otherElectricAge = new Node(otherElectricEngine, Command.AGE);
//        otherElectricEngine.addChildren(List.of(otherElectricAge));
//
//        //other juridical gasoline nodes queue
//        Node otherGasolineEngine = new Node(otherJuridical, Command.GASOLINE_TYPE_ENGINE);
//        otherJuridical.addChildren(List.of(otherGasolineEngine, otherElectricEngine));
//        Node otherGasolineVolume = new Node(otherGasolineEngine, Command.VOLUME);
//        otherGasolineEngine.addChildren(List.of(otherGasolineVolume));
//        Node otherGasolineAge = new Node(otherGasolineVolume, Command.AGE);
//        otherGasolineVolume.addChildren(List.of(otherGasolineAge));

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/home/eugene/IdeaProjects/custom_calculator_chatbot/src/main/resources/tree.json"));
        } catch (final FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final Gson gson = new GsonBuilder().create();

        Node treeRootJson = gson.fromJson(bufferedReader, Node.class);
        fillParents(treeRootJson);
        return treeRootJson;
    }

    private static void fillParents(final Node node) {
        if (!node.getChildren().isEmpty()) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                fillParents(node.getChildren().get(i));
                node.getChildren().get(i).setParent(node);
            }
        }
    }
}
