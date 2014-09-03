package org.hogel.config;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SampleConfigTest extends ConfigTestCase {
    @Test
    public void loadDefaultValue() throws Exception {
        SampleConfig sampleConfig = new SampleConfig(testConfigPath("empty.yaml"));
        assertThat(sampleConfig.getTimeout(), is(1000));
        assertThat(sampleConfig.getMark(), is(200_000L));
        assertThat(sampleConfig.getSalt(), is("salt"));
        assertThat(sampleConfig.isAbortOnFail(), is(true));
        assertThat(sampleConfig.getNanika(), is(1800.0));
        assertThat(sampleConfig.getIgnore(), is("ignore"));
    }

    @Test
    public void loadYamlValue() throws Exception {
        SampleConfig sampleConfig = new SampleConfig(testConfigPath("sample.yaml"));
        assertThat(sampleConfig.getTimeout(), is(2000));
        assertThat(sampleConfig.getMark(), is(3000L));
        assertThat(sampleConfig.getSalt(), is("secret"));
        assertThat(sampleConfig.isAbortOnFail(), is(false));
        assertThat(sampleConfig.getNanika(), is(2800.0));
        assertThat(sampleConfig.getIgnore(), is("ignore"));
    }
}
