package org.hogel.config.loader;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class LoaderFactory {
    private static Map<Class<? extends AttributeLoader>, AttributeLoader> loaderMap = new HashMap<>();

    public synchronized static AttributeLoader getInstance(Class<? extends AttributeLoader> loaderClass) {
        AttributeLoader loader = loaderMap.get(loaderClass);
        if (loader != null) {
            return loader;
        }
        AttributeLoader newLoader = newLoader(loaderClass);
        loaderMap.put(loaderClass, newLoader);
        return newLoader;
    }

    private static AttributeLoader newLoader(Class<? extends AttributeLoader> loaderClass) {
        try {
            Constructor<? extends AttributeLoader> constructor = loaderClass.getConstructor();
            return constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(e);
        }
    }
}
