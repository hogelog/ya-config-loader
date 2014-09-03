package org.hogel.config.loader;

import java.lang.annotation.Annotation;

public interface AttributeLoader<T> extends Loader {
    T load(Annotation[] annotations, Object source) throws InvalidAttributeException;
}
