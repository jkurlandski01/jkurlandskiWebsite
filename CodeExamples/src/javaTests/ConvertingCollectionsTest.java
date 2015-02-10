package javaTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ConvertingCollectionsTest {

	@Test
	public void setToList() {
		Set<String> set1 = Sets.newHashSet("London", "Tokyo", "Warsaw");
		List<String> list1 = Lists.newArrayList(set1);
		assertEquals(3, list1.size());
		
		// Under Java 1.8.
		Set<String> set2 = new HashSet<>();
	    set2.add("London");
	    set2.add("Tokyo");
	    set2.add("Warsaw");
	        
	    List<String> list2 = new ArrayList<>(set2);
		assertEquals(3, list2.size());
	}
	
	@Test
	public void listToSet() {
		List<String> list1 = Lists.newArrayList("London", "Tokyo", "Warsaw");
		Set<String> set1 = Sets.newHashSet(list1);
		assertEquals(3, set1.size());
		
		// Under Java 1.8.
		List<String> list2 = new ArrayList<>();
		list2.add("London");
		list2.add("Tokyo");
		list2.add("Warsaw");
	        
	    Set<String> set2 = new HashSet<>(list2);
		assertEquals(3, set2.size());
	}
	
	@Test
	public void arrayToList()	{
		String[] cityArray = {"Prague", "Minsk", "Barcelona"};
	    List<String> cityList = Arrays.asList(cityArray);
	    
	    assertEquals(3, cityList.size());
	}
	
	@Test
	public void listToArray()	{
		List<String> list1 = Lists.newArrayList("Amsterdam", "Chicago", "Mexico City");
		String[] array1 = list1.toArray((new String[list1.size()]));
	    
	    assertEquals(3, array1.length);
	}
	
	@Test
	public void setToArray()	{
		Set<String> set1 = Sets.newHashSet("London", "Beijing", "New Delhi");
		String[] array1 = set1.toArray((new String[set1.size()]));
	    
	    assertEquals(3, array1.length);
	}
	
	@Test
	public void arrayToSet()	{
		String[] cityArray = {"Prague", "Minsk", "Barcelona"};
	    Set<String> citySet = Sets.newHashSet(Arrays.asList(cityArray));
	    
	    assertEquals(3, citySet.size());
	}
}
