package interviews.facetoface;

import java.util.Arrays;
import java.util.HashMap;
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

public class FindNonRepeatedNumber_JTian {

	
	public int findNonRepeatedInt(int[] intArray) {
	    if(intArray.length  == 0)return 0;
	    
	   int[] bucket = new int[Integer.MAX_VALUE];
	   
	   for(int i = 0;i<intArray.length;i++){
	       bucket[intArray[i]]++;
	   }
	   
	   for(int i = 0;i<bucket.length;i++){
	           if(bucket[i] == 1)
	           return i;
	   }
	   
	   IllegalStateException e = new IllegalStateException();
	   throw e;
	   
	    
	}

}
