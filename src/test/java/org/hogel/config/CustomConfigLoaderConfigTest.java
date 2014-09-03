package org.hogel.config;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomConfigLoaderConfigTest extends ConfigTestCase {
    @Test
    public void load() throws Exception {
        CustomConfigLoaderConfig config = new CustomConfigLoaderConfig(testConfigPath("custom.yaml"));

        assertThat(config.getValue(), is(1000));
    }
}
