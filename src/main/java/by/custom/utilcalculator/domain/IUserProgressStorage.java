package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.exception.ReadingUserProgressFromFileException;
import by.custom.utilcalculator.exception.UserFileNotFoundException;
import by.custom.utilcalculator.exception.UtilsborException;
import by.custom.utilcalculator.exception.WritingUserProgressIntoFileException;

public interface IUserProgressStorage {
    void save(final UserProgress userProgress) throws UtilsborException;

    UserProgress get(final String chatID) throws UtilsborException;
}
