package glib.data.good.binaryTree;

import glib.data.good.interfaces.GMap;

public class Tree<T> extends TreeImplementation<T> implements GMap<String, T>{
	private TreeNode<T> root;
	
	public T put(String key,T value){
		root = insert(new TreeNode<T>(key, value), root);
		return value;
	}
	
	public T remove(String key){
		root = remove(key, root);
		return null;
	}
	
	public T get(String key){
		TreeNode<T> result = find(key, root); 
		return (result != null ? result.getValue() : null);
	}
	
	public boolean containsKey(String key){
		return hasKey(key, root);
	}
	
	public boolean constainsValue(T value) {
		return hasValue(value, root);
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
}
