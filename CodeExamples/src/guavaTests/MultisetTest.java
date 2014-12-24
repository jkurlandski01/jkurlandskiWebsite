package guavaTests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;

public class MultisetTest {
	
	@Test
	public void simpleCase() {
		List<String> input = Lists.newArrayList("aaa", "bbb", "aaa");
		Multiset<String> multiset = HashMultiset.create(input);
		multiset.add("ddd");
		
		assertEquals(2, multiset.count("aaa"));
		assertEquals(1, multiset.count("bbb"));
		assertEquals(0, multiset.count("ccc"));
		assertEquals(1, multiset.count("ddd"));
		
		assertEquals(4, multiset.size());
	}

	@Test
	public void additionalFunctionality() {
		List<String> input = Lists.newArrayList("aaa", "bbb", "aaa");
		Multiset<String> multiset = HashMultiset.create(input);
		multiset.addAll(input);
		
		// Quick count check.
		assertEquals(4, multiset.count("aaa"));
		assertEquals(2, multiset.count("bbb"));
		assertEquals(0, multiset.count("ccc"));

		// Verify elementSet( ).
		Set<String> expectedEntrySet = Sets.newHashSet("aaa", "bbb");
		assertEquals(expectedEntrySet, multiset.elementSet());
		
		// Verify remove(Object, int).
		multiset.remove("aaa", 3);
		assertEquals(1, multiset.count("aaa"));
		
		// Verify remove(Object).
		multiset.remove("bbb");
		assertEquals(1, multiset.count("bbb"));
		
		// Verify setCount().
		multiset.setCount("ccc", 10);
		assertEquals(10, multiset.count("ccc"));
	}

	@Test
	public void entrySetAndToString() {
		List<String> input = Lists.newArrayList("aaa", "bbb", "aaa", "ccc");
		Multiset<String> multiset = HashMultiset.create(input);
		multiset.addAll(input);
		
		assertEquals(8, multiset.size());
		
		System.out.println(multiset);
		
		for(Multiset.Entry<String> entrySet : multiset.entrySet())	{
			String key = entrySet.getElement();
			int count = entrySet.getCount();
			System.out.println(key + ": " + count);
		}
	}


}
