package glib.data.good.avlTree;

public class GTreeNode<S, T> {
	private GTreeNode<S, T> left;
	private GTreeNode<S, T> right;
	private GTreeNode<S, T> parent;
	private S key;
	private T value;
	private int childrens = 0;
	
	public GTreeNode(S key, T value) {
		this.value = value;
		this.key = key;
	}
	
	public String toString() {
//		return key + ": " + value;

//		return left + " == [" + key + ": "+ value + "] == " + right + "\n";
		return (left != null ? left : "") + "[" + key + ": " + value +"]\n" + (right != null ? right : "");
	}

	public S getKey() {return key;}
	public GTreeNode<S, T> getLeft() {return left;}
	public T getValue() {return value;}
	public GTreeNode<S, T> getRight() {return right;}
	public GTreeNode<S, T> getParent() {return parent;}
	public int getChildrens() {return childrens;}

	public void setKey(S key) {this.key = key;}
	public void setLeft(GTreeNode<S, T> left) {this.left = left;}
	public void setValue(T value) {this.value = value;}
	public void setRight(GTreeNode<S, T> right) {this.right = right;}
	public void setParent(GTreeNode<S, T> parent) {this.parent = parent;}
	public void setChildrens(int childrens) {this.childrens = childrens;}


	
	
}
