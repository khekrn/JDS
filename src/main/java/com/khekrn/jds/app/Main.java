package com.khekrn.jds.app;

import com.khekrn.jds.stack.array.ArrayStack;

/**
 * @author khekrn
 */
public class Main {

    public static void main(String args[] ) throws Exception {
        var stack = new ArrayStack<Integer>();
        for(int i = 1; i <= 15; i++){
            stack.push(i);
        }

        for(int i = 1; i <= 15; i++){
            System.out.println("Peek = "+stack.peek()+" and Pop = "+stack.pop() +" and Size = "+stack.size());
        }

        for(int i = 1; i <= 15; i++){
            stack.push(i);
        }
    }
}
