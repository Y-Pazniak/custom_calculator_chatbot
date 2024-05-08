package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
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
        URL res = HelperTree.class.getClassLoader().getResource("tree.json");
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        final ObjectMapper objectMapper = new ObjectMapper();

        Node treeRootJson = null;
        try {
            treeRootJson = objectMapper.readValue(file, Node.class);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        if (treeRootJson != null) {
            fillParents(treeRootJson);
        }
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
