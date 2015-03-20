package javaTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MiscJavaTest {
	    final String PARENT_PATH = (new File("")).getAbsoluteFile().getParentFile().getAbsolutePath();
	private static final String PROJECT_PATH = (new File("")).getAbsoluteFile().getAbsolutePath();
	private static final File RESOURCE_FILE = new File(PROJECT_PATH, "/src/resources");
	
	static final String SIGMA_HOME = System.getenv("SIGMA_HOME");
	
	private static final String INPUT_FILE_PATH = "resources/input.txt";
	private static final Class<MiscJavaTest> CLASS = MiscJavaTest.class;
	
	public static void read(String input)   {
	    if(input == null || input.isEmpty())    {
	        throw new IllegalArgumentException("Parameter input is null or empty.");
	    }
	    
	    // ...
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNull() {
	    read(null);
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	    
	@Test
	public void testNullSpecific() {
		read("hi!");
		read("what's new, pussycat?");
		
	    expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage("Parameter input is null or empty.");
	    
	    read(null);
	}

	
	@Test
	public void testGetResourcesWithBadExceptionOutput()	{	
		String contents = null;
		try {
			URI uri = CLASS.getClassLoader().getResource(INPUT_FILE_PATH).toURI();
	        File file = new File(uri);
	        // Using org.apache.commons.io.FileUtils
	        contents = FileUtils.readFileToString(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		};
	
		assertEquals("Got it!", contents);
	}
	
	@Test
	public void testGetResourcesThrowsNPEWithUsefulExceptionOutput()	{	
		String contents = null;
		try {
			URI uri = CLASS.getClassLoader().getResource(INPUT_FILE_PATH).toURI();
	        File file = new File(uri);
	        // Using org.apache.commons.io.FileUtils
	        contents = FileUtils.readFileToString(file);
		} catch (Exception ex1) {
            try {
                URI uri = CLASS.getClassLoader().getResource("").toURI();
                String msg = "Could not find " + INPUT_FILE_PATH + " in " + uri.toString();
                throw new IllegalStateException((msg));
            } catch (URISyntaxException eURI) {
            	eURI.printStackTrace();
            }
		};
	
		assertEquals("Got it!", contents);
	}
	


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
	public void testProjectPath()	{		
		assertTrue("True path: " + PROJECT_PATH, PROJECT_PATH.endsWith("CodeExamples"));
	}
	
	@Test
	public void testResourcePath()	{		
		assertTrue(RESOURCE_FILE.exists());
	}
	
	@Test
	public void testEnvironmentVariable()	{		
		assertEquals(null, SIGMA_HOME);
	}
	
}
