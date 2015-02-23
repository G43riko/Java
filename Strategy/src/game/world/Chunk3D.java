package game.world;

import game.object.GameObject;
import game.rendering.RenderingEngine;
import glib.util.vector.GVector3f;

public class Chunk3D extends GameObject{
	public static final int NUM_X = 16;
	public static final int NUM_Y = 8;
	public static final int NUM_Z = 16;
	
	private Chunk3D[] neighboards = new Chunk3D[4];
	public Block[][][] blocks = new Block[NUM_X][NUM_Y][NUM_Z];
	
	public Chunk3D(GVector3f position) {
		super(position, 9);
		create();
	}
	
	private void create(){
		for(int i=0 ; i<NUM_X ; i++){
			for(int k=0 ; k<NUM_Z ; k++){
				float height = World.map[i+getPosition().getXi()/2][k+getPosition().getZi()/2]*NUM_Y;
				for(int j=0 ; j<NUM_Y ; j++){
					
					int type = (int)(Block.blockDatas.size()*Math.random())+1;
					
//					type = (int)(Block.blockDatas.size()*height)+1;
//					System.out.println(height);
					//if(j<height)
//					System.out.println(i+" "+j+" "+k);
					if(height>j)
					blocks[i][j][k] = new Block(getPosition().add(new GVector3f(i,j/*(int)(height*10-5)*/,k).mul(new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH).mul(2))),type);
				}
			}
		}
	}
	
	public boolean exist(int x, int y, int z, boolean checkNull){
		if(checkNull)
			return x>=0 && z>=0 && y>=0 && y<NUM_Y && x<NUM_X && z<NUM_Z && blocks[x][y][z]!=null;
		else
			return x>=0 && z>=0 && y>=0 && y<NUM_Y && x<NUM_X && z<NUM_Z;
	}
	
	public void render(RenderingEngine renderingEngine) {
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				for(int k=0 ; k<NUM_Z ; k++){
					Block b = blocks[i][j][k];
					if(b!=null && b.isActive() && b.getType()>0){
						b.render(renderingEngine);
					}
				}
			}
		}
	}
	
	public void setSides(){
		for(int i=0 ; i<NUM_X ; i++){
			for(int j=0 ; j<NUM_Y ; j++){
				for(int k=0 ; k<NUM_Z ; k++){
					Block b = blocks[i][j][k];
					if(b==null){
						continue;
					}
					b.setSide(0, !exist(i, j+1, k ,true));
					b.setSide(2, !exist(i, j-1, k ,true));
					
					b.setSide(3, !exist(i+1, j, k ,true));
					b.setSide(1, !exist(i-1, j, k ,true));
					
					b.setSide(4, !exist(i, j, k+1 ,true));
					b.setSide(5, !exist(i, j, k-1 ,true));
					
					if(j==0)
						b.setSide(2, false);
					
					if(i==0 && neighboards[3] != null && neighboards[3].exist(NUM_X-1, j, k,true))
						b.setSide(1, false);
					if(i+1==NUM_X && neighboards[1] != null && neighboards[1].exist(0, j, k,true))
						b.setSide(3, false);
					
					if(k==0 && neighboards[2] != null && neighboards[2].exist(i, j, NUM_Z-1,true))
						b.setSide(5, false);
					if(k+1==NUM_Z && neighboards[0] != null && neighboards[0].exist(i, j, 0,true))
						b.setSide(4, false);
				}
			}
		}
	}
	
	public Block getBlock(int i, int j, int k){
		return blocks[i][j][k];
	}
	
	public Block getTop(int i, int k){
		for(int j=NUM_Y-1 ; j>=0 ; j--){
			if(blocks[i][j][k] != null)
				return blocks[i][j][k];
		}
		return null;
	}
	
	public void setNeighboard(int i, Chunk3D n){
		neighboards[i] = n;
	}
}
