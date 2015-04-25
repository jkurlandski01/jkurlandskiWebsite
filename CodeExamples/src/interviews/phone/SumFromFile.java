package interviews.phone;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

/**
 * Given a file containing integers, returns the sum of all integers in the file.
 */

public class SumFromFile {

	private static final String PROJECT_PATH = (new File("")).getAbsoluteFile().getAbsolutePath();
	private static final File RESOURCE_FOLDER = new File(PROJECT_PATH, "/src/resources");
	private static final File INPUT_FILE = new File(RESOURCE_FOLDER, "SumFromFile_Input.txt");
	
	File inFile;

	public static void main(String[] args) {

	}
	
	private void setFile(File in)	{
		inFile = in;
	}
	
	// Approaches 1 and 2: Using pre-Java 7 way of reading file, and 1.7's readAllBytes.
	
	@Test
	public void test() throws IOException {
		SumFromFile sff = new SumFromFile();
		sff.setFile(INPUT_FILE);
		
		int actual = sff.sum();
		assertEquals(1, actual);
	}

	private int sum( ) throws IOException {
		String str = getFileContents(inFile);
		
		String[] lines = str.split(System.getProperty("line.separator"));
		int total = 0;
		for(String line : lines)	{
			int val;
			try {
				val = new Integer(line).intValue();
				total += val;
			} catch (NumberFormatException e) {
				//e.printStackTrace();
				//throw new IOException(e.getMessage());
			}
		}
		return total;
	}
	
	/**
	 * Return the contents of the file as a string.
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException if file is null or doesn't exist
	 */
	public static String getFileContents(File file) throws IOException	{
		//return getFileContents_OldFashioned(file);
		return getFileContents_1_7(file, Charset.defaultCharset());
	}

	/**
	 * Use Java 1.7's Files and Paths classes to get the contents of a file.
	 * @param file
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	private static String getFileContents_1_7(File file, Charset encoding) throws IOException {
		if(file == null || ! file.exists())	{
			throw new IllegalStateException("Input file doesn't exist.");
		}
		
		byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		return new String(encoded, encoding);
	}

	/**
	 * Return the contents of a file using a FileReader and BufferedReader.
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileContents_OldFashioned(File file) throws IOException {
		if(file == null || ! file.exists())	{
			throw new IllegalStateException("Input file doesn't exist.");
		}
		
		BufferedReader reader = new BufferedReader( new FileReader (file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while( ( line = reader.readLine() ) != null ) {
			stringBuilder.append( line );
			stringBuilder.append( ls );
		}
		
		reader.close();

		return stringBuilder.toString();
	}
	
	
	// Approach 3: using Java 7's readAllLines( ) method.

	@Test
	public void test_UsingReadAllLines() throws IOException {
		SumFromFile sff = new SumFromFile();
		sff.setFile(INPUT_FILE);
		
		int actual = sff.sum_UsingReadAllLines();
		assertEquals(1, actual);
	}

	private int sum_UsingReadAllLines() throws IOException {
		Path path = Paths.get(inFile.getAbsolutePath());
		
		List<String> lines = Files.readAllLines(path);
		
		int total = 0;
		for(String line : lines)	{
			int val;
			try {
				val = new Integer(line).intValue();
				total += val;
			} catch (NumberFormatException e) {
				//e.printStackTrace();
				//throw new IOException(e.getMessage());
			}
		}
		return total;
	}

}
