package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.exception.ReadingUserProgressFromFileException;
import by.custom.utilcalculator.exception.UserFileNotFoundException;
import by.custom.utilcalculator.exception.UtilsborException;
import by.custom.utilcalculator.exception.WritingUserProgressIntoFileException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUserProgressStorage implements IUserProgressStorage {
    private final String databaseEnvVariable = System.getenv("chatbot_file_storage");
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
    public void save(final UserProgress userProgress) throws WritingUserProgressIntoFileException {
        String chatID = userProgress.getChatID();
        String filePath = buildFilePath(chatID);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeObject(userProgress);
        } catch (IOException e) {
            throw new WritingUserProgressIntoFileException(chatID, filePath, e);
        }
    }

    @Override
    public UserProgress get(final String chatID) throws UtilsborException {
        String filePath = buildFilePath(chatID);
        if (!isUserFileExists(chatID)) {
            throw new UserFileNotFoundException(chatID, filePath);
        }
        try (ObjectInputStream ois  = new ObjectInputStream(new FileInputStream(filePath))){
            return (UserProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ReadingUserProgressFromFileException(chatID, filePath, e);
        }
    }

    private boolean isUserFileExists(final String chatID) {
        return Files.exists(Paths.get(buildFilePath(chatID)));
    }

    private String buildFilePath(final String chatID) {
        return String.format(path, chatID);
    }

    public String getPath(final String chatID) {
        return buildFilePath(chatID);
    }
}
