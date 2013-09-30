package com.artemis.utils;

import com.badlogic.gdx.utils.Array;


/**
 * A non-throwing array, until the logic is reworked to avoid throwing
 * @author apotapov
 *
 * @param <T>
 */
public class SafeArray<T> extends Array<T> {

    public SafeArray() {
        super();
    }

    public SafeArray(Array<? extends T> array) {
        super(array);
    }

    public SafeArray(boolean ordered, int capacity, Class<?> arrayType) {
        super(ordered, capacity, arrayType);
    }

    public SafeArray(boolean ordered, int capacity) {
        super(ordered, capacity);
    }

    public SafeArray(boolean ordered, T[] array, int start, int count) {
        super(ordered, array, start, count);
    }

    public SafeArray(Class<?> arrayType) {
        super(arrayType);
    }

    public SafeArray(int capacity) {
        super(capacity);
    }

    public SafeArray(T[] array) {
        super(array);
    }

    @Override
    public void set(int index, T value) {
        ensureFit(this, index);
        super.set(index, value);
    }

    @Override
    public T get(int index) {
        if (index < size) {
            return super.get(index);
        }
        return null;
    }

    /**
     * If index is larger than the size of the array,
     * will grow the array to the index.
     * @param array
     * @param index
     */
    public static <T> void ensureFit(Array<T> array, int index) {
        if (index >= array.size) {
            array.ensureCapacity(index - array.size + 1);
            while (index >= array.size) {
                array.add(null);
            }
        }
    }
}
