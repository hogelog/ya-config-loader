package org.hogel.config;

import java.io.Serializable;

public class CustomObject implements Serializable {
    private final int a;
    private final int b;

    public CustomObject(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
