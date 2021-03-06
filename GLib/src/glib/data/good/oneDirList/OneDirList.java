package glib.data.good.oneDirList;


import glib.data.good.interfaces.GMap;

public class OneDirList<T> extends OneDirListImplementation<T> implements GMap<String, T>{
	private OneListNode<T> first;
	private boolean recursive;
	
	public OneDirList() {
		this(false);
	}
	
	public OneDirList(boolean recursive) {
		this.recursive = recursive;
	}
	
	public T put(String key,T value){
		if(recursive)
			first = insertRecursive(new OneListNode<T>(key, value), first);
		else
			first = insert(new OneListNode<T>(key, value), first);
		return value;
	}
	
	public T get(String key){
		OneListNode<T> result = find(key, first); 
		return (result != null ? result.getValue() : null);
	}
	
	@Override
	public String toString() {
		return first.toString();
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public void clear() {
		first = clear(first);
	}

	@Override
	public int getSize() {
		return getSize(first);
	}

	@Override
	public T remove(String key) {
		first = delete(key, first);
		return null;
	}

	@Override
	public boolean containsKey(String key) {
		return hasKey(key, first);
	}

	@Override
	public boolean constainsValue(T value) {
		return hasValue(value, first);	
	}
	
}
