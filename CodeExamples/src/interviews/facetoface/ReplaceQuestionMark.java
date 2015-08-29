package interviews.facetoface;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class ReplaceQuestionMark {

    public static void main(String[] args) {
    }
    
    private void doAssert(String input, Set<String> expected)	{
    	ReplaceQuestionMark replaceQuestionMark = new ReplaceQuestionMark();
    	Set<String> result = replaceQuestionMark.replaceQuestionMarks(input);
    	assertEquals(expected, result);    	
    }
    
    @Test
    public void test1()	{
    	doAssert("jk?jjkk?", Sets.newHashSet("jkjjjkkj", "jkjjjkkk", "jkkjjkkj", "jkkjjkkk"));
    }

    @Test
    public void test2()	{
    	doAssert("?", Sets.newHashSet("j", "k"));
    }

    @Test
    public void test3()	{
    	doAssert("?j", Sets.newHashSet("jj", "kj"));
    }

	private Set<String> replaceQuestionMarks(String input) {
		Set<String> permutations = Sets.newHashSet();
		if(input == null || input.isEmpty())	{
			return permutations;
		}
		
		// Pick your implementation here.
		depthFirstSearchWorkingOn(input, 0, new StringBuilder(), permutations);
		//depthFirstSearch(input, 0, new StringBuilder(), permutations);
		//depthFirstSearchMultipleStringBuilders(input, 0, new StringBuilder(), permutations);
		
		return permutations;
	}
	
	// TODO: rewriting the method to see if I can make it shorter and simpler.
	private void depthFirstSearchWorkingOn(String input, int idx, StringBuilder stringThusFar, Set<String> list) {
		// Base case when the idx is pointing at what was a question mark and there nothing more to consume.
		if(idx >= input.length())	{
			list.add(stringThusFar.toString());
			return;
		}
		
		// Consume j and k's.
//		int newIdx = idx;
		while(idx < input.length() && (input.charAt(idx) == 'j' || input.charAt(idx) == 'k'))	{
			stringThusFar.append(input.charAt(idx));
			idx++;
		}
		
		if(idx >= input.length())	{
			// We've consumed the string, finding no new question marks.
			list.add(stringThusFar.toString());
			return;
		}
		
		if(input.charAt(idx) == '?')	{			
			depthFirstSearch(input, idx + 1, stringThusFar.append("j"), list);
			stringThusFar.setLength(idx);
			
			depthFirstSearch(input, idx + 1, stringThusFar.append("k"), list);
			stringThusFar.setLength(idx);
		}
	}
		
	private void depthFirstSearch(String input, int idx, StringBuilder stringThusFar, Set<String> list) {
		// Base case when the idx is pointing at what was a question mark and there nothing more to consume.
		if(idx >= input.length())	{
			list.add(stringThusFar.toString());
			return;
		}
		
		// Consume j and k's.
		int newIdx = idx;
		while(newIdx < input.length() && (input.charAt(newIdx) == 'j' || input.charAt(newIdx) == 'k'))	{
			stringThusFar.append(input.charAt(newIdx));
			newIdx++;
		}
		
		if(newIdx >= input.length())	{
			// We've consumed the string, finding no new question marks.
			list.add(stringThusFar.toString());
			return;
		}
		
		if(input.charAt(newIdx) == '?')	{			
			depthFirstSearch(input, newIdx + 1, stringThusFar.append("j"), list);
			stringThusFar.setLength(newIdx);
			
			depthFirstSearch(input, newIdx + 1, stringThusFar.append("k"), list);
			stringThusFar.setLength(idx);
		}
	}
		
	private void depthFirstSearchMultipleStringBuilders(String input, int idx, StringBuilder stringThusFar, Set<String> list) {
		if(idx >= input.length())	{
			list.add(stringThusFar.toString());
			return;
		}
		
		while(idx < input.length() && (input.charAt(idx) == 'j' || input.charAt(idx) == 'k'))	{
			stringThusFar.append(input.charAt(idx));
			idx++;
		}
		
		if(idx >= input.length())	{
			// We've consumed the string, finding no new question marks.
			list.add(stringThusFar.toString());
			return;
		}
		
		if(input.charAt(idx) == '?')	{			
			StringBuilder jSB = new StringBuilder(stringThusFar.toString() + "j");
			depthFirstSearchMultipleStringBuilders(input, idx + 1, jSB, list);
			StringBuilder kSB = new StringBuilder(stringThusFar.toString() + "k");
			depthFirstSearchMultipleStringBuilders(input, idx + 1, kSB, list);
		}
	}
		


}
