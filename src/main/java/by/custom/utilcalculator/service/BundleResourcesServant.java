package by.custom.utilcalculator.service;

import java.util.Locale;
import java.util.ResourceBundle;

public class BundleResourcesServant {
    private ResourceBundle bundle = null;

    private BundleResourcesServant() {
        createBundleResources();
    }

    public static BundleResourcesServant getInstance() {
        return BundleResourcesCreatorHolder.BUNDLE_INSTANCE;
    }

    private static class BundleResourcesCreatorHolder {
        private static final BundleResourcesServant BUNDLE_INSTANCE = new BundleResourcesServant();
    }

    public String getString(final String stringToGetFromBundle) {
        if (bundle.containsKey(stringToGetFromBundle)) {
            return bundle.getString(stringToGetFromBundle);
        } else {
            System.out.println("no such string in bundle, check the path");
            return "";
        }
    }

    private void createBundleResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        bundle = ResourceBundle.getBundle("words", new Locale("ru"), classLoader);
    }
}
