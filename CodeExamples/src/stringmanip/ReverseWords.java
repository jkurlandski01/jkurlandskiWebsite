package stringmanip;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReverseWords {
	
	public static String[] reverseWords(String input)		{
		if(input == null || input.isEmpty())	{
			throw new IllegalArgumentException("Input is empty");
		}
		
		String[] wordsIn = input.split(" ");
		String[] wordsOut = new String[wordsIn.length];
		
		int len = wordsIn.length;
		for(int ct = len - 1; ct >= 0 ; ct--)	{
			wordsOut[len - 1 - ct] = wordsIn[ct];
		}
		
		return wordsOut;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test1()	{
		reverseWords("");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNull()	{
		reverseWords(null);
	}

	@Test
	public void test2()	{
		String[] actual = reverseWords("a");
//		String[] expected = new String[] {"a"};
		assertEquals(1, actual.length);
		assertEquals("a", actual[0]);
	}

	@Test
	public void test3()	{
		String[] actual = reverseWords("a ab");
//		String[] expected = new String[] {"ab", "a"};
		assertEquals(2, actual.length);
		assertEquals("ab", actual[0]);
		assertEquals("a", actual[1]);
	}

	@Test
	public void test4()	{
		String[] actual = reverseWords("a ab abc");
//		String[] expected = new String[] {"abc", "ab", "a"};
		assertEquals(3, actual.length);
		assertEquals("abc", actual[0]);
		assertEquals("ab", actual[1]);
		assertEquals("a", actual[2]);
	}

}
