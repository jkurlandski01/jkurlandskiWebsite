package guavatable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

// JERRY: This should implement a general interface, which should include a remove method.
// insert( ), deleteRowByKey( ), deleteRowByColumnAndField( ), getTable( ), getFieldForKeyAndColumn( ), getRowForKey( ), 
// getSortedFieldsForColumn( ), getTableSortedByColumn( )
public class NewMovieTable {
	// JERRY: need string as well
    Ordering<Table.Cell<String, Column, Object>> doubleComparator =
            new Ordering<Table.Cell<String, Column, Object>>() {
        @Override
        public int compare(
                Table.Cell<String, Column, Object> cell1, 
                Table.Cell<String, Column, Object> cell2) {
            Double cell1Val = (Double) cell1.getValue();
            Double cell2Val = (Double) cell2.getValue();
            return cell1Val.compareTo(cell2Val);
        }
    };

    public enum Column {TITLE, DIRECTOR, COUNTRY, RUNTIME;
    	public void sortList(List<Object> list){
    		switch(this)	{
    		case RUNTIME:
    			list.stream()
    			  	.sorted((object1, object2) -> ((Integer)object1).compareTo(((Integer)object2)));
    			break;
    		default:
    			Collections.sort(list, (object1, object2) -> ((String)object1).compareTo(((String)object2)));
    		}
    	}
    	
    }
    
    private Table<String, Column, Object> table = HashBasedTable.create();
    
    private Column primaryKey = Column.TITLE;
    
    public void insert(String title, String director, String country, Integer runtime)   {
        table.put(title, Column.TITLE, title);
        table.put(title, Column.DIRECTOR, director);
        table.put(title, Column.COUNTRY, country);
        table.put(title, Column.RUNTIME, runtime);
    }
    
    public Object get(String key, Column col)	{
        Map<Column, Object> row = table.row(key);
        return row.get(col);
    }

    public Map<Column, Object> getRow(String title) {
        return table.row(title);
    }
    
   
    public List<Object> getSortedFieldsForColumn(Column col)	{
    	List<Object> result = Lists.newArrayList();
    	
		// JERRY: See getTableSortedByColumn( ) in original MovieTable

        		// We can sort fast on the primary key.
    	// JERRY: works only if primary key is a string
    	if (col.equals(primaryKey))		{
    		List<String> keys = Lists.newArrayList(table.rowKeySet());
    		Collections.sort(keys);
    		result.addAll(keys);
    	}
    	else	{
    	
    		// Slow sorting on other columns.
            List<Object> fields = Lists.newArrayList();
            
            Set<Cell<String, Column, Object>> cells = table.cellSet();
            List<Cell<String, Column, Object>> filteredList = Lists.newArrayList();
            for(Cell<String, Column, Object> cell : cells)  {
                if(cell.getColumnKey().equals(col))    {
                    filteredList.add(cell);
                }
            }
            
            // JERRY: trying comparator
            Collections.sort(filteredList, doubleComparator);
            
            for(Cell<String, Column, Object> cell : filteredList)   {
                Object obj = cell.getValue();
                fields.add(obj);
            }
            col.sortList(fields);
            result.addAll(fields);
    	}
    	
    	return result;

    }

}
