package deletable;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.*;
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

	@Test
	public void testListAddAll() {
		List<String> list1 = Lists.newArrayList("a", "b", "c");
		List<String> list2 = Lists.newArrayList("c", "c", "d", "e");
		
		// Union
		List<String> listUnion = Lists.newArrayList(list1);
		listUnion.addAll(list2);
		
		// Test the results.
		List<String> expectedList = Lists.newArrayList("a", "b", "c", "c", "d", "e");
		Collections.sort(expectedList);
		Collections.sort(listUnion);
		assertEquals(expectedList, listUnion);

		// Verify that the operation is commutative.
		List<String> listUnion2 = Lists.newArrayList(list2);
		listUnion2.addAll(list1);
		Collections.sort(listUnion2);
		assertEquals(expectedList, listUnion2);
	}

	@Test
	public void testListRetainAll() {
		List<String> list1 = Lists.newArrayList("a", "b", "c");
		List<String> list2 = Lists.newArrayList("c", "c", "d", "e");
		
		// Intersection
		List<String> listIntersection = Lists.newArrayList(list1);
		listIntersection.retainAll(list2);
		
		// Test the results.
		assertEquals(Lists.newArrayList("c"), listIntersection);

		// Verify that the operation is commutative.
		List<String> listIntersection2 = Lists.newArrayList(list2);
		listIntersection2.retainAll(list1);
		assertEquals(Lists.newArrayList("c"), listIntersection2);
	}

	@Test
	public void testMultisetIntersection() {
		List<String> list1 = Lists.newArrayList("a", "b", "c", "c");
		Multiset<String> multiset1 = HashMultiset.create(list1);
		List<String> list2 = Lists.newArrayList("c", "d", "e");
		Multiset<String> multiset2 = HashMultiset.create(list2);
		
		// Intersection
		Multiset<String> multisetIntersection = Multisets.intersection(multiset1, multiset2);
		
		// Test the results.
		Multiset<String> expectedMultiset = HashMultiset.create(Lists.newArrayList("c"));
		assertEquals(expectedMultiset, multisetIntersection);

		// Verify that the operation is commutative.
		Multiset<String> multisetIntersection2 = Multisets.intersection(multiset2, multiset1);
		assertEquals(expectedMultiset, multisetIntersection2);
	}

	@Test
	public void testMultisetUnion() {
		List<String> list1 = Lists.newArrayList("a", "b", "c", "c");
		Multiset<String> multiset1 = HashMultiset.create(list1);
		List<String> list2 = Lists.newArrayList("c", "d", "e");
		Multiset<String> multiset2 = HashMultiset.create(list2);
		
		// Union
		Multiset<String> multisetUnion = Multisets.union(multiset1, multiset2);
		
		// Test the results.
		Multiset<String> expectedMultiset = HashMultiset.create(Lists.newArrayList("a", "b", "c", "c", "d", "e"));
		assertEquals(expectedMultiset, multisetUnion);

		// Verify that the operation is commutative.
		Multiset<String> multisetUnion2 = Multisets.union(multiset2, multiset1);
		assertEquals(expectedMultiset, multisetUnion2);
	}

}
