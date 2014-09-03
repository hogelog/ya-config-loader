package org.hogel.config;

import org.hogel.config.annotation.*;

import java.io.IOException;
import java.nio.file.Path;

public class SampleConfig extends Config {
    @Attribute
    @IntDefaultValue(1000)
    private int timeout;

    @Attribute
    @LongDefaultValue(200_000)
    private long mark;

    @Attribute
    @StringDefaultValue("salt")
    private String salt;

    @Attribute(name = "abort_on_fail")
    @BooleanDefaultValue(true)
    private boolean abortOnFail;

    @Attribute
    @DoubleDefaultValue(1800.0)
    private double nanika;

    private String ignore = "ignore";

    public SampleConfig(Path path) throws IOException, InvalidConfigException {
        super(path);
    }

    public int getTimeout() {
        return timeout;
    }

    public long getMark() {
        return mark;
    }

    public String getSalt() {
        return salt;
    }

    public boolean isAbortOnFail() {
        return abortOnFail;
    }

    public double getNanika() {
        return nanika;
    }

    public String getIgnore() {
        return ignore;
    }
}
