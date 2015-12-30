package glib.data.good.avlTree;

import glib.data.good.interfaces.GMap;

public final class AvlTree<T> extends AvlTreeImplementation<T> implements GMap<String, T>{
	private AvlTreeNode<T> root;
	
	public T add(String key,T value){
		root = insert(new AvlTreeNode<T>(key, value), root);
		return value;
	}
	
	public T remove(String key){
		root = remove(key, root);
		return null;
	}
	
	public T get(String key){
		AvlTreeNode<T> result = find(key, root); 
		return (result != null ? result.getValue() : null);
	}
	
	public boolean containsKey(String key){
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
