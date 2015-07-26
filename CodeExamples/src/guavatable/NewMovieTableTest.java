package guavatable;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import guavatable.NewMovieTable.Column;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class NewMovieTableTest {

    private NewMovieTable movieTable = new NewMovieTable();
    
    @Before 
    public void initializeFileTable() {
        movieTable.insert("Seventh Seal", "Ingmar Bergman", "Sweden", 140);
        movieTable.insert("Seven Samari", "Akira Kurosawa", "Japan", 130);
        movieTable.insert("Seven", "Andy Sidaris", "United States", 99);
     }

	@Test
	public void testGetCountry() {
		Object obj = movieTable.get("Seventh Seal", Column.COUNTRY);
		assertTrue(obj instanceof String);
		assertEquals("Sweden", (String) obj);
	}

	@Test
	public void testGetRuntime() {
		Object obj = movieTable.get("Seven", Column.RUNTIME);
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
        assertEquals(expected, movieTable.getRow("Seven Samari"));
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

}
