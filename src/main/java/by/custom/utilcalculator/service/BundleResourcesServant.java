package by.custom.utilcalculator.service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
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

    public String getString(String stringToGetFromBundle) {
        return bundle.getString(stringToGetFromBundle);
    }

    private void createBundleResources() {
        try {
            File fileResources = new File("D:\\IdeaProjects\\custom_calculator_bot\\src\\main\\resources");
            URL[] urls = {fileResources.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            bundle = ResourceBundle.getBundle("words", Locale.getDefault(), loader);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static class BundleResourcesCreatorHolder {
        private static final BundleResourcesServant BUNDLE_INSTANCE = new BundleResourcesServant();
    }
}
