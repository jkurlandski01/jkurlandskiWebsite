package javaTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class miscJavaTest {
	
	public class Thing {}
	
	public boolean isAThing(Object thing)  {
	    if(thing instanceof Thing)	{
	    	return false;
	    }
	    return true;
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
}
