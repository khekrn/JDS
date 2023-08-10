package com.khekrn.jds.stack.array;

import java.util.Iterator;

import com.khekrn.jds.stack.IStack;

public class ArrayStack<T> implements IStack<T>{

    private T[] bucket;
    private int size;

    public ArrayStack(){
        this(10);
    }

    public ArrayStack(int size){
        this.bucket = (T[]) new Object[size];
        this.size = 0;
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
    public void clear() {
        bucket = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public String name() {
        return "Stack Implementation using dynamic array";
    }

    @Override
    public void push(T item) {
        if(size == bucket.length){
            resize(size * 2);
        }
        bucket[size] = item;
        size = size + 1;
    }

    @Override
    public T pop() {
        T result = null;
        if(isEmpty()){
            return result;
        }
        result = bucket[--size];
        bucket[size] = null;
        if(size == bucket.length/4){
            resize(bucket.length/2);
        }
        return result;
    }

    @Override
    public T peek() {
        T result = null;
        if (!isEmpty()){
            result = bucket[size-1];
        }
        return result;
    }

    private void resize(int newSize){
        var newBucket = (T[]) new Object[newSize];
        for(int i = 0; i<size; i++){
            newBucket[i] = this.bucket[i];
        }
        this.bucket = newBucket;
    }

    private class ArrayIterator implements Iterator<T>{

        private int top = size-1;

        @Override
        public boolean hasNext() {
            return top >= 0;
        }

        @Override
        public T next() {
            var result = bucket[top];
            top--;
            return result;
        }

    } 
    
    
}
