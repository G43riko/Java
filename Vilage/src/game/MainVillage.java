package game;

import java.util.ArrayList;

import game.vilage.view.Window;
import game.vilage.view.component.ResourceSelector;

public class MainVillage {

	public static void main(String[] args) {
		Window w = new Window();
		w.init();
		w.add(new ResourceSelector("nazov", 200));
		w.repaint();
		w.setVisible(true);
	}

}

