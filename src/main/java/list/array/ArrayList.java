package list.array;

import java.util.Iterator;
import java.util.Objects;
import java.util.Arrays;

import list.IList;

public class ArrayList<T> implements IList<T>{
	
	private static final int INTITIAL_CAPACITY = 10;
	
	private static final float GROWTH_FACTOR = 2.0f;
	private static final float SHRINK_FACTOR = 0.25f;
	
	private T[] data;
	private int size;
	
	private ArrayList(){
		this(INTITIAL_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList(int capacity) {
		this.data = (T[]) new Object[capacity];
		this.size = 0;
	}
	
	@SafeVarargs
	private ArrayList(T... elements) {
		this.data = elements;
		this.size = elements.length;
	}
	
	public static <T> ArrayList<T> newInstance() {
		return new ArrayList<>();
	}
	
	public static <T> ArrayList<T> newArrayList(int capacity) {
		return new ArrayList<>(capacity);
	}
	
	@SafeVarargs
	public static <T> ArrayList<T> of(T... elements) {
		return new ArrayList<>(elements);
	}

	@Override
	public void clear() {
		this.data = null;
		this.size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T[] values() {
		return this.data;
	}

	@Override
	public Iterator<T> iterator() {
		return ArrayIterator.createNewArrayIterator(this.data, this.size);
	}

	@Override
	public void add(T item) {
		Objects.requireNonNull(item);
		this.ensureCapacity(1);
		this.data[this.size++] = item;
	}

	@Override
	public void addAt(int index, T item) {
		Objects.requireNonNull(item);
		this.rangeCheck(index);
		this.ensureCapacity(1);
		if(index != this.size-1) {
			System.arraycopy(this.data, index, this.data, index+1, this.size-index);
		}
		this.data[index] = item;
		this.size++;
	}

	@Override
	public void addAll(IList<T> list) {
		Objects.requireNonNull(list);
		this.ensureCapacity(list.size());
		final var listArray = list.toArray();
		System.arraycopy(listArray, 0, this.data, this.size, listArray.length);
		this.size += listArray.length;
	}

	@Override
	public T get(int index) {
		this.rangeCheck(index);
		
		T indexItem = null;
		if(!this.isEmpty()) {
			indexItem = this.data[index];
		}
		return indexItem;
	}

	@Override
	public T remove(int index) {
		this.rangeCheck(index);
		
		if(this.isEmpty()){
			throw new UnsupportedOperationException("remove operation not supported for empty list");
		}
		
		var newSize = this.size - 1;
		final T item = this.data[index];
		this.data[index] = null;
		if(newSize > index) {
			System.arraycopy(this.data, index+1, this.data, index, newSize-index);
		}

		this.size = newSize;
		this.shrink();
		
		return item;
	}

	@Override
	public boolean remove(T item) {
		var res = this.remove(this.indexOf(item)) != null ? true : false;
		return res;
	}

	@Override
	public boolean removeAll(IList<T> list) {
		var res = false;
		for(final T item : list) {
			if(this.remove(item)) {
				res = true;
			}
		}
		return res;
	}

	@Override
	public boolean contains(Object o) {
		@SuppressWarnings("unchecked")
		var item = (T) o;
		T foundItem = this.toStream().filter(temp -> temp == item).findFirst().get();
		return  foundItem != null ? true : false;
	}

	@Override
	public boolean containsAll(IList<T> list) {
		var found = true;
		for(T item : list) {
			if(!this.contains(item)) {
				found = false;
				break;
			}
		}
		return found;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(Object o) {
		var index = 0;
		for(int i = 0; i < size; i++){
			if(this.data[i] == (T) o){
				index = i;
				break;
			}
		}
		return index;
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(this.data, this.size);
	}
	
	public String toString(){
		var iter = this.iterator();
		if(!iter.hasNext()) {
			return "[]";
		}
		
		
		var builder = new StringBuilder(this.size*3);
		builder.append('[');
		for(;;){
			var item = iter.next();
			builder.append(item);
			if(!iter.hasNext()){
				return builder.append(']').toString();
			}
			builder.append(",").append(" ");
		}
	}
	
	private void ensureCapacity(int k) {
		if(this.size+k >= this.data.length) {
			var newSize = (int) ((this.size+k) * ArrayList.GROWTH_FACTOR);
			this.resize(newSize);
		}
	}
	
	private void shrink() {
		if(this.size <= (int) (this.data.length) * ArrayList.SHRINK_FACTOR) {
			this.resize(this.size);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int n) {
		final var newData = (T[]) new Object[n];
		System.arraycopy(this.data, 0, newData, 0, this.size);
		this.data = newData;
	}

}
