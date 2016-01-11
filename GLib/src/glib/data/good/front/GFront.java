package glib.data.good.front;

import glib.data.good.interfaces.GSimpleCollection;

public class GFront<T> implements GSimpleCollection<T>{
	private Node last, first;
	private int size = 0;
	
	public void add(T value){
		last = new Node(value, last);
		
		if(first == null)
			first = last;
		size++;
	}
	
	public T get(){
		if(size == 0)
			return null;
		
		T result = first.value;
		first    = first.prev;
		
		size--;
		return result;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		last = first = null;;
		size = 0;
	}

	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		return last != null ? last.toString() : "empty";
	}
	
	private class Node{
		T value;
		Node prev;
		
		public Node(T value, Node next) {
			this.value = value;
			if(next != null)
				next.prev = this;
		}
		
		@Override
		public String toString() {
			return "[ " + value + " ] -> " + (prev != null ? prev : "");
		}
	}
}
