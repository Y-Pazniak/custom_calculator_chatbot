package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.domain.constants.steps.Step;
import by.custom.utilcalculator.exception.UtilsborCommandTreeReadingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class ModifierTree {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final URL res = ModifierTree.class.getClassLoader().getResource("tree.json");

    public static Node buildTree() throws UtilsborCommandTreeReadingException {
        File file = readTreeFile();
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

    private static File readTreeFile() throws UtilsborCommandTreeReadingException {
        File file;
        try {
            assert res != null;
            file = Paths.get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new UtilsborCommandTreeReadingException("Tree reading has failed", e);
        }
        return file;
    }

    public static void addNode(final Node parent, final Command key, final String description, final Step nextStep) throws UtilsborCommandTreeReadingException {
        Node newNode = new Node(parent, key, description, nextStep);
        //File file = readTreeFile(); - doesn't work properly during development - changes data only in /target/classes/

        //remove after testing
        File file = new File("src/main/resources/tree_test.json"); //works fine with /src/main/resources/
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Node root;
        try {
            root = mapper.readValue(file, Node.class);
        } catch (IOException e) {
            throw new UtilsborCommandTreeReadingException("Error reading tree ", e);
        }
        root.getChildren().add(newNode);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, root);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
}
