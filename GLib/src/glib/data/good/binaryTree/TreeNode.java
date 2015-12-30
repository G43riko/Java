package glib.data.good.binaryTree;

public class TreeNode<T> {
	private TreeNode<T> left;
	private TreeNode<T> right;
	private String key;
	private T value;
	private int childrens = 1;
	
	public TreeNode(String key, T value) {
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
	public TreeNode<T> getLeft() {return left;}
	public TreeNode<T> getRight() {return right;}
	public int getChildrens() {return childrens;}
	
	public void setValue(T value) {this.value = value;}
	public void setLeft(TreeNode<T> left) {this.left = left;}
	public void setRight(TreeNode<T> right) {this.right = right;}
	public void setChildrens(int childrens) {this.childrens = childrens;}
}
