package org.hogel.config;

import com.google.common.collect.ImmutableList;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ComplexConfigTest extends ConfigTestCase {
    @SuppressWarnings("unchecked")
    @Test
    public void load() throws Exception {
        ComplexConfig config = new ComplexConfig();
        config.load(testConfigPath("complex.yaml"));

        assertThat(config.getList(), is((List<Integer>) ImmutableList.of(10, 20, 30)));
        assertThat((Integer) config.getTable().get("a"), is(1000));
        assertThat((String) config.getTable().get("b"), is("hoge"));
        assertThat((List<Integer>) config.getTable().get("c"), is((List<Integer>) ImmutableList.of(40, 50, 60)));
        assertThat(config.getList(), CoreMatchers.<List<Integer>>is(ImmutableList.of(10, 20, 30)));
    }
}
