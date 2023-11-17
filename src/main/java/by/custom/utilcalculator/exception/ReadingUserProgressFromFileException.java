package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.service.BundleResourcesServant;

public class ReadingUserProgressFromFileException extends Exception {
    @Override
    public String getMessage() {
        return BundleResourcesServant.getInstance().getString("answers.user_not_found");
    }
}
