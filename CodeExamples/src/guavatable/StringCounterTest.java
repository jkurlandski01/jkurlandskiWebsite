package guavatable;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class StringCounterTest {
	
	private static String generateRandomString()	{
		return UUID.randomUUID().toString();
	}

	@Test
	public void simpleCase() {
		List<String> input = Lists.newArrayList("aaa", "bbb", "aaa");
		StringCounter counter = new StringCounter(input);
		
		assertEquals(2, counter.getStringCount("aaa"));
		assertEquals(1, counter.getStringCount("bbb"));
		assertEquals(0, counter.getStringCount("ccc"));
		
		assertEquals(3, counter.getSize());
	}

	@Test
	public void loadTestStringCounter1MillionFails() {
		final int nbrReps = 1000000;
		
		List<String> input = Lists.newArrayList();
		
		for(int ct = 0; ct < nbrReps ; ct++)	{
			input.add("aaa");
			input.add("aaa");
			input.add("bbb");
			input.add(generateRandomString());
		}
		// Add some strings whose counts will be relatively low.
		
		StringCounter counter = new StringCounter(input);
		assertEquals(2 * nbrReps, counter.getStringCount("aaa"));
		assertEquals(nbrReps, counter.getStringCount("bbb"));
		assertEquals(0, counter.getStringCount("ccc"));
		
		assertEquals(4 * nbrReps, counter.getSize());
		
		// Iterate through all the elements.
		int count = 0;
		for(String str : counter.getElementSet())	{
			//System.out.println(str);
			count++;
		}
		//System.out.println(count);
	}

	// FIXME: This test takes a little over 30 seconds.
	@Ignore
	@Test
	public void loadTestStringCounter10Million() {
		final int nbrReps = 10000000;
		
		List<String> input = Lists.newArrayList();
		
		for(int ct = 0; ct < nbrReps ; ct++)	{
			input.add("aaa");
			input.add("aaa");
			input.add("bbb");
			input.add(generateRandomString());
		}
		// Add some strings whose counts will be relatively low.
		
		StringCounter counter = new StringCounter(input);
		assertEquals(2 * nbrReps, counter.getStringCount("aaa"));
		assertEquals(nbrReps, counter.getStringCount("bbb"));
		assertEquals(0, counter.getStringCount("ccc"));
		
		assertEquals(4 * nbrReps, counter.getSize());
		
		// Iterate through all the elements.
		int count = 0;
		for(String str : counter.getElementSet())	{
			//System.out.println(str);
			count++;
		}
		//System.out.println(count);
	}

	@Test
	public void pairElementsTestFails() {
		List<String> input = Lists.newArrayList("aaa", "bbb", "aaa", "ccc");
		Set<String> output = StringCounter.pairElements(input);
		
		Set<String> expected = Sets.newHashSet("aaa;bbb", "aaa;ccc", "bbb;ccc");
		
		assertEquals(expected, output);
	}

	@Test
	public void testGetHavingCt() {
		List<String> input = Lists.newArrayList("aaa", "bbb", "aaa", "ccc", "ccc", "ccc");
		StringCounter counter = new StringCounter(input);
		
		StringCounter actual = counter.getHavingCtGreaterThanOrEqual(3);
		assertEquals(3, actual.getSize());
		assertEquals(3, actual.getStringCount("ccc"));
		assertEquals(0, actual.getStringCount("aaa"));
		assertEquals(0, actual.getStringCount("bbb"));
		
		actual = counter.getHavingCtGreaterThanOrEqual(2);
		assertEquals(5, actual.getSize());
		assertEquals(3, actual.getStringCount("ccc"));
		assertEquals(2, actual.getStringCount("aaa"));
		assertEquals(0, actual.getStringCount("bbb"));
	}

}
