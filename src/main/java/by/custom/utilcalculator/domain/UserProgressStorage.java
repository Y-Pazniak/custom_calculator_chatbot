package by.custom.utilcalculator.domain;

import org.telegram.telegrambots.meta.api.objects.Message;

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
        return users.get(chatId);
    }

    public void checkAndCreateNewUser(String chatId) {
        if (!users.containsKey(chatId)) {
            UserProgress userProgress = new UserProgress(chatId);
            users.put(chatId, userProgress);
        }
        save();
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
