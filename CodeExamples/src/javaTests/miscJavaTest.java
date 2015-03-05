package javaTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class miscJavaTest {
    private static final String PARENT_PATH = (new File("")).getAbsoluteFile().getParentFile().getAbsolutePath();
    private static final String PROJECT_PATH = (new File("")).getAbsoluteFile().getAbsolutePath();
    private static final File RESOURCE_FILE = new File(PROJECT_PATH, "resources");

	public class Thing {}
	
	public boolean isAThing(Object thing)  {
	    if(thing instanceof Thing)	{
	    	return false;
	    }
	    return true;
	}
	
	public String readFileThing(BufferedReader reader)	{
        if(reader == null)	{
        	return "";
        }
        
        StringBuffer buffer = new StringBuffer();
        int read;
        char[] chars = new char[1024];
        try {
			while ((read = reader.read(chars)) != -1)	{
			    buffer.append(chars, 0, read);  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return buffer.toString();
	}

	//@Test
	public void forgotTestAnnotation()	{
		String input = "";
		boolean actual = isAThing(input);
		assertFalse(actual);
	}
	
	@Test
	public void testAssertInsteadOfUnitAssert() {
		Thing input = new Thing();
		boolean actual = isAThing(input);
		assert(actual);
	}
	
	@Test
	public void testReadFileThing()	{
		String input = "Hi, ducks!";
		BufferedReader reader = new BufferedReader(new StringReader(input));
		String actual = readFileThing(reader);
		assertEquals(input, actual);
	}
	@Test
	public void testResourcePath()	{
		
		assertTrue(RESOURCE_FILE.exists());
	}
	
}
