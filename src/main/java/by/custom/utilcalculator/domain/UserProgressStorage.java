package by.custom.utilcalculator.domain;

import java.io.*;
import java.util.HashMap;

public class UserProgressStorage {

    private static class UserProgressStorageHolder {
        private static final UserProgressStorage USER_PROGRESS_STORAGE = new UserProgressStorage();
    }

    private HashMap<String, UserProgress> users;

    private UserProgressStorage() {
        users = new HashMap<>();
    }

    public UserProgress getUser(String chatId) {
        get();
        if (!users.containsKey(chatId)) {
            UserProgress userProgress = new UserProgress(chatId);
            users.put(chatId, userProgress);
            return userProgress;
        } else {
            return users.get(chatId);
        }
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/users.dat"))) {
            oos.writeObject(users);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void get() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/users.dat"))) {
            users = ((HashMap<String, UserProgress>) ois.readObject());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static UserProgressStorage getInstance() {
        return UserProgressStorageHolder.USER_PROGRESS_STORAGE;
    }
}
