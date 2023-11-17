package by.custom.utilcalculator.domain;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MapUserProgressStorage implements IUserProgressStorage {
    public static MapUserProgressStorage getInstance() {
        return UserProgressStorageHolder.USER_PROGRESS_STORAGE;
    }

    private static class UserProgressStorageHolder {
        private static final MapUserProgressStorage USER_PROGRESS_STORAGE = new MapUserProgressStorage();
    }

    private Map<String, UserProgress> users;

    private MapUserProgressStorage() {
        users = new HashMap<>();
    }

    public UserProgress getUser(String chatId) {
        get(chatId);
        return users.get(chatId);
    }

    public void createNewUser (String chatId) {
        if (!users.containsKey(chatId)) {
            UserProgress userProgress = new UserProgress(chatId);
            users.put(chatId, userProgress);
            save(userProgress);
        }
    }

    @Override
    public void save(UserProgress userProgress) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/users.dat"))) {
            oos.writeObject(users);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UserProgress get(String chatID) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/users.dat"))) {
            users = ((HashMap<String, UserProgress>) ois.readObject());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
