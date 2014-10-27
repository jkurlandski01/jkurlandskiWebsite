/**
 * 
 */
package guavatable;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import com.google.common.util.concurrent.AtomicDouble;
import utils.DialogUtility;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import guavatable.FileTable.Column;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class stores information in a database table-like structure that allows sorting on the columns.
 */
public class MovieTable {
    
    /**
     * TODO: There may be better ways to do this...
     * Table<unique_filepath_as_string, column_name, value_as_string>
     * All values represented as string--have to be converted by comparators and getters/setters.
     */
    private Table<String, Column, String> table = HashBasedTable.create();
    
    public enum Column {TITLE, DIRECTOR, COUNTRY}
    
    public void insert(String title, String director, String country)   {
        table.put(title, Column.TITLE, title);
        table.put(title, Column.DIRECTOR, director);
        table.put(title, Column.COUNTRY, country);
    }
    
    Ordering<Table.Cell<String, Column, String>> doubleComparator =
            new Ordering<Table.Cell<String, Column, String>>() {
        @Override
        public int compare(
                Table.Cell<String, Column, String> cell1, 
                Table.Cell<String, Column, String> cell2) {
            Double cell1Val = new Double(cell1.getValue());
            Double cell2Val = new Double(cell2.getValue());
            return cell1Val.compareTo(cell2Val);
        }
    };
    
    Ordering<Table.Cell<String, Column, String>> stringComparator =
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
    
    public String getCellValue(String title, Column column) {
        Map<Column, String> row = table.row(title);
        return row.get(column);
    }

    public Map<Column, String> getRow(String title) {
        return table.row(title);
    }

    public List<String> getSortedKeys() {
        List<String> keys = Lists.newArrayList(table.rowKeySet());
        Collections.sort(keys);
        return keys;
    }
    
    public List<Map<Column, String>> getTableSortedByColumn(Column COLUMN_VAL)   {
    	// JERRY: make this a sorted map
    	List<Map<Column, String>> retList = Lists.newArrayList();

    	Set<Cell<String, Column, String>> cells = table.cellSet();
    	List<Cell<String, Column, String>> filteredList = Lists.newArrayList();
    	for(Cell<String, Column, String> cell : cells)  {
    		if(cell.getColumnKey().equals(COLUMN_VAL))    {
    			filteredList.add(cell);
    		}
    	}

    	Collections.sort(filteredList, stringComparator);
    	for(Cell<String, Column, String> cell : filteredList)   {
    		String str = cell.getValue();
    		Map<Column, String> row = table.row(cell.getRowKey());

    		retList.add(row);
    	}

    	return retList;
    }

