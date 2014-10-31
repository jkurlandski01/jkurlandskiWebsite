package guavatable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.collect.SortedMultiset;
//import com.google.common.collect.SortedMultiset;
import com.google.common.collect.TreeMultiset;

/**
 * Counts String instances.
 */
public class StringCounter {

	
	protected Multiset<String> multiset;
	
	public StringCounter()	{
		multiset = HashMultiset.create();
	}
	
	public StringCounter(Collection<String> strings)	{
		this();
		for(String str : strings)	{
			multiset.add(str);
		}
	}
	
	
	public void addAll(Collection<String> strings)	{
		for(String str : strings)	{
			multiset.add(str);
		}
	}
	
	
	public int getStringCount(String str)	{
		return multiset.count(str);
	}
	
	public int getSize()	{
		return multiset.size();
	}
	
	public Set<String> getElementSet()	{
		return multiset.elementSet();
	}
	


	/**
	 * Return a new StringCounter subset of this StringCounter of those strings having a count greater than
	 * or equal to min.
	 * @param min
	 * @return
	 */
	public StringCounter getHavingCtGreaterThanOrEqual(int min)	{
		SortedMultiset<String> newMultiset = TreeMultiset.create();
		Iterator<String> it = multiset.iterator();
		while(it.hasNext())		{
			String str = it.next();
			if(newMultiset.contains(str))	{
				continue;
			}
			
			int cnt = multiset.count(str);
			if(cnt < min)	{
				continue;
			}
			
			newMultiset.add(str, cnt);

		}
		StringCounter returnCounter = new StringCounter(newMultiset);
		return returnCounter;
	}
	
	
	/**
	 * Iterates through the given collection, pairing every element with every other element.
	 * Elements are trimmed before inserted.
	 * @param singles
	 * @return
	 */
	public static Set<String> pairElements(Collection<String> singles)	{
		Set<String> pairs = Sets.newHashSet();
		
		for(String str1 : singles)	{
			for(String str2 : singles)	{
				if(str1.equals(str2))	{
					continue;
				}
//				PairOfString pair = new PairOfString(str1.trim(), str2.trim());
//				pairs.add(pair.toString());
			}
		}
		
		return pairs;
	}
	
}
