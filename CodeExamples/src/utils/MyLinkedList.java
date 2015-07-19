package utils;

import java.util.NoSuchElementException;

// JERRY: fix various methods
// Then re-implement so that pop retrieves the first element--like a true stack.
public class MyLinkedList<E>	{
	MyLinkedList<E> list;
	E data;
	
	int size = 0;
	
	public MyLinkedList() {
		
	}
	
	public MyLinkedList (E datum)	{
		data = datum;
		size++;
	}
	
	public void add(E el)	{
		if(data == null)	{
			data = el;
			size++;
			return;
		}
		
		MyLinkedList<E> currList = this;
		while(currList.list != null)	{
			currList = currList.list;
		}
		currList.list = new MyLinkedList<E>(el);
		
		size++;
	}
	
	/**
	 * Return the last element, removing it from the list.
	 * @return
	 */
	public E pollLast()	{
		if (data == null)
			throw new IllegalStateException();
		
		if (this.list == null) {
			E result = data;
			data = null;
			size--;
			return result;
		}
		
		MyLinkedList<E> currList = list;
		MyLinkedList<E> prevList = this;
		
		while (currList.list != null)	{
			prevList = currList;
			currList = currList.list;
		}
				
		E result = currList.data;
		currList.data = null;
		prevList.list = null;
		size--;
		return result;
	}
	
	/**
	 * Return the last element, but do not remove it.
	 * @return
	 */
	public E peekLast()	{
		if (data == null)
			throw new IllegalStateException("Nothing there");
		
		if (this.list == null)	{
			return data;
		}
		
		MyLinkedList<E> currList = this.list;
//		MyLinkedList<E> prevList = this;
		
		while (currList.list != null)	{
//			prevList = currList;
			currList = currList.list;
		}
		return currList.data;
	}
	
	public boolean isEmpty()	{
		return data == null;
	}
	
	public int size()	{
		return size;
	}
	
	// TODO: implement indexOf(E) and remove(int)
	public int indexOf(E element)	{
		if (data == null)	{
			return -1;
		}
		
		int ct = 0;
		
		MyLinkedList<E> currList = this;
		while (currList != null)	{
			if (currList.data.equals(element))	{
				return ct;
			}
			currList = currList.list;
		}
		
		return -1;
	}
	
	public E remove(int idx)	{
		if (data == null)	{
			throw new NoSuchElementException("Not there.");
		}
		
		if (idx == 0)	{
			E result = this.data;
			size--;
			this = this.list;
			return result;
		}
		
		MyLinkedList<E> prevList = this;
		MyLinkedList<E> currList = this.list;
		int ct = 0;
		while (currList != null && ct < idx)	{
			prevList = currList;
			currList = currList.list;
			ct++;
		}
		if (ct == idx)	{
			E result = currList.data;
			size--;
			prevList.list = currList.list;
			return result;
		}
		throw new NoSuchElementException("Not there.");
	}
}
