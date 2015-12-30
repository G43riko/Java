package glib.data.good.oneDirList;

public class OneListNode<T>{
	private String key;
	private OneListNode<T> next;
	private T value;
	
	public OneListNode(String key, T value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "[ " + key + ": " + value + " ]\n" + (next != null ? next : "");
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public OneListNode<T> getNext() {
		return next;
	}
	public void setNext(OneListNode<T> next) {
		this.next = next;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
}
