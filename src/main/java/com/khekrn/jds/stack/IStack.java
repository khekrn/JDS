package com.khekrn.jds.stack;

import com.khekrn.jds.ICollection;
import com.khekrn.jds.IDataStructure;

public interface IStack<T> extends ICollection<T>, IDataStructure{

    void push(T item);

    T pop();

    T peek();
    
}
