package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.*;

import java.util.AbstractMap;
import java.util.Map;

public class HelperTree {
    public static final Map<StepsIndicator, String> fieldsToCommands = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(CountryOrigin.EAES, Command.EAES),
            new AbstractMap.SimpleEntry<>(CountryOrigin.OTHER, Command.OTHER_COUNTRIES),

            new AbstractMap.SimpleEntry<>(OwnersType.PHYSICAL, Command.PHYSICAL_PERSON),
            new AbstractMap.SimpleEntry<>(OwnersType.JURIDICAL, Command.JURIDICAL_PERSON),

            new AbstractMap.SimpleEntry<>(CarAge.ANY_AGE, Command.AGE),

            new AbstractMap.SimpleEntry<>(TypeOfEngine.ELECTRIC, Command.ELECTRIC_TYPE_ENGINE),
            new AbstractMap.SimpleEntry<>(TypeOfEngine.GASOLINE, Command.GASOLINE_TYPE_ENGINE),

            new AbstractMap.SimpleEntry<>(VolumeOfEngine.ANY_VOLUME, Command.VOLUME));
}
