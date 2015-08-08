package PathFinder_4;

import glib.util.vector.GVector2f;

import java.awt.Graphics2D;

public class Map {
	private Block[][] map;
	private GVector2f blockSize;
	private int numX;
	private int numY;
	public Map(int width, int height, int numX, int numY) {
		this.numX = numX;
		this.numY = numY;
		map = new Block[numX][numY];
		blockSize = new GVector2f(width / numX, height / numY);
	}

	public void render(Graphics2D g2) {
		for(int i=0 ; i<numX ; i++){
			for(int j=0 ; j<numY ; j++){
				g2.setColor(map[i][j].getColor());
				g2.fillRect(blockSize.getXi() * i, blockSize.getYi() * j, blockSize.getXi(), blockSize.getYi());
			}
		}
	}

}
