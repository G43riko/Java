package game.components;

import java.util.ArrayList;

import game.object.GameObject;
import game.rendering.RenderingEngine;
import game.world.Block;
import glib.util.vector.GVector3f;

public class Explosion extends GameObject{
	private class BlockPart{
		private Block b;
		private GVector3f dir;
		private GVector3f rot;
	}
	
	private ArrayList<BlockPart> blocks = new ArrayList<BlockPart>();
	
	public Explosion(Block b,int num){
		super(b.getPosition(), 14);
		GVector3f startPos = b.getPosition().sub(new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH));
		GVector3f blockSize = new GVector3f((float)Block.WIDTH/(float)num, (float)Block.HEIGHT/(float)num, (float)Block.DEPTH/(float)num);
		for(int i=0 ; i<num ; i++){
			for(int j=0 ; j<num ; j++){
				for(int k=0 ; k<num ; k++){
					BlockPart p = new BlockPart();
					p.b = new Block(startPos.add(new GVector3f(i, j, k).mul(blockSize).mul(2)),b.getBlockType());
					p.b.setScale(blockSize.div(2));
					p.b.setRotation(new GVector3f("rand").mul(360));
					p.dir = new GVector3f("rand").sub(0.5f).div(2);
					p.rot = new GVector3f("rand").mul(360).normalize();
					blocks.add(p);

					
				}
			}
		}
	}
	
	public void update(){
		for(BlockPart b:blocks){
			b.b.move(b.dir);
			b.dir = b.dir.sub(Player.GRAVITY);
			b.b.rotate(b.rot);
		}
		System.out.println("tu to je");
	}
	
	public void render(RenderingEngine renderingEngine) {
		for(BlockPart b:blocks){
			b.b.render(renderingEngine);
		}
	}
	
}
