package org.hogel.config.loader;

import org.hogel.config.CustomObject;

import java.lang.annotation.Annotation;

public class CustomObjectLoader implements AttributeLoader<CustomObject> {
    @Override
    public CustomObject load(Annotation[] annotations, Object source) throws InvalidAttributeException {
        try {
            int a = (int) source;
            int b = a * 10;
            return new CustomObject(a, b);
        } catch (ClassCastException e) {
            throw new InvalidAttributeException(e);
        }
    }
}
