package org.hogel.config;

public class InvalidConfigException extends Exception {
    private final Config config;

    public InvalidConfigException(Config config, Throwable cause) {
        super(cause);
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }
}
