package glib.data.good.binaryTree;

public abstract class TreeImplementation<T> {
	protected TreeNode<T> insert(TreeNode<T> nova, TreeNode<T> stara){
		if(stara == null)
			return nova;
		
		int cmpResult = stara.getKey().compareTo(nova.getKey()); 
		
		if(cmpResult < 0)
			stara.setLeft(insert(nova, stara.getLeft()));
		
		if(cmpResult > 0)
			stara.setRight(insert(nova, stara.getRight()));
		
		if(cmpResult == 0)
			stara.setValue(nova.getValue());
		
		stara.setChildrens(calcChild(stara));
		
		return stara;
	}
	
	protected TreeNode<T> find(String key, TreeNode<T> stara){
		if(stara == null)
			return null;
		
		int cmpResult = stara.getKey().compareTo(key);
		
		if(cmpResult < 0)
			return find(key, stara.getLeft());
		
		if(cmpResult > 0)
			return find(key, stara.getRight());
		
		return stara;
	}
	
	protected TreeNode<T> remove(String key, TreeNode<T> stara){
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
				return null;
			
			if(child == 1)
				return stara.getLeft() == null ? stara.getRight() : stara.getLeft();
				
			return removeFull(stara);
		}

		stara.setChildrens(calcChild(stara));
		return stara;
	}
	
	protected TreeNode<T> clear(TreeNode<T> stara) {
		if(stara == null)
			return null;
		
		stara.setLeft(clear(stara.getLeft()));
		stara.setRight(clear(stara.getRight()));
		
		return null;
	}
	
	protected boolean hasKey(String key, TreeNode<T> stara) {
		if(stara == null)
			return false;
		
		int cmpResult = stara.getKey().compareTo(key);
		
		if(cmpResult < 0)
			return hasKey(key, stara.getLeft());
		
		if(cmpResult > 0)
			return hasKey(key, stara.getRight());
		
		return true;
	}
	
	protected boolean hasValue(T value, TreeNode<T> stara) {
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
	//HELPERS
	
	private TreeNode<T> removeFull(TreeNode<T> tree){
		TreeNode<T> act, pred;
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

	private int calcDirDesc(TreeNode<T> node){
		int result = 0;
		
		if(node.getLeft() != null)
			result++;
		
		if(node.getRight() != null)
			result++;
		
		return result;
	}

	private int calcChild(TreeNode<T> node){
		int childrens = 1;
		
		childrens += node.getLeft()  != null ? node.getLeft().getChildrens()  : 0;
		childrens += node.getRight() != null ? node.getRight().getChildrens() : 0;
		
		return childrens;
	}
	
	private int calcChildRecursive(TreeNode<T> node){
		if(node.getLeft() != null)
			node.getLeft().setChildrens(calcChildRecursive(node.getLeft()));
		if(node.getRight() != null)
			node.getRight().setChildrens(calcChildRecursive(node.getRight()));
		
		return calcChild(node);
	}
}
