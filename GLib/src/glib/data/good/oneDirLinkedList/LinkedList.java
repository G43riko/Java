package glib.data.good.oneDirLinkedList;

public class LinkedList<S extends Comparable<S>> extends LinkedListImplementation{
	private Node<S> first;

	public void add(S value){
		Node<S> novy = new Node<S>(value); 
		if(first == null)
			first = novy;
		else{
			Node<S> act, pred;
			
			for(act=pred=first ; act != null ; pred = act, act = act.getNext()){
				int res = value.compareTo(act.getValue());
				if(res > 0)
					continue;
				if(res == 0)
					return;
				if(res < 0){
					if(act.equals(first)){
						first = novy;
						novy.setNext(act);
						return;
					}
					pred.setNext(novy);
					novy.setNext(act);
					return;
				}
			}
			pred.setNext(novy);
		}
	}
	
	
	@Override
	public String toString() {
		return first != null ? first.toString() : "empty";
	}

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		list.add("e");
		System.out.println("list: \n" + list);
	}
	
}
