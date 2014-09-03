package org.hogel.config.loader;

import org.hogel.config.Config;
import org.hogel.config.InvalidConfigException;

public interface ConfigLoader<T extends Config> extends Loader {
    void load(T config, Object source) throws InvalidConfigException;
}
