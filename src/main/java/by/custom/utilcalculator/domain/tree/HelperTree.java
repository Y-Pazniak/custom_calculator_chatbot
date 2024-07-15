package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.exception.UtilsborCommandTreeReadingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class HelperTree {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Map<StepsIndicator, Command> fillFieldsToCommandsMap() {
        final Map<StepsIndicator, Command> fieldsToCommands = new HashMap<>(21);
        //vehicle type step
        fieldsToCommands.put(GeneralTransportType.M1, Command.M1);
        fieldsToCommands.put(GeneralTransportType.EXCEPT_M1, Command.EXCEPT_M1);
        fieldsToCommands.put(GeneralTransportType.SELF_PROPELLED_VEHICLES, Command.TRAILERS);
        //vehicle type m1-m3
        fieldsToCommands.put(N1_N3TransportType.N1_N3, Command.N1_N3);
        fieldsToCommands.put(N1_N3TransportType.M2_M3, Command.M2_M3);
        fieldsToCommands.put(N1_N3TransportType.TRUCK_UNITS, Command.TRUCK_UNITS);
        fieldsToCommands.put(N1_N3TransportType.TRAILERS_O4, Command.TRAILERS_O4);
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

    public static Node buildTree() throws UtilsborCommandTreeReadingException {
        URL res = HelperTree.class.getClassLoader().getResource("tree.json");
        File file;
        try {
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new UtilsborCommandTreeReadingException("Tree reading has failed", e);
        }

        Node treeRootJson;

        try {
            treeRootJson = mapper.readValue(file, Node.class);
        } catch (IOException e) {
            throw new UtilsborCommandTreeReadingException("Error reading tree ", e);
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
