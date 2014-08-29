package org.hogel.config;

import com.google.common.io.Resources;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigTestCase {
    Path testConfigPath(String name) throws URISyntaxException {
        URL resource = Resources.getResource("config/" + name);
        return Paths.get(resource.toURI());
    }
}
