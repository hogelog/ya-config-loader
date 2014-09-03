package org.hogel.config;

import org.hogel.config.loader.AttributeLoader;
import org.hogel.config.loader.InvalidAttributeException;

import java.lang.annotation.Annotation;

public class X10IntLoader implements AttributeLoader<Integer> {
    @Override
    public Integer load(Annotation[] annotations, Object source) throws InvalidAttributeException {
        return 10 * (int) source;
    }
}
