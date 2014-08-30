package org.hogel.config;

import org.hogel.config.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.util.Map;

public abstract class Config implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(Config.class);

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Yaml yaml = new Yaml();

    private final Path path;

    private FileTime lastModified;

    public Config(Path path) throws IOException, InvalidConfigException {
        this.path = path;
        load();
    }

    public synchronized boolean load() throws IOException, InvalidConfigException {
        FileTime fileLastModified = Files.getLastModifiedTime(path);
        if (lastModified != null && lastModified.compareTo(fileLastModified) >= 0) {
            return false;
        }

        lastModified = fileLastModified;

        try (BufferedReader yamlReader = Files.newBufferedReader(path, UTF_8)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> configMap = yaml.loadAs(yamlReader, Map.class);
            if (configMap == null) {
                configMap = new HashMap<>();
            }

            Class<? extends Config> klass = getClass();
            Field[] fields = klass.getDeclaredFields();
            for (Field field : fields) {
                loadFieldValue(field, configMap);
            }
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage(), e);
            throw new InvalidConfigException(e);
        }
        return true;
    }

    private void loadFieldValue(Field field, Map<String, Object> configMap) throws IllegalAccessException {
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

        if (configMap.containsKey(name)) {
            setFieldValue(field, configMap.get(name));
            return;
        }

        for (Annotation annotation : annotations) {
            if (annotation instanceof IntDefaultValue) {
                setFieldValue(field, ((IntDefaultValue) annotation).value());
                return;
            } else if (annotation instanceof LongDefaultValue) {
                setFieldValue(field, ((LongDefaultValue) annotation).value());
                return;
            } else if (annotation instanceof DoubleDefaultValue) {
                setFieldValue(field, ((DoubleDefaultValue) annotation).value());
                return;
            } else if (annotation instanceof BooleanDefaultValue) {
                setFieldValue(field, ((BooleanDefaultValue) annotation).value());
                return;
            } else if (annotation instanceof StringDefaultValue) {
                setFieldValue(field, ((StringDefaultValue) annotation).value());
                return;
            }
        }
    }

    private void setFieldValue(Field field, Object value) throws IllegalAccessException {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        field.set(this, value);
    }
}
