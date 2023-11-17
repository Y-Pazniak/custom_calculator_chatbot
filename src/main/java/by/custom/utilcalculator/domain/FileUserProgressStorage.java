package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.exception.ReadingUserProgressFromFileException;
import by.custom.utilcalculator.exception.UserFileNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUserProgressStorage implements IUserProgressStorage {
    private final String databaseEnvVariable = System.getenv("CHATBOT_DATABASE_STORAGE");
    private final String path = databaseEnvVariable + "%s.dat";

    private static class FileUserProgressStorageHolder {
        private static final FileUserProgressStorage FILE_USER_PROGRESS_STORAGE = new FileUserProgressStorage();
    }

    private FileUserProgressStorage() {
        File databaseDirectory = new File(databaseEnvVariable);
        if (!databaseDirectory.exists()) {
            databaseDirectory.mkdirs();
        }
    }

    public static FileUserProgressStorage getInstance() {
        return FileUserProgressStorageHolder.FILE_USER_PROGRESS_STORAGE;
    }

    @Override
    public void save(final UserProgress userProgress) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(buildFilePath(userProgress.getChatID())));
            oos.writeObject(userProgress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserProgress get(final String chatID) throws UserFileNotFoundException, ReadingUserProgressFromFileException {
        if (!isUserFileExists(chatID)) {
            throw new UserFileNotFoundException();
        }
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(buildFilePath(chatID)));
            return (UserProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ReadingUserProgressFromFileException();
        }
    }

    public void createNewUser (final String chatID) {
        if (!isUserFileExists(chatID)) {
            UserProgress userProgress = new UserProgress(chatID);
            save(userProgress);
        }
    }

    private boolean isUserFileExists(final String chatID) {
        return Files.exists(Paths.get(buildFilePath(chatID)));
    }

    private String buildFilePath(final String chatID) {
        return String.format(path, chatID);
    }
}
