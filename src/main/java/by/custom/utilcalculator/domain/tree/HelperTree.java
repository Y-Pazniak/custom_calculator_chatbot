package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.exception.TreeReadingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class HelperTree {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<StepsIndicator, String> fillFieldsToCommandsMap() {
        final Map<StepsIndicator, String> fieldsToCommands = new HashMap<>(14);
        //country step
        fieldsToCommands.put(CountryOrigin.EAES, Command.EAES.getCommand());
        fieldsToCommands.put(CountryOrigin.OTHER, Command.OTHER_COUNTRIES.getCommand());
        //type of person step
        fieldsToCommands.put(OwnersType.PHYSICAL, Command.PHYSICAL_PERSON.getCommand());
        fieldsToCommands.put(OwnersType.JURIDICAL, Command.JURIDICAL_PERSON.getCommand());
        //age step
        fieldsToCommands.put(CarAge.LESS_OR_3_YEARS, Command.AGE.getCommand());
        fieldsToCommands.put(CarAge.MORE_3_YEARS, Command.AGE.getCommand());
        //type of engine step
        fieldsToCommands.put(TypeOfEngine.ELECTRIC, Command.ELECTRIC_TYPE_ENGINE.getCommand());
        fieldsToCommands.put(TypeOfEngine.GASOLINE, Command.GASOLINE_TYPE_ENGINE.getCommand());
        //engine's volume step
        fieldsToCommands.put(VolumeOfEngine.LESS_1000, Command.VOLUME.getCommand());
        fieldsToCommands.put(VolumeOfEngine.BETWEEN_1000_AND_2000, Command.VOLUME.getCommand());
        fieldsToCommands.put(VolumeOfEngine.BETWEEN_2000_AND_3000, Command.VOLUME.getCommand());
        fieldsToCommands.put(VolumeOfEngine.BETWEEN_3000_AND_3500, Command.VOLUME.getCommand());
        fieldsToCommands.put(VolumeOfEngine.MORE_3500, Command.VOLUME.getCommand());

        return fieldsToCommands;
    }

    public static Node buildTree() throws TreeReadingException {
        URL res = HelperTree.class.getClassLoader().getResource("tree.json");
        File file = null;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Node treeRootJson;

        try {
            treeRootJson = mapper.readValue(file, Node.class);
        } catch (IOException e) {
            throw new TreeReadingException("Error reading tree ", e);
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
