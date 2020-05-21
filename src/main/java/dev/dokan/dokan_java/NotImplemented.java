package dev.dokan.dokan_java;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * If a method is marked with this annotation it won't be registered in DokanOperations struct.
 * <p>
 * This annotation is not inheritable, so all overridden method will be registered.
 * <p>
 * The goal of this annotation is performance, if the method is not registered in the DokanOperations struct
 * then the native &rarr; java call will not be performed.
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
