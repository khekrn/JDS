
/**
* created by kkishore
*/

import list.array.*;
import list.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final IList<Integer> list = ArrayList.newArrayList(10);
		
		for(int i = 0; i < 10; i++) {
			list.add(i+1);
		}
		
		System.out.println(list.size());
		for(int i = 0; i < 10; i++) {
			System.out.println(list.get(i));
		}
		
		list.toStream().forEach(System.out::println);
		
	}

}
