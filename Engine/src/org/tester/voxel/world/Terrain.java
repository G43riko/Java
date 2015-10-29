package org.tester.voxel.world;

import org.engine.app.GameAble;
import org.engine.component.GameComponent;
import org.engine.rendering.RenderingEngine;

import glib.math.GMath;
import glib.util.noise.ProceduralTerrain;
import glib.util.vector.GVector3f;

public class Terrain extends GameComponent{
	private Block[][][] blocks;
	
	
	public Terrain(GameAble parent, int numX, int numY, int height){
		super(parent);
		createTerrain(numX, numY, height);
	}

	private void createTerrain(int numX, int numY, int height) {
		blocks = new Block[numX][height * 2][numY];
		
		float[][] mapa = ProceduralTerrain.create(numX, numY);
		GVector3f off = new GVector3f(numX, 0, numY);
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numY ; j++){
				float res = GMath.between(mapa[i][j], 0, 1);
				GVector3f pos = new GVector3f(i*Block.WIDTH*2,(int)(mapa[i][j]*height/16)*2,j*Block.DEPTH*2);
				blocks[i][(int)(res * height)][j] = new Block(getParent(), Blocks.getRandomType(),pos.sub(off));
			}
		}
	}
	@Override
	public void render(RenderingEngine renderingEngine) {
		for(int i=0 ; i<blocks.length ; i++){
			for(int j=0 ; j<blocks[i].length ; j++){
				for(int k=0 ; k<blocks[i][j].length ; k++){
					if(blocks[i][j][k] != null && renderingEngine.getMainCamera().isVisible(blocks[i][j][k].getPosition(), new GVector3f(1)))
						blocks[i][j][k].render(renderingEngine);
				}
			}
		}
	}
}
