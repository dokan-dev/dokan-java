package dev.dokan.dokan_java.wrappers;

import dev.dokan.dokan_java.NativeName;
import dev.dokan.dokan_java.FileSystemInformation;
import dev.dokan.dokan_java.NotImplemented;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractEasyDokanFileSystem implements EasyDokanFileSystem {

	private final Set<String> notImplementedMethods;
	private final ForwardingFileSystem lowLevelFS;

	public AbstractEasyDokanFileSystem(FileSystemInformation fileSystemInformation) {
		this.notImplementedMethods = Arrays.stream(getClass().getMethods())
			.filter(method -> method.getAnnotation(NotImplemented.class) != null)
			.map((method) -> getNativeName(method))
			.collect(Collectors.toSet());

		this.lowLevelFS = new ForwardingFileSystem(fileSystemInformation, this.notImplementedMethods, this);
	}

	private String getNativeName(Method method) {
		Method annotated = getAnnotatedMethod(NativeName.class, method);

		if(annotated == null) {
			return method.getName();
		}
		return annotated.getAnnotation(NativeName.class).value();
	}

	private Method getAnnotatedMethod(Class<? extends Annotation> annotation, Method method) {
		return getAnnotatedMethod(annotation, method.getDeclaringClass(), method.getName(), method.getParameterTypes());
	}

	private Method getAnnotatedMethod(Class<? extends Annotation> annotation,
		Class<?> inClass,
		String methodName,
		Class<?>... parameterTypes) {

		Method result;
		try {
			result = inClass.getMethod(methodName, parameterTypes);
		} catch(NoSuchMethodException e) {
			return null;
		}

		if(result.isAnnotationPresent(annotation)) {
			return result;
		}

		Class<?> superClass = inClass.getSuperclass();
		if(superClass != null) {
			result = getAnnotatedMethod(annotation, superClass, methodName, parameterTypes);
		}

		if(result != null) {
			return result;
		}

		return Arrays.stream(inClass.getInterfaces())
			.map((cInterface) -> getAnnotatedMethod(annotation, cInterface, methodName, parameterTypes))
			.findFirst().orElse(null);
	}
}
