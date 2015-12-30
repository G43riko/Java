package glib.data.good.avlTree;

public class AvlTreeNode<T> {
	private AvlTreeNode<T> left;
	private AvlTreeNode<T> right;
	private String key;
	private T value;
	private int height = 0;
	private int childrens = 1;
	
	public AvlTreeNode(String key, T value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return (left != null ? left : "") + "[ " + key + ": " + value + ": " + childrens + " ]\n" + (right != null ? right : "");
	}
	
	public T getValue() {return value;}
	public String getKey() {return key;}
	public int getHeight() {return height;}
	public AvlTreeNode<T> getLeft() {return left;}
	public AvlTreeNode<T> getRight() {return right;}
	public int getChildrens() {return childrens;}
	
	public void setValue(T value) {this.value = value;}
	public void setHeight(int height) {this.height = height;}
	public void setLeft(AvlTreeNode<T> left) {this.left = left;}
	public void setRight(AvlTreeNode<T> right) {this.right = right;}
	public void setChildrens(int childrens) {this.childrens = childrens;}
}
