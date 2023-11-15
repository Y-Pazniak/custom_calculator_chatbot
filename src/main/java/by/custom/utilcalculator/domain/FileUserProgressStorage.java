package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.exception.UserFileNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUserProgressStorage implements iUserProgressStorage {
    private final String path = "D:/%s.dat";

    private static class FileUserProgressStorageHolder {
        private static final FileUserProgressStorage FILE_USER_PROGRESS_STORAGE = new FileUserProgressStorage();
    }

    private FileUserProgressStorage() {
    }

    public static FileUserProgressStorage getInstance() {
        return FileUserProgressStorageHolder.FILE_USER_PROGRESS_STORAGE;
    }

    @Override
    public void save(UserProgress userProgress) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(String.format(path, userProgress.getChatID())));
            oos.writeObject(userProgress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserProgress get(String chatID) throws UserFileNotFoundException {
        UserProgress localUserProgress;
        if (isUserFileExists(chatID)) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(String.format(path, chatID)));
                localUserProgress = (UserProgress) ois.readObject();
                return localUserProgress;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new UserFileNotFoundException();
        }
    }

    public void createNewUserFile(String chatID) {
        if (!isUserFileExists(chatID)) {
            UserProgress userProgress = new UserProgress(chatID);
            save(userProgress);
        }
    }

    private boolean isUserFileExists(String chatID) {
        String localPath = String.format(path, chatID);
        return Files.exists(Paths.get(localPath));
    }
}
