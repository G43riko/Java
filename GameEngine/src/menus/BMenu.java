package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import terrains.Block;
import entities.Camerka;

public class BMenu extends JPanel{
	private JPanel cameraWindow;
	private JPanel blockWindow;
	private JLabel[] blockData;
	private JLabel[] cameraData;
	private JButton cameraReset;
	private Camerka camerka;
	private Block actBlock;
	private JComboBox selectedBlock;
	
	public void init(){	
		setPreferredSize(new Dimension(100,100));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new BorderLayout());
		
		JPanel content = new JPanel();
		content.setLayout(new FlowLayout());
		
		String[] selectedBlocks = { "Dirt", "Grass", "Water", "Rock" };
		selectedBlock = new JComboBox(selectedBlocks);
		selectedBlock.setSelectedIndex(0);
		content.add(selectedBlock);
		
		content.add(initCameraWindow());
		content.add(initBlockWindow());
		
		add(content,BorderLayout.EAST);

	}
	
	private JPanel initBlockWindow(){
		blockWindow = new JPanel();
		blockWindow.setLayout(new GridBagLayout());
		blockData = new JLabel[4];
		
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("BlockType: "));
		blockData[0] = new JLabel("typ");
		helper.add(blockData[0]);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		blockWindow.add(helper, c);
		
		c.gridwidth = 1;
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("pos.X: "));
		blockData[1] = new JLabel("surX");
		helper.add(blockData[1]);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		blockWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("pos.Y: "));
		blockData[2] = new JLabel("surY");
		helper.add(blockData[2]);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		blockWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("pos.Z: "));
		blockData[3] = new JLabel("surZ");
		helper.add(blockData[3]);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		blockWindow.add(helper, c);
		
		return blockWindow;
	}
	
	private JPanel initCameraWindow(){
		cameraData = new JLabel[6];
		
		cameraWindow = new JPanel();
		cameraWindow.setLayout(new GridBagLayout());
		
		JPanel helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("p. x:"));
		cameraData[0] = new JLabel("0");
		helper.add(cameraData[0]);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		cameraWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("p. y:"));
		cameraData[1] = new JLabel("0");
		helper.add(cameraData[1]);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		cameraWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("p. z:"));
		cameraData[2] = new JLabel("0");
		helper.add(cameraData[2]);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		cameraWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("r. x:"));
		cameraData[3] = new JLabel("0");
		helper.add(cameraData[3]);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		cameraWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("r. y:"));
		cameraData[4] = new JLabel("0");
		helper.add(cameraData[4]);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		cameraWindow.add(helper, c);
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		helper.add(new JLabel("r. z:"));
		cameraData[5] = new JLabel("0");
		helper.add(cameraData[5]);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		cameraWindow.add(helper, c);
		
		
		helper = new JPanel();
		helper.setLayout(new FlowLayout());
		cameraReset = new JButton("reset");
		cameraReset.setPreferredSize(new Dimension(140,20));
		cameraReset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				camerka.reset();
			}
		});
		helper.add(cameraReset);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		cameraWindow.add(helper, c);
		return cameraWindow;
	}
	
	public void setActBlock(Block block){
		actBlock = block;
	}
	
	public void updateBlockWindow(){
		blockData[0].setText(String.valueOf(actBlock.getType()));
		blockData[1].setText(String.valueOf(actBlock.getX()/2/Block.WIDTH));
		blockData[2].setText(String.valueOf(actBlock.getY()/2/Block.HEIGHT));
		blockData[3].setText(String.valueOf(actBlock.getZ()/2/Block.DEPTH));
	}
	
	public void updateCameraWindow(){
		cameraData[0].setText(String.valueOf((int)camerka.getPosition().x));
		cameraData[1].setText(String.valueOf((int)camerka.getPosition().y));
		cameraData[2].setText(String.valueOf((int)camerka.getPosition().z));
		cameraData[3].setText(String.valueOf((int)camerka.getPitch()));
		cameraData[4].setText(String.valueOf((int)camerka.getYaw()));
		cameraData[5].setText(String.valueOf((int)camerka.getRoll()));
	}
	
	public void setCamerka(Camerka camerka){
		this.camerka = camerka;
	}
	
	public int getSelectedType(){
		return selectedBlock.getSelectedIndex()+1;
	}
	
	public Block getActBlock() {
		return actBlock;
	}
	
}
