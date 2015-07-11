package interviews.facetoface;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

import utils.GenUtils;

// Not sure what the complexity of this is.
/**
 * Given a string, find the longest palindrome in it.
 * 
 * FIXME: Use memoization to make this more efficient. Write tests that verify this.
 */
public class LongestPalindromicSubString {

	private String longest = "";
	
	private Map<String, String> palMap = Maps.newHashMap();

	public String longestPalindromeSlow(String s) {
    	StringBuilder str = new StringBuilder(s);
    	for(int i = 0; i < str.length(); i++)	{
    		findLongestPalindrome(str, i);
    	}
    	return longest;
    }

	public String longestPalindromeFast(String s) {
    	//StringBuilder str = new StringBuilder(s);
    	for(int i = 0; i < s.length(); i++)	{
    		findLongestPalindromeMemoized(s, i);
    	}
    	return longest;
    }

    private void findLongestPalindromeMemoized(String str, int i) {
    	if (palMap.containsKey(str.toString()))		{
    		String val = palMap.get(str);
    		if (val.length() > longest.length())	{
    			longest = val;
    		}
    	}
    	
    	for (int ct = str.length(); ct >= i; ct--)	{
    		if (isPalindrome(str.substring(i, ct)))	{
        		String val = str.substring(i, ct);
        		if (val.length() > longest.length())	{
        			palMap.put(str, val);
        			longest = val;
        		}
    		}
    	}
		
	}

	private void findLongestPalindrome(StringBuilder str, int i) {
    	for(int j = str.length(); j > i; j--)	{
    		while(j - i > longest.length())	{
    			CharSequence chars = str.subSequence(i,  j);

    			if(isPalindrome(chars) && chars.length() > longest.length())	{
    				longest = chars.toString();
    				return;
    			}
    			break;
    		}
    	}
    }

    private boolean isPalindrome(String str) {
		StringBuilder sBuild = new StringBuilder(str);
		if(sBuild.toString().equals(sBuild.reverse().toString()))	{
			return true;
		}
		return false;
	}

    private boolean isPalindrome(CharSequence chars) {
		StringBuilder sBuild = new StringBuilder(chars);
		if(sBuild.toString().equals(sBuild.reverse().toString()))	{
			return true;
		}
		return false;
	}

    public static void main(String[] args) {
        String result = new LongestPalindromicSubString().longestPalindromeSlow("abab");
        //String result = new LongestPalindromicSubString().longestPalindrome("ababa aba");
        System.out.println(result);
    }
    
    @Test
    public void test1()	{
        String result = new LongestPalindromicSubString().longestPalindromeSlow("abab");
    	assertEquals("aba", result);
    }
    
    @Test
    public void test2()	{
        String result = new LongestPalindromicSubString().longestPalindromeSlow("ababa aba");
    	assertEquals("aba aba", result);
    }
    
    // Duration of slow = 2561
    @Test
    public void testTimeOfSlowVersion()	{
    	String longStr = GenUtils.repeatStr("0123456789", 200);
    	String input = longStr + "ababa aba";
    	
    	long startTime = System.currentTimeMillis();
        String result = new LongestPalindromicSubString().longestPalindromeSlow(input);
        long durationTime = System.currentTimeMillis() - startTime;
    	assertEquals("aba aba", result);
    	
    	System.out.println("\nDuration of slow = " + durationTime);
    }
    
    // Duration of fast = 2093
    @Test
    public void testTimeOfFastVersion()	{
    	String longStr = GenUtils.repeatStr("0123456789", 200);
    	String input = longStr + "ababa aba";
    	
    	long startTime = System.currentTimeMillis();
        String result = new LongestPalindromicSubString().longestPalindromeFast(input);
        long durationTime = System.currentTimeMillis() - startTime;
    	assertEquals("aba aba", result);
    	
    	System.out.println("\nDuration of fast = " + durationTime);
    }
    
    // Duration of fast 400 = 16298
    @Test
    public void testTimeOfFastVersion400()	{
    	String longStr = GenUtils.repeatStr("0123456789", 400);
    	String input = longStr + "ababa aba";
    	
    	long startTime = System.currentTimeMillis();
        String result = new LongestPalindromicSubString().longestPalindromeFast(input);
        long durationTime = System.currentTimeMillis() - startTime;
    	assertEquals("aba aba", result);
    	
    	System.out.println("\nDuration of fast 400 = " + durationTime);
    }
}
