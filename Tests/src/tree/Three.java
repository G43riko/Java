package tree;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class Three implements TreeSelectionListener{
	private JTree tree;
	
	public Three(){
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("The Java Series");
		DefaultMutableTreeNode category = new DefaultMutableTreeNode("Books for Java Programmers");
		top.add(category);
		tree = new JTree(top);
		tree.getSelectionModel().setSelectionMode
        (TreeSelectionModel.SINGLE_TREE_SELECTION);
		
	}
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
