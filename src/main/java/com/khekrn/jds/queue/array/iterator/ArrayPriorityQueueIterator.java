package com.khekrn.jds.queue.array.iterator;

import com.khekrn.jds.queue.IQueue;
import com.khekrn.jds.queue.array.ArrayPriorityQueue;

import java.util.Comparator;
import java.util.Iterator;

/**
 * @author khekrn
 */
public final class ArrayPriorityQueueIterator<T> implements Iterator<T> {

    private final T[] elements;
    private final Comparator<T> defaultComparator;
    private int size;

    private ArrayPriorityQueueIterator(T[] elements, Comparator<T> comparator, int size) {
        this.elements = elements;
        this.size = size;
        this.defaultComparator = comparator;
    }

    public static <T> ArrayPriorityQueueIterator<T> newIterator(T[] elements, Comparator<T> comparator, int size){
        return new ArrayPriorityQueueIterator<>(elements, comparator, size);
    }

    @Override
    public boolean hasNext() {
        return size > 0;
    }

    @Override
    public T next() {
        var maxIndex = IQueue.maxIndex(elements, defaultComparator, size);
        size--;
        var result = elements[maxIndex];
        elements[maxIndex] = elements[size];
        elements[size] = null;
        return result;
    }
}
