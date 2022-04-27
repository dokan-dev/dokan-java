package dev.dokan.java.nativeannotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target(value = {TYPE_PARAMETER, FIELD})
public @interface Boolean {

}
