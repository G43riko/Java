package GUI.gameWindow;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class Window extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Window(){
		setSize(new Dimension(1280,720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	}
}
