package deletable;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

public class MultimapTest {

	private Map<String, List<String>> getHashMap() {
		Map<String, List<String>> map = Maps.newHashMap();
		List<String> diveForms = Lists.newArrayList("dive", "dives", "dived");
		map.put("dive", diveForms);
		return map;
	}

	@Test
	public void testOldWay() {
		// Get the map.
		Map<String, List<String>> map = getHashMap();
		
		// Carefully add a new value to the list.
		List<String> diveForms = map.get("dive");
		diveForms.add("dove");
		// Put not necessary, since we modified a reference. But invoking put( ) will not cause anything to break.
		// map.put("dive", diveForms);
		
		// Test passes.
		assertEquals(4, map.get("dive").size());
		
		// Oops! Overwriting the old list because you forgot to update the list instead of the map.
		List<String> moreDiveForms = Lists.newArrayList("diveth");
		map.put("dive", moreDiveForms);
		
		// Test fails.
		assertEquals(5, map.get("dive").size());		
	}
	
	private Multimap<String, String> getMultimap() {
		Multimap<String, String> mm = ArrayListMultimap.create();
		List<String> diveForms = Lists.newArrayList("dive", "dives", "dived");
		mm.putAll("dive", diveForms);
		return mm;
	}

	@Test
	public void testNewWay() {
		Multimap<String, String> mm = getMultimap();
		mm.put("dive", "dove");
		
		// Test passes.
		assertEquals(4, mm.get("dive").size());
		
		// Add a new value to the list.
		mm.put("dive", "diveth");
		
		// Test passes.
		assertEquals(5, mm.get("dive").size());
	}
	
	@Test
	public void testArrayListMultimap() {
		Multimap<String, String> mm = ArrayListMultimap.create();
		mm.put("ask", "ask");
		mm.put("ask", "asks");
		mm.put("ask", "asked");
		List<String> beList = Lists.newArrayList("am", "are", "is", "was", "were");
		mm.putAll("be", beList);

		// Iterate through all the entries
		for(Entry<String, String> mapPair : mm.entries())  {
		    String key = mapPair.getKey();
		    String value = mapPair.getValue();
		    System.out.println("key = " + key + "; value = " + value);
		}

		System.out.println("Write using toString():");
		System.out.println(mm.toString());
		
		// Getting all entries when you don't care about the key.
		Collection<String> valuesAsCollection = mm.values();
		List<String> valuesAsList = Lists.newArrayList(valuesAsCollection);
		Collections.sort(valuesAsList);
		System.out.println(valuesAsList);
		
		// Testing the values for the key "be".
		// containsKey( )
		assertTrue(mm.containsKey("be"));
		// get( )
		Collection<String> beForms = mm.get("be");
		assertEquals(5, beForms.size());
		// containsEntry( )
		assertTrue(mm.containsEntry("be", "was"));
		// keySet( )
		Set<String> keys = mm.keySet();
		assertEquals(2, keys.size());
		
		
		Multiset<String> multiset = mm.keys();
		int size = multiset.size();
		assertEquals(8, size);
		assertEquals(5, multiset.count("be"));
		assertEquals(3, multiset.count("ask"));
		
	}

	@Test
	public void testHashsetMultimap() {
		Multimap<String, String> mm = HashMultimap.create();
		mm.put("ask", "ask");
		mm.put("ask", "asks");
		mm.put("ask", "asked");
		List<String> beList = Lists.newArrayList("am", "are", "is", "was", "were");
		mm.putAll("be", beList);

		// Iterate through all the entries
		for(Entry<String, String> mapPair : mm.entries())  {
		    String key = mapPair.getKey();
		    String value = mapPair.getValue();
		    System.out.println("key = " + key + "; value = " + value);
		}

		System.out.println("Write using toString():");
		System.out.println(mm.toString());
		
		// Getting all entries when you don't care about the key.
		Collection<String> valuesAsCollection = mm.values();
		List<String> valuesAsList = Lists.newArrayList(valuesAsCollection);
		Collections.sort(valuesAsList);
		System.out.println(valuesAsList);
		
		// Testing the values for the key "be".
		// containsKey( )
		assertTrue(mm.containsKey("be"));
		// get( )
		Collection<String> beForms = mm.get("be");
		assertEquals(5, beForms.size());
		// containsEntry( )
		assertTrue(mm.containsEntry("be", "was"));
		// keySet( )
		Set<String> keys = mm.keySet();
		assertEquals(2, keys.size());
		
		
		Multiset<String> multiset = mm.keys();
		int size = multiset.size();
		assertEquals(8, size);
		assertEquals(5, multiset.count("be"));
		assertEquals(3, multiset.count("ask"));
		
	}

}
