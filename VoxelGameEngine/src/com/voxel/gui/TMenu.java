package com.voxel.gui;

import glib.util.GLog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.voxel.main.MainVoxel2;

public class TMenu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private JMenu menuA;
	
	public TMenu(){
		if(MainVoxel2.OSLOOK){
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e2) {
				e2.printStackTrace();
			}
		}
		
		initMenuA();
	}

	private void initMenuA() {
		menuA = new JMenu("Files");
		add(menuA);
		
		initNM();
		initOM();
		initSM();
		initE();
		
	}
	
	private void initE() {
		JMenuItem menuItem = new JMenuItem("Exit", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na Exit","menu");
				System.exit(0);
			}
		});
		menuA.add(menuItem);
	}

	private void initSM() {
		JMenuItem menuItem = new JMenuItem("Save Map", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na Save Map","menu");
			}
		});
		menuA.add(menuItem);
	}

	private void initOM() {
		JMenuItem menuItem = new JMenuItem("Opem Map", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na Open Map","menu");
			}
		});
		menuA.add(menuItem);
	}

	private void initNM(){
		JMenuItem menuItem = new JMenuItem("New Map", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GLog.write("klikol si na New Map","menu");
			}
		});
		menuA.add(menuItem);
	}
}
