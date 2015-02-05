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
		public Color c;
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
//		//funguje ale blbne
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
	
//	public void simplification(){
//		//nefunguje
//		for(int i=0 ; i<mapa.length ; i++){
//			for(int j=0 ; j<mapa[i].length ; j++){
//				Block b = blocks[i][j];
//				boolean lavo = true;
//				for(int x=0 ; x<b.h ; x++){
//					if(exist(i+b.w, j+x) && !(blocks[i+b.w][j+x].w==0) || !b.same(blocks[i+b.w][j+x])){
//						lavo = false;
//						break;
//					}
//				}
//				if(lavo){
//					for(int x=0 ; x<b.h ; x++){
//						blocks[b.x+b.w][b.y+x]=b;
//					}
//					b.w++;
//				}
//			}
//		}
//	}
	
//	public void simplification(){
//		//funguje
//		for(int i=0 ; i<mapa.length ; i++){
//			for(int j=0 ; j<mapa[i].length ; j++){
//				Block b = blocks[i][j];
//				for(int k=1 ; ; k++){
//					if(exist(i+k,j) && blocks[i+k][j].same(b) && blocks[i+k][j]!=b){
//						b.w += blocks[i+k][j].w;
//						blocks[i+k][j] = b;
//						continue;
//					}
//					break;
//				}
//			}
//		}
//		
//		for(int i=0 ; i<mapa.length ; i++){
//			for(int j=0 ; j<mapa[i].length ; j++){
//				Block b = blocks[i][j];
//				if(exist(i,j+b.h) &&blocks[i][j+b.h].same(b) && blocks[i][j+b.h].w == b.w && blocks[i][j+b.h]!=b && blocks[i][j+b.h].x == b.x && blocks[i][j+b.h].h == b.h){
//					for(int k=0 ; k<b.w ; k++){
//						if(exist(i+k,j+b.h))
//						blocks[i+k][j+b.h] = b;
//						uspi(10);
//					}
//					b.h += b.h;
//				}
//			}
//		}
//	}
	
