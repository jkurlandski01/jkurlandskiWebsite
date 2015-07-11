package interviews.findfoo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Bar {
	
	public Bar()	{
	}
	

	/**  
	* Given a list of objects, returns the last object in the list that is an instance of type Bar.  
	* @param stuff the list of objects  
	* @return an object of type Bar  
	* @throws IllegalStateException if there is no Bar in the list  
	*/ 
	public static Bar getLastBar(List<Object> stuff) throws IllegalStateException	{
		if (stuff == null)	
			throw new IllegalArgumentException("Argument is null");
		
		for (int i=stuff.size()-1; i >= 0; i--) {
		     if (stuff.get(i) instanceof Bar) {
		    	 return (Bar) stuff.get(i);
		     }
		}
		throw new IllegalStateException();
	}

	/**  
	* Given a list of objects, returns the nth-last object in the list that is an instance of type Bar.  
	* @param stuff the list of objects  
	* @return an object of type Bar  
	* @throws IllegalStateException if there is no nth-last Bar in the list  
	*/ 
	public static Bar getNthLastBar(List<Object> stuff, int n) throws IllegalStateException	{
		if (stuff == null)	
			throw new IllegalArgumentException("Argument is null");
		
		// TODO: What should we do if n is 0 or negative?
		
		int counter = 1;
		
		for (int i=stuff.size()-1; i >= 0; i--) {
		     if (stuff.get(i) instanceof Bar) {
		    	 if(counter == n)	{
		    		 return (Bar) stuff.get(i);
		    	 }
		    	 else	{
		    		 counter++;
		    	 }
		     }
		}
		throw new IllegalStateException();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetLastBarNull()	{
		getLastBar(null);
	}
	
	@Test
	public void testNthLastBar()	{
		List<Object> list = new ArrayList<Object>();
		list.add("Hello");
		list.add(new Integer(1));
		Bar first = new Bar();
		list.add(first);
		list.add(new Double(2.3));
		list.add("hi!");
		Bar second = new Bar();
		list.add(second);
		list.add(new Long(1));
		Bar third = new Bar();
		list.add(third);
		list.add(new Long(1));
		
		Bar actual = getNthLastBar(list, 1);
		assertEquals(third, actual);
		
		actual = getNthLastBar(list, 2);
		assertEquals(second, actual);
		
		actual = getNthLastBar(list, 3);
		assertEquals(first, actual);
	}

	@Test(expected=IllegalStateException.class)
	public void testNthLastBarException1()	{
		List<Object> list = new ArrayList<Object>();
		list.add("Hello");
		list.add(new Integer(1));
		Bar first = new Bar();
		list.add(first);
		list.add(new Double(2.3));
		list.add("hi!");
		Bar second = new Bar();
		list.add(second);
		list.add(new Long(1));
		Bar third = new Bar();
		list.add(third);
		list.add(new Long(1));
		
		Bar actual = getNthLastBar(list, 4);
	}

	@Test(expected=IllegalStateException.class)
	public void testNthLastBarException2()	{
		List<Object> list = new ArrayList<Object>();
		list.add("Hello");
		list.add(new Integer(1));
		list.add(new Double(2.3));
		list.add("hi!");
		list.add(new Long(1));
		list.add(new Long(1));
		
		Bar actual = getNthLastBar(list, 1);
	}

	public static void main(String[] args) {
//		List<Object> list = new ArrayList<Object>();
//		list.add("Hello");
//		list.add(new Integer(1));
//		list.add(new Bar("first"));
//		list.add("Goodbye");
//		list.add(new Double(2.3));
//		list.add("hi!");
//		list.add(new Bar("second"));
//		list.add(new Long(1));
		
//		System.out.println(getLastBar(list).toString());
	}

}

