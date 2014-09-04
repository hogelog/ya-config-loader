package org.hogel.config;

import org.hogel.config.annotation.Attribute;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ComplexConfig extends Config {
    @Attribute
    List<Integer> list;

    @Attribute
    Map<String, Object> table;

    public ComplexConfig() throws IOException, InvalidConfigException {
    }

    public List<Integer> getList() {
        return list;
    }

    public Map<String, Object> getTable() {
        return table;
    }
}
