package utils;

public class GenUtils {

	public static String repeatStr(String in, int nbr)	{
		
		StringBuilder sBuild = new StringBuilder();
		
		for(int ct = 1; ct <= nbr; ct++)	{
			sBuild.append(in);
		}
		
		return sBuild.toString();
	}
}
