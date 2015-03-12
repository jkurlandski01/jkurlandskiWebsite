package javaTests;

import static org.junit.Assert.*;

import org.junit.*;

public class UnitTestTest {
	
	private static class SomeClass	{
		static void readConfigFile() { }		
	}
	private class ClassBeingTested { 
		String doSomething()	{ return "hi!"; }
	}
	
	@BeforeClass
	public static void doSomethingOnceBeforeEachTestClass()  {
		SomeClass.readConfigFile();
	}
	
	private ClassBeingTested inputClass;
    
    @Before
    public void doSomethingOnceBeforeEachTestMethod( )  {
	   inputClass = new ClassBeingTested( );
    }
    
    @Test
    public void test()  {
        String output = inputClass.doSomething( );
        // ...
        assertEquals("hi!", output);
    }
}
