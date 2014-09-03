package org.hogel.config;

import org.hogel.config.annotation.LoadWith;
import org.hogel.config.loader.BasicConfigLoader;
import org.hogel.config.loader.ConfigLoader;
import org.hogel.config.loader.LoaderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

@LoadWith(BasicConfigLoader.class)
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
            LoadWith loadWith = getClass().getAnnotation(LoadWith.class);
            ConfigLoader configLoader = LoaderFactory.getInstance(loadWith.value());
            configLoader.load(this, yaml.load(yamlReader));
        }
        return true;
    }

    public Path getPath() {
        return path;
    }

    public FileTime getLastModified() {
        return lastModified;
    }
}
