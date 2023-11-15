package by.custom.utilcalculator.exception;

import by.custom.utilcalculator.service.BundleResourcesServant;

public class UserFileNotFoundException extends Exception {
    private final BundleResourcesServant bundle;
    public UserFileNotFoundException(){
        bundle = BundleResourcesServant.getInstance();
    }

    @Override
    public String getMessage() {
        return bundle.getString("answers.user_not_found");
    }
}
