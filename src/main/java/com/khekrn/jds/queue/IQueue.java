package com.khekrn.jds.queue;

import com.khekrn.jds.ICollection;
import com.khekrn.jds.IDataStructure;
import com.khekrn.jds.queue.exception.QueueEmptyException;

import java.util.Comparator;

/**
 * @author khekrn
 */
public interface IQueue<T> extends IDataStructure, ICollection<T> {

    void enqueue(T item);

    T dequeue() throws QueueEmptyException;

    T peek() throws QueueEmptyException;

    static <T> int maxIndex(T[] elements, Comparator<T> defaultComparator, int size){
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            if (defaultComparator.compare(elements[maxIndex], elements[i]) > 0) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
