package com.khekrn.jds.queue.array;

import com.khekrn.jds.queue.IQueue;
import com.khekrn.jds.queue.array.iterator.ArrayPriorityQueueIterator;
import com.khekrn.jds.queue.exception.QueueEmptyException;

import java.util.Comparator;
import java.util.Iterator;

/**
 * @author khekrn
 * <p>
 * Priority queue implementation
 * enqueue - O(1)
 * dequeue - O(N)
 * </p>
 */
public final class ArrayPriorityQueue<T extends Comparable<? super T>> implements IQueue<T> {

    private T[] elements;
    private int tail;
    private Comparator<T> defaultComparator;

    private ArrayPriorityQueue(int capacity) {
        this(capacity, Comparator.naturalOrder());
    }

    private ArrayPriorityQueue(int capacity, Comparator<T> comparator) {
        this.elements = (T[]) new Comparable[capacity];
        this.defaultComparator = comparator;
        this.tail = 0;
    }

    public static <T extends Comparable<T>> ArrayPriorityQueue<T> create(int capacity) {
        return new ArrayPriorityQueue<>(capacity);
    }

    public static <T extends Comparable<T>> ArrayPriorityQueue<T> create(int capacity, Comparator<T> comparator) {
        return new ArrayPriorityQueue<>(capacity, comparator);
    }

    @Override
    public void enqueue(T item) {
        if (tail == elements.length) {
            resize(tail * 2);
        }
        elements[tail] = item;
        tail++;
    }

    private void resize(int capacity) {
        var newElements = (T[]) new Comparable[capacity];
        System.arraycopy(elements, 0, newElements, 0, tail);
        elements = newElements;
    }


    @Override
    public T dequeue() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Empty queue operation not possible");
        }
        var maxIndex = IQueue.maxIndex(elements, defaultComparator, tail);
        T item = elements[maxIndex];

        tail--;
        elements[maxIndex] = elements[tail];
        elements[tail] = null;

        if (tail == (elements.length / 4)) {
            resize(elements.length / 2);
        }

        return item;
    }

    @Override
    public T peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Empty queue operation not possible");
        }
        return elements[IQueue.maxIndex(elements, defaultComparator, tail)];
    }

    @Override
    public boolean isEmpty() {
        return tail == 0;
    }

    @Override
    public int size() {
        return tail;
    }

    @Override
    public void clear() {
        elements = null;
        tail = 0;
        defaultComparator = null;
    }

    @Override
    public Iterator<T> iterator() {
        return ArrayPriorityQueueIterator.newIterator(elements.clone(), defaultComparator, tail);
    }

    @Override
    public String name() {
        return "Priority queue implementation using array where dequeue is O(1)";
    }
}
