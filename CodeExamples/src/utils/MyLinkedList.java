package utils;

import java.util.NoSuchElementException;

// JERRY: Need a Stack object that contains a node; other nodes can contain other nodes
// JERRY: more efficient to have first element be the top of the stack
public class MyLinkedList<E>	{
	public class Node<E>	{
		Node<E> next;
		E data;
		
		public void add(E el)	{
//			if(data == null)	{
//				data = el;
//				return;
//			}
			
			Node<E> currList = this;
			while(currList.next != null)	{
				currList = currList.next;
			}
			currList.data = el;
			
//			size++;
		}
		
		/**
		 * Return the last element, removing it from the list.
		 * @return
		 */
		public E pollLast()	{
//			if (data == null)
//				throw new IllegalStateException();
			
			
			Node<E> currList = next;
			Node<E> prevList = this;
			
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


	}
	
	Node<E> first;
	
	int size = 0;
	
	public MyLinkedList() {
		
	}
	
	public MyLinkedList (E datum)	{
		if (first == null)	{
			first = new Node<E>();
		}
		first.add(datum);
		size++;
	}
	
	public void add(E datum)	{
		first.add(datum);
	}
	
	
	
	public E pollLast()	{
		if (first == null)	{
			throw new IllegalStateException();
		}
		if (first.next == null)	{
			E datum = first.data;
			first = null;
			size--;
			return datum;
		}
		size--;
		return first.pollLast();
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
			// JERRY: doesn't compile: this = this.list;
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
