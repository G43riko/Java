package glib.data.good.avlTree;

import java.util.ArrayList;

import glib.data.good.interfaces.GMap;

public class GBinaryTree<S, T> extends GBinaryTreeImplementation<S, T> implements GMap<S, T>{
	private GTreeNode<S, T> root; 
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T add(S key, T value) {
		root = insert(new GTreeNode<S, T>(key, value), root);
		return value;
	}

	@Override
	public void addAll(GMap<S, T> tree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T remove(S key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAll(GMap<S, T> tree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get(S key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<T> getAllValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean constainsValue(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(S key) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString() {
		return root.toString();
	}
}
