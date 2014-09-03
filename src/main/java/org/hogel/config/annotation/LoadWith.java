package org.hogel.config.annotation;

import org.hogel.config.loader.BasicConfigLoader;
import org.hogel.config.loader.ConfigLoader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoadWith {
    Class<? extends ConfigLoader> value() default BasicConfigLoader.class;
}
