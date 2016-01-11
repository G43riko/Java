package glib.data.good.buffer;

import glib.data.good.interfaces.GSimpleCollection;

public class GBuffer<T> implements GSimpleCollection<T>{
	private Node last;
	private int  size = 0;

	public void add(T value){
		last = new Node(value, last);
		size++ ;
	}
	
	public T get(){
		if(size == 0)
			return null;
		
		T result = last.value;
		last 	 = last.parent;
		
		size--;
		return result;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		last = null;
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
		private T value;
		private Node parent;
		
		public Node(T value, Node parent) {
			this.value = value;
			this.parent = parent;
		}
		
		@Override
		public String toString() {
			return "[ " + value + " ] -> " + (parent != null ? parent : "");
		}
	}
}
