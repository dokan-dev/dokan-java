package dev.dokan.dokan_java;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {METHOD, FIELD, PARAMETER, LOCAL_VARIABLE, TYPE_PARAMETER, TYPE_USE})
public @interface Unsigned {

}
