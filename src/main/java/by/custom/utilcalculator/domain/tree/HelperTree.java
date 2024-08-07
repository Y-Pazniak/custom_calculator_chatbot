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
        fieldsToCommands.put(N1N3TransportWeight.LESS_2_TONS, Command.N1_N3_WEIGHT);
        fieldsToCommands.put(N1N3TransportWeight.BETWEEN_2_5_AND_3_5, Command.N1_N3_WEIGHT);
        fieldsToCommands.put(N1N3TransportWeight.BETWEEN_3_5_AND_5, Command.N1_N3_WEIGHT);
        fieldsToCommands.put(N1N3TransportWeight.BETWEEN_5_AND_8, Command.N1_N3_WEIGHT);
        fieldsToCommands.put(N1N3TransportWeight.BETWEEN_8_AND_12, Command.N1_N3_WEIGHT);
        fieldsToCommands.put(N1N3TransportWeight.BETWEEN_12_AND_20, Command.N1_N3_WEIGHT);
        fieldsToCommands.put(N1N3TransportWeight.BETWEEN_20_AND_50, Command.N1_N3_WEIGHT);
        //m2-m3 engine types
        fieldsToCommands.put(M2M3EngineType.ELECTRIC, Command.ELECTRIC_TYPE_ENGINE_BUSES);
        fieldsToCommands.put(M2M3EngineType.GASOLINE, Command.GASOLINE_TYPE_ENGINE_BUSES);
        //m2-m3 gasoline volume
        fieldsToCommands.put(M2EngineVolume.LESS_2500, Command.M2_M3_GASOLINE_ENGINE_VOLUME);
        fieldsToCommands.put(M2EngineVolume.BETWEEN_2500_AND_5000, Command.M2_M3_GASOLINE_ENGINE_VOLUME);
        fieldsToCommands.put(M2EngineVolume.BETWEEN_5000_AND_10000, Command.M2_M3_GASOLINE_ENGINE_VOLUME);
        fieldsToCommands.put(M2EngineVolume.MORE_10000, Command.M2_M3_GASOLINE_ENGINE_VOLUME);
        //country step
        fieldsToCommands.put(CountryOrigin.EAES, Command.EAES);
        fieldsToCommands.put(CountryOrigin.OTHER, Command.OTHER_COUNTRIES);
        //type of person step
        fieldsToCommands.put(OwnersType.PHYSICAL, Command.PHYSICAL_PERSON);
        fieldsToCommands.put(OwnersType.JURIDICAL_EAES, Command.JURIDICAL_PERSON_EAES);
        fieldsToCommands.put(OwnersType.JURIDICAL_OTHER, Command.JURIDICAL_PERSON_OTHER);
        //age step
        fieldsToCommands.put(CarAge.LESS_OR_3_YEARS, Command.AGE);
        fieldsToCommands.put(CarAge.MORE_3_YEARS, Command.AGE);
        //type of engine step
        fieldsToCommands.put(M1TypeOfEngine.ELECTRIC, Command.ELECTRIC_TYPE_ENGINE_M1);
        fieldsToCommands.put(M1TypeOfEngine.GASOLINE, Command.GASOLINE_TYPE_ENGINE_M1);
        //engine's volume step
        fieldsToCommands.put(M1EngineVolume.LESS_1000, Command.M1_GASOLINE_ENGINE_VOLUME);
        fieldsToCommands.put(M1EngineVolume.BETWEEN_1000_AND_2000, Command.M1_GASOLINE_ENGINE_VOLUME);
        fieldsToCommands.put(M1EngineVolume.BETWEEN_2000_AND_3000, Command.M1_GASOLINE_ENGINE_VOLUME);
        fieldsToCommands.put(M1EngineVolume.BETWEEN_3000_AND_3500, Command.M1_GASOLINE_ENGINE_VOLUME);
        fieldsToCommands.put(M1EngineVolume.MORE_3500, Command.M1_GASOLINE_ENGINE_VOLUME);
        //truck units step
        fieldsToCommands.put(TruckUnitClass.TRUCK_UNITS_6_CLASS, Command.TRUCK_UNITS_6_CLASS);
        fieldsToCommands.put(TruckUnitClass.TRUCK_UNITS_EXCEPT_6_CLASS, Command.TRUCK_UNITS_OTHER);
        //truck units weight step
        fieldsToCommands.put(TruckUnitWeight.FROM_12_TILL_20_TONS, Command.TRUCK_UNIT_WEIGHT);
        fieldsToCommands.put(TruckUnitWeight.FROM_20_TILL_50_TONS, Command.TRUCK_UNIT_WEIGHT);
        //trailers O4 type
        fieldsToCommands.put(TrailerO4Type.TRAILERS, Command.TRAILERS_O4_TYPE);
        fieldsToCommands.put(TrailerO4Type.HALF_TRAILERS, Command.TRAILERS_O4_TYPE);
        //other vehicles -> graders
        fieldsToCommands.put(SelfPropelledType.GRADER, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(SelfPropelledPower.LESS_100, Command.SELF_PROPELLED_POWER);
        fieldsToCommands.put(SelfPropelledPower.BETWEEN_100_140, Command.SELF_PROPELLED_POWER);
        fieldsToCommands.put(SelfPropelledPower.BETWEEN_140_200, Command.SELF_PROPELLED_POWER);
        fieldsToCommands.put(SelfPropelledPower.MORE_200, Command.SELF_PROPELLED_POWER);
        //other vehicles -> bulldozers
        fieldsToCommands.put(SelfPropelledType.BULLDOZER, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(SelfPropelledPower.BETWEEN_100_200, Command.SELF_PROPELLED_POWER);
        fieldsToCommands.put(SelfPropelledPower.BETWEEN_200_300, Command.SELF_PROPELLED_POWER);
        fieldsToCommands.put(SelfPropelledPower.BETWEEN_300_400, Command.SELF_PROPELLED_POWER);
        fieldsToCommands.put(SelfPropelledPower.MORE_400, Command.SELF_PROPELLED_POWER);
        //other vehicles -> excavators
        fieldsToCommands.put(SelfPropelledType.EXCAVATOR, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(SelfPropelledPower.LESS_170, Command.SELF_PROPELLED_POWER);
        fieldsToCommands.put(SelfPropelledPower.BETWEEN_170_250, Command.SELF_PROPELLED_POWER);
        fieldsToCommands.put(SelfPropelledPower.MORE_250, Command.SELF_PROPELLED_POWER);
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
