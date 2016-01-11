package glib.data.good.hashMap;

public class Node<S, T> {
	private Node<S, T> next;
	private T value;
	private final S key; 

	public Node(S key, T value) {
		this.value = value;
		this.key = key;
	}
	
	@Override
	public String toString() {
		return "[ " + key +": "+ value +" ]" + (next != null ? " -> " + next : "");
	}
	
	public Node<S, T> getNext() {
		return next;
	}
	
	public void setValue(T value) {
		this.value = value;
	}

	public void setNext(Node<S, T> next) {
		this.next = next;
	}
	
	public T getValue() {
		return value;
	}

	public S getKey() {
		return key;
	}
}
