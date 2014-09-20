package org.hogel.config;

import org.hogel.config.annotation.LoadWith;
import org.hogel.config.loader.BasicConfigLoader;
import org.hogel.config.loader.ConfigLoader;
import org.hogel.config.loader.LoaderFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

@LoadWith(BasicConfigLoader.class)
public abstract class Config implements Serializable {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Yaml yaml = new Yaml();

    public Config() {
    }

    public void load(Path path) throws IOException, InvalidConfigException {
        try (BufferedReader reader = Files.newBufferedReader(path, UTF_8)) {
            load(reader);
        }
    }

    public void load(String config) throws InvalidConfigException {
        load(new StringReader(config));
    }

    public void load(Reader reader) throws InvalidConfigException {
        load(yaml.load(reader));
    }

    public void load(Object source) throws InvalidConfigException {
        LoadWith loadWith = getClass().getAnnotation(LoadWith.class);
        ConfigLoader configLoader = LoaderFactory.getInstance(loadWith.value());
        configLoader.load(this, source);
    }
}
