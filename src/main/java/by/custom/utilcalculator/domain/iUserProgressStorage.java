package by.custom.utilcalculator.domain;

import by.custom.utilcalculator.exception.UserFileNotFoundException;

public interface iUserProgressStorage {
    void save(UserProgress userProgress);

    UserProgress get(String chatID) throws UserFileNotFoundException;
}
