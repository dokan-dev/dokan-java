module dokan.java.lib {
	requires com.sun.jna;
	requires transitive com.sun.jna.platform;

	exports dev.dokan.java;
	exports dev.dokan.java.constants;
	exports dev.dokan.java.enums;
	exports dev.dokan.java.nativeannotations;
	exports dev.dokan.java.structures;
}