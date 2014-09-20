package org.hogel.config.annotation;

import org.hogel.config.loader.AttributeLoader;
import org.hogel.config.loader.BasicAttributeLoader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Attribute {
    String name() default "";

    Class<? extends AttributeLoader> loader() default BasicAttributeLoader.class;
}
