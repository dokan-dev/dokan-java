package com.dokan.java;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * If method marked this annotation it would'n be registered in FuseOperation struct.
 * All methods in TODO marked this annotation.
 *
 * This annotation is not inheritable, so all overridden method would be registered in FuseOperation.
 *
 * The goal of this annotation is performance, if method not registered in FuseOperation
 * then native &rarr; java call wouldn't be performed.
 *
 * <p>This class is a copy of <a href="https://github.com/SerCeMan/jnr-fuse/blob/master/src/main/java/ru/serce/jnrfuse/NotImplemented.java">ru.serce.jnrfuse.NotImplemented</a>.</p>
 *
 * @author Sergey Tselovalnikov
 * @since 2.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = METHOD)
public @interface NotImplemented {

}
