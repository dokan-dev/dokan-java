package dev.dokan.dokan_java;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;


@Documented
@Retention(RetentionPolicy.RUNTIME)
//In Theory TYPE_USE should contain TYPE_PARAMETER,
//but the documentation is so vague that I'm really not sure to be honest
@Target(value = {TYPE_PARAMETER, TYPE_USE})
public @interface Unsigned {

}
