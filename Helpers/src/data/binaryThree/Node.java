package data.binaryThree;

public class Node {
	Node parent,leftChild,rightChild;
	int key;
	String value;
	
	public Node(int key, String value){
		this.key = key;
		this.value = value;
		this.parent = this.leftChild = rightChild = null;
		
		leftChild = null;
		rightChild = null;
		parent = null;
	}
	
	public void add(Node add){
		if(add.key<this.key){
			if(this.leftChild==null){
				this.setLeftChild(add);
			}
			else{
				this.leftChild.add(add);
			}
		}
		else if(add.key>this.key){
			if(this.rightChild==null){
				this.setRightChild(add);
			}
			else{
				this.rightChild.add(add);
			}
		}
		else return;
	}
	
	public Node get(int key){
		if(this.key==key){
			return this;
		}
		else if(this.key>key&&this.leftChild!=null){
			return leftChild.get(key);
		}
		else if(this.key<key&&this.rightChild!=null){
			return rightChild.get(key);
		}
		return null;
	}

	private void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
		this.leftChild.parent = this;
		System.out.println(leftChild.value+" sa pridal nalavo pod "+this.value);
	}
	
	private void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
		this.rightChild.parent = this;
		System.out.println(rightChild.value+" sa pridal napravo pod "+this.value);
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
