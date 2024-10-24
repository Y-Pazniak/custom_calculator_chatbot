package by.custom.utilcalculator.domain.tree;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.constants.Command;
import by.custom.utilcalculator.exception.UtilsborCommandTreeReadingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ModifierTree {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Node buildTree() throws UtilsborCommandTreeReadingException {
        Node treeRootJson;
        try {
            treeRootJson = mapper.readValue(getTreeInputStream(), Node.class);
        } catch (IOException e) {
            throw new UtilsborCommandTreeReadingException("Error reading tree ", e);
        }
        if (treeRootJson != null) {
            fillParents(treeRootJson);
        }
        return treeRootJson;
    }

    public static String getPrice(final UserProgress userProgress) throws UtilsborCommandTreeReadingException {
        Node root;
        try {
            root = mapper.readValue(getTreeInputStream(), Node.class);
        } catch (IOException e) {
            throw new UtilsborCommandTreeReadingException("Error reading tree ", e);
        }

        Node localNode = root;
        if (root != null) { //searching for the proper node according to user's path
            for (Command command : userProgress.getUserPath()) {
                for (Node node : localNode.getChildren()) {
                    if (node.getKey().equals(command)) {
                        localNode = node;
                        if (node.getPrice() != null) {
                            return node.getPrice();
                        }
                    }
                }
            }
        }
        return "error during calculation";
    }

    private static InputStream getTreeInputStream() {
        return ModifierTree.class.getClassLoader().getResourceAsStream("tree.json");
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