package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.service.BundleResourcesServant;

public class UserFileNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return BundleResourcesServant.getInstance().getString("answers.file_reading_error");
    }
}
