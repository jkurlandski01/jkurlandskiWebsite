package interviews.findfoo;

import java.util.ArrayList;
import java.util.List;

public class Foo {
	String description;
	
	public Foo(String desc)	{
		description = desc;
	}
	
	@Override
	public String toString()	{ return description; }

	/**  
	* Given a list of objects, returns the last object in the list that is an instance of type Foo.  
	* @param stuff the list of objects  
	* @return an object of type Foo  
	* @throws IllegalStateException if there is no Foo in the list  
	*/ 
	public static Foo getLastFoo(List<Object> stuff) throws IllegalStateException	{
		for (int i=stuff.size()-1; i >= 0; i--) {
		     if (stuff.get(i) instanceof Foo) {
		    	 return (Foo) stuff.get(i);
		     }
		}
		throw new IllegalStateException();
	}

	public static void main(String[] args) {
		List<Object> list = new ArrayList<Object>();
		list.add("Hello");
		list.add(new Integer(1));
		list.add(new Foo("first"));
		list.add("Goodbye");
		list.add(new Double(2.3));
		list.add("hi!");
		list.add(new Foo("second"));
		list.add(new Long(1));
		
		System.out.println(getLastFoo(list).toString());
	}

}

