package list.array;

/**
* created by kkishore
*/

import java.util.Iterator;

public final class ArrayIterator<T> implements Iterator<T> {
	
	private final T[] data;
	private final int size;
	private int index;
	
	private ArrayIterator(T[] data, int size){
		this.data = data;
		this.size = size;
		this.index = 0;
	}
	
	public static <T> ArrayIterator<T> createNewArrayIterator(T[] data, int size){
		return new ArrayIterator<T>(data, size);
	}

	@Override
	public boolean hasNext() {
		return index < size;
	}

	@Override
	public T next() {
		T temp = this.data[index];
		this.index++;
		return temp;
	}

}
