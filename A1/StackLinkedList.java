/**
 * Created by
 * Michael Pingert
 * Sebastian Hartinger
 * on 17.10.15.
 * 
 * A generic stack, implemented using a linked List.
 * 
 * pop(): get the first element of the stack.
 * push(): put a element on the stack.
 * swap(): change Lifo order (last and next to last).
 * getTotal(): get the numbers of elements of the stack.
 */


import java.util.NoSuchElementException;

public class StackLinkedList<T> implements Stack<T> {

	private Node first;

	private class Node {
		private T ele;
		private Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public StackLinkedList() { }

	public void push(T ele)
	{
		Node current = first;
		first = new Node();
		first.ele = ele;
		first.next = current;
	}

	public T pop()
	{
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		if (first == null) new java.util.NoSuchElementException();
		T ele = first.ele;
		first = first.next;

		return ele;
	}
}

