package fibonacci;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Write a method to return the fibonacci sequence given the nth number as input.
 * 0, 1, 1, 2, 3, 5, 8,
 */
public class Fibonacci {
	Map<Integer, Integer> fibMap = new HashMap<>();

	public List<Integer> getFib(int nbr)    {

	    if(nbr < 0)    
	        throw new IllegalArgumentException("nbr can't be less than 0.");

	    List<Integer> arr = new ArrayList<>();

	    for(int ct = 0; ct <= nbr; ct++)    {
	        arr.add(getFibRecurse(nbr));
	    }

	    return arr;

	}



	public int getFibRecurse(int nbr)    {
	    if(fibMap.containsKey(nbr))    {
	        return fibMap.get(nbr);
	    }

	    else    {
	        int newItem = getFibRecurse(nbr-1) + getFibRecurse(nbr-2);
	        fibMap.put(nbr, newItem);
	        return newItem;
	    }
	}



	public int getFibRecurseSlow(int nbr)    {

	    if (nbr == 1 )    {
	        return 0;
	    }
	    else if (nbr == 2)    {
	        return 1;
	    }

	    return getFibRecurseSlow(nbr-1) + getFibRecurseSlow(nbr-2);
	}

	@Test
	public void fibRecurse0()	{
		assertEquals(0, getFibRecurse(1));
	}


	@Test
	public void fibRecurseSlow0()	{
		assertEquals(0, getFibRecurseSlow(0));
	}

	@Test
	public void fibRecurseSlow1()	{
		assertEquals(0, getFibRecurseSlow(1));
	}

	@Test
	public void fibRecurseSlow2()	{
		assertEquals(1, getFibRecurseSlow(2));
	}
}
