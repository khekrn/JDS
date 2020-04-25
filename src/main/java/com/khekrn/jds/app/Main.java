package com.khekrn.jds.app;

import com.khekrn.jds.queue.array.ArrayQueue;
import com.khekrn.jds.text.distance.CosineDistance;
import com.khekrn.jds.text.similarity.CosineSimilarity;

/**
 * @author khekrn
 */
public class Main {

    public static void main(String[] args) {
        var queue = ArrayQueue.<Integer>create(10);

        for(int i = 1; i <= 10; i++){
            queue.enqueue(i);
        }

        for (Integer integer : (Iterable<Integer>) queue) {
            System.out.println(integer);
        }
    }
}
