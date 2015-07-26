package guavatable;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Map;

import utils.DialogUtility;
import guavatable.MovieTableOld;
import guavatable.MovieTableOld.Column;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AtomicDouble;


public class MovieTableOldTest {
    
    private MovieTableOld movieTable = new MovieTableOld();
    
    @Before public void initializeFileTable() {
        movieTable.insert("Seventh Seal", "Ingmar Bergman", "Sweden");
        movieTable.insert("Seven Samari", "Akira Kurosawa", "Japan");
        movieTable.insert("Seven", "Andy Sidaris", "United States");
     }

    @Test
    public void testGetCellData()    {
        assertEquals("Ingmar Bergman", movieTable.getCellValue("Seventh Seal", Column.DIRECTOR));
        assertEquals("United States", movieTable.getCellValue("Seven", Column.COUNTRY));
        assertNull(movieTable.getCellValue("Magnificent Seven", Column.COUNTRY));
    }

    @Test
    public void testGetRow()    {
    	Map<Column, String> expected = Maps.newHashMap();
    	expected.put(Column.TITLE, "Seven Samari");
    	expected.put(Column.DIRECTOR, "Akira Kurosawa");
    	expected.put(Column.COUNTRY, "Japan");
        assertEquals(expected, movieTable.getRow("Seven Samari"));
    }

    @Test
    public void testGetSortedTableData()    {
    	List<String> expectedList = Lists.newArrayList("Japan", "Sweden", "United States");
        assertEquals(expectedList, movieTable.getSortedColumnData(MovieTableOld.Column.COUNTRY));
    }

    @Test
    public void testSortOnTitle()    {
    	List<String> expectedList = Lists.newArrayList("Seven", "Seven Samari", "Seventh Seal");
        assertEquals(expectedList, movieTable.getSortedColumnData(MovieTableOld.Column.TITLE));
    }

    @Test
    public void testGetSortedKeys()    {
    	List<String> expectedList = Lists.newArrayList("Seven", "Seven Samari", "Seventh Seal");
        assertEquals(expectedList, movieTable.getSortedKeys());
    }

    @Test
    public void testGetTableSortedByColumn()	{
    	List<Map<Column, String>> expectedList = Lists.newArrayList();
    	Map<Column, String> element = Maps.newHashMap(ImmutableMap.of(Column.TITLE, "Seven Samari", Column.DIRECTOR, "Akira Kurosawa", 
    			Column.COUNTRY, "Japan"));
    	expectedList.add(element);
    	element = Maps.newHashMap(ImmutableMap.of(Column.TITLE, "Seven", Column.DIRECTOR, "Andy Sidaris", 
    			Column.COUNTRY, "United States"));
    	expectedList.add(element);
    	element = Maps.newHashMap(ImmutableMap.of(Column.TITLE, "Seventh Seal", Column.DIRECTOR, "Ingmar Bergman", 
    			Column.COUNTRY, "Sweden"));
    	expectedList.add(element);
    	
    	List<Map<Column, String>> actual = movieTable.getTableSortedByColumn(Column.DIRECTOR);
    	assertEquals(expectedList, actual);
    }

}
