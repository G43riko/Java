package data.binaryThree;

public class BinaryThree {
	private Node root;
	
	public BinaryThree(){
		root = null;
	}
	
	public void add(int key, String name){
		Node add = new Node(key,name);
		if(root == null){
			root = add;
		}
		else{
			root.add(add);
		}
	};
	
	public String get(int key){
		Node find = root.get(key);
		if(find != null){
			return find.getValue();
		}
		return null;
	};
	
	public void set(int key, String name){
		
	};
	
	public void remove(int key){
		
	}
	
}
