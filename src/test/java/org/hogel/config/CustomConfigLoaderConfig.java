package org.hogel.config;

import org.hogel.config.annotation.LoadWith;
import org.hogel.config.loader.CustomConfigLoader;

import java.io.IOException;
import java.nio.file.Path;

@LoadWith(CustomConfigLoader.class)
public class CustomConfigLoaderConfig extends Config {
    private int value;

    public CustomConfigLoaderConfig(Path path) throws IOException, InvalidConfigException {
        super(path);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
