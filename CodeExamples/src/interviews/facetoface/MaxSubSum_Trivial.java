package interviews.facetoface;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Uses a very trivial, naive solution taking O(n^2) time.
 *
 */
public class MaxSubSum_Trivial {
	
	public static class Result {
		int sum = Integer.MIN_VALUE;
		int startIdx = Integer.MIN_VALUE;
		int endIdx = Integer.MIN_VALUE;
		public Result(int start, int end, int val) {
			startIdx = start;
			endIdx = end;
			sum = val;
		}
		
		public Result() {
		}
		
		@Override
		public boolean equals(Object obj)	{
			if (this == obj)	{
				return true;
			}
			if (obj == null || obj.getClass() != this.getClass())	{
				return false;
			}
			Result result = (Result) obj;
			if (this.sum == result.sum && this.startIdx == result.startIdx && this.endIdx == result.endIdx)	{
				return true;
			}
			return false;
		}
		
		@Override
		public String toString()	{
			String str = "start=" + startIdx + "; end=" + endIdx + "; sum=" + sum;
			return str;
		}
	}
	
	public static Result maxSubSum(int[] arr)	{
		Result result = new Result(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
		
		if (arr.length == 0)	{
			return result;
		}
		
		int currSum = Integer.MIN_VALUE;
		for (int startIdx = 0; startIdx < arr.length; startIdx++)	{

			for (int endIdx = startIdx; endIdx < arr.length; endIdx++)	{
				if (startIdx == endIdx)	{
					currSum = arr[startIdx];
				}
				else	{
					currSum = currSum += arr[endIdx];
				}
				
				if (currSum > result.sum)	{
					result.sum = currSum;
					result.startIdx = startIdx;
					result.endIdx = endIdx;
				}
			}

		}
		
		
		return result;
	}

	@Test
	public void testAllNegative()	{
		int[] input = {-2, -3, -4, -1, -2000};
		Result actual = maxSubSum(input);
		Result expected = new Result(3, 3, -1);
		assertEquals(expected, actual);
	}

	@Test
	public void testOnePositiveVal()	{
		int[] input = {2000};
		Result actual = maxSubSum(input);
		Result expected = new Result(0, 0, 2000);
		assertEquals(expected, actual);
	}

	@Test
	public void testThreePositiveVals()	{
		int[] input = {2000, 1, 56};
		Result actual = maxSubSum(input);
		Result expected = new Result(0, 2, 2057);
		assertEquals(expected, actual);
	}

	@Test
	public void testMixedSimple1()	{
		int[] input = {-2, 1, -56};
		Result actual = maxSubSum(input);
		Result expected = new Result(1, 1, 1);
		assertEquals(expected, actual);
	}

	@Test
	public void testMixedSimple2()	{
		int[] input = {2, 1, -56};
		Result actual = maxSubSum(input);
		Result expected = new Result(0, 1, 3);
		assertEquals(expected, actual);
	}

	@Test
	public void testMixedSimple3()	{
		int[] input = {12, -3, 36};
		Result actual = maxSubSum(input);
		Result expected = new Result(0, 2, 45);
		assertEquals(expected, actual);
	}
}
