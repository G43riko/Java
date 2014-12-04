package PathFinder_2;
import java.awt.Color;
import java.awt.Graphics;

public class Mapa {
	public int type,direction,stepsFromGoal;
	public double distToGoal;
	
	public Mapa(int type){
		this.type=type;
		this.stepsFromGoal=this.direction=0;
		this.distToGoal=-1;
	};
	
	public static void create(int sizeY,int sizeX){
		Main.map=new Mapa[sizeX][sizeY];
		for(int i=0 ; i<sizeX ; i++){
			for(int j=0 ; j<sizeY ; j++){
				int co=(int)Math.floor(Math.random()*100);
				if(co==0){
					Main.map[i][j]=new Mapa(3);
				}
				else{
					Main.map[i][j]=new Mapa(0);
				}
			}
		}
	};
	
	public static void draw(Graphics g){
		for( int i=0 ; i<Main.map.length ; i++){
			for( int j=0 ; j<Main.map[i].length ; j++){
				g.setColor(PathFinding.getColor(Main.map[i][j].type));
				g.fillRect(i*Main.block, j*Main.block, Main.block, Main.block);
				g.setColor(Color.BLACK);
				g.drawRect(i*Main.block, j*Main.block, Main.block, Main.block);
				g.setColor(Color.BLACK);
				g.drawString(Double.toString(Main.map[i][j].distToGoal), i*Main.block, j*Main.block+10);
				g.drawString(Integer.toString(Main.map[i][j].direction), i*Main.block+Main.block-8, j*Main.block+Main.block);
				g.drawString(Integer.toString(Main.map[i][j].stepsFromGoal), i*Main.block, j*Main.block+Main.block);
			}
		}
	};
}
