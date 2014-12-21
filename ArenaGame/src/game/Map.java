package game;

import java.awt.Graphics2D;

public class Map {
	public static final int size = 40;
	private Block[][] mapa;
	private Vector2f num;
	
	public Map(){
		
		num = new Vector2f(Window.WIDTH/size, Window.HEIGHT/size);
		Block.setSize(new Vector2f(Window.WIDTH/num.getX(),Window.HEIGHT/num.getY()));
		mapa = new Block[(int)num.getX()][(int)num.getY()];	
	}
	
	public void createRandomMap(int percentBlocks){
		for(int i=0 ; i<num.getX() ; i++){
			for(int j=0 ; j<num.getY() ; j++){
				if(Math.random()*100<percentBlocks)
					mapa[i][j] = new Block(i,j,1);
				else
					mapa[i][j] = new Block(i,j,0);
			}
		}
	}
	
	public void draw(Graphics2D g2){
		for(Block[] b:mapa){
			for(Block a:b){
				a.draw(g2);
			}
		}
	}
	
	public boolean exist(int x, int y){
		if(x>=0&&x<num.getX()&&y>0&&y<num.getY())
			return true;
		return false;
	}

	public Block[][] getMapa() {
		return mapa;
	}
}
