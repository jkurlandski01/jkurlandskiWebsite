package fibonacci;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

/**
 * Write a method to return the fibonacci sequence given the nth number as input.
 * 0, 1, 1, 2, 3, 5, 8,
 */
public class Fibonacci {
	Map<Integer, Integer> fibMap = new HashMap<>();

	public List<Integer> getFib(int nbr)    {

	    if(nbr <= 0)    
	        throw new IllegalArgumentException("nbr can't be less than 0.");

	    List<Integer> arr = new ArrayList<>();

	    for(int ct = 1; ct <= nbr; ct++)    {
	        arr.add(getFibRecurse(ct));
	    }

	    return arr;
	}
	
	
	public int[] getFibAsArray(int nbr)	{
		int[] arr = new int[nbr];
		
		for(int ct = 1; ct <= nbr; ct++)	{
			arr[ct-1] = getFibRecurse(ct);
		}
		
		return arr;
	}



	public int getFibRecurse(int nbr)    {
	    if(nbr <= 0)    
	        throw new IllegalArgumentException("nbr can't be less than 0.");

	    if (nbr == 1 )    {
	        return 0;
	    }
	    else if (nbr == 2)    {
	        return 1;
	    }

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

	    if(nbr <= 0)    
	        throw new IllegalArgumentException("nbr can't be less than 0.");

	    if (nbr == 1 )    {
	        return 0;
	    }
	    else if (nbr == 2)    {
	        return 1;
	    }

	    return getFibRecurseSlow(nbr-1) + getFibRecurseSlow(nbr-2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void fibRecurse0()	{
		assertEquals(0, getFibRecurse(0));
	}


	@Test
	public void fibRecurse1()	{
		assertEquals(0, getFibRecurse(1));
	}

	@Test
	public void fibRecurse2()	{
		assertEquals(1, getFibRecurse(2));
	}

	@Test(expected=IllegalArgumentException.class)
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
	
	@Test(expected=IllegalArgumentException.class)
	public void getFib0()	{
		List<Integer> actual = getFib(0);
		assertEquals(Lists.newArrayList(), actual);
	}
	
	@Test
	public void getFib1()	{
		List<Integer> actual = getFib(1);
		assertEquals(Lists.newArrayList(0), actual);
	}
	
	@Test
	public void getFib2()	{
		List<Integer> actual = getFib(2);
		assertEquals(Lists.newArrayList(0, 1), actual);
	}
	
	@Test
	public void getFib3()	{
		List<Integer> actual = getFib(3);
		assertEquals(Lists.newArrayList(0, 1, 1), actual);
	}
	
	@Test
	public void getFib4()	{
		List<Integer> actual = getFib(4);
		assertEquals(Lists.newArrayList(0, 1, 1, 2), actual);
	}
	
	@Test
	public void getFibAsArray4()	{
		int[] actual = getFibAsArray(4);
		//List<Integer> actualList = Arrays.asList(actual);
		List<Integer> actualList = Ints.asList(actual);
		List<Integer> expected = Lists.newArrayList(0, 1, 1, 2);
		assertEquals(expected, actualList);
	}
	
}
