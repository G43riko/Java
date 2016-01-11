package glib.data.good.avlTree;

import glib.data.good.interfaces.GMap;

public final class AvlTree<S extends Comparable<S>, T> extends AvlTreeImplementation<S, T> implements GMap<S, T>{
	private AvlTreeNode<S, T> root;
	
	public T put(S key,T value){
		root = insert(new AvlTreeNode<S, T>(key, value), root);
		return value;
	}
	
	public T remove(S key){
		root = remove(key, root);
		return null;
	}
	
	public T get(S key){
		AvlTreeNode<S, T> result = find(key, root); 
		return (result != null ? result.getValue() : null);
	}
	
	public boolean containsKey(S key){
		return hasKey(key, root);
	}

	@Override
	public String toString() {
		return root != null ? root.toString() : "empty";
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public void clear(){
		root = clear(root);
	}

	public int getSize(){
		return root == null ? 0 : root.getChildrens();
	}

	@Override
	public boolean constainsValue(T value) {
		return hasValue(value, root);
	}
}
