package com.khekrn.jds.queue.array;

import com.khekrn.jds.queue.exception.QueueEmptyException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author khekrn
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArrayQueueTest {

    private static ArrayQueue<Integer> queue = ArrayQueue.create();

    private void addElements() {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i + 1);
        }
    }

    @org.junit.jupiter.api.Test
    @Order(1)
    void enqueue() {

        addElements();

        assertEquals(10, queue.size());

        for (int i = 0; i < 5; i++) {
            try {
                assertEquals(i + 1, queue.dequeue());
            } catch (QueueEmptyException e) {
                e.printStackTrace();
            }
        }

        for (int i = 10; i < 20; i++) {
            queue.enqueue(i + 1);
        }

        assertEquals(15, queue.size());
    }

    @org.junit.jupiter.api.Test
    @Order(2)
    void dequeue() {
        queue.clear();

        queue = ArrayQueue.create();
        assertThrows(QueueEmptyException.class, () -> queue.dequeue());

        addElements();

        for (int i = 0; i < 5; i++) {
            try {
                assertEquals(i + 1, queue.dequeue());
            } catch (QueueEmptyException e) {
                e.printStackTrace();
            }
        }


    }

    @org.junit.jupiter.api.Test
    @Order(3)
    void peek() {
        queue.clear();

        queue = ArrayQueue.create();

        for (int i = 0; i < 10; i++) {
            queue.enqueue(i + 1);
        }
        assertEquals(1, queue.peek());
    }

    @org.junit.jupiter.api.Test
    @Order(4)
    void isEmpty() {
        assertFalse(queue.isEmpty());
    }

    @org.junit.jupiter.api.Test
    @Order(5)
    void size() {
        assertEquals(10, queue.size());
    }

    @Test
    @Order(6)
    void iterator() {
        var iterator = queue.iterator();
        var index = 0;
        while (iterator.hasNext()) {
            var result = iterator.next();
            assertEquals(index + 1, result);
            index++;
        }
    }

    @Test
    @Order(7)
    void clear() {
        queue.clear();
        assertEquals(0, queue.size());
    }
}