//	public void simplification(){
//		//funguje
//		for(int a=1 ; a <= 8 ; a *= 2){
//			for(int i=0 ; i<mapa.length ; i++){
//				for(int j=0 ; j<mapa[i].length ; j++){
//					Block b = blocks[i][j];
//					int k = a;
//					if(exist(i, j+k) && blocks[i][j+k].same(b) && blocks[i][j+k].w == b.w && blocks[i][j+k].h == b.h && blocks[i][j+k]!=b &&
//					   exist(i+k, j) && blocks[i+k][j].same(b) && blocks[i+k][j].w == b.w && blocks[i+k][j].h == b.h && blocks[i+k][j]!=b &&
//					   exist(i+k, j+k) && blocks[i+k][j+k].same(b) && blocks[i+k][j+k].w == b.w && blocks[i+k][j+k].h == b.h && blocks[i+k][j+k]!=b &&
//					   b.x ==i && b.y == j &&
//					   blocks[i+k][j+k].x == i+k && blocks[i+k][j+k].y == j+k && blocks[i+k][j].x == i+k && blocks[i+k][j].y == j && blocks[i][j+k].x == i && blocks[i][j+k].y == j+k){
//						boolean change = true;
//						for(int l=0 ;l<k*2 ; l++)
//							for(int m=0 ;m<k*2 ; m++)
//								if(!exist(i+l, j+m) || !blocks[i+l][j+m].same(b) || blocks[i+l][j+m].w != b.w || blocks[i+l][j+m].h != b.h &&
//									blocks[i+l][j+m].x < b.x || blocks[i+l][j+m].y < b.y)
//									change = false;
//						if(change){
//							nahrad(blocks[i+b.w][j], blocks[i][j]);
//							nahrad(blocks[i][j+b.h], blocks[i][j]);
//							nahrad(blocks[i+b.w][j+b.h], blocks[i][j]);
//							b.h *= 2;
//							b.w *= 2;
//						}
//					}
//				}
//			}
//		}
//		for(int a=1 ; a <= 8 ; a *= 2){
//			for(int i=0 ; i<mapa.length ; i++){
//				for(int j=0 ; j<mapa[i].length ; j++){
//					Block b = blocks[i][j];
//					if(b.x == i && b.y == j && exist(i+b.w, j) && blocks[i+b.w][j].x == i+b.w && blocks[i+b.w][j].y == j && blocks[i+b.w][j].same(b) &&
//					   blocks[i+b.w][j].w == b.w && blocks[i+b.w][j].h == b.h && blocks[i+b.w][j].y == b.y ){
//						nahrad(blocks[i+b.w][j], blocks[i][j]);
//						b.w *= 2;
//					}
//					if(b.x == i && b.y == j && exist(i, j+b.h) && blocks[i][j+b.h].x == i && blocks[i][j+b.h].y == j+b.h && blocks[i][j+b.h].same(b) &&
//						blocks[i][j+b.h].w == b.w && blocks[i][j+b.h].h == b.h && blocks[i][j+b.h].x == b.x ){
//						nahrad(blocks[i][j+b.h], blocks[i][j]);
//						b.h *= 2;
//					}
//				}
//			}
//		}
//	}
	public void simplification(){
		for(int i=0 ; i<mapa.length ; i++){
			for(int j=0 ; j<mapa[i].length ; j++){
				Block b = blocks[i][j];
				if(b.x == i && b.y == j){
					boolean Cright = true;
					boolean Cdown = true;
					while(Cright || Cdown){
						kukni(b,1);
						int canRight = 0;
						int canDown = 0;
						if(index % 2 == 0){
							if(Cright && exist(i+b.w, j) && blocks[i+b.w][j].same(b) && blocks[i+b.w][j].x == i+b.w && blocks[i+b.w][j].y == j && blocks[i+b.w][j].h == b.h){
								int len = blocks[i+b.w][j].w;
								nahrad(blocks[i+b.w][j], blocks[i][j]);
								b.w += len;
								Cdown = true;
								Cright = false;
							}
							else
								Cright = false;
							
							if(Cdown && exist(i, j+b.h) && blocks[i][j+b.h].same(b) && blocks[i][j+b.h].x == i && blocks[i][j+b.h].y == j+b.h && blocks[i][j+b.h].w == b.w){
								int len = blocks[i][j+b.h].h;
								nahrad(blocks[i][j+b.h], blocks[i][j]);
								b.h += len;
	
								Cdown = false;
								Cright = true;
							}
							else
								Cdown = false;
						}
						else{
							if(Cdown && exist(i, j+b.h) && blocks[i][j+b.h].same(b) && blocks[i][j+b.h].x == i && blocks[i][j+b.h].y == j+b.h && blocks[i][j+b.h].w == b.w){
								int len = blocks[i][j+b.h].h;
								nahrad(blocks[i][j+b.h], blocks[i][j]);
								b.h += len;

								Cdown = false;
								Cright = true;
							}
							else
								Cdown = false;
							
						
							if(Cright && exist(i+b.w, j) && blocks[i+b.w][j].same(b) && blocks[i+b.w][j].x == i+b.w && blocks[i+b.w][j].y == j && blocks[i+b.w][j].h == b.h){
								int len = blocks[i+b.w][j].w;
								nahrad(blocks[i+b.w][j], blocks[i][j]);
								b.w += len;
								Cdown = true;
								Cright = false;
							}
							else
								Cright = false;
							
						}
					}
				}
			}
		}
		index++;
	}
	private void nahrad(Block a, Block b){
		int XS = a.w;
		int YS = a.h;
		int X = a.x;
		int Y = a.y;
		for(int l=0 ;l<XS ; l++){
			for(int m=0 ;m<YS ; m++){
				if(exist(X+l, Y+m))
					blocks[X+l][Y+m] = b;
			}
		}
		
	}
		
	private void kukni(Block b,int t){
		Color oldC = b.c;
		b.c = Color.WHITE;
		uspi(t);
		b.c = oldC;
	}
	
	private void uspi(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
