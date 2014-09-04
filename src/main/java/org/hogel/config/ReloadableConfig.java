package org.hogel.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public abstract class ReloadableConfig extends Config {
    private static final Logger LOG = LoggerFactory.getLogger(ReloadableConfig.class);

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Yaml yaml = new Yaml();

    private final Path path;

    private FileTime lastModified;

    public ReloadableConfig(Path path) throws IOException, InvalidConfigException {
        this.path = path;
        reload();
    }

    public synchronized boolean reload() throws IOException, InvalidConfigException {
        FileTime fileLastModified = currentLastModifiedTime();
        if (lastModified != null && lastModified.compareTo(fileLastModified) >= 0) {
            return false;
        }
        lastModified = fileLastModified;

        load(path);
        return true;
    }

    FileTime currentLastModifiedTime() throws IOException {
        return Files.getLastModifiedTime(path);
    }

    public Path getPath() {
        return path;
    }

    public FileTime getLastModified() {
        return lastModified;
    }
}
