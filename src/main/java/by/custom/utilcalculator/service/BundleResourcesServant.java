package by.custom.utilcalculator.service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Objects;
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
        return bundle.getString(stringToGetFromBundle);
    }

    private void createBundleResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        bundle = ResourceBundle.getBundle("words", new Locale("ru"), classLoader);
    }
}
