package dev.dokan.dokan_java.conv;


import java.util.AbstractSet;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Objects;


public class MaskValueSetImpl<T extends Enum<T> & MaskValueEnum> extends AbstractSet<T> implements MaskValueSet<T> {

    private final EnumSet<T> elements;

    public MaskValueSetImpl(final Class<T> clazz) {
        this.elements = EnumSet.noneOf(clazz);
    }

    @SafeVarargs
    public MaskValueSetImpl(T first, T... others) {
        this.elements = EnumSet.of(first, others);
    }


    @SafeVarargs
    @Override
    public final void add(T... items) {
        if (items == null) {
            throw new IllegalArgumentException("Adding null is not allowed.");
        }

        Arrays.stream(items).filter(Objects::nonNull).forEach(this::add);
    }

    @Override
    public int toInt() {
        int toReturn = 0;
        for (final T current : this.elements) {
            toReturn |= current.intValue();
        }
        return toReturn;
    }

    @Override
    public boolean add(final T e) {
        return this.elements.add(e);
    }

    @Override
    public Iterator<T> iterator() {
        return this.elements.iterator();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public String toString() {
        return "EnumIntegerSet(elements=" + this.elements + ")";
    }

}