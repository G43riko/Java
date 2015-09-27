package org.tester;


import org.engine.app.Controlable;
import org.engine.app.GameAble;
import org.engine.core.CoreEngine;
import org.engine.core.Input;
import org.engine.core.Window;


public class CoreGame extends CoreEngine implements Controlable{
	private static final long serialVersionUID = 1L;

	private static final int MAIN_MENU = 0;
	private static final int LOADING = 1;
	private static final int RUNNING = 2;
	
	private GameAble game;
	private boolean exit;
//	private Menu mainMenu;
	private int gameIs = MAIN_MENU;
	
	public CoreGame(){
	}
	
	@Override
	public void init() {
//		mainMenu = new MainMenu(this);
	}

	@Override
	public void exit() {
	}

	@Override
	public void newGame() {
//		game = new GameTest(this);
//		game = new GameVoxel(this);
		game = new GameBomber(this);
		gameIs = RUNNING;
	}

	@Override
	public void initGame() {
	}

	@Override
	public Window getWindow() {
		return null;
	}

	@Override
	public GameAble getGame() {
		return null;
	}

	@Override
	protected void render() {
		if(gameIs == RUNNING)
			game.render(getRenderingEngine());
//		else if(gameIs == MAIN_MENU)
//			mainMenu.render(getRenderingEngine());
	}

	@Override
	protected void update() {
		if(gameIs == RUNNING)
			game.update(1);
//		else if(gameIs == MAIN_MENU)
//			mainMenu.update(1);;
	}

	@Override
	protected void input() {
		
		if(Input.getKeyDown(Input.KEY_LCONTROL))
			newGame();
		
		if(gameIs == RUNNING)
			game.input();
//		else if(gameIs == MAIN_MENU)
//			mainMenu.input();;
	}


}