    public List<String> getSortedColumnData(Column COLUMN_VAL)   {
        List<String> retList = Lists.newArrayList();
        
        Set<Cell<String, Column, String>> cells = table.cellSet();
        List<Cell<String, Column, String>> filteredList = Lists.newArrayList();
        for(Cell<String, Column, String> cell : cells)  {
            if(cell.getColumnKey().equals(COLUMN_VAL))    {
                filteredList.add(cell);
            }
        }
        
        for(Cell<String, Column, String> cell : filteredList)   {
            String str = cell.getValue();
            retList.add(str);
        }
        Collections.sort(retList);
        
        return retList;
    }

//    public String getFileLevelStats()    {
//        StringBuilder sBuild = new StringBuilder();
//        
//        List<String> keys = getSortedFilepathInfo();
//        for(String key : keys) {
//            sBuild.append("File: ").append(key).append(DialogUtility.NEWLINE);
//            Map<Column, String> row = table.row(key);
//            sBuild.append("   Size: ").append(row.get(Column.SIZE)).append(DialogUtility.NEWLINE);
//            sBuild.append("   Empty utterance ratio: ").append(row.get(Column.EMPTY_UTTERANCE_RATIO)).append(DialogUtility.NEWLINE);
//        }
//
//        sBuild.append(keys.size() + " files successfully processed.").append(DialogUtility.NEWLINE);
//        return sBuild.toString();
//    }
//
//    public String getFileSizeStats() {
//        List<String> info = getNumericInfo(Column.SIZE);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public String getEmptyLineStats() {
//        List<String> info = getNumericInfo(Column.EMPTY_LINE_RATIO);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public String getSpaceAlphaStats() {
//        List<String> info = this.getNumericInfo(Column.SPACE_ALPHA_RATIO);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public static double calcEmptyLineRatio(String contents) {
//        if(StringUtils.isBlank(contents)) {
//            return -1.0;
//        }
//        
//        String[] lines = contents.split(DialogUtility.NEWLINE);
//        int nbrLines = 0;
//        int nbrBlankLines = 0;
//        for(String line : lines)    {
//            nbrLines++;
//            if(StringUtils.isBlank(line))   {
//                nbrBlankLines++;
//            }
//        }
//        return ((double) nbrBlankLines) / nbrLines;
//    }
//
//    public static double calcLowercaseSpeakerNameRatio(String contents) {
//        if(StringUtils.isBlank(contents)) {
//            return -1.0;
//        }
//        
//        String[] lines = contents.split(DialogUtility.NEWLINE);
//        int nbrLines = 0;
//        int nbrOneTokenAllLowercaseLines = 0;
//        for(String line : lines)    {
//            nbrLines++;
//            
//            // Ignore lines ending in a period.
//            if(line.endsWith("."))  {
//                continue;
//            }
//            
//            // Ignore lines ending or starting with brackets/braces/parentheses. (Ignoring square brackets for the sake of simplicity.)
//            if(line.matches("^[(){}<>].*") || line.matches(".*[(){}<>]$"))  {
//                continue;
//            }        
//            
//            // See how many tokens on line.
//            // Trim and get rid of consecutive spaces.
//            String tempLine = line.trim().replaceAll(" +", " ");
//            if(StringUtils.isBlank(tempLine)) {
//                continue;
//            }
//
//            // Remove remaining spaces. If the difference > 1, there are more than two words on the line, so it is probably not a speaker.
//            long nbrCharsTotal = tempLine.length();
//            long nbrTargetCharsTotal = nbrCharsTotal - tempLine.replace(" ", "").length();
//            if(nbrTargetCharsTotal < 2) {
//                // Line has only one or two tokens. See if all lowercase.
//                String allLowerCase = tempLine.toLowerCase();
//                if(tempLine.equals(allLowerCase))    {
//                    nbrOneTokenAllLowercaseLines++;                    
//                }
//            }           
//        }
//        return ((double) nbrOneTokenAllLowercaseLines) / nbrLines;
//    }
//
//    public static void calcLineLevelStats(String contents, AtomicDouble emptyLineRatio, AtomicDouble emptyUtteranceRatio, AtomicDouble oddEndOfLineCharRatio, 
//            AtomicDouble repeatedSpeakerRatio, AtomicDouble noSpeakerRatio, AtomicDouble mixedAllUpperInUtteranceRatio) {
//        
//        if(StringUtils.isBlank(contents)) {
//            return;
//        }
//        
//        String[] lines = contents.split(DialogUtility.NEWLINE);
//        
//        int nbrLines = 0;
//        int nbrBlankLines = 0;
//        
//        String lastSpeakerName = "";
//        int nbrRepeatedSpeakers = 0;
//        
//        int nbrEmptyUtterances = 0;
//        int nbrOddCharsAtLineEnd = 0;
//        int nbrNoSpeakerLines = 0;
//        int nbrMixedAllUppercaseAndOtherCaseInUtteranceLines = 0;
//        
//        for(String line : lines)    {
//            nbrLines++;
//            if(StringUtils.isBlank(line))   {
//                nbrBlankLines++;
//                continue;
//            }
//            
//            // Check the last char at the end of the line.
//            line = line.replaceAll(" *$", "");
//            String lastChar = line.substring(line.length() - 1);
//            if(! lastChar.matches("[!?.a-zA-Z]"))   {
//                nbrOddCharsAtLineEnd++;
//            }
//            
//            // Split the speaker name from the utterance.
//            int idx = line.indexOf(":");
//            if(idx >= 0)    {
//                String speakerName = line.substring(0, idx);
//                String utterance = line.substring(idx + 1);
//                if(speakerName.equals(lastSpeakerName)) {
//                    nbrRepeatedSpeakers++;
//                }
//                else    {
//                    lastSpeakerName = speakerName;
//                }
//                
//                if(StringUtils.isBlank(utterance))  {
//                    nbrEmptyUtterances++;
//                }
//                else    {
//                    // Count the all-uppercase words and the words that are in mixed case in the utterance.
//                    int nbrUpperCase = 0;
//                    int nbrLowerCase = 0;
//                    int nbrMixedCase = 0;
//                    String[] words = utterance.split(" ");
//                    for(String word : words)    {
//                        // Possessives are often tacked onto the end of an all-capped name, e.g. "GLORIA's".
//                        word = word.replaceAll("'s$", "");
//                        if(word.isEmpty() || word.matches("[0-9]+") || word.length() < 2)    {
//                            continue;
//                        }
//                        if(word.equals(word.toUpperCase())) {
//                            nbrUpperCase++;
//                        }
//                        else if(word.equals(word.toLowerCase()))    {
//                            nbrLowerCase++;
//                        }
//                        else    {
//                            nbrMixedCase++;
//                        }
//                    }
//                    if(nbrUpperCase > 0 && (nbrLowerCase + nbrMixedCase > 0))   {
//                        nbrMixedAllUppercaseAndOtherCaseInUtteranceLines++;
//                    }
//                }
//            }
//            else    {
//                nbrNoSpeakerLines++;
//            }
//        }
//        
//        emptyLineRatio.set(((double) nbrBlankLines) / nbrLines);
//        repeatedSpeakerRatio.set(((double) nbrRepeatedSpeakers) / nbrLines);
//        emptyUtteranceRatio.set(((double) nbrEmptyUtterances) / nbrLines);
//        oddEndOfLineCharRatio.set(((double) nbrOddCharsAtLineEnd) / nbrLines);
//        noSpeakerRatio.set(((double) nbrNoSpeakerLines) / nbrLines);
//        
//        int nbrLinesOfDialog = nbrLines - nbrNoSpeakerLines - nbrBlankLines;
//        mixedAllUpperInUtteranceRatio.set(((double) nbrMixedAllUppercaseAndOtherCaseInUtteranceLines) / nbrLinesOfDialog);
//    }
//
//    public static double calcSpaceAlphaRatio(String contents) {
//        if(StringUtils.isBlank(contents)) {
//            return -1.0;
//        }
//        
//        long nbrCharsTotal = contents.length();
//        long nbrSpacesTotal = nbrCharsTotal - contents.replace(" ", "").length();
//        return ((double) nbrSpacesTotal) / nbrCharsTotal;
//    }
//
//    public static double calcReplacementCharRatio(String contents) {
//        if(StringUtils.isBlank(contents)) {
//            return -1.0;
//        }
//        
//        long nbrCharsTotal = contents.length();
//        long nbrTargetCharsTotal = nbrCharsTotal - contents.replace("\uFFFD", "").length();
//        return ((double) nbrTargetCharsTotal) / nbrCharsTotal;
//    }
//
//    public static double calcBracesRatio(String contents) {
//        if(StringUtils.isBlank(contents)) {
//            return -1.0;
//        }
//        
//        long nbrCharsTotal = contents.length();
//        long nbrTargetCharsTotal = nbrCharsTotal - contents.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("[(){}<>]", "").length();
//        return ((double) nbrTargetCharsTotal) / nbrCharsTotal;
//    }
//
//    public static double calcNonAsciiCharRatio(String contents) {
//        if(StringUtils.isBlank(contents)) {
//            return -1.0;
//        }
//        
//        long nbrCharsTotal = contents.length();
//        long nbrTargetCharsTotal = nbrCharsTotal - contents.replaceAll("[^\\p{ASCII}]", "").length();
//        return ((double) nbrTargetCharsTotal) / nbrCharsTotal;
//    }
//
//    public String getEmptyUtteranceStats() {
//        List<String> info = getNumericInfo(Column.EMPTY_UTTERANCE_RATIO);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public String getMixedAllUpperInUtteranceStats() {
//        List<String> info = getNumericInfo(Column.MIXED_ALLUPPER_UTTERANCE);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public String getOddEndOfLineStats() {
//        List<String> info = getNumericInfo(Column.ODD_END_OF_LINE_CHAR_RATIO);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    /**
//     * Get the sorted info for the given column using a sorting comparator suitable for numbers.
//     * @param COLUMN_VAL
//     * @return
//     */
//    public List<String> getNumericInfo(Column COLUMN_VAL)   {
//        List<String> retList = Lists.newArrayList();
//        
//        Set<Cell<String, Column, String>> cells = table.cellSet();
//        List<Cell<String, Column, String>> filteredList = Lists.newArrayList();
//        for(Cell<String, Column, String> cell : cells)  {
//            if(cell.getColumnKey().equals(COLUMN_VAL))    {
//                filteredList.add(cell);
//            }
//        }
//        
//        Collections.sort(filteredList, doubleComparator);
//        for(Cell<String, Column, String> cell : filteredList)   {
//            String str = cell.getValue() + " " + cell.getRowKey();
//            retList.add(str);
//        }
//        
//        return retList;
//    }
//
//    public String getRepeatedSpeakerStats() {
//        List<String> info = getNumericInfo(Column.REPEATED_SPEAKER_RATIO);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public String getReplacementCharStats() {
//        List<String> info = getNumericInfo(Column.REPLACEMENT_CHAR_RATIO);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public String getBracesStats() {
//        List<String> info = getNumericInfo(Column.BRACES_RATIO);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public String getNonAsciiCharStats() {
//        List<String> info = getNumericInfo(Column.NON_ASCII_CHAR_RATIO);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public String getNoSpeakerStats() {
//        List<String> info = getNumericInfo(Column.NO_SPEAKER_RATIO);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public String getLowercaseSpeakerNameRatio() {
//        List<String> info = getNumericInfo(Column.LOWERCASE_SPEAKER_NAME);
//        StringBuilder sBuild = new StringBuilder();
//        for(String str : info)  {
//            sBuild.append(str).append(DialogUtility.NEWLINE);
//        }
//        return sBuild.toString();
//    }
//
//    public String getSingleColumnStats(Column column) {
//        StringBuilder sBuild = new StringBuilder();
//        
//        List<String> keys = getSortedFilepathInfo();
//        for(String key : keys) {
//            sBuild.append(key).append(DialogUtility.NEWLINE);
//            Map<Column, String> row = table.row(key);
//            sBuild.append(row.get(column)).append(DialogUtility.NEWLINE);
//        }
//
//        sBuild.append(keys.size() + " files processed.").append(DialogUtility.NEWLINE);
//        return sBuild.toString();
//    }

}
