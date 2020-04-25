package com.khekrn.jds.queue;

import com.khekrn.jds.ICollection;
import com.khekrn.jds.IDataStructure;
import com.khekrn.jds.queue.exception.QueueEmptyException;

/**
 * @author khekrn
 */
public interface IQueue<T> extends IDataStructure, ICollection {

    void enqueue(T item);

    T dequeue() throws QueueEmptyException;

    T peek();
}
