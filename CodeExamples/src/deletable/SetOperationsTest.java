package deletable;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;


public class SetOperationsTest
{

	
	@Test
	public void testTrueSetAddAll() {
		Set<String> set1 = Sets.newHashSet("a", "b", "c");
		Set<String> set2 = Sets.newHashSet("c", "d", "e");
		
		// Union
		Set<String> setUnion = Sets.newHashSet(set1);
		setUnion.addAll(set2);
		
		// Test the results.
		assertEquals(Sets.newHashSet("a", "b", "c", "d", "e"), setUnion);
		assertEquals(Sets.newHashSet("a", "b", "c"), set1);
		assertEquals(Sets.newHashSet("c", "d", "e"), set2);
	}

	@Test
	public void testTrueSetGuavaUnion() {
		Set<String> set1 = Sets.newHashSet("a", "b", "c");
		Set<String> set2 = Sets.newHashSet("c", "d", "e");
		
		// Union
		Set<String> setUnion = Sets.union(set1, set2);
		
		// Test the results.
		assertEquals(Sets.newHashSet("a", "b", "c", "d", "e"), setUnion);
	}

	@Test
	public void testTrueSetRetainAll() {
		Set<String> set1 = Sets.newHashSet("a", "b", "c");
		Set<String> set2 = Sets.newHashSet("c", "d", "e");
		
		// Intersection
		Set<String> setIntersection1 = Sets.newHashSet(set1);
		setIntersection1.retainAll(set2);	
		assertEquals(Sets.newHashSet("c"), setIntersection1);
		
		// Verify commutativity
		Set<String> setIntersection2 = Sets.newHashSet(set2);
		setIntersection2.retainAll(set1);	
		assertEquals(Sets.newHashSet("c"), setIntersection2);
	}

	@Test
	public void testTrueSetGuavaIntersection() {
		Set<String> set1 = Sets.newHashSet("a", "b", "c");
		Set<String> set2 = Sets.newHashSet("c", "d", "e");
		
		// Intersection
		Set<String> setIntersection1 = Sets.intersection(set1, set2);
		assertEquals(Sets.newHashSet("c"), setIntersection1);
		
		// Verify commutativity
		Set<String> setIntersection2 = Sets.intersection(set2, set1);
		assertEquals(Sets.newHashSet("c"), setIntersection2);
	}

}
