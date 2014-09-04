package org.hogel.config;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class CustomAttributeLoaderConfigTest extends ConfigTestCase {
    @Test
    public void load() throws Exception {
        CustomAttributeLoaderConfig config = spy(new CustomAttributeLoaderConfig(testConfigPath("custom.yaml")));

        assertThat(config.getCustomObject().getA(), is(100));
        assertThat(config.getCustomObject().getB(), is(1000));
        assertThat(config.getCount(), is(200));
        assertThat(config.getPath(), is(testConfigPath("custom.yaml")));

        FileTime lastModified = config.getLastModified();

        assertThat(config.reload(), is(false));
        assertThat(config.getLastModified(), is(lastModified));

        doReturn(FileTime.fromMillis(lastModified.toMillis() + 1))
            .when(config)
            .currentLastModifiedTime();

        assertThat(config.reload(), is(true));
    }

    @Test
    public void failure() throws Exception {
        Path configPath = testConfigPath("invalid_custom.yaml");
        try {
            new CustomAttributeLoaderConfig(configPath);
            assertTrue(false);
        } catch (InvalidConfigException e) {
            Config config = e.getConfig();
            assertTrue(config.getClass() == CustomAttributeLoaderConfig.class);
        }
    }
}
