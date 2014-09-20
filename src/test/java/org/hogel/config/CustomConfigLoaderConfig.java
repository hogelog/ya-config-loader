package org.hogel.config;

import org.hogel.config.annotation.LoadWith;
import org.hogel.config.loader.CustomConfigLoader;

@LoadWith(CustomConfigLoader.class)
public class CustomConfigLoaderConfig extends Config {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
