package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.exception.ReadingUserProgressFromFileException;
import by.custom.utilcalculator.exception.UserFileNotFoundException;

public interface IUserProgressStorage {
    void save(final UserProgress userProgress);

    UserProgress get(final String chatID) throws UserFileNotFoundException, ReadingUserProgressFromFileException;

    void createNewUser(final String chatID);
}
