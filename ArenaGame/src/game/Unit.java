package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Unit {
	protected Vector2f pos;
	protected Vector2f dir;
	protected float speed;
	protected float radius;
	protected Color color;
	private static final float TARGET_DISTANCE = 20;
	
	private ArrayList<Vector2f> targets = new ArrayList<Vector2f>();
	
	public void fintPathTo(Vector2f target,Block[][] mapa){
		int[] actPos = new int[]{(int)pos.getX()/Map.size,(int)pos.getY()/Map.size};
		int[] goal = new int[]{(int)target.getX()/Map.size,(int)target.getY()/Map.size};
		boolean finding = true;
		if(!Map.exist(actPos[0], actPos[1], mapa)||mapa[actPos[0]][actPos[1]].getDistToGoal()<0){
			return;
		}
		while(finding){
			int smer = mapa[actPos[0]][actPos[1]].getDir();
//			if(mapa[actPos[0]][actPos[1]].dir2!=0&&Math.random()<0.5)
//				smer = mapa[actPos[0]][actPos[1]].dir2;
			if(smer==0){
				break;
			}
			Vector2f addable = new Vector2f(actPos[0]*Map.size+Map.size/2,actPos[1]*Map.size+Map.size/2);
			targets.add(addable);
			actPos[0] -= Map.DIRECTIONS[smer][0];
			actPos[1] -= Map.DIRECTIONS[smer][1];
		}
		for(int i=1 ; i+1<targets.size() ; i++){
			if(targets.get(i).getX()==targets.get(i-1).getX()){
				if(targets.get(i).getX()==targets.get(i+1).getX()){
					targets.remove(i);
					i--;
				}
			}
			else if(targets.get(i).getY()==targets.get(i-1).getY()){
				if(targets.get(i).getY()==targets.get(i+1).getY()){
					targets.remove(i);
					i--;
				}
			}
		}
		
		targets.add(target);
		//stop();
		setDirToTarget(targets.get(0));
	}

	public void move(Block[][] blocks){
		if(!targets.isEmpty()){
			if(targets.get(0).dist(pos)<TARGET_DISTANCE){
				targets.remove(0);
				if(!targets.isEmpty()){
					setDirToTarget(targets.get(0));
				}
//				else this.radius = 0;
			}
		}
		
		//pohne sa horizontálne
		pos.move(dir.getX()*speed, 0);
		Vector2f act = new Vector2f(pos.getX()/Map.size,pos.getY()/Map.size);
		if(Map.exist((int)act.getX(), (int)act.getY(), blocks))
			if(blocks[(int)act.getX()][(int)act.getY()].getType()==1){
				this.dir.setX(this.dir.getX()*-1);
				pos.move(dir.getX()*speed, 0);
			}
		
		//pohne sa vertikálne
		pos.move(0, dir.getY()*speed);
		act = new Vector2f(pos.getX()/Map.size,pos.getY()/Map.size);
		if(Map.exist((int)act.getX(), (int)act.getY(), blocks))
			if(blocks[(int)act.getX()][(int)act.getY()].getType()==1){
				this.dir.setY(this.dir.getY()*-1);
				pos.move(0, dir.getY()*speed);
			}
		
		//skontroluje okraje mapy
		if(pos.getX()<=0+radius||pos.getX()+radius>=Window.WIDTH){
			dir.setX(dir.getX()*-1);
		}
		if(pos.getY()<=0+radius||pos.getY()+radius>=Window.HEIGHT){
			dir.setY(dir.getY()*-1);
		}
		
	};
	
	public void draw(Graphics2D g2){
		g2.setColor(this.color);
		g2.fillArc((int)(pos.getX()-radius), (int)(pos.getY()-radius), (int)radius*2, (int)radius*2, 0, 360);
		
		g2.setColor(Color.BLACK);
		g2.drawArc((int)(pos.getX()-radius), (int)(pos.getY()-radius), (int)radius*2, (int)radius*2, 0, 360);
		
		g2.setColor(Color.red);
		if(!targets.isEmpty())
			g2.drawLine((int)pos.getX(), (int)pos.getY(), (int)targets.get(0).getX(), (int)targets.get(0).getY());
		
		for(int i=0 ; i+1<targets.size() ; i++){
			g2.drawLine((int)targets.get(i).getX(), (int)targets.get(i).getY(), (int)targets.get(i+1).getX(), (int)targets.get(i+1).getY());
		}
		
		
//		for(Vector2f bod:targets){
//			g2.setColor(Color.red);
//			g2.fillArc((int)bod.getX(), (int)bod.getY(), 4, 4, 0, 360);
//		}
	}
	
	private void setDirToTarget(Vector2f t){
		float randX = (float)Math.random()*TARGET_DISTANCE*2-TARGET_DISTANCE;
		float randY = (float)Math.random()*TARGET_DISTANCE*2-TARGET_DISTANCE;
		this.dir = new Vector2f(t.getX()-pos.getX(),t.getY()-pos.getY()).getNormalize();
	}
	
	public void addTarget(Vector2f t){
		targets.add(t);
	}
	
	public void stop(){
		this.dir = new Vector2f(0,0);
		System.out.println("stoplo sa to");
	}
	

	public void clear() {
		targets.clear();
		
	}
}
