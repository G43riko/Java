package org.strategy.particles;

import java.util.ArrayList;

import org.engine.component.GameComponent;
import org.engine.physics.Enviroment;
import org.engine.rendeing.RenderingEngine;
import org.strategy.entity.player.Player;
import org.strategy.world.Block;

import glib.util.vector.GVector3f;

public class Explosion extends GameComponent{
	private class BlockPart{
		private Block b;
		private GVector3f dir;
		private GVector3f rot;
		private int life;
	}

	private ArrayList<BlockPart> blocks = new ArrayList<BlockPart>();
	
	//CONSTRUCTORS
	
	public Explosion(Block b,int num){
		super(b.getPosition(), GameComponent.EXPLOSION);
		GVector3f startPos = b.getPosition().sub(new GVector3f(Block.WIDTH, Block.HEIGHT, Block.DEPTH));
		GVector3f blockSize = new GVector3f((float)Block.WIDTH/(float)num, (float)Block.HEIGHT/(float)num, (float)Block.DEPTH/(float)num);
		for(int i=0 ; i<num ; i++){
			for(int j=0 ; j<num ; j++){
				for(int k=0 ; k<num ; k++){
					BlockPart p = new BlockPart();
					p.b = new Block(startPos.add(new GVector3f(i, j, k).mul(blockSize).mul(2)),b.getBlockType());
					p.b.setClickable(false);
					p.b.setScale(blockSize);
					p.b.setRotation(new GVector3f("rand").mul(360));
					p.dir = new GVector3f("rand").sub(0.5f).div(2);
					p.rot = new GVector3f("rand").mul(360).normalize();
					p.life = 30+(int)(Math.random()*50)-25;
					blocks.add(p);
				}
			}
		}
	}
	
	//OVERRIDES
	
	public void update(){
		ArrayList<BlockPart> forRemove = new ArrayList<BlockPart>();
		for(BlockPart b:blocks){
			b.b.move(b.dir);
			b.dir = b.dir.add(Enviroment.GRAVITY);
			b.b.rotate(b.rot);
			b.life--;
			b.b.setScale(b.b.getScale().sub(0.01f));
			if(b.life<=0 || b.b.getScale().isNegative()){
				forRemove.add(b);
			}
		}
		blocks.removeAll(forRemove);
	}
	
	public void render(RenderingEngine renderingEngine) {
		for(BlockPart b:blocks){
			b.b.render(renderingEngine);
		}
	}

	//GETTERS
	
	public ArrayList<BlockPart> getBlocks() {
		return blocks;
	}
	
}
