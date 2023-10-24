package by.custom.utilcalculator.controller;

import by.custom.utilcalculator.domain.UserProgress;
import by.custom.utilcalculator.domain.UserProgressStorage;

public class UserStorageManager {
    private final UserProgressStorage userProgressStorage;

    private UserStorageManager() {
        userProgressStorage = UserProgressStorage.getInstance();
    }

    public static UserStorageManager getInstance() {
        return UserProgressManagerHolder.USER_PROGRESS_MANAGER;
    }

    public UserProgress getUserProgress(String chatId) {
        return userProgressStorage.getUser(chatId);
    }

    private static class UserProgressManagerHolder {
        private static final UserStorageManager USER_PROGRESS_MANAGER = new UserStorageManager();
    }
}
