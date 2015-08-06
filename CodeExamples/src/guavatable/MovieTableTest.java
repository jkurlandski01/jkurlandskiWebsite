package guavatable;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import guavatable.MovieTable.Column;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class MovieTableTest {

    private MovieTable movieTable = new MovieTable();
    
    @Before 
    public void initializeFileTable() {
        movieTable.insert("Seventh Seal", "Ingmar Bergman", "Sweden", 140);
        movieTable.insert("Seven Samari", "Akira Kurosawa", "Japan", 130);
        movieTable.insert("Seven", "Andy Sidaris", "United States", 99);
     }

	@Test
	public void testGetCountry() {
		Object obj = movieTable.getFieldForKeyAndColumn("Seventh Seal", Column.COUNTRY);
		assertTrue(obj instanceof String);
		assertEquals("Sweden", (String) obj);
	}

	@Test
	public void testGetRuntime() {
		Object obj = movieTable.getFieldForKeyAndColumn("Seven", Column.RUNTIME);
		assertTrue(obj instanceof Integer);
		assertEquals(99, ((Integer) obj).intValue());
	}

    @Test
    public void testGetRow()    {
    	Map<Column, Object> expected = Maps.newHashMap();
    	expected.put(Column.TITLE, "Seven Samari");
    	expected.put(Column.DIRECTOR, "Akira Kurosawa");
    	expected.put(Column.COUNTRY, "Japan");
    	expected.put(Column.RUNTIME, 130);
        assertEquals(expected, movieTable.getRowForKey("Seven Samari"));
    }

    @Test
    public void testSortOnTitle()    {
    	List<Object> expectedList = Lists.newArrayList();
    	expectedList.add("Seven");
    	expectedList.add("Seven Samari");
    	expectedList.add("Seventh Seal");
        assertEquals(expectedList, movieTable.getSortedFieldsForColumn(Column.TITLE));
    }

    @Test
    public void testSortOnDirector()    {
    	List<Object> expectedList = Lists.newArrayList();
    	expectedList.add("Akira Kurosawa");
    	expectedList.add("Andy Sidaris");
    	expectedList.add("Ingmar Bergman");
        assertEquals(expectedList, movieTable.getSortedFieldsForColumn(Column.DIRECTOR));
    }

    @Test
    public void testSortOnRuntime()    {
    	List<Object> expectedList = Lists.newArrayList();
    	expectedList.add(Integer.valueOf(99));
    	expectedList.add(Integer.valueOf(130));
    	expectedList.add(Integer.valueOf(140));
        assertEquals(expectedList, movieTable.getSortedFieldsForColumn(Column.RUNTIME));
    }
    
    @Test
    public void testGetTableSortedByDirectorsColumn()	{
    	List<Map<Column, Object>> expectedList = Lists.newArrayList();
    	Map<Column, Object> element = Maps.newHashMap(ImmutableMap.of(Column.TITLE, "Seven Samari", Column.DIRECTOR, "Akira Kurosawa", 
    			Column.COUNTRY, "Japan", Column.RUNTIME, 130));
    	expectedList.add(element);
    	element = Maps.newHashMap(ImmutableMap.of(Column.TITLE, "Seven", Column.DIRECTOR, "Andy Sidaris", 
    			Column.COUNTRY, "United States", Column.RUNTIME, 99));
    	expectedList.add(element);
    	element = Maps.newHashMap(ImmutableMap.of(Column.TITLE, "Seventh Seal", Column.DIRECTOR, "Ingmar Bergman", 
    			Column.COUNTRY, "Sweden", Column.RUNTIME, 140));
    	expectedList.add(element);
    	
    	List<Map<Column, Object>> actual = movieTable.getTableSortedByColumn(Column.DIRECTOR);
    	assertEquals(expectedList, actual);
    }

    @Test
    public void testGetTableSortedByRuntimeColumn()	{
    	List<Map<Column, Object>> expectedList = Lists.newArrayList();
    	Map<Column, Object> element = Maps.newHashMap(ImmutableMap.of(Column.TITLE, "Seven", Column.DIRECTOR, "Andy Sidaris", 
    			Column.COUNTRY, "United States", Column.RUNTIME, 99));
    	expectedList.add(element);
       	element = Maps.newHashMap(ImmutableMap.of(Column.TITLE, "Seven Samari", Column.DIRECTOR, "Akira Kurosawa", 
    			Column.COUNTRY, "Japan", Column.RUNTIME, 130));
    	expectedList.add(element);
    	element = Maps.newHashMap(ImmutableMap.of(Column.TITLE, "Seventh Seal", Column.DIRECTOR, "Ingmar Bergman", 
    			Column.COUNTRY, "Sweden", Column.RUNTIME, 140));
    	expectedList.add(element);
    	
    	List<Map<Column, Object>> actual = movieTable.getTableSortedByColumn(Column.RUNTIME);
    	assertEquals(expectedList, actual);
    }

    @Test
    public void testGetTable()	{
    	Table<String, Column, Object> copy = movieTable.getTable();
    	assertEquals(12, copy.size());
    	
    	copy.clear();
    	assertEquals(0, copy.size());
    	
    	// Verify that our original movie table is untouched.
    	assertEquals(12, movieTable.getTable().size());

    }

    @Test
    public void testGetRows()	{
    	Map<String, Map<Column, Object>> actual = movieTable.getRows();
    	assertEquals(3, actual.size());
    	
    	Set<String> expectedKeys = Sets.newHashSet("Seven", "Seven Samari", "Seventh Seal");
    	assertEquals(expectedKeys, actual.keySet());
    }

    @Test
    public void testDeleteRowByKeyAndCopyConstructor()	{
    	assertEquals(3, movieTable.getRows().size());
    	
    	// Test completeness of copy constructor.
    	MovieTable movieTableCopy = new MovieTable(movieTable);
    	assertEquals(3, movieTableCopy.getRows().size());
    	
    	// Verify one row has been deleted.
    	movieTableCopy.deleteRowByKey("Seven Samari");
    	assertEquals(2, movieTableCopy.getRows().size());
    	Set<String> expectedKeys = Sets.newHashSet("Seven", "Seventh Seal");
    	assertEquals(expectedKeys, movieTableCopy.getRows().keySet());

    	// Verify copy constructor has created a deep copy.
    	assertEquals(3, movieTable.getRows().size());
    	
    }

    // JERRY: stopped here
//    @Test
//    public void testDeleteRowByColumnValue()	{
//    	assertEquals(3, movieTable.getRows().size());
//    	
//    	// Verify one row has been deleted.
//    	movieTableCopy.deleteRowByKey("Seven Samari");
//    	assertEquals(2, movieTableCopy.getRows().size());
//    	Set<String> expectedKeys = Sets.newHashSet("Seven", "Seventh Seal");
//    	assertEquals(expectedKeys, movieTableCopy.getRows().keySet());
//    }
}
