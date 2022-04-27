package dev.dokan.java;

//TODO: idea for DokaOptions Global context. We can even paramterize the type!
public abstract class GlobalContext<T> {

	public T to() {
		return null;
	}

	public static <T> GlobalContext<T> from(T obj) {
		//add 64bit check
		return new GlobalContext<T>() {
		};
	}


}
