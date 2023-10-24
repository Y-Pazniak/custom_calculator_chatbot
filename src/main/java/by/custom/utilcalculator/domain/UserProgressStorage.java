package by.custom.utilcalculator.domain;

import java.util.HashMap;

public class UserProgressStorage {
    private final HashMap<String, UserProgress> users;

    private UserProgressStorage() {
        users = new HashMap<>();
    }

    public static UserProgressStorage getInstance() {
        return UserProgressStorageHolder.USER_PROGRESS_STORAGE;
    }

    public UserProgress getUser(String chatId) {
        if (!users.containsKey(chatId)) {
            UserProgress userProgress = new UserProgress();
            users.put(chatId, userProgress);
            return userProgress;
        } else return users.get(chatId);
    }

    private static class UserProgressStorageHolder {
        private static final UserProgressStorage USER_PROGRESS_STORAGE = new UserProgressStorage();
    }
}
