package com.khekrn.jds.queue.exception;

/**
 * @author khekrn
 */
public class QueueEmptyException extends Exception{
    public QueueEmptyException(String message) {
        super(message);
    }
}
