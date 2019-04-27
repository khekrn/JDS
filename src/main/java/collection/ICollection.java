package collection;

/*
  Created by kishore on Apr, 2019;
*/

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface ICollection<T> extends Iterable<T>{

    boolean isEmpty();

    void clear();

    int size();

    T[] values();

    Iterator<T> iterator();

    default Stream<T> toStream(){
        final var splitIterator = Spliterators.spliteratorUnknownSize(iterator(), Spliterator.IMMUTABLE);
        return StreamSupport.stream(splitIterator, false);
    }
}
