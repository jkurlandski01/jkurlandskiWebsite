package deletable;

import static org.junit.Assert.*;

import guavatable.MovieTable.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class deletableTest {

	@Test
	public void testTreeMap() {
		TreeMap<String, Double> map1 = Maps.newTreeMap();
		map1.put("one", new Double(1.0));
		map1.put("two", new Double(2.0));
		map1.put("three", new Double(3.0));
		
		// Convert to list for convenience.
		List<String> keyList = Lists.newArrayList(map1.keySet());
		
		Double actual = map1.get(keyList.get(0));
		assertEquals(1.0, actual, 0.0);
		actual = map1.get(keyList.get(1));
		assertEquals(3.0, actual, 0.0);
		actual = map1.get(keyList.get(2));
		assertEquals(2.0, actual, 0.0);
		
	}

	@Test
	public void testKeysetToArrayAndList() {
		Map<String, Double> map1 = Maps.newTreeMap();
		map1.put("one", new Double(1.0));
		map1.put("two", new Double(2.0));
		map1.put("three", new Double(3.0));
		
    	Map<String, Double> map2 = Maps.newHashMap(ImmutableMap.of("one", new Double(1), "two", 
    			new Double(2), "three", new Double(3)));
    	
    	Set<String> keys = map2.keySet();
		String[] keysArray = keys.toArray((new String[keys.size()]));
		Arrays.sort(keysArray);
    	List<String> keysList = Arrays.asList(keysArray);
		Collections.sort(keysList);

		Double actual = map2.get(keysList.get(0));
		assertEquals(1.0, actual, 0.0);
		actual = map2.get(keysList.get(1));
		assertEquals(3.0, actual, 0.0);
		actual = map2.get(keysList.get(2));
		assertEquals(2.0, actual, 0.0);
		
	}

}
