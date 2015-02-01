package minecraft2D.main;

import minecraft2D.core.GameCore;
import minecraft2D.core.Texture;

public class MainMinecraft extends GameCore{
	public MainMinecraft(){
		start();
	}
	
	protected void render(){
		Texture.debugTexture(new Texture(), 20, 20, 100, 100);
	}
}
