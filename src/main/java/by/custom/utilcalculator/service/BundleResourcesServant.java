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

    public String getString(final String localizationKey) {
        if (bundle.containsKey(localizationKey)) {
            return bundle.getString(localizationKey);
        } else {
            System.out.printf("no such string in bundle, check the path: %s", localizationKey);
            return "";
        }
    }

    private void createBundleResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        bundle = ResourceBundle.getBundle("words", new Locale("ru"), classLoader);
    }
}
