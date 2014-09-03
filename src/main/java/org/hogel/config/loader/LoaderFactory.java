package org.hogel.config.loader;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class LoaderFactory {
    private static Map<Class<? extends Loader>, Loader> loaderMap = new HashMap<>();

    public synchronized static <T extends Loader> T getInstance(Class<T> loaderClass) {
        Loader loader = loaderMap.get(loaderClass);
        if (loader != null) {
            return (T) loader;
        }
        T newLoader = newLoader(loaderClass);
        loaderMap.put(loaderClass, newLoader);
        return newLoader;
    }

    private static <T extends Loader> T newLoader(Class<T> loaderClass) {
        try {
            Constructor<? extends Loader> constructor = loaderClass.getConstructor();
            return (T) constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }
}
