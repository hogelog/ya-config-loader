package org.hogel.config.loader;

import org.hogel.config.annotation.BooleanDefaultValue;
import org.hogel.config.annotation.DoubleDefaultValue;
import org.hogel.config.annotation.IntDefaultValue;
import org.hogel.config.annotation.LongDefaultValue;
import org.hogel.config.annotation.StringDefaultValue;

import java.lang.annotation.Annotation;

public class BasicAttributeLoader implements AttributeLoader<Object> {

    @Override
    public Object load(Annotation[] annotations, Object source) throws InvalidAttributeException {
        if (source != null) {
            return source;
        }

        for (Annotation annotation : annotations) {
            if (annotation instanceof IntDefaultValue) {
                return ((IntDefaultValue) annotation).value();
            } else if (annotation instanceof LongDefaultValue) {
                return ((LongDefaultValue) annotation).value();
            } else if (annotation instanceof DoubleDefaultValue) {
                return ((DoubleDefaultValue) annotation).value();
            } else if (annotation instanceof BooleanDefaultValue) {
                return ((BooleanDefaultValue) annotation).value();
            } else if (annotation instanceof StringDefaultValue) {
                return ((StringDefaultValue) annotation).value();
            }
        }
        return null;
    }
}
