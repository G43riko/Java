package tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class MainTree extends JFrame{
	private JTree tree;
	
	public MainTree() {
		setSize(new Dimension(800,600));
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Game engine");
		createNodes(top);
		tree = new JTree(top);

		tree.getSelectionModel().setSelectionMode
        (TreeSelectionModel.SINGLE_TREE_SELECTION);
		JScrollPane treeView = new JScrollPane(tree);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JEditorPane htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        JScrollPane htmlView = new JScrollPane(htmlPane);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);
        
        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));
 
        //Add the split pane to this panel.
        add(splitPane);
        setVisible(true);
	}
	
	public MainTree(int i) {
		setSize(new Dimension(1280,720));
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JSplitPane topSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JScrollPane materialView = new JScrollPane();
		JScrollPane sceneView = new JScrollPane();
		JScrollPane threeView = new JScrollPane();
		
		JFrame okno = this;
		addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	topSplit.setDividerLocation(getWidth()-200);
            	mainSplit.setDividerLocation(getHeight()-200);
                System.out.println("componentResized "+okno.getWidth());
            }
        });
		materialView.setBackground(Color.RED);
		sceneView.setBackground(Color.GREEN);
		threeView.setBackground(Color.BLUE);
		materialView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		threeView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainSplit.setDividerLocation(getHeight()-200);
		mainSplit.setTopComponent(topSplit);
		mainSplit.setBottomComponent(materialView);
		topSplit.setRightComponent(threeView);
		topSplit.setDividerLocation(getWidth()-200);
		topSplit.setLeftComponent(sceneView);
		System.out.println(sceneView.getSize());
		add(mainSplit);
		setVisible(true);
//		mainSplit.updateUI();
	}

	public DefaultMutableTreeNode addNew(String name){
		return new DefaultMutableTreeNode(name);
	}
	
	public void createNodes(DefaultMutableTreeNode top){
		DefaultMutableTreeNode res = new DefaultMutableTreeNode("res");
		DefaultMutableTreeNode core = new DefaultMutableTreeNode("core");
		DefaultMutableTreeNode components = new DefaultMutableTreeNode("components");
		DefaultMutableTreeNode src = new DefaultMutableTreeNode("src");
		DefaultMutableTreeNode dat = new DefaultMutableTreeNode("dat");
		DefaultMutableTreeNode bl = new DefaultMutableTreeNode("BaseLight");
		DefaultMutableTreeNode dl = new DefaultMutableTreeNode("DirectionalLight");
		DefaultMutableTreeNode pl = new DefaultMutableTreeNode("PointLight");
		DefaultMutableTreeNode sl = new DefaultMutableTreeNode("SpotLight");
		DefaultMutableTreeNode lights = new DefaultMutableTreeNode("lights");
		DefaultMutableTreeNode entities = new DefaultMutableTreeNode("entities");
		DefaultMutableTreeNode tm = new DefaultMutableTreeNode("TexturedModel");
		DefaultMutableTreeNode mat = new DefaultMutableTreeNode("Material");
		top.add(res);
			res.add(core);
				core.add(addNew("Vector3f"));
				core.add(addNew("Vector2f"));
				core.add(addNew("Matrix4f"));
				core.add(addNew("Quaternation"));
				core.add(addNew("Vertex"));
			res.add(components);
				components.add(addNew("Camera"));
				components.add(addNew("Loader"));
				components.add(addNew("Renderer"));
				components.add(lights);
					lights.add(bl);
					lights.add(dl);
					lights.add(pl);
					lights.add(sl);
				components.add(entities);
					entities.add(tm);
						tm.add(addNew("RawModel"));
						tm.add(mat);
							mat.add(addNew("Texture"));
		top.add(src);
			src.add(addNew("textures"));
			src.add(addNew("models"));
			src.add(addNew("shaders"));
		top.add(dat);
	}

	public static void main(String[] args){
		// TODO Auto-generated method stub
		//MainTree three = new MainTree();
		MainTree three = new MainTree(1);
	}

}
