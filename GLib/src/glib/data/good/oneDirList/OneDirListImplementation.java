package glib.data.good.oneDirList;

public abstract class OneDirListImplementation<T>{
	protected OneListNode<T> insert(OneListNode<T> novy, OneListNode<T> stary) {
		if(stary == null)
			return novy;
		int cmpResult = stary.getKey().compareTo(novy.getKey());
		
		if(cmpResult < 0)
			stary.setNext(insert(novy, stary.getNext()));
		
		if(cmpResult == 0)
			stary.setValue(novy.getValue());
		
		if(cmpResult > 0){
			novy.setNext(stary);
			return novy;
		}
		
		return stary;
	}
	
	protected OneListNode<T> find(String key, OneListNode<T> stary){
		if(stary == null)
			return null;
		
		int cmpResult = stary.getKey().compareTo(key);
		
		if(cmpResult < 0)
			return find(key, stary.getNext());
		
		if(cmpResult == 0)
			return stary;
		
		return null;
	}

	protected OneListNode<T> delete(String key, OneListNode<T> first) {
		if(first == null)
			return null;
		
		int cmpResult = first.getKey().compareTo(key);
		
		if(cmpResult < 0)
			first.setNext(delete(key, first.getNext()));
		
		if(cmpResult == 0)
			return first.getNext();
		
		return first;
	}
	
	protected OneListNode<T> clear(OneListNode<T> first) {
		if(first == null)
			return null;
		
		first.setNext(clear(first.getNext()));
		
		return null;
	}
	
	protected int getSize(OneListNode<T> first) {
		if(first == null)
			return 0;
		
		return getSize(first.getNext()) + 1;
	}

	protected boolean hasKey(String key, OneListNode<T> first){
		if(first == null)
			return false;
		
		int cmpResult = first.getKey().compareTo(key);
		
		if(cmpResult < 0)
			return hasKey(key, first.getNext());
		
		if(cmpResult == 0)
			return true;
		
		return false;
	}
	
	protected boolean hasValue(T value, OneListNode<T> first) {
		if(first == null)
			return false;
		
		if(first.getValue().equals(value))
			return true;
		
		return hasValue(value, first.getNext());
	}
}
