package com.dokan.java;

/**
 * A byteable object can be converted into an array of Bytes.
 */
public interface Byteable {

    /**
     * Computes the byte representation of the object.
     *
     * @return array of bytes
     */
    byte[] toByteArray();

    /**
     * Function to retrieve the length of the byte array needed to represent the object.
     *
     * @return the size of byte array
     */
    int sizeOfByteArray();

}
