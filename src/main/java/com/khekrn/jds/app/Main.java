package com.khekrn.jds.app;

import com.khekrn.jds.queue.array.ArrayPriorityQueue;

/**
 * @author khekrn
 */
public class Main {

    public static void main(String args[] ) throws Exception {
//        var pqueue = new PriorityQueue<Integer>(10, Comparator.reverseOrder());
//        for(int i = 1; i <=5; i++){
//            pqueue.add(i);
//        }
//
//        for(int i = 10; i >= 6; i--){
//            pqueue.add(i);
//        }
//
//        var iter = pqueue.iterator();
//        while (iter.hasNext()){
//            System.out.println(iter.next());
//        }

        System.out.println("\n\n\n");

        var myqueue = ArrayPriorityQueue.<Integer>create(5);
        myqueue.enqueue(15);
        myqueue.enqueue(1);
        myqueue.enqueue(2);
        myqueue.enqueue(5);
        myqueue.enqueue(10);


        System.out.println(myqueue.dequeue());
        System.out.println(myqueue.dequeue());
        System.out.println(myqueue.dequeue());
        System.out.println(myqueue.dequeue());
        System.out.println(myqueue.dequeue());

    }
}
