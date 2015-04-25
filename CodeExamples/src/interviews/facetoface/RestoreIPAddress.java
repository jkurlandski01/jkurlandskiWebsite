package interviews.facetoface;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * Given a string containing only digits, restore it by returning all possible IP address combinations
 * A valid IP address has 4 parts separated by a period. Each part can contain 0-255. The first part can't 
 * start with 0.
 */
public class RestoreIPAddress {

    public static void main(String[] args) {
    	RestoreIPAddress restoreIPAddress = new RestoreIPAddress();
    	List<String> result = restoreIPAddress.restorelpAddresses("25525511135");
        System.out.println(result);
    }
    
    @Test
    public void test()	{
    	RestoreIPAddress restoreIPAddress = new RestoreIPAddress();
    	List<String> result = restoreIPAddress.restorelpAddresses("25525511135");
    	assertEquals(Lists.newArrayList(), result);
    }

	private List<String> restorelpAddresses(String string) {
		List<String> list = new ArrayList<>();
		if(string == null || string.isEmpty())	{
			return list;
		}
		helper(list, "", string);
		return list;
	}

	private void helper(List<String> list, String formed, String remain) {
		if(formed.split("\\.").length == 4)	{
			if(remain.isEmpty())	{
				list.add(formed);
			}
			return;
		}
		
		String current = "";
		for(int i = 0; i < remain.length(); i++)	{
			current += remain.charAt(i);
			// invalid [010].1.3.12
			if(Pattern.compile("^0.+").matcher(current).find())	{
				return;
			}
			
			if(Integer.parseInt(current) > 255)	{
				break;
			}
			
			String newFormed = formed.isEmpty()? current: formed + "." + current;
			helper(list, newFormed, remain.substring(i + 1));
		}
		
	}

}
