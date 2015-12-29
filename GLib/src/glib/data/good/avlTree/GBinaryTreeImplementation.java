package glib.data.good.avlTree;

public class GBinaryTreeImplementation<S, T> {
	protected GTreeNode<S, T> insert(GTreeNode<S, T> l_new, GTreeNode<S, T> l_old){
		if(l_old == null)
			return l_new;
		
		if(l_new.getKey().toString().compareTo(l_old.getKey().toString()) < 0)
			l_old.setLeft(insert(l_new, l_old.getLeft()));
		
		if(l_new.getKey().toString().compareTo(l_old.getKey().toString()) > 0)
			l_old.setRight(insert(l_new, l_old.getRight()));
		
		l_old.setValue(l_new.getValue());
		
		return l_old;
	}
}
