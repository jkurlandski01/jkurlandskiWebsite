package utils;

import java.util.NoSuchElementException;

// JERRY: more efficient to have first element be the top of the stack

public class MyLinkedList<E>	{
	public class Node<F>	{
		Node<F> next;
		F data;
		
		public void add(F el)	{
//			if(data == null)	{
//				data = el;
//				return;
//			}
			
			Node<F> currList = this;
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
		public F pollLast()	{
//			if (data == null)
//				throw new IllegalStateException();
			
			
			Node<F> currList = next;
			Node<F> prevList = this;
			
			while (currList.next != null)	{
				prevList = currList;
				currList = currList.next;
			}
					
			F result = currList.data;
			currList.data = null;
			prevList.next = null;
//			size--;
			return result;
		}

		/**
		 * Return the last element, but do not remove it.
		 * @return
		 */
		public F peekLast()	{
//			if (next == null)	{
//				return data;
//			}
			
			Node<F> currList = this;			
			while (currList.next != null)	{
				currList = currList.next;
			}
			return currList.data;
		}

		public int indexOf(F element)	{
			int ct = 0;
			
			Node<F> currList = this;
			while (currList != null)	{
				if (currList.data.equals(element))	{
					return ct;
				}
				currList = currList.next;
				ct++;
			}
			
			return -1;
		}

		public F remove(int idx)	{
		
			Node<F> prevList = this;
			Node<F> currList = this.next;
			
			int ct = 0;
			while (currList != null && ct < idx)	{
				prevList = currList;
				currList = currList.next;
				ct++;
			}
			if (ct == idx)	{
				F result = currList.data;
				size--;
				prevList.next = currList.next;
				return result;
			}
			throw new NoSuchElementException("Not there.");
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
		size++;
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
		if (first == null)
			throw new IllegalStateException("Nothing there");
		
		return first.peekLast();
		
	}
	
	public boolean isEmpty()	{
		return first == null;
	}
	
	public int size()	{
		return size;
	}
	
	public int indexOf(E element)	{
		if (first == null)	{
			return -1;
		}
		
		return first.indexOf(element);
	}
	
	public E remove(int idx)	{
		if (first == null)	{
			throw new NoSuchElementException("Not there.");
		}

		if (idx == 0)	{
			E result = first.data;
			size--;
			first = null;
			return result;
		}

		return first.remove(idx);
	}
		
}
