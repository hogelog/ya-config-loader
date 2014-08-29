package org.hogel.config;

import org.hogel.config.annotation.Attribute;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class ComplexConfig extends AbstractConfig {
    @Attribute
    List<Integer> list;

    @Attribute
    Map<String, Object> table;

    public ComplexConfig(Path path) throws IOException, InvalidConfigException {
        super(path);
    }

    public List<Integer> getList() {
        return list;
    }

    public Map<String, Object> getTable() {
        return table;
    }
}
