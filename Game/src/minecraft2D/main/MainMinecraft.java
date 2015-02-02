package minecraft2D.main;

import minecraft2D.core.Block;
import minecraft2D.core.GameCore;
import minecraft2D.core.Shader;
import minecraft2D.core.Texture;

public class MainMinecraft extends GameCore{
	private Block b;
	private Shader s;
	private Texture t;
	public MainMinecraft(){
		b = new Block(20,20,new Texture("image.png"));
		s = new Shader("shader");
		t = new Texture("image.png");
		start();
	}
	
	protected void render(){
		
		b.render(s);
//		Texture.debugTexture(new Texture("image.png"), 0, 0, MainMinecraft2D.WIDTH, MainMinecraft2D.HEIGHT);
	}
}
