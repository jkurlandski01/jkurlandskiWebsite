package guavaTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

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
		List<Part> partList = Lists.newLinkedList();
		partList.add(new Part("head"));
		partList.add(new Part("nose"));
		partList.add(new Part("nostril"));
		
		assertEquals(3, partList.size());
	}

	@Test
	public void initializeLinkedListWithCollection()	{
		List<Part> tempList = Lists.newArrayList(new Part("head"), new Part("nose"), new Part("nostril"));
		List<Part> partList = Lists.newLinkedList(tempList);
		
		assertEquals(3, partList.size());
	}


}
