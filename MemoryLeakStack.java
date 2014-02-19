package org.liubey.example.effectivejava;

import java.util.Arrays;

/**
 * memory leak example
 * @author vf
 *
 */
public class MemoryLeakStack {
	
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	
	public MemoryLeakStack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	// memory leak
	//public Object pop() {
	//	if(size == 0)
	//		throw new IllegalArgumentException();
	//	return elements[--size];
	//}
	
	//fixed
	public Object pop() {
		if(size == 0)
			throw new IllegalArgumentException();
		Object result = elements[--size];
		elements[size] = null;
		return result;
	}
	
	public void push(Object o) {
		ensureCapacity();
		elements[size ++ ] = o;
	}
	
	private void ensureCapacity() {
		if(elements.length == size)
			elements = Arrays.copyOf(elements, size* 2 + 1);
	}
	
}
