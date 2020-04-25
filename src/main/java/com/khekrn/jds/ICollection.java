package com.khekrn.jds;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author khekrn
 */
public interface ICollection<T> extends Iterable<T>{

    boolean isEmpty();

    int size();

    void clear();

    Iterator<T> iterator();

    default Stream<T> toStream(){
        return StreamSupport.stream(this.spliterator(), false);
    }
}
