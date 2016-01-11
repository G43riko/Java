package glib.data.good;

import java.util.ArrayList;

import glib.data.good.interfaces.GMap;

public class GTree<T> implements GMap<Integer, T>{
	private GTree<T> rightChild = null;
	private GTree<T> leftChild  = null;
	
	private int key;
	private T value;
	
	//CONTRUCTORS
	
	public GTree(int key, T value){
		this.key = key;
		this.value = value;
	}
	
	//ADDERS
	
	@Override
	public T put(Integer key, T value) {
		if(key < this.key){
			if(leftChild == null)
				leftChild = new GTree<T>(key, value);
			else
				leftChild.put(key, value);
		}
		
		else if(key > this.key){
			if(rightChild == null)
				rightChild = new GTree<T>(key, value);
			else
				rightChild.put(key, value);
		}
		else{
			T old = this.value;
			this.value = value;
			return old;
		}
		return null;
	}

	public void addAll(GMap<Integer,T> list) {
		// TODO Auto-generated method stub
		
	}

	//GETTERS
	
	@Override
	public T get(Integer key) {
		if(key < this.key)
			return leftChild.get(key);
		else if(key > this.key)
			return rightChild.get(key);
		else if(key == this.key)
			return value;
		else
			return null;
	}

	public ArrayList<T> getAllValues() {
		// TODO Auto-generated method stub
		return null;
	}

	//REMOVERS

	@Override
	public T remove(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void removeAll(GMap<Integer,T> list) {
		// TODO Auto-generated method stub
		
	}

	//OTHERS
	
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
	public boolean containsKey(Integer key) {
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
	public boolean isEmpty() {
		return false;
	}

	public boolean constainsValue(T value) {
		if(this.value == value)
			return true;
		
		if(rightChild != null)
			if(rightChild.constainsValue(value))
				return true;
		
		if(leftChild != null)
			if(leftChild.constainsValue(value))
				return true;
		
		return false;
	}
}
