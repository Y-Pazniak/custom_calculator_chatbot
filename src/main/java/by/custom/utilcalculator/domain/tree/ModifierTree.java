package by.custom.utilcalculator.domain.tree;

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

    public static void fillTreeByNodes() {
        String filePath = "src/main/resources/tree_test.json";
        File file = getTreeFile(filePath);
        FillerTree.fillTreeJson(mapper, file);
    }

    private static File getTreeFile(String path) {
        //File file = readTreeFile(); - doesn't work properly during development - changes data only in /target/classes/

        //remove after testing
        File file = new File("src/main/resources/tree_test.json"); //works fine with /src/main/resources/
        if (!file.exists() || file.length() == 0) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
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

    private static void fillParents(final Node node) {
        if (!node.getChildren().isEmpty()) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                fillParents(node.getChildren().get(i));
                node.getChildren().get(i).setParent(node);
            }
        }
    }
}
