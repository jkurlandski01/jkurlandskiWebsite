package guavatable;

import guavatable.MovieTableOld.Column;

import com.google.common.collect.Ordering;
import com.google.common.collect.Table;

public class Utils {

    public static Ordering<Table.Cell<String, Column, String>> stringComparator =
            new Ordering<Table.Cell<String, Column, String>>() {
        @Override
        public int compare(
                Table.Cell<String, Column, String> cell1, 
                Table.Cell<String, Column, String> cell2) {
            String cell1Val = cell1.getValue();
            String cell2Val = cell2.getValue();
            return cell1Val.compareTo(cell2Val);
        }
    };
    
}
