package list;

import java.util.Arrays;
import java.util.Comparator;

import collection.ICollection;

/*
  Created by kkishore on Apr, 2019;
*/
public interface IList<T> extends ICollection<T> {

	default boolean isEmpty() {
		return size() == 0;
	}

	void add(T item);

	void addAt(int index, T item);

	void addAll(IList<T> list);

	T get(int index);

	T remove(int index);

	boolean remove(T item);

	boolean removeAll(IList<T> list);

	boolean contains(Object o);

	boolean containsAll(IList<T> list);

	int hashCode();

	boolean equals(Object o);

	int indexOf(Object o);
	
	Object[] toArray();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	default void sort(Comparator<? super T> comparator) {
		Object[] arr = this.toArray();
		Arrays.sort(arr, (Comparator) comparator);
		int index = 0;
		for (Object item : arr) {
			this.addAt(index, (T) item);
			index++;
		}
	}
}
