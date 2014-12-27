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
	
	public Node removeRoot(){
		if(leftChild==null&&rightChild==null){
			return null;
		}
		else if(leftChild==null){
			return rightChild;
		}
		else if(rightChild==null){
			return leftChild;
		}
		else{
			Node p_act = rightChild;
			while(p_act.leftChild!=null){
				p_act = p_act.leftChild; 
			}
			if(p_act.rightChild != null){
				p_act.parent.setLeftChild(p_act.rightChild);
			}
			p_act.setLeftChild(leftChild);
			p_act.setRightChild(rightChild);
			p_act.parent=null;
			return p_act;
		}
	}
	
	public void remove(int key){
		if(leftChild!=null&&leftChild.getKey()==key){
			if(leftChild.leftChild==null&&leftChild.rightChild==null){
				leftChild=null;
			}
			else if(leftChild.leftChild == null){
				leftChild = leftChild.rightChild;
			}
			else if(leftChild.rightChild == null){
				leftChild = leftChild.leftChild;
			}
			else{
				Node p_act = rightChild;
				while(p_act.leftChild!=null){
					p_act = p_act.leftChild; 
				}
				if(p_act.rightChild != null){
					p_act.parent.setLeftChild(p_act.rightChild);
				}
				p_act.setLeftChild(leftChild.leftChild);
				p_act.setRightChild(leftChild.rightChild);
			}
		}
		else if(rightChild!=null&&rightChild.getKey()==key){
			if(rightChild.rightChild==null&&rightChild.leftChild==null){
				rightChild=null;
			}
			else if(rightChild.rightChild == null){
				rightChild = rightChild.leftChild;
			}
			else if(rightChild.leftChild == null){
				rightChild = rightChild.rightChild;
			}
			else{
				Node p_act = leftChild;
				while(p_act.rightChild!=null){
					p_act = p_act.rightChild; 
				}
				if(p_act.leftChild != null){
					p_act.parent.setRightChild(p_act.leftChild);
				}
				p_act.setRightChild(rightChild.rightChild);
				p_act.setLeftChild(rightChild.leftChild);
			}
		}
		else if(key<this.key){
			leftChild.remove(key);
		}
		else if(key>this.key){
			rightChild.remove(key);
		}
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
//		System.out.println(leftChild.value+" sa pridal nalavo pod "+this.value);
	}
	
	private void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
		this.rightChild.parent = this;
//		System.out.println(rightChild.value+" sa pridal napravo pod "+this.value);
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
	

	public void vypis() {
		if(leftChild!=null){
			leftChild.vypis();
		}
		System.out.println(this);
		if(rightChild!=null){
			rightChild.vypis();
		}
	}
	
	public String toString(){
		return this.key+" = "+this.value;
	}
}
