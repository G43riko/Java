package glib.data.good.oneDirList;

import java.util.ArrayList;
import java.util.function.Consumer;

import glib.data.good.interfaces.GList;

public class Node<T>{
	private T value;
	private int key;
	
	private Node<T> next = null;
	
	public Node(){
		
	}
	
	public Node(int key, T value, Node<T> next){
		this.value = value;
		this.next = next;
		this.key = key;
	}
	
	public void add(int key, T value){
		
		if(key == this.key)
			this.value = value;
		else if(key > this.key){
			if(next == null)
				next = new Node<T>(key, value, null);
			else{
				if(key < next.key)
					next = new Node<T>(key, value, next);
				else
					next.add(key, value);
			}
		}
	}
	
	public T get(int key) {
		if(key == this.key)
			return value;
		else
			if(next == null)
				return null;
			else
				return next.get(key);
		
	}
	
	public void remove(int key) {
		if(next != null)
			if(next.key == key)
				next = next.next;
			else
				next.remove(key);
	}

	public boolean containsKey(int key) {
		if(key == this.key)
			return true;
		else
			if(next == null)
				return false;
			else
				return next.containsKey(key);
	}
	
	public boolean containsValue(T value){
		if(value == this.value)
			return true;
		else
			if(next == null)
				return false;
			else
				return next.containsValue(value);
	}
	
	public String toString() {
		String result = "["+key+": "+value+"] ";
		
		if(next != null)
			result += next.toString();
		
		return  result;
	}

	public int getKey() {
		return key;
	}
	
	public Node<T> getNext() {
		return next;
	}

	public int getSize() {
		if(next != null)
			return next.getSize() + 1;
		else
			return 1;
	}

	public T getValue() {
		return value;
	}

	public void clear() {
		if(next != null)
			next.clear();
		next = null;
		value = null;
		key = 0;
	}
	
	public void forEachElement(Consumer<? super T> action) {
		if(next != null)
			next.forEachElement(action);
		
		action.accept(value);
		
	}

	public ArrayList<T> getAllValues() {
		ArrayList<T> list = new ArrayList<T>();
		list.add(value);
		
		if(next != null)
			list.addAll(next.getAllValues());
		
		return list;
	}
	
	public ArrayList<Node<T>> getAllNodes(){
		ArrayList<Node<T>> list = new ArrayList<Node<T>>();
		list.add(this);
		
		if(next != null)
			list.addAll(next.getAllNodes());
		
		return list;
	};
}
