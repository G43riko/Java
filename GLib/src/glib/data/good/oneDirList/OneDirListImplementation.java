package glib.data.good.oneDirList;

public abstract class OneDirListImplementation<T>{
	protected OneListNode<T> insert(OneListNode<T> novy, OneListNode<T> stary){
		if(stary == null)
			return novy;
		
		OneListNode<T> pred, act;
		
		for(pred = act = stary ; act != null ; pred = act, act = act.getNext()){
			int cmpResult = act.getKey().compareTo(novy.getKey());
			
			if(cmpResult < 0)
				continue;
			
			if(cmpResult == 0){
				act.setValue(novy.getValue());
				break;
			}
			
			if(cmpResult > 0){
				pred.setNext(novy);
				if(!act.equals(novy))
					novy.setNext(act);
				break;
			}
		}
		return stary;
	}
	protected OneListNode<T> insertRecursive(OneListNode<T> novy, OneListNode<T> stary) {
		if(stary == null)
			return novy;
		int cmpResult = stary.getKey().compareTo(novy.getKey());
		System.out.println(cmpResult);
		if(cmpResult < 0)
			stary.setNext(insert(novy, stary.getNext()));
		else if(cmpResult == 0)
			stary.setValue(novy.getValue());
		else if(cmpResult > 0){
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
