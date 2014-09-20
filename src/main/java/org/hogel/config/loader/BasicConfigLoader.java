package org.hogel.config.loader;

import org.hogel.config.Config;
import org.hogel.config.InvalidConfigException;
import org.hogel.config.annotation.Attribute;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BasicConfigLoader implements ConfigLoader<Config> {
    @Override
    public void load(Config config, Object source) throws InvalidConfigException {
        @SuppressWarnings("unchecked")
        Map<String, Object> configMap = (Map<String, Object>) source;
        if (configMap == null) {
            configMap = Collections.emptyMap();
        }

        Class<? extends Config> klass = config.getClass();
        List<Field> fields = getConfigFields(klass);
        try {
            for (Field field : fields) {
                loadFieldValue(config, field, configMap);
            }
        } catch (IllegalAccessException e) {
            throw new InvalidConfigException(config, e);
        }
    }

    private List<Field> getConfigFields(Class<? extends Config> configClass) {
        List<Field> configFields = new ArrayList<>();
        Class klass = configClass;
        do {
            Field[] fields = klass.getDeclaredFields();
            for (Field field : fields) {
                configFields.add(field);
            }
            klass = klass.getSuperclass();
        } while (Config.class != klass);
        return configFields;
    }

    private void loadFieldValue(Config config, Field field, Map<String, Object> configMap) throws IllegalAccessException, InvalidConfigException {
        Annotation[] annotations = field.getAnnotations();
        Attribute attributeAnnotation = field.getAnnotation(Attribute.class);
        if (attributeAnnotation == null) {
            return;
        }

        String name;
        if (attributeAnnotation.name().isEmpty()) {
            name = field.getName();
        } else {
            name = attributeAnnotation.name();
        }

        Object value = configMap.get(name);
        AttributeLoader loader = LoaderFactory.getInstance(attributeAnnotation.loader());
        try {
            setFieldValue(config, field, loader.load(annotations, value));
        } catch (InvalidAttributeException e) {
            throw new InvalidConfigException(config, e);
        }
    }

    private void setFieldValue(Config config, Field field, Object value) throws IllegalAccessException {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        field.set(config, value);
    }
}
