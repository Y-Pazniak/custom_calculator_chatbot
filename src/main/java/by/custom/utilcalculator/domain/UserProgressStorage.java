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
        if (!users.containsKey(chatId)) {
            UserProgress userProgress = new UserProgress(chatId);
            users.put(chatId, userProgress);
//            saveUserProgressIntoMap(userProgress);
//            saveMapIntoFile();
            return userProgress;
        } else {
            return users.get(chatId);
        }
    }

//    private void saveUserProgressIntoMap(UserProgress userProgress) {
//        users.put(userProgress.getChatID(), userProgress);
//        saveMapIntoFile();
//    }

//    private void saveMapIntoFile() {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/users.dat"))) {
//            oos.writeObject(users);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        updateMapFromFile();
//    }

//    private void updateMapFromFile() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/users.dat"))) {
//            users = ((HashMap<String, UserProgress>) ois.readObject());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public static UserProgressStorage getInstance() {
        return UserProgressStorageHolder.USER_PROGRESS_STORAGE;
    }
}
