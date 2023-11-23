package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.exception.ReadingUserProgressFromFileException;
import by.custom.utilcalculator.exception.UserFileNotFoundException;
import by.custom.utilcalculator.exception.WritingUserProgressIntoFileException;

public interface IUserProgressStorage {
    void save(final UserProgress userProgress) throws WritingUserProgressIntoFileException;

    UserProgress get(final String chatID) throws UserFileNotFoundException, ReadingUserProgressFromFileException;

    void create(final String chatID) throws WritingUserProgressIntoFileException;

    String getPath(final String chatID);
}
