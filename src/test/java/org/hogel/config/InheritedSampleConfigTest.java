package org.hogel.config;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InheritedSampleConfigTest extends ConfigTestCase {
    @Test
    public void loadDefaultInheritedValue() throws Exception {
        InheritedSampleConfig sampleConfig = new InheritedSampleConfig();
        sampleConfig.load(testConfigPath("empty.yaml"));

        assertThat(sampleConfig.getTimeout(), is(1000));
        assertThat(sampleConfig.getMark(), is(200_000L));
        assertThat(sampleConfig.getSalt(), is("salt"));
        assertThat(sampleConfig.isAbortOnFail(), is(true));
        assertThat(sampleConfig.getNanika(), is(1800.0));
        assertThat(sampleConfig.getIgnore(), is("ignore"));
    }

}
