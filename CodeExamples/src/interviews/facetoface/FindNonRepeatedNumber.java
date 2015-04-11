package interviews.facetoface;

import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import static org.junit.Assert.assertEquals;


/**
* This method receives an array with positive integers, where all values in the array are repeated - except for one which is unique. 
* Return the unique element in the array.
*
* @param intArray[] array of ints 
* @return the non-repeated int
*
* e.g. 
* int[] arr = {1,2,3,5,6,3,2,5,1,32,32,3,4,4,1}
* int nonRepeated = findNonRepeatedInt(arr);
* System.out.println(nonRepeated); // prints 6 
*
*/
//public int findNonRepeatedInt(int[] intArray) {

public class FindNonRepeatedNumber {

    public static void main(String[] args) {
    }

    @Test
    public void testUsingMapNormal()	{
    	int[] arr = {1,2,3,5,6,3,2,5,1,32,32,3,4,4,1};
    	int actual = findNonRepeatedIntMapNormal(arr);
    	assertEquals(6, actual);
    }
    
    public int findNonRepeatedIntMapNormal(int[] intArray) {
    	Map<Integer, Integer> intMap = Maps.newHashMap();
    	for(int i = 0; i < intArray.length; i++)	{
    		int element = intArray[i];
    		if(intMap.containsKey(element))	{
    			int old = intMap.get(element);
    			intMap.put(element,  ++old);
    		}
    		else	{
    			intMap.put(element, 1);
    		}
    	}
    	for(Entry<Integer, Integer> entry : intMap.entrySet())	{
    		if(entry.getValue() == 1)	{
    			return entry.getKey();
    		}
    	}
    	throw new IllegalStateException("No non-repeated int found.");
    }

    // Try another approach which is still O(n), but reduces runtime by half--no second loop through each element.
    
    @Test
    public void testUsingMapOneLoop()	{
    	int[] arr = {1,2,3,5,6,3,2,5,1,32,32,3,4,4,1};
    	int actual = findNonRepeatedIntMapOneLoop(arr);
    	assertEquals(6, actual);
    }
    
    public int findNonRepeatedIntMapOneLoop(int[] intArray) {
    	// Holds the list of single-occurring items for the current state of the process.
    	List<Integer> results = Lists.newArrayList();
    	
    	Map<Integer, Integer> intMap = Maps.newHashMap();
    	for(int i = 0; i < intArray.length; i++)	{
    		//int element = intArray[i];
    		Integer element = new Integer(intArray[i]);
    		if(intMap.containsKey(element))	{
    			// The item has occurred more than once; remove it.
    			results.remove(element);
     		}
    		else	{
    			intMap.put(element, 1);
    			results.add(element);
    		}
    	}
    	if (results.isEmpty())	{
    		throw new IllegalStateException("No non-repeated int found.");
    	}
    	if (results.size() > 1)	{
    		throw new IllegalStateException("More than one non-repeated int found.");
    	}
    	return results.get(0);
    	
    }

    // Try another approach which doesn't use a map.
    
    @Test
    public void testUsingNoMap()	{
    	int[] arr = {1,2,3,5,6,3,2,5,1,32,32,3,4,4,1};
    	int actual = findNonRepeatedIntNoMap(arr);
    	assertEquals(6, actual);
    }
    
    public int findNonRepeatedIntNoMap(int[] intArray) {
    	Arrays.sort(intArray);
    	
    	for(int i = 1; i < intArray.length - 1; i++)	{
			if(intArray[i-1] != intArray[i] && intArray[i] != intArray[i+1])	{
				return intArray[i];
			}
    	}    	
    	throw new IllegalStateException("No non-repeated int found.");
    }

}
