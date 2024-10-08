package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.Step;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class FillerTree {
    public static void fillTreeJson(final ObjectMapper mapper, final File file) {
        Node root = new Node(Command.START, "base node to start - runs automatically", Step.GENERAL_TRANSPORT_TYPE);

        Node m1 = new Node(Command.M1, "M1 - passenger's vehicles", Step.COUNTRY_ORIGIN);
        root.addKid(m1);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, root);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
