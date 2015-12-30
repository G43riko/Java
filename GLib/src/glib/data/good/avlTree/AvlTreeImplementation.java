package glib.data.good.avlTree;

public abstract class AvlTreeImplementation<T> {
	protected AvlTreeNode<T> insert(AvlTreeNode<T> nova, AvlTreeNode<T> stara){
		if(stara == null)
			return nova;
		
		int cmpResult = stara.getKey().compareTo(nova.getKey()); 
		
		if(cmpResult <= 0){
			stara.setLeft(insert(nova, stara.getLeft()));
			if(height(stara.getLeft()) - height(stara.getRight()) == 2 ){
				if(nova.getKey().compareTo(stara.getLeft().getKey()) > 0)
					stara = leftRotation(stara);
				else
					stara = doubleLeftRotation(stara);
			}
		}
		
		if(cmpResult > 0){
			stara.setRight(insert(nova, stara.getRight()));
			if(height(stara.getRight()) - height(stara.getLeft()) == 2){
				if(nova.getKey().compareTo(stara.getRight().getKey()) <= 0)
					stara = rightRotation(stara);
				else
					stara = doubleRightRotation(stara);
			}
		}
		
		if(cmpResult == 0)
			stara.setValue(nova.getValue());
		
		stara.setChildrens(calcChildRecursive(stara));
		
		stara.setHeight(Math.max(height(stara.getLeft()), height(stara.getRight())) + 1);
		return stara;
	}
	
	protected AvlTreeNode<T> find(String key, AvlTreeNode<T> stara){
		if(stara == null)
			return null;
		
		int cmpResult = stara.getKey().compareTo(key);
		
		if(cmpResult < 0)
			return find(key, stara.getLeft());
		
		if(cmpResult > 0)
			return find(key, stara.getRight());
		
		return stara;
	}
	
	protected AvlTreeNode<T> remove(String key, AvlTreeNode<T> stara){
		if(stara == null)
			return null;
		
		int cmpResult = stara.getKey().compareTo(key);
		
		if(cmpResult < 0)
			stara.setLeft(remove(key, stara.getLeft()));
		
		if(cmpResult > 0)
			stara.setRight(remove(key, stara.getRight()));
		
		if(cmpResult == 0){
			int child = calcDirDesc(stara);
			
			if(child == 0)
				stara = null;
			else if(child == 1)
				stara = stara.getLeft() == null ? stara.getRight() : stara.getLeft();
			else if(child == 2)
				stara = removeFull(stara);
			
				
			if(height(stara.getRight()) - height(stara.getLeft()) == 2){
				if(stara.getKey().compareTo(stara.getRight().getKey()) > 0)
					stara = rightRotation(stara);
				else
					stara = doubleRightRotation(stara);
			}
			if(height(stara.getLeft()) - height(stara.getRight()) == 2 ){
				if(stara.getKey().compareTo(stara.getLeft().getKey()) <= 0)
					stara = leftRotation(stara);
				else
					stara = doubleLeftRotation(stara);
			}

			stara.setHeight(Math.max(height(stara.getLeft()), height(stara.getRight())) + 1);
			return stara;
		}

		stara.setChildrens(calcChild(stara));
		return stara;
	}
	
	protected AvlTreeNode<T> clear(AvlTreeNode<T> stara) {
		if(stara == null)
			return null;
		
		stara.setLeft(clear(stara.getLeft()));
		stara.setRight(clear(stara.getRight()));
		
		return null;
	}
	
	protected boolean hasKey(String key, AvlTreeNode<T> stara) {
		if(stara == null)
			return false;
		
		int cmpResult = stara.getKey().compareTo(key);
		
		if(cmpResult < 0)
			return hasKey(key, stara.getLeft());
		
		if(cmpResult > 0)
			return hasKey(key, stara.getRight());
		
		return true;
	}
	
	protected boolean hasValue(T value, AvlTreeNode<T> stara) {
		if(stara == null)
			return false;
		
		if(stara.getValue().equals(value))
			return true;
		
		if(stara.getRight() != null)
			if(hasValue(value, stara.getRight()))
				return true;
		
		if(stara.getLeft() != null)
			if(hasValue(value, stara.getLeft()))
				return true;
		
		return false;
	}
	
	//TRANSFORMATIONS
	
	private AvlTreeNode<T> leftRotation(AvlTreeNode<T> k2){
		AvlTreeNode<T> k1 = k2.getLeft();

		k2.setLeft(k1.getRight());
		k1.setRight(k2);
	 
		k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
		k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight()) + 1);
		return k1;
	}
	
	
	private AvlTreeNode<T> rightRotation(AvlTreeNode<T> k1){
		AvlTreeNode<T> k2 = k1.getRight();

		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
	 
		k1.setHeight(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);
		k2.setHeight(Math.max(height(k2.getLeft()), k1.getHeight()) + 1);
		
		return k2;
	}

	
	private AvlTreeNode<T> doubleLeftRotation(AvlTreeNode<T> k3){
		k3.setLeft(rightRotation(k3.getLeft()));
		return leftRotation(k3);
	}

	
	private AvlTreeNode<T> doubleRightRotation(AvlTreeNode<T> k1){
		k1.setRight(leftRotation(k1.getRight()));
		return rightRotation(k1);
	}
	//HELPERS
	
	private int height(AvlTreeNode<T> tree){
		return (tree == null ? - 1 : tree.getHeight());
	}
	
	private AvlTreeNode<T> removeFull(AvlTreeNode<T> tree){
		AvlTreeNode<T> act, pred;
		for(pred = act = tree.getLeft(); act.getRight() != null ; pred = act, act = act.getRight());

		if(pred != act)
			pred.setRight(act.getLeft());
		if(tree.getLeft() != act)
			act.setLeft(tree.getLeft());
		if(tree.getRight() != act)
			act.setRight(tree.getRight());
		
		act.setChildrens(calcChildRecursive(act));
		return act;
	}

	private int calcDirDesc(AvlTreeNode<T> node){
		int result = 0;
		
		if(node.getLeft() != null)
			result++;
		
		if(node.getRight() != null)
			result++;
		
		return result;
	}

	private int calcChild(AvlTreeNode<T> node){
		if(node == null)
			return 0;
		
		int childrens = 1;
		
		childrens += node.getLeft()  != null ? node.getLeft().getChildrens()  : 0;
		childrens += node.getRight() != null ? node.getRight().getChildrens() : 0;
		
		return childrens;
	}
	
	private int calcChildRecursive(AvlTreeNode<T> node){
		if(node.getLeft() != null)
			node.getLeft().setChildrens(calcChildRecursive(node.getLeft()));
		if(node.getRight() != null)
			node.getRight().setChildrens(calcChildRecursive(node.getRight()));
		
		return calcChild(node);
	}
}
