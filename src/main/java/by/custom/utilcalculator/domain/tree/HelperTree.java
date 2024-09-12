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
        final Map<StepsIndicator, Command> fieldsToCommands = new HashMap<>();
        //vehicle type step
        fieldsToCommands.put(GeneralTransportType.M1, Command.M1);
        fieldsToCommands.put(GeneralTransportType.BUSES_AND_TRUCKS, Command.BUSES_AND_TRUCKS);
        fieldsToCommands.put(GeneralTransportType.SELF_PROPELLED_VEHICLES, Command.SELF_PROPELLED_VEHICLES);
        //vehicle type m1-m3
        fieldsToCommands.put(BusesAndTrucksTransportType.N1_N3, Command.N1_N3);
        fieldsToCommands.put(BusesAndTrucksTransportType.M2_M3, Command.M2_M3);
        fieldsToCommands.put(BusesAndTrucksTransportType.TRUCK_UNITS, Command.TRUCK_UNITS);
        fieldsToCommands.put(BusesAndTrucksTransportType.TRAILERS_O4, Command.TRAILERS_O4);
        //vehicle's weight for "exceptM1 -> n1, n2, n3" branch
        fieldsToCommands.put(Weight.LESS_2_TONS, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_2_5_AND_3_5, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_3_5_AND_5, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_5_AND_8, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_8_AND_12, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_12_AND_20, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_20_AND_50, Command.WEIGHT);

        //m2-m3 gasoline volume
        fieldsToCommands.put(EngineVolume.LESS_2500, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolume.BETWEEN_2500_AND_5000, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolume.BETWEEN_5000_AND_10000, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolume.MORE_10000, Command.ENGINE_VOLUME);
        //country step
        fieldsToCommands.put(CountryOrigin.EAES, Command.EAES);
        fieldsToCommands.put(CountryOrigin.OTHER, Command.OTHER_COUNTRIES);
        //type of person step
        fieldsToCommands.put(OwnersType.PHYSICAL, Command.PHYSICAL);
        fieldsToCommands.put(OwnersType.JURIDICAL, Command.JURIDICAL);
        //age step
        fieldsToCommands.put(CarAge.LESS_OR_3_YEARS, Command.AGE);
        fieldsToCommands.put(CarAge.MORE_3_YEARS, Command.AGE);
        //type of engine step
        fieldsToCommands.put(EngineType.ELECTRIC, Command.ELECTRIC);
        fieldsToCommands.put(EngineType.GASOLINE, Command.GASOLINE);
        //engine's volume step
        fieldsToCommands.put(EngineVolume.LESS_1000, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolume.BETWEEN_1000_AND_2000, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolume.BETWEEN_2000_AND_3000, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolume.BETWEEN_3000_AND_3500, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolume.MORE_3500, Command.ENGINE_VOLUME);
        //truck units step
        fieldsToCommands.put(TruckUnitClass.TRUCK_UNITS_6_CLASS, Command.TRUCK_UNITS_6_CLASS);
        fieldsToCommands.put(TruckUnitClass.TRUCK_UNITS_EXCEPT_6_CLASS, Command.TRUCK_UNITS_OTHER);
        //truck units weight step
        fieldsToCommands.put(Weight.FROM_12_TILL_20_TONS, Command.WEIGHT);
        fieldsToCommands.put(Weight.FROM_20_TILL_50_TONS, Command.WEIGHT);
        //trailers O4 type
        fieldsToCommands.put(TrailerO4Type.TRAILERS, Command.TRAILERS_O4_TYPE);
        fieldsToCommands.put(TrailerO4Type.HALF_TRAILERS, Command.TRAILERS_O4_TYPE);

        return fieldsToCommands;
    }

    public static Node buildTree() throws UtilsborCommandTreeReadingException {
        URL res = HelperTree.class.getClassLoader().getResource("tree.json");
        File file;
        try {
            assert res != null;
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
