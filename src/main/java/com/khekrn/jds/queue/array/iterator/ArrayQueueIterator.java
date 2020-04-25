package com.khekrn.jds.queue.array.iterator;

import java.util.Iterator;

/**
 * @author khekrn
 */
public final class ArrayQueueIterator<T> implements Iterator<T> {

    private final T[] elements;
    private final int head;
    private final int size;
    private int index;

    private ArrayQueueIterator(T[] elements, int head, int size){
        this.elements = elements;
        this.head = head;
        this.size = size;
    }

    public static <T> ArrayQueueIterator<T> newIterator(T[] elements, int head, int size){
        return new ArrayQueueIterator<>(elements, head, size);
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public T next() {
        T item = elements[(head + index) % size];
        index++;
        return item;
    }
}
