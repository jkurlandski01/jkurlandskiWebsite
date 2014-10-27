package guavatable;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import utils.DialogUtility;
import guavatable.FileTable;
import guavatable.FileTable.Column;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.util.concurrent.AtomicDouble;


public class FileTableTest {
    
    private FileTable fileTable = new FileTable();
    
    @Before public void initializeFileTable() {
        // Insert with File object.
        File myFile = new File("/tmp/test/one.pdf");
        fileTable.insert(myFile, 0.7, 0.7, 0.5, 0.5);
        fileTable.insert(myFile, Column.EMPTY_UTTERANCE_RATIO, "0.0004");
        
        // Insert all values.
        fileTable.insert("/tmp/test/two.txt", "txt", 100, 0.5, 0.3);
        fileTable.insert(new File("/tmp/test/two.txt"), Column.EMPTY_UTTERANCE_RATIO, "0.0003");
        fileTable.insert("/tmp/test/three.doc", "doc", 10, 0.2, 0.01);
        fileTable.insert(new File("/tmp/test/three.doc"), Column.EMPTY_UTTERANCE_RATIO, "0.0002");
     }

    @Test
    public void testSortOnSpaceAlphaRatio()    {
        List<String> info = fileTable.getNumericInfo(FileTable.Column.SPACE_ALPHA_RATIO);
        assertEquals("0.01 /tmp/test/three.doc", info.get(0));
        assertEquals("0.3 /tmp/test/two.txt", info.get(1));
        assertEquals("0.7 /tmp/test/one.pdf", info.get(2));
    }

    @Test
    public void testWriteFileLevelStats()    {
        String info = fileTable.getFileLevelStats();
        
        String expected = "File: /tmp/test/one.pdf" + DialogUtility.NEWLINE
                + "   Size: 0" + DialogUtility.NEWLINE
                + "   Empty utterance ratio: 0.0004" + DialogUtility.NEWLINE
                + "File: /tmp/test/three.doc" + DialogUtility.NEWLINE
                + "   Size: 10" + DialogUtility.NEWLINE
                + "   Empty utterance ratio: 0.0002" + DialogUtility.NEWLINE
                + "File: /tmp/test/two.txt" + DialogUtility.NEWLINE
                + "   Size: 100" + DialogUtility.NEWLINE
                + "   Empty utterance ratio: 0.0003" + DialogUtility.NEWLINE
                + "3 files successfully processed." + DialogUtility.NEWLINE;
        assertEquals(expected, info);
    }

    @Test
    public void testSortOnFilepath()    {
        List<String> info = fileTable.getSortedFilepathInfo();
        assertEquals("/tmp/test/one.pdf", info.get(0));
        assertEquals("/tmp/test/three.doc", info.get(1));
        assertEquals("/tmp/test/two.txt", info.get(2));
    }

    @Test
    public void testSortOnEmptyLineRatio()    {
        List<String> info = fileTable.getNumericInfo(Column.EMPTY_LINE_RATIO);
        assertEquals("0.2 /tmp/test/three.doc", info.get(0));
        assertEquals("0.5 /tmp/test/two.txt", info.get(1));
        assertEquals("0.7 /tmp/test/one.pdf", info.get(2));
    }

    @Test
    public void testSortOnSize()    {
        List<String> info = fileTable.getNumericInfo(Column.SIZE);
        assertEquals("0 /tmp/test/one.pdf", info.get(0));
        assertEquals("10 /tmp/test/three.doc", info.get(1));
        assertEquals("100 /tmp/test/two.txt", info.get(2));
    }

    @Test
    public void testCalcSpaceAlphaRatio()    {
        String input = "a b c d f";
        double actual = FileTable.calcSpaceAlphaRatio(input);
        assertEquals(0.44, actual, 0.01);
    }

    @Ignore
    @Test
    public void testCalcReplacementCharRatio()    {
        String input = "Well�";
        double actual = FileTable.calcReplacementCharRatio(input);
        assertEquals(0.2, actual, 0.01);
    }

    @Ignore
    @Test
    public void testCalcNonAsciiCharRatio()    {
        String input = "Well�";
        input = input + "\u05D0\u05D1" + ".";
        double actual = FileTable.calcNonAsciiCharRatio(input);
        assertEquals(0.375, actual, 0.01);
    }

    @Test
    public void testCalcBracesRatio()    {
        String input = "()[]{}<>xx";
        double actual = FileTable.calcBracesRatio(input);
        assertEquals(0.8, actual, 0.01);
    }

    @Test
    public void testCalcLineLevelStats()    {
        StringBuilder input = new StringBuilder();
        input.append("Header Information *").append(DialogUtility.NEWLINE);
        input.append("HANK: ").append(DialogUtility.NEWLINE);
        input.append("FRAN: (with a wistful smile) I remember?").append(DialogUtility.NEWLINE);
        input.append(DialogUtility.NEWLINE);
        input.append("FRAN: Boy, do I remember.").append(DialogUtility.NEWLINE);
        input.append(DialogUtility.NEWLINE);
        input.append("HANK: I'll BET you do!").append(DialogUtility.NEWLINE);
        input.append("FRAN: I do").append(DialogUtility.NEWLINE);
        input.append("HANK: I'll bet you do 222 * ").append(DialogUtility.NEWLINE);
        input.append("Footer Information ").append(DialogUtility.NEWLINE);
        
        AtomicDouble emptyLineRatio = new AtomicDouble(-1.0);
        AtomicDouble emptyUtteranceRatio = new AtomicDouble(-1.0);
        AtomicDouble oddEndOfLineCharRatio = new AtomicDouble(-1.0);
        AtomicDouble repeatedSpeakerRatio = new AtomicDouble(-1.0);
        AtomicDouble noSpeakerRatio = new AtomicDouble(-1.0);
        AtomicDouble mixedAllUpperInUtteranceRatio = new AtomicDouble(-1.0);
        FileTable.calcLineLevelStats(input.toString(), emptyLineRatio, emptyUtteranceRatio, oddEndOfLineCharRatio, repeatedSpeakerRatio, noSpeakerRatio, mixedAllUpperInUtteranceRatio);
        
        assertEquals(0.2, emptyLineRatio.get(), 0.01);
        assertEquals(0.1, repeatedSpeakerRatio.get(), 0.01);
        assertEquals(0.1, emptyUtteranceRatio.get(), 0.01);
        assertEquals(0.3, oddEndOfLineCharRatio.get(), 0.01);
        assertEquals(0.2, noSpeakerRatio.get(), 0.01);
        assertEquals(0.16, mixedAllUpperInUtteranceRatio.get(), 0.01);
    }

}
