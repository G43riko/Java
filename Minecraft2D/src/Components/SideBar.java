package Components;
import java.awt.Color;
import java.awt.Graphics2D;

import Main.Main;


public class SideBar {
	private int x,y,w,h,num;
	public int isSelect;
	public Item[] items;
	
	public SideBar(int num,int size){
		this.x=(Main.WIDTH-size*num)/2;
		this.y=Main.HEIGHT-size-20;
		this.w=size*num;
		this.h=size;
		this.num=num;
		this.isSelect=0;
		this.items=new Item[num];
	}
	
	public void draw(Graphics2D g2){
		g2.setColor(Color.WHITE);
		g2.fillRect(this.x, this.y, this.w, this.h);
		g2.setColor(Color.BLACK);
		for(int i=0 ; i<this.num ; i++){
			if(i==this.isSelect){
				g2.setColor(Color.LIGHT_GRAY);
				g2.fillRect(this.x+i*this.h, this.y, this.h, this.h);
				g2.setColor(Color.BLACK);
			}
			g2.drawRect(this.x+i*this.h, this.y, this.h, this.h);
		}
	}
	
	public boolean click(double cx, double cy) {
		for(int i=0 ; i<this.num ; i++){
			if(cx>this.x+i*this.h&&
			   cy>this.y&&
			   cx<this.x+i*this.h+this.h&&
			   cy<this.y+this.h){
				this.setTo(i);
				return true;
			}
		}
		return false;
	}
	
	private void setTo(int co) {
		this.isSelect=co;
	}
}
