package org.engine.gui.windows;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.MainStrategy;
import org.engine.component.GameComponent;
import org.engine.gui.Gui;
import org.engine.light.DirectionalLight;
import org.engine.light.PointLight;
import org.engine.object.GameObject;
import org.engine.object.GameObjectPhysics;
import org.strategy.object.GameObjectWithLight;

import glib.util.GColor;

public class SceneWindow extends JPanel{
	private static final long serialVersionUID = 1L;

	private JTree tree;
	private JScrollPane scroll;
	private ArrayList<GameComponent> scene;
	private Gui gui;
	
	//ACTIONS
	
	TreeSelectionListener listener = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent e) {
			if(tree.getLastSelectedPathComponent() instanceof DefaultMutableTreeNode){
				DefaultMutableTreeNode d = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			
				if(d.getUserObject() instanceof GameComponent){
					gui.getBmenu().addSelectComponent((GameComponent)d.getUserObject());
					return;
				}
			}
			gui.getBmenu().addSelectComponent(null);
		}
	};
	
	//CONSTRUCTORS
	
	public SceneWindow(Gui gui) {
		this.gui = gui;
		if(gui.getCoreEngine() != null)
			scene = gui.getCoreEngine().getScene();
		
		createTree();
		
		scroll = createScrollPane(gui.getRmenu().getPreferredSize().width,6,tree);
		add(scroll);
	}
	
	//OTHERS
	
	public JScrollPane createScrollPane(int width,int offset, JTree tree){
		scroll = new JScrollPane(tree);
		scroll.setPreferredSize(new Dimension(width-offset*2,300));
		scroll.setLocation(offset, 0);
		scroll.setBackground(GColor.CYAN);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		return scroll;
	}
	
	private void createTree(){
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(MainStrategy.TITLE);
		top = addAllNodes(top);
		tree = new JTree(top);
		tree.addTreeSelectionListener(listener);
//		tree.setPreferredSize(new Dimension(parent.getPreferredSize().width-30,parent.getSize().height));
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	}

	private DefaultMutableTreeNode addAllNodes(DefaultMutableTreeNode top) {
		if(scene == null){
			top.setUserObject("nieje nastavená scéna");
			return top;
		}
		
		DefaultMutableTreeNode objectsWithPhysics = new DefaultMutableTreeNode("objectsWithPhysics");
		DefaultMutableTreeNode objectsWithLights = new DefaultMutableTreeNode("objectsWithLights");
		DefaultMutableTreeNode objects = new DefaultMutableTreeNode("objects");
		DefaultMutableTreeNode lights = new DefaultMutableTreeNode("lights");
		DefaultMutableTreeNode others = new DefaultMutableTreeNode("others");
		
		for(GameComponent g : scene){
			if(g instanceof GameObject){
				if(g instanceof GameObjectPhysics)
					objectsWithPhysics.add(new DefaultMutableTreeNode(g));
				else if(g instanceof GameObjectWithLight)
					objectsWithLights.add(new DefaultMutableTreeNode(g));
				else
					objects.add(new DefaultMutableTreeNode(g));
			}
			else if(g instanceof PointLight || g instanceof DirectionalLight)
				lights.add(new DefaultMutableTreeNode(g));
			else
				others.add(new DefaultMutableTreeNode(g));
		}
		
		objects.add(objectsWithLights);
		objects.add(objectsWithPhysics);
		
		
		top.add(others);
		top.add(lights);
		top.add(objects);
		
		return top;
	}
}
