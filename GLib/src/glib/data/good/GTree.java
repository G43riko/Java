package glib.data.good;

import glib.data.good.interfaces.GList;

import java.util.ArrayList;
import java.util.function.Consumer;

public class GTree<T> implements GList<T>{
	private GTree<T> rightChild = null;
	private GTree<T> leftChild = null;
	
	private int key;
	private T value;
	
	public GTree(int key, T value){
		this.key = key;
		this.value = value;
	}
	
	@Override
	public void add(int key, T value){
		if(key < this.key){
			if(leftChild == null)
				leftChild = new GTree<T>(key, value);
			else
				leftChild.add(key, value);
		}
		
		else if(key > this.key){
			if(rightChild == null)
				rightChild = new GTree<T>(key, value);
			else
				rightChild.add(key, value);
		}
		else
			this.value = value;
	}
	
	@Override
	public T get(int key){
		if(key < this.key)
			return leftChild.get(key);
		else if(key > this.key)
			return rightChild.get(key);
		else if(key == this.key)
			return value;
		else
			return null;
	}

	@Override
	public int getSize() {
		int result = 1;
		if(rightChild != null)
			result += rightChild.getSize();
		
		if(leftChild != null)
			result += leftChild.getSize();
		
		return result;
	}

	@Override
	public void remove(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(int key) {
		if(this.key == key)
			return true;
		
		if(rightChild != null)
			if(rightChild.containsKey(key))
				return true;
		
		if(leftChild != null)
			if(leftChild.containsKey(key))
				return true;
		
		return false;
	}

	@Override
	public boolean containsValue(T value) {
		if(this.value == value)
			return true;
		
		if(rightChild != null)
			if(rightChild.containsValue(value))
				return true;
		
		if(leftChild != null)
			if(leftChild.containsValue(value))
				return true;
		
		return false;
	}

	@Override
	public void clear() {
		if(rightChild != null)
			rightChild.clear();
		
		if(leftChild != null)
			leftChild.clear();
		
		value = null;
		key = 0;
		leftChild = null;
		rightChild = null;
		
	}

	@Override
	public void forEachElement(Consumer<? super T> action) {
		if(rightChild != null)
			rightChild.forEachElement(action);
		
		if(leftChild != null)
			leftChild.forEachElement(action);
		
		action.accept(value);
		
	}

	
	@Override
	public ArrayList<T> getAllValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAll(GList<T> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAll(GList<T> list) {
		// TODO Auto-generated method stub
		
	}
}
