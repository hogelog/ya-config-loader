package org.hogel.config.loader;

import org.hogel.config.CustomConfigLoaderConfig;
import org.hogel.config.InvalidConfigException;

public class CustomConfigLoader implements ConfigLoader<CustomConfigLoaderConfig> {
    @Override
    public void load(CustomConfigLoaderConfig config, Object source) throws InvalidConfigException {
        config.setValue(1000);
    }
}
