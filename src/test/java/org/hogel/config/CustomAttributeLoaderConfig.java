package org.hogel.config;

import org.hogel.config.annotation.Attribute;
import org.hogel.config.loader.CustomObjectLoader;

import java.io.IOException;
import java.nio.file.Path;

public class CustomAttributeLoaderConfig extends Config {
    @Attribute(name = "custom", loader = CustomObjectLoader.class)
    CustomObject customObject;

    @Attribute(loader = X10IntLoader.class)
    int count;

    public CustomAttributeLoaderConfig(Path path) throws IOException, InvalidConfigException {
        super(path);
    }

    public CustomObject getCustomObject() {
        return customObject;
    }

    public int getCount() {
        return count;
    }
}
