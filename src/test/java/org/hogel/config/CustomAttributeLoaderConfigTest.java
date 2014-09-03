package org.hogel.config;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CustomAttributeLoaderConfigTest extends ConfigTestCase {
    @Test
    public void load() throws Exception {
        CustomAttributeLoaderConfig config = new CustomAttributeLoaderConfig(testConfigPath("custom.yaml"));

        assertThat(config.getCustomObject().getA(), is(100));
        assertThat(config.getCustomObject().getB(), is(1000));
        assertThat(config.getCount(), is(200));

        FileTime lastModified = config.getLastModified();

        assertThat(config.load(), is(false));
        assertThat(config.getLastModified(), is(lastModified));
    }

    @Test
    public void failure() throws Exception {
        Path configPath = testConfigPath("invalid_custom.yaml");
        try {
            new CustomAttributeLoaderConfig(configPath);
            assertTrue(false);
        } catch (InvalidConfigException e) {
            Config config = e.getConfig();
            assertThat(config.getPath(), is(configPath));
        }
    }
}
