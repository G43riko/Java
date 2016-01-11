package simplification;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import glib.cycle.GCanvasCicle;
import glib.util.Utils;
import glib.util.noise.PerlinNoise;

public class MainSimplification extends GCanvasCicle{
//	private ArrayList<Block> blocks = new ArrayList<Block>();
	private HashMap<Integer, Block> blocks = new HashMap<Integer, Block>(); 
	private int numX = 8;
	private int numY = 35;
	private int colors = 5; 
	private int w = getWidth() / numX;
	private int h = getHeight() / numY;
	private int divider = 255 / colors;
	
	private int hashedConstant = 1000000;
	
	public MainSimplification() {
		super(1280, 720, 30);
		System.out.println("W: " + w + ", H: " + h);
		float[][] data = PerlinNoise.GeneratePerlinNoise(PerlinNoise.generateWhiteNoise(numX, numY), 6, 0.7f, true);
		
		
		for(int i=0 ; i<numX ; i++)
			for(int j=0 ; j<numY ; j++)
//				blocks.add(new Block(i, j, w, h, (int)(data[i][j] * colors) * divider));
				put(i, j, new Block(i, j, w, h, (int)(data[i][j] * colors) * divider));
				
		

		int lastNum = blocks.size();
		int i=1;

		while(true){
			simplifiedVertical();
			blocks.entrySet().removeIf(a -> a.getValue().t < 0);
			if(blocks.size() == lastNum)
				break;
			lastNum = blocks.size();
		}
		while(true){
			simplifiedHorizontal();
			blocks.entrySet().removeIf(a -> a.getValue().t < 0);
			if(blocks.size() == lastNum)
				break;
			lastNum = blocks.size();
		}
		blocks.entrySet().removeIf(a -> a.getValue().t < 0);
//		blocks = blocks.entrySet().stream().filter(a -> !a.getValue().d).collect(Collectors.toCollection(HashMap<Integer, Block>::new));
		start();
	}

	private void simplifiedHorizontal(){
		for(Entry<Integer, Block> e : blocks.entrySet()){
			Block a = e.getValue();
			Block b = get(a.x + a.w / w, a.y);
			if(b == null || b.t < 0)
				return;
			if(b.t == a.t && b.h == a.h){
				a.w += b.w;
				b.t = -1;
			}
		}
	}
	
	private void simplifiedVertical(){
		for(Entry<Integer, Block> e : blocks.entrySet()){
			Block a = e.getValue();
			Block b = get(a.x, a.y + a.h / h);
			if(b == null || b.t < 0)
				return;
			if(b.t == a.t && b.w == a.w){
				a.h += b.h;
				b.t = -1;
			}
		}
	}
	
	@Override
	public void input() {
		
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics2D g2) {
		blocks.entrySet().stream().map(a -> a.getValue()).filter(a -> a.t >= 0).forEach(a -> {
			g2.setColor(new Color(a.t, 0, a.t));
			g2.fillRect(a.x * w, a.y * h, a.w, a.h);
		});
		g2.setColor(Color.DARK_GRAY);
		blocks.entrySet().stream().map(a -> a.getValue()).forEach(a -> g2.drawRect(a.x * w, a.y * h, a.w, a.h));

		g2.setColor(Color.BLACK);
		blocks.entrySet().stream().map(a -> a.getValue()).forEach(a -> g2.drawString(a.toString(), a.x * w + 5, a.y * h + 15));
		
		
//		blocks.stream().forEach(a -> {
//			g2.setColor(new Color(a.t, 0, a.t));
//			g2.fillRect(a.x * a.w, a.y * a.h, a.w, a.h);
//		});
//
//		g2.setColor(Color.BLACK);
//		blocks.parallelStream().forEach(a -> g2.drawRect(a.x * a.w, a.y * a.h, a.w, a.h));
	}

	public static void main(String[] args) {
		new MainSimplification();
	}
	
	private void put(int x, int y, Block b){
		blocks.put(hashVal(x, y), b);
	}
	
	private Block get(int x, int y){
		return blocks.get(hashVal(x, y));
	}
	
	private int hashVal(int x, int y){
		return (x * hashedConstant) + y;
	}
	
	private class Block{
		private int x, y;
		private int w, h;
		private int t;
		
		private Block(int x, int y, int w, int h, int t) {
			this.x = Integer.valueOf(x);
			this.y = Integer.valueOf(y);
			this.w = Integer.valueOf(w);
			this.h = Integer.valueOf(h);
			this.t = Integer.valueOf(t);
		}
		
		@Override
		public String toString() {
			return "x: " + x + ",y: " + y + ",w: " + w + ",h: " + h + ",t: " + t;
		}
	}
}
