package interviews.facetoface;

import static org.junit.Assert.*;

import org.junit.Test;

// This implementation has a complexity of O(n).
public class LongestPalindromicSubString {

	private String longest = "";

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

    private boolean isPalindrome(CharSequence chars) {
		StringBuilder sBuild = new StringBuilder(chars);
		if(sBuild.toString().equals(sBuild.reverse().toString()))	{
			return true;
		}
		return false;
	}

	public String longestPalindrome(String s) {
    	StringBuilder str = new StringBuilder(s);
    	for(int i = 0; i < str.length(); i++)	{
    		findLongestPalindrome(str, i);
    	}
    	return longest;
    }

    public static void main(String[] args) {
        String result = new LongestPalindromicSubString().longestPalindrome("abab");
        //String result = new LongestPalindromicSubString().longestPalindrome("ababa aba");
        System.out.println(result);
    }
    
    @Test
    public void test1()	{
        String result = new LongestPalindromicSubString().longestPalindrome("abab");
    	assertEquals("aba", result);
    }
    
    @Test
    public void test2()	{
        String result = new LongestPalindromicSubString().longestPalindrome("ababa aba");
    	assertEquals("aba aba", result);
    }
}
