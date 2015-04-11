package interviews.facetoface;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class ReplaceQuestionMark {

    public static void main(String[] args) {
    }
    
    @Test
    public void test()	{
    	ReplaceQuestionMark replaceQuestionMark = new ReplaceQuestionMark();
    	Set<String> result = replaceQuestionMark.replaceQuestionMarks("jk?jjkk?");
    	
    	Set<String> expected = Sets.newHashSet("jkjjjkkj", "jkjjjkkk", "jkkjjkkj", "jkkjjkkk");
    	assertEquals(expected, result);
    }

	private Set<String> replaceQuestionMarks(String input) {
		Set<String> permutations = Sets.newHashSet();
		if(input == null || input.isEmpty())	{
			return permutations;
		}
		depthFirstSearch(input, 0, new StringBuilder(), permutations);
		return permutations;
	}
	
	private void depthFirstSearch(String input, int idx, StringBuilder stringThusFar, Set<String> list) {
		if(idx >= input.length())	{
			list.add(stringThusFar.toString());
			return;
		}
		
		int newIdx = idx;
		while(newIdx < input.length() && (input.charAt(newIdx) == 'j' || input.charAt(newIdx) == 'k'))	{
			stringThusFar.append(input.charAt(newIdx));
			newIdx++;
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
		
		if(input.charAt(idx) == '?')	{			
			StringBuilder jSB = new StringBuilder(stringThusFar.toString() + "j");
			depthFirstSearch(input, idx + 1, jSB, list);
			StringBuilder kSB = new StringBuilder(stringThusFar.toString() + "k");
			depthFirstSearch(input, idx + 1, kSB, list);
		}
	}
		


}
