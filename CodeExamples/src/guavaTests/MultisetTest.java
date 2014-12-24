package guavaTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

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


}
