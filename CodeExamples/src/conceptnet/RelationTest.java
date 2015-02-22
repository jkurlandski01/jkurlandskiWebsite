package conceptnet;

import static org.junit.Assert.*;
import conceptnet.Relation;

import org.junit.Test;

public class RelationTest {
	
	@Test
	public void testToString() {
		Relation atLocation = Relation.AtLocation;  	
		assertEquals("is at", atLocation.toString());
	}
	
	@Test
	public void testName() {
		Relation atLocation = Relation.AtLocation; 	
		assertEquals("AtLocation", atLocation.name());
	}

	@Test
	public void testValues() {
		Relation[] values = Relation.values(); 	
		assertEquals(37, values.length);
	}

	@Test
	public void testValueOfMatch() {
		try {
			Relation relation = Relation.valueOf("AtLocation"); 	
			assertEquals(Relation.AtLocation, relation);
		} catch (IllegalArgumentException e) {
			System.out.println("Not a recognized Relation type.");
		}
	}
	
	@Test
	public void testValueOfNoMatch() {
		try {
			Relation.valueOf("KnownAs"); 	
	        // Note: Instead of failing the test here, consider using 
	        // @Test(expected=IllegalArgumentException.class)
			fail();
		} catch (IllegalArgumentException e) {
			System.out.println("Not a recognized Relation type.");
			assertTrue(true);
		}
	}

	@Test
	public void testValueOf2Parameter() {
		Relation relation = Enum.valueOf(Relation.class, "AtLocation"); 	
		assertEquals(Relation.AtLocation, relation);
	}
	
	@Test
	public void testIsRelationMatch()	{
		boolean actual = Relation.isRelation("Causes");
		assertEquals(true, actual);
	}

	@Test
	public void testIsRelationNoMatch()	{
		boolean actual = Relation.isRelation("KnownAs");
		assertEquals(false, actual);
	}

}

