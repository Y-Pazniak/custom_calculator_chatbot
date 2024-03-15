package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;
import org.checkerframework.checker.units.qual.C;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class HelperTree {
    public static final Map<StepsIndicator, String> fieldsToCommands = new HashMap<>(14);

    static {
        //country step
        fieldsToCommands.put(CountryOrigin.EAES, Command.EAES);
        fieldsToCommands.put(CountryOrigin.OTHER, Command.OTHER_COUNTRIES);
        //type of person step
        fieldsToCommands.put(OwnersType.PHYSICAL, Command.PHYSICAL_PERSON);
        fieldsToCommands.put(OwnersType.JURIDICAL, Command.JURIDICAL_PERSON);
        //age step
        fieldsToCommands.put(CarAge.LESS_3_YEARS, Command.AGE);
        fieldsToCommands.put(CarAge.BETWEEN_3_AND_7_YEARS, Command.AGE);
        fieldsToCommands.put(CarAge.MORE_7_YEARS, Command.AGE);
        //type of engine step
        fieldsToCommands.put(TypeOfEngine.ELECTRIC, Command.ELECTRIC_TYPE_ENGINE);
        fieldsToCommands.put(TypeOfEngine.GASOLINE, Command.GASOLINE_TYPE_ENGINE);
        //engine's volume step
        fieldsToCommands.put(VolumeOfEngine.LESS_1000, Command.VOLUME);
        fieldsToCommands.put(VolumeOfEngine.BETWEEN_1000_AND_2000, Command.VOLUME);
        fieldsToCommands.put(VolumeOfEngine.BETWEEN_2000_AND_3000, Command.VOLUME);
        fieldsToCommands.put(VolumeOfEngine.BETWEEN_3000_AND_3500, Command.VOLUME);
        fieldsToCommands.put(VolumeOfEngine.MORE_3500, Command.VOLUME);
    }
}
