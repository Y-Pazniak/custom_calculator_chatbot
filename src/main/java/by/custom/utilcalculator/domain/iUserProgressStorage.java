package by.custom.utilcalculator.domain;

public interface iUserProgressStorage {
    void save(UserProgress userProgress);

    UserProgress get(String chatID);
}
