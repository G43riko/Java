package glib.data.good.oneDirLinkedList;

public class Node<S extends Comparable<S>> {
	private S value;
	private Node<S> next;

	public Node(S value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "[ " + value +" ]\n" + (next != null ? next : "");
	}
	
	public Node<S> getNext() {
		return next;
	}

	public void setNext(Node<S> next) {
		this.next = next;
	}

	public S getValue() {
		return value;
	}
	public void setValue(S value) {
		this.value = value;
	}
}
