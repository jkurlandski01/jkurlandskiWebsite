package utils;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Ignore;
import org.junit.Test;

public class Stack<E> {
	
	//LinkedList<E> stack = new LinkedList<E>();
	MyLinkedList<E> stack = new MyLinkedList<E>();
	
	public void push(E element)	{
		stack.add(element);
	}
	
	public E pop()	{
		if(stack.isEmpty())	{
			throw new IllegalStateException("Stack is empty.");
		}
		return stack.pollLast();
	}
	
	public E remove(E element)	{
		int idx = stack.indexOf(element);
		
		if (idx < 0)	{
			return null;
		}
		
		return stack.remove(idx);
		
	}
	
	public E peekLast()	{
		return stack.peekLast();
	}
	
	
	@Test
	public void testAdd1Remove()		{
		Stack<String> stack = new Stack<>();
		stack.push("Hi!");
		
		assertEquals(stack.stack.size(), 1);
		
		stack.pop();
		assertEquals(0, stack.stack.size());
	}
	
	@Test
	public void testAdd2Remove()		{
		Stack<String> stack = new Stack<>();
		stack.push("Hi!");
		stack.push("Hello.");
		
		assertEquals(2, stack.stack.size());
		
		String el = stack.pop();
		assertEquals(1, stack.stack.size());
		assertEquals("Hello.", el);
	}
	
	@Test
	public void testPeekLast()		{
		Stack<String> stack = new Stack<>();
		stack.push("Hi!");
		stack.push("Hello.");
		
		assertEquals(2, stack.stack.size());
		
		String el = stack.peekLast();
		assertEquals("Hello.", el);
		assertEquals(2, stack.stack.size());
	}
	
	//@Ignore
	// JERRY: unignore
	@Test
	public void testRemoveFromMiddle()		{
		Stack<String> stack = new Stack<>();
		stack.push("Hi!");
		stack.push("Hello.");
		stack.push("How are you?");
		
		assertEquals(3, stack.stack.size());
		
		String e = stack.remove("Hello.");
		assertEquals(2, stack.stack.size());
		assertEquals("Hello.", e);
		
		e = stack.remove("Hello.");
		assertEquals(2, stack.stack.size());
		assertNull(e);
		
		e = stack.remove("Hi!");
		assertEquals("Hi!", e);
		stack.remove("How are you?");
		assertEquals(0, stack.stack.size());

	}
	
	@Test(expected=IllegalStateException.class)
	public void testPopWhenEmpty()	{
		Stack<String> stack = new Stack<>();
		stack.push("Hi!");
		stack.pop();
		stack.pop();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testWhenEmpty2()	{
		Stack<String> stack = new Stack<>();
		stack.pop();
	}
	
}
