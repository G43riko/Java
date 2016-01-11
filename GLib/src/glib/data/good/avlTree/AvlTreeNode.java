package glib.data.good.avlTree;

public class AvlTreeNode<S extends Comparable<S>, T> {
	private AvlTreeNode<S, T> left;
	private AvlTreeNode<S, T> right;
	private S key;
	private T value;
	private int height = 0;
	private int childrens = 1;
	
	public AvlTreeNode(S key, T value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return (left != null ? left : "") + "[ " + key + ": " + value + ": " + childrens + " ]\n" + (right != null ? right : "");
	}
	
	public T getValue() {return value;}
	public S getKey() {return key;}
	public int getHeight() {return height;}
	public AvlTreeNode<S, T> getLeft() {return left;}
	public AvlTreeNode<S, T> getRight() {return right;}
	public int getChildrens() {return childrens;}
	
	public void setValue(T value) {this.value = value;}
	public void setHeight(int height) {this.height = height;}
	public void setLeft(AvlTreeNode<S, T> left) {this.left = left;}
	public void setRight(AvlTreeNode<S, T> right) {this.right = right;}
	public void setChildrens(int childrens) {this.childrens = childrens;}
}
