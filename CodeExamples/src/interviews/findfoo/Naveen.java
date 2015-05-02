package interviews.findfoo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class Naveen {
	
	

	/**  
	* Given a list of objects, returns the last object in the list that is an instance of type Bar.  
	* @param stuff the list of objects  
	* @return an object of type Bar  
	* @throws IllegalStateException if there is no Bar in the list  
	*/ 
	public static Bar getLastBar(List<Object> stuff) throws IllegalStateException	{
		for (int i=stuff.size()-1; i >= 0; i--) {
		     if (stuff.get(i) instanceof Bar) {
		    	 return (Bar) stuff.get(i);
		     }
		}
		throw new IllegalStateException();
	}

    public static Bar getNthLastBar(List<Object> stuff, int n) throws IllegalStateException {
        ArrayList<Object> al = new ArrayList<Object>();
        Iterator itr = stuff.iterator();
        while(itr.hasNext()) {
            Object obj = itr.next();
            if(obj instanceof Bar)    {
                al.add(obj);
            }
        }
        return (Bar) al.get(al.size() - n);
    }

	

}

