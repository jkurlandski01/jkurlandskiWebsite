package guavaTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class CollectionsInstantiatingTest {

	@Test
	public void initializeHashMap() {
		Map<Integer, String> map = Maps.newHashMap();
		// JERRY: best way to create Integer?
		map.put(Integer.valueOf(1), "one");
		map.put(Integer.valueOf(2), "two");
		
		assertEquals(2, map.size());
		
		// Under Java 1.8.
		Map<Integer, String> hashMap1_8 = new HashMap<>();
		hashMap1_8.put(new Integer(1), "one");
		hashMap1_8.put(Integer.valueOf(2), "two");
		
		assertEquals(2, hashMap1_8.size());
	}
	
	@Test
	public void initializeTreeMap() {
		Map<Integer, String> map = Maps.newTreeMap();
		map.put(new Integer(1), "one");
		map.put(Integer.valueOf(2), "two");
		
		assertEquals(2, map.size());
	}
	
	@Test
	public void initializeHashSet()	{
		Set<String> diveForms = Sets.newHashSet("dive", "dives", "dived", "dived");
		
		assertEquals(3, diveForms.size());
	}

	@Test
	public void initializet()	{
		Set<String> diveForms1 = Sets.newHashSet("dive", "dives", "dived", "dived");
		Set<String> diveForms2 = Sets.newTreeSet(diveForms1);
		
		assertEquals(3, diveForms2.size());
	}

	@Test
	public void initializeArrayList()	{
		List<String> diveForms = Lists.newArrayList("dive", "dives", "dived", "dived");
		
		assertEquals(4, diveForms.size());
		
		// Old way.
	List<String> java1_8List = new ArrayList<>();
	java1_8List.add("dive");
	java1_8List.add("dives");
	java1_8List.add("dived");
	java1_8List.add("dived");
	
	assertEquals(4, java1_8List.size());

	List<String> java1_7List = new ArrayList<String>();
	java1_7List.add("dive");
	java1_7List.add("dives");
	java1_7List.add("dived");
	java1_7List.add("dived");
	
	assertEquals(4, java1_7List.size());
}

	public class Part	{
		String name;
		public Part(String part)	{ name = part; }
	}
	
	@Test
	public void initializeLinkedList()	{
		List<String> partList = Lists.newLinkedList();
		partList.add("body");
		partList.add("head");
		partList.add("nostril");
		
		partList.add(2, "nose");
		
		List<String> expected = Lists.newArrayList("body", "head", "nose", "nostril");		
		assertEquals(expected, partList);
	}

	@Test
	public void initializeLinkedListWithCollection()	{
		List<String> tempList = Lists.newArrayList("body", "head", "nose", "nostril");
		List<String> partList = Lists.newLinkedList(tempList);
		
		assertEquals(4, partList.size());
	}


	@Test
	public void testMapInitialize() {
    	Map<String, Double> map = Maps.newHashMap(ImmutableMap.of("one", new Double(1), "two", 
    			new Double(2), "three", new Double(3), "four", new Double(4)));
    	map.put("five", new Double(5));
    	map.put("one", new Double(4));
    	
//    	Map<String, Double> map1 = Maps. .newHashMap("one", new Double(1), "two", 
//    			new Double(2), "three", new Double(3), "four", new Double(3));
    	
    	Set<String> keys = map.keySet();
    	List<String> keyList = Lists.newArrayList(keys);
		Collections.sort(keyList);

		System.out.println("Printing the sorted key list.");
		System.out.println(keyList.toString());
		System.out.println("Printing the original map's keys.");
		System.out.println(map.keySet());

		// Test the sorted keys.
		String actual = keyList.get(0);
		assertEquals("five", actual);
		actual = keyList.get(1);
		assertEquals("four", actual);
		actual = keyList.get(2);
		assertEquals("one", actual);
		actual = keyList.get(3);
		assertEquals("three", actual);
		actual = keyList.get(4);
		assertEquals("two", actual);
	}


}
