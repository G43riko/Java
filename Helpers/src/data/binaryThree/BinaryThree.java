package data.binaryThree;

public class BinaryThree {
	private Node root;
	private int num;
	private boolean vypis;
	public BinaryThree(){
		root = null;
		num = 0;
	}
	
	public void add(int key, String name){
		Node add = new Node(key,name);
		if(root == null){
			root = add;
		}
		else{
			root.add(add);
		}
		num++;
	};
	
	public String get(int key){
		Node find = root.get(key);
		if(find != null){
			return find.getValue();
		}
		return null;
	};
	
	public void set(int key, String name){
		Node find = root.get(key);
		if(find != null){
			find.setValue(name);
		}
	};
	
	public void remove(int key){
		if(root.getKey()==key){
			//root = root.removeRoot();
		}
		else{
			root.remove(key);
		}
		num--;
	}
	
	public void vypis(){
		root.vypis();
	}

	public void setVypis(boolean vypis) {
		this.vypis = vypis;
	}
}
