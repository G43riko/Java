import glib.util.GColor;
import glib.util.noise.PerlinNoise;
import glib.util.noise.SimplexNoise;

import java.awt.Color;
import java.awt.Graphics;


public class G2D {
	private float[][] mapa;
	private Block[][] blocks;
	private int width = 10;
	private int height = 10;
	private static int index = 1;
	private class Block{
		
		public int x;
		public int y;
		public int w;
		public int h;
		private Color c;
		boolean draw;
		boolean same(Block b){
			return c.equals(b.c);
		}
	}
	
	G2D(){
		
		int x = Main.WIDTH/width;
		int y = Main.HEIGHT/height;
		System.out.println(x+" "+y);
		mapa = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(x, y), 8, 0.7f, true);
//		mapa = SimplexNoise.generateOctavedSimplexNoise(x, y, 6, 0.8f, 0.008f);
		Main.keyboard.setG2D(this);
		blocks = new Block[mapa.length][mapa[0].length];
		for(int i=0 ; i<mapa.length ; i++){
			for(int j=0 ; j<mapa[i].length ; j++){
				float color = mapa[i][j]*255;
				color = Math.max(0,Math.min(255, color));
				Color actColor;
				if(color < 51)
					actColor = (new GColor(51,0,0,255));
				else if(color < 102)
					actColor = (new GColor(102,0,0,255));
				else if(color < 153)
					actColor = (new GColor(153,0,0,255));
				else if(color < 204)
					actColor = (new GColor(204,0,0,255));
				else
					actColor = (new GColor(255,0,255,255));
				
//				g.setColor(new GColor((int)color,(int)color,(int)color,255));
				blocks[i][j] = new Block();
				blocks[i][j].x = i;
				blocks[i][j].y = j;
				blocks[i][j].w = 1;
				blocks[i][j].h = 1;
				blocks[i][j].c = actColor;
			}
		}
	};
	
	public void drawMenu(Graphics g){
		g.setColor(GColor.DARK_GRAY);
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
	};
	
//	public void simplification(){
//		for(int i=0 ; i<mapa.length ; i++){
//			for(int j=0 ; j<mapa[i].length ; j++){
//				
//				Block b = blocks[i][j];
//				for(int k=b.w ; k<b.w*2 ; k*=2){
//					if(exist(i+k,j) && b.same(blocks[i+k][j]) && 
//					   exist(i,j+k) && b.same(blocks[i][j+k]) &&
//					   exist(i+k,j+k) && b.same(blocks[i+k][j+k]) && 
//					   
//					   blocks[i][j+k].w == b.w && blocks[i][j+k].h == b.h &&
//					   blocks[i+k][j].w == b.w && blocks[i+k][j].h == b.h &&
//					   blocks[i+k][j+k].w == b.w && blocks[i+k][j+k].h == b.h &&
//					   
//					   blocks[i+k][j].y == b.y && blocks[i+k][j].x == b.x+k &&
//					   blocks[i][j+k].x == b.x && blocks[i][j+k].y == b.y+k &&
//					   blocks[i+k][j+k].x == b.x+k && blocks[i+k][j+k].y == b.y+k ){
//						System.out.println( "yeah");
//						b.w *=2;
//						b.h *=2;
//						for(int l=0 ;l<b.w ; l++)
//							for(int m=0 ;m<b.h ; m++)
//								if(exist(i+l, j+m))
//									blocks[i+l][j+m] = b;
//						i=0;
//						j=0;
//						break;
//								
//					}
//				}
//			}
//		}
//	}
	
	public void simplification(){
		for(int i=0 ; i<mapa.length ; i++){
			for(int j=0 ; j<mapa[i].length ; j++){
				Block b = blocks[i][j];
				boolean lavo = true;
				for(int x=0 ; x<b.h ; x++){
					if(exist(i+b.w, j+x) && !(blocks[i+b.w][j+x].w==0) || !b.same(blocks[i+b.w][j+x])){
						lavo = false;
						break;
					}
				}
				if(lavo){
					for(int x=0 ; x<b.h ; x++){
						blocks[b.x+b.w][b.y+x]=b;
					}
					b.w++;
				}
			}
		}
	}
	
	private boolean exist(int x,int y){
		if(x>=0 && y>=0 && x<blocks.length && y<blocks[x].length && blocks[x][y]!=null)
			return true;
		return false;
	}
	
	public void drawGame(Graphics g){
		g.setColor(GColor.BLACK);
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
		
		if(Main.gameIs==1){
			for(int i=0 ; i<mapa.length ; i++){
				for(int j=0 ; j<mapa[i].length ; j++){
					if(exist(i, j))
					blocks[i][j].draw = false;
				}
			}
			for(int i=0 ; i<mapa.length ; i++){
				for(int j=0 ; j<mapa[i].length ; j++){
					if(exist(i, j) && !blocks[i][j].draw){
						g.setColor(blocks[i][j].c);
						g.fillRect(i*width, j*height, blocks[i][j].w*width, blocks[i][j].h*height);
						
						g.setColor(Color.black);
						g.drawRect(i*width, j*height, blocks[i][j].w*width, blocks[i][j].h*height);
						blocks[i][j].draw = true;
					}
				}
			}
		}
	};
	
	public void drawPause(Graphics g){
		
	}
}
