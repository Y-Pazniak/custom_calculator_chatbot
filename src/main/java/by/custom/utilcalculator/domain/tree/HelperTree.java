package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import by.custom.utilcalculator.exception.UtilsborCommandTreeReadingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
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
        fieldsToCommands.put(ParticularTransportType.N1_N3, Command.N1_N3);
        fieldsToCommands.put(ParticularTransportType.M2_M3, Command.M2_M3);
        fieldsToCommands.put(ParticularTransportType.TRUCK_UNITS, Command.TRUCK_UNITS);
        fieldsToCommands.put(ParticularTransportType.TRAILERS_O4, Command.TRAILERS_O4);
        //vehicle's weight for "exceptM1 -> n1, n2, n3" branch
        fieldsToCommands.put(Weight.LESS_2_TONS, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_2_5_AND_3_5, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_3_5_AND_5, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_5_AND_8, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_8_AND_12, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_12_AND_20, Command.WEIGHT);
        fieldsToCommands.put(Weight.BETWEEN_20_AND_50, Command.WEIGHT);

        //m2-m3 gasoline volume
        fieldsToCommands.put(EngineVolumeOrPower.LESS_2500, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_2500_AND_5000, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_5000_AND_10000, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_10000, Command.ENGINE_VOLUME);
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
        fieldsToCommands.put(EngineVolumeOrPower.LESS_1000, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_1000_AND_2000, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_2000_AND_3000, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_3000_AND_3500, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_3500, Command.ENGINE_VOLUME);
        //truck units step
        fieldsToCommands.put(TruckUnitClass.TRUCK_UNITS_6_CLASS, Command.TRUCK_UNITS_6_CLASS);
        fieldsToCommands.put(TruckUnitClass.TRUCK_UNITS_EXCEPT_6_CLASS, Command.TRUCK_UNITS_OTHER);
        //truck units weight step
        fieldsToCommands.put(Weight.FROM_12_TILL_20_TONS, Command.WEIGHT);
        fieldsToCommands.put(Weight.FROM_20_TILL_50_TONS, Command.WEIGHT);
        //trailers O4 type
        fieldsToCommands.put(TrailerO4Type.TRAILERS, Command.TRAILERS_O4_TYPE);
        fieldsToCommands.put(TrailerO4Type.HALF_TRAILERS, Command.TRAILERS_O4_TYPE);

        //other vehicles -> graders
        fieldsToCommands.put(ParticularTransportType.GRADER, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.LESS_100, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_100_140, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_140_200, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_200, Command.ENGINE_VOLUME);
        //other vehicles -> bulldozers
        fieldsToCommands.put(ParticularTransportType.BULLDOZER, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_100_200, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_200_300, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_300_400, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_400, Command.ENGINE_VOLUME);
        //other vehicles -> excavators
        fieldsToCommands.put(ParticularTransportType.EXCAVATOR, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.LESS_170, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_170_250, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_250, Command.ENGINE_VOLUME);
        //other vehicles -> wheel loaders
        fieldsToCommands.put(ParticularTransportType.WHEEL_LOADER, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_100_125, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_125_150, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_150, Command.ENGINE_VOLUME);

        //other vehicles -> temping machines
        fieldsToCommands.put(ParticularTransportType.TAMPING_MACHINE, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.LESS_40, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_40_80, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_80, Command.ENGINE_VOLUME);

        //other vehicles -> front loaders
        fieldsToCommands.put(ParticularTransportType.FRONT_LOADER, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_5_50, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_50_100, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_200_250, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_250_300, Command.ENGINE_VOLUME);

        //other vehicles -> wheel cranes
        fieldsToCommands.put(ParticularTransportType.WHEELED_CRANES, Command.SELF_PROPELLED_TYPE);

        //other vehicles -> pipelayers
        fieldsToCommands.put(ParticularTransportType.PIPELAYERS, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.LESS_130, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_130_200, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_300, Command.ENGINE_VOLUME);

        //trailers
        fieldsToCommands.put(ParticularTransportType.TRAILERS_OTHER, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.TRAILERS_OTHER_FULL, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.TRAILERS_OTHER_HALF, Command.ENGINE_VOLUME);

        //road maintenance
        fieldsToCommands.put(ParticularTransportType.ROAD_MAINTENANCE, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_100_220, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_220, Command.ENGINE_VOLUME);

        //forestry
        fieldsToCommands.put(ParticularTransportType.FORESTRY, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_20_100, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_100_300, Command.ENGINE_VOLUME);

        //forwaders
        fieldsToCommands.put(ParticularTransportType.FORWADERS, Command.SELF_PROPELLED_TYPE);

        //timber loaders
        fieldsToCommands.put(ParticularTransportType.TIMBER_LOADERS, Command.SELF_PROPELLED_TYPE);

        //wheeled tractors
        fieldsToCommands.put(ParticularTransportType.WHEELED_TRACTORS, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_5p5_30, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_30_60, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_60_90, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_90_130, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_130_180, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_180_220, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_220_280, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_280_340, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_340_380, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_380, Command.ENGINE_VOLUME);

        //crawler tractors
        fieldsToCommands.put(ParticularTransportType.CRAWLER_TRACTORS, Command.SELF_PROPELLED_TYPE);

        //combine_harvesters
        fieldsToCommands.put(ParticularTransportType.COMBINE_HARVESTERS, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_25_160, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_160_220, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_220_255, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_255_325, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_325_400, Command.ENGINE_VOLUME);

        //forage harvesters
        fieldsToCommands.put(ParticularTransportType.FORAGE_HARVESTERS, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.LESS_295, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_295_401, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_401, Command.ENGINE_VOLUME);

        //agricultural vehicles
        fieldsToCommands.put(ParticularTransportType.AGRICULTURAL_VEHICLES, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_100_120, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_120_300, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.SELF_PROPELLED_MOWERS, Command.ENGINE_VOLUME);

        //off-road dump trucks
        fieldsToCommands.put(ParticularTransportType.OFF_ROAD_DUMP_TRUCKS, Command.SELF_PROPELLED_TYPE);
        fieldsToCommands.put(EngineVolumeOrPower.LESS_200, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_200_650, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.BETWEEN_650_1750, Command.ENGINE_VOLUME);
        fieldsToCommands.put(EngineVolumeOrPower.MORE_1750, Command.ENGINE_VOLUME);

        return fieldsToCommands;
    }

    public static Node buildTree() throws UtilsborCommandTreeReadingException {
        try (InputStream inputStream = HelperTree.class.getClassLoader().getResourceAsStream("tree.json")) {
            if (inputStream == null) {
                throw new UtilsborCommandTreeReadingException("the tree file reading has been failed :(", "InputStream is null. Impossible to find or to read the json tree file");
            }
            Node treeRootJson;
            try {
                treeRootJson = mapper.readValue(inputStream, Node.class);
            } catch (IOException e) {
                throw new UtilsborCommandTreeReadingException("Error reading tree ", e);
            }
            if (treeRootJson != null) {
                fillParents(treeRootJson);
            }
            return treeRootJson;

        } catch (IOException e) {
            throw new UtilsborCommandTreeReadingException("Error closing tree source file", e);
        }
    }

    private static void fillParents(final Node node) {
        if (!node.getChildren().isEmpty()) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                fillParents(node.getChildren().get(i));
                node.getChildren().get(i).setParent(node);
            }
        }
    }

    public static Map<Command, StepsIndicator> fillCommandsToFields(Map<StepsIndicator, Command> fieldsToCommands) {
        Map<Command, StepsIndicator> commandsToFields = new HashMap<>();
        for (Map.Entry<StepsIndicator, Command> entry : fieldsToCommands.entrySet()) {
            commandsToFields.put(entry.getValue(), entry.getKey());
        }
        return commandsToFields;
    }
}
