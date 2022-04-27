package dev.dokan.java.nativeannotations;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE_USE;


/**
 * This annotation is used used to indicate that a value with an integer type or an integer type pointer refers to an <b>unsigned value.</b><br>
 * In this case integers are <i>numbers without positions after decimal point.</i>
 * (Java's Default integer types being {@code byte, short, int, long} and their corresponding wrappers.)
 *
 * @author <a href="https://github.com/JaniruTEC">JaniruTEC</a>
 * @since 2.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(value = {FIELD, TYPE_USE})
public @interface Unsigned {

}
