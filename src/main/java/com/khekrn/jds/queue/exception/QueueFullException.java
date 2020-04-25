package com.khekrn.jds.queue.exception;

import java.util.concurrent.Executors;

/**
 * @author khekrn
 */
public final class QueueFullException extends Exception {

    public QueueFullException(String message) {
        super(message);
    }
}
