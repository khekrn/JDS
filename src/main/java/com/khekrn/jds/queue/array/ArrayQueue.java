package com.khekrn.jds.queue.array;

import com.khekrn.jds.queue.IQueue;
import com.khekrn.jds.queue.array.iterator.ArrayQueueIterator;
import com.khekrn.jds.queue.exception.QueueEmptyException;

import java.util.Iterator;

/**
 * @author khekrn
 * <p>
 * Implementation of queue using circular array
 * <p>
 * {@code enqueue} - O(1)
 * {@code dequeue} - O(1)
 * {@code peek} - O(1)
 */
public final class ArrayQueue<T> implements IQueue<T> {

    private static final int DEFAULT_SIZE = 10;

    private T[] elements;
    private int head;
    private int tail;
    private int size;

    private ArrayQueue(int size) {
        elements = (T[]) new Object[size];
    }

    public static <T> ArrayQueue<T> create() {
        return new ArrayQueue<>(DEFAULT_SIZE);
    }

    public static <T> ArrayQueue<T> create(int size) {
        return new ArrayQueue<>(size);
    }

    @Override
    public void enqueue(T item) {
        if (elements.length == size) {
            resize(2 * size);
        }
        elements[tail] = item;
        tail = (tail + 1) % elements.length;
        size++;
    }

    @Override
    public T dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Dequeue is not permitted on empty queue");
        }
        var item = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;

        if (size == (elements.length / 4)) {
            resize(elements.length / 2);
        }

        return item;
    }

    @Override
    public T peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Empty queue operation not possible");
        }
        return elements[head];
    }

    @Override
    public void clear() {
        elements = null;
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return ArrayQueueIterator.newIterator(elements, head, size);
    }

    @Override
    public String name() {
        return "Queue implementation using array";
    }

    private void resize(int newSize) {
        final T[] newElements = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(head + i) % elements.length];
        }
        elements = newElements;
        head = 0;
        tail = size;
    }
}
