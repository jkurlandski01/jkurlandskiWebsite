package guavatable;

import guavatable.MovieTable.Column;

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
// deleteRowByColumnValue( ) 
//  Also needs to be made iterable.
// JERRY: you should probably familiarize yourself with the Table methods, to make sure your own
// methods are efficient.

/**
 * An in-memory table that can be sorted on any column.
 * Currently implemented: insert( ), getTableSortedByColumn( ), getRowForKey( ),
 * getFieldForKeyAndColumn( ), getTable( ), getRows( ), deleteRowByKey( )
 * Inefficient: getSortedFieldsForColumn( ) if not using key.
 */
public class MovieTable {
	// JERRY: works only on Integer
    public static Ordering<Table.Cell<String, Column, Object>> numericComparator =
            new Ordering<Table.Cell<String, Column, Object>>() {
        @Override
        public int compare(
                Table.Cell<String, Column, Object> cell1, 
                Table.Cell<String, Column, Object> cell2) {

          Integer cell1Val = (Integer) cell1.getValue();
          Integer cell2Val = (Integer) cell2.getValue();
            return cell1Val.compareTo(cell2Val);
        }
    };

    public static Ordering<Table.Cell<String, Column, Object>> stringComparator =
            new Ordering<Table.Cell<String, Column, Object>>() {
        @Override
        public int compare(
                Table.Cell<String, Column, Object> cell1, 
                Table.Cell<String, Column, Object> cell2) {
            String cell1Val = (String) cell1.getValue();
            String cell2Val = (String) cell2.getValue();
            return cell1Val.compareTo(cell2Val);
        }
    };
    
  public enum Column {TITLE, DIRECTOR, COUNTRY, RUNTIME;
    	public void sortColumnValuesList(List<Object> list){
    		switch(this)	{
    		case RUNTIME:
    			list.stream()
    			  	.sorted((object1, object2) -> ((Integer)object1).compareTo(((Integer)object2)));
    			break;
    		default:
    			Collections.sort(list, (object1, object2) -> ((String)object1).compareTo(((String)object2)));
    		}
    	}

		public void sortCellValuesList(List<Cell<String, Column, Object>> filteredList) {
    		switch(this)	{
    		case RUNTIME:
    			Collections.sort(filteredList, numericComparator);
    			break;
    		default:
    			Collections.sort(filteredList, stringComparator);
    		}
		}
    	
    }
    
    private Table<String, Column, Object> table = HashBasedTable.create();
    
    private Column primaryKey = Column.TITLE;
    
    public MovieTable( ) {
	}

    public MovieTable(MovieTable movieTable) {
		this.table = HashBasedTable.create(movieTable.getTable());
	}

	public void insert(String title, String director, String country, Integer runtime)   {
        table.put(title, Column.TITLE, title);
        table.put(title, Column.DIRECTOR, director);
        table.put(title, Column.COUNTRY, country);
        table.put(title, Column.RUNTIME, runtime);
    }
    
    /**
     * Return a defensive copy of this table. Modifying the returned table will not affect
     * this object.
     * @return
     */
    public Table<String, Column, Object> getTable()	{
    	return HashBasedTable.create(table);
    }
    
    public Object getFieldForKeyAndColumn(String key, Column col)	{
        Map<Column, Object> row = table.row(key);
        return row.get(col);
    }

    public Map<Column, Object> getRowForKey(String title) {
        return table.row(title);
    }
    
    public void deleteRowByColumnValue(Column column, Object value)	{
    	
    }
    
    /**
     * Return the entire table sorted on the given column.
     * @param col
     * @return
     */
    public List<Map<Column, Object>> getTableSortedByColumn(Column col)   {
    	Set<Cell<String, Column, Object>> cells = table.cellSet();
    	List<Cell<String, Column, Object>> filteredList = Lists.newArrayList();
    	for(Cell<String, Column, Object> cell : cells)  {
    		if(cell.getColumnKey().equals(col))    {
    			filteredList.add(cell);
    		}
    	}
    	
    	col.sortCellValuesList(filteredList);

    	List<Map<Column, Object>> retList = Lists.newArrayList();

    	for(Cell<String, Column, Object> cell : filteredList)   {
    		Map<Column, Object> row = table.row(cell.getRowKey());

    		retList.add(row);
    	}

    	return retList;
    }
    
   // JERRY: the non-key section of this method is inefficient because it does the work of getTableSortedByColumn( ) and
    // then iterates through one more time to extract just the values for the column. Perhaps
    // better to just provide getTableSortedByColumn( ) and force user to do the work himself?
    public List<Object> getSortedFieldsForColumn(Column col)	{
    	List<Object> result = Lists.newArrayList();
    	
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
            
            for(Cell<String, Column, Object> cell : filteredList)   {
                Object obj = cell.getValue();
                fields.add(obj);
            }
            col.sortColumnValuesList(fields);
            result.addAll(fields);
    	}
    	
    	return result;
    }

    /** 
     * Get all rows in no particular order.
     * @return
     */
	public Map<String, Map<Column, Object>> getRows() {
    	return table.rowMap();
	}

	public void deleteRowByKey(String key) {
		Set<String> keys = table.rowKeySet();
		keys.remove(key);
	}

}
