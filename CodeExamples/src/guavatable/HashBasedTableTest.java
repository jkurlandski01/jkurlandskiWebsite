package guavatable;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

public class HashBasedTableTest {

    //public enum Column {TITLE, DIRECTOR, COUNTRY}

    public static Ordering<Table.Cell<String, String, String>> stringComparator =
            new Ordering<Table.Cell<String, String, String>>() {
        @Override
        public int compare(
                Table.Cell<String, String, String> cell1, 
                Table.Cell<String, String, String> cell2) {
            String cell1Val = cell1.getValue();
            String cell2Val = cell2.getValue();
            return cell1Val.compareTo(cell2Val);
        }
    };

    @Test
	public void test() {
	    Table<String, String, String> table = HashBasedTable.create();
        table.put("Seventh Seal", "Title", "Seventh Seal");
        table.put("Seventh Seal", "Director", "Ingmar Bergman");
        table.put("Seventh Seal", "Country", "Sweden");
        table.put("Seven Samari", "Title", "Seven Samari");
        table.put("Seven Samari", "Director", "Akira Kurosawa");
        table.put("Seven Samari", "Country", "Japan");
        table.put("Seven", "Title", "Seven");
        table.put("Seven", "Director", "Andy Sidaris");
        table.put("Seven", "Country", "United States");
        
        // Iterate through all the table cells, getting only those in the Director column.
        // O(nbrRows X nbrColumns)
        // Elements of table.cellSet() include:
        // (Seven Samari,Country)=Japan; (Seven Samari,Title)=Seven Samari; (Seven Samari,Director)=Akira Kurosawa
    	List<Cell<String, String, String>> filteredList = Lists.newArrayList();
    	Set<Cell<String, String, String>> cells = table.cellSet();
    	for(Cell<String, String, String> cell : cells)  {
    		if(cell.getColumnKey().equals("Director"))    {
    			filteredList.add(cell);
    		}
    	}
    	// Sort the filtered Director cells.
    	// O(nbrRows)
    	Collections.sort(filteredList, stringComparator);
    	
    	// For the fun of it, put it all into a big string and output the string.
    	StringBuilder strBuilder = new StringBuilder();
    	for(Cell<String, String, String> cell : filteredList) {
    		Map<String, String> row = table.row(cell.getRowKey());
    		strBuilder.append(row.get("Title")).append(", ");
    		strBuilder.append(row.get("Director")).append(", ");
    		strBuilder.append(row.get("Country")).append("\n");
    	}
    	System.out.println(strBuilder.toString());


    	// O(nbrRows)
    	List<Map<String, String>> sortedRowsList = Lists.newArrayList();
    	for(Cell<String, String, String> cell : filteredList)   {
    		Map<String, String> row = table.row(cell.getRowKey());
    		sortedRowsList.add(row);
    	}
    	
    	List<Map<String, String>> expectedList = Lists.newArrayList();
    	Map<String, String> element = Maps.newHashMap(ImmutableMap.of("Title", "Seven Samari", "Director", "Akira Kurosawa", 
    			"Country", "Japan"));
    	expectedList.add(element);
    	element = Maps.newHashMap(ImmutableMap.of("Title", "Seven", "Director", "Andy Sidaris", 
    			"Country", "United States"));
    	expectedList.add(element);
    	element = Maps.newHashMap(ImmutableMap.of("Title", "Seventh Seal", "Director", "Ingmar Bergman", 
    			"Country", "Sweden"));
    	expectedList.add(element);
    	
    	assertEquals(expectedList, sortedRowsList);
	}

//    @Test
//	public void test2() {
//	    Table<String, String, String> table = HashBasedTable.create();
//        table.put("Seventh Seal", "Title", "Seventh Seal");
//        table.put("Seventh Seal", "Director", "Ingmar Bergman");
//        table.put("Seventh Seal", "Country", "Sweden");
//        table.put("Seven Samari", "Title", "Seven Samari");
//        table.put("Seven Samari", "Director", "Akira Kurosawa");
//        table.put("Seven Samari", "Country", "Japan");
//        table.put("Seven", "Title", "Seven");
//        table.put("Seven", "Director", "Andy Sidaris");
//        table.put("Seven", "Country", "United States");
//        
//        Set<String> set = table.columnKeySet();
//        for(String str : set)	{
//        	System.out.println(str);
//        }
//        Map<String, Map<String, String>> map = table.columnMap();
//        Set<String> set2 = map.keySet();
//        
//        // Iterate through all the table cells, getting only those in the Director column.
//        // O(n), where n is the number of cells = nbrRows X nbrColumns
//        // Set<Cell<String, String, String>> is what? Give ex.
//    	List<Cell<String, String, String>> filteredList = Lists.newArrayList();
//    	Set<Cell<String, String, String>> cells = table.cellSet();
//    	for(Cell<String, String, String> cell : cells)  {
//    		if(cell.getColumnKey().equals("Director"))    {
//    			filteredList.add(cell);
//    		}
//    	}
//    	// Sort the filtered Director cells.
//    	// O(m), where m is the number of columns.
//    	Collections.sort(filteredList, stringComparator);
//
//    	// O(m), where m is the number of columns.
//    	List<Map<String, String>> sortedRowsList = Lists.newArrayList();
//    	for(Cell<String, String, String> cell : filteredList)   {
//    		Map<String, String> row = table.row(cell.getRowKey());
//    		sortedRowsList.add(row);
//    	}
//    	
//    	List<Map<String, String>> expectedList = Lists.newArrayList();
//    	Map<String, String> element = Maps.newHashMap(ImmutableMap.of("Title", "Seven Samari", "Director", "Akira Kurosawa", 
//    			"Country", "Japan"));
//    	expectedList.add(element);
//    	element = Maps.newHashMap(ImmutableMap.of("Title", "Seven", "Director", "Andy Sidaris", 
//    			"Country", "United States"));
//    	expectedList.add(element);
//    	element = Maps.newHashMap(ImmutableMap.of("Title", "Seventh Seal", "Director", "Ingmar Bergman", 
//    			"Country", "Sweden"));
//    	expectedList.add(element);
//    	
//    	assertEquals(expectedList, sortedRowsList);
//	}
//
}
