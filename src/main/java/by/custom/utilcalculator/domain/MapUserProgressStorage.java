package by.custom.utilcalculator.domain;

import java.util.HashMap;
import java.util.Map;

public class MapUserProgressStorage implements IUserProgressStorage {
    public static MapUserProgressStorage getInstance() {
        return UserProgressStorageHolder.USER_PROGRESS_STORAGE;
    }

    private static class UserProgressStorageHolder {
        private static final MapUserProgressStorage USER_PROGRESS_STORAGE = new MapUserProgressStorage();
    }

    private final Map<String, UserProgress> users;

    private MapUserProgressStorage() {
        users = new HashMap<>();
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

    @Override
    public String getPath(final String chatID) {
        return "not available for a map";
    }
}