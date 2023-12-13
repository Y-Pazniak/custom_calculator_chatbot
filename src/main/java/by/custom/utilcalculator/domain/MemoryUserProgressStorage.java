package by.custom.utilcalculator.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryUserProgressStorage implements IUserProgressStorage {
    public static MemoryUserProgressStorage getInstance() {
        return UserProgressStorageHolder.USER_PROGRESS_STORAGE;
    }

    private static class UserProgressStorageHolder {
        private static final MemoryUserProgressStorage USER_PROGRESS_STORAGE = new MemoryUserProgressStorage();
    }

    private final Map<String, UserProgress> users;

    private MemoryUserProgressStorage() {
        users = new ConcurrentHashMap<>();
    }
    public void create(final String chatId) {
        if (!users.containsKey(chatId)) {
            save(new UserProgress(chatId));
        }
    }

    @Override
    public void save(final UserProgress userProgress) {
        users.put(userProgress.getChatID(), userProgress);
    }

    public UserProgress get(final String chatID) {
        if (!users.containsKey(chatID)) {
            create(chatID);
        }
        return users.get(chatID);
    }
}