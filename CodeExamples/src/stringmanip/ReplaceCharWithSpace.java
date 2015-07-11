package stringmanip;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class ReplaceCharWithSpace {

	/**
	 * Turn the remove parameter into a set of chars that should be removed from the str parameter, 
	 * and replaced with an empty space.
	 * @param str
	 * @param remove
	 * @return
	 */
	public String replaceChars(String str, String remove)	{
		Set<Character> toRemove = toCharSet(remove);
		return removeThem(str, toRemove);
	}

	private String removeThem(final String str, final Set<Character> chars) {
		StringBuilder strBld = new StringBuilder();
		
		for (char theChar : str.toCharArray())	{
			if (chars.contains(theChar))	{
				strBld.append(" ");
			}
			else	{
				strBld.append(theChar);
			}
		}
		
		return strBld.toString();
	}

	private Set<Character> toCharSet(final String remove) {
		Set<Character> result = Sets.newHashSet();
		for(char theChar : remove.toCharArray())	{
			result.add(theChar);
		}
		return result;
	}
	
	@Test
	public void testRemove()	{
		String input = "Mister, I ain't a boy, no I'm a man, and I believe in a promised land.";
		String actual = replaceChars(input, "aeiou");
		
		String expected = "M st r, I   n't   b y, n  I'm   m n,  nd I b l  v   n   pr m s d l nd.";
		assertEquals(expected, actual);
	}
}
