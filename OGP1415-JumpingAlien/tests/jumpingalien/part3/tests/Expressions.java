package jumpingalien.part3.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Expressions {
	
	
	@Test
	public void testList() {
		List<Object> list = new ArrayList<Object>();
		list.add(5);
		list.add(8);
		list.add(9);
		System.out.println(list.toString());
		list.remove(list.get(0));
		System.out.println(list.toString());

	}
}
