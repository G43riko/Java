import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Checkbox {
	private int x,y,width,height;
	private String text;
	private Color color,textColor;
	public boolean observer;
	
	Checkbox(int x,int y, int width, int height, String text, boolean observer){
		this.width=width;
		this.height=height;
		this.observer=observer;
		this.x=x;
		if(x==0){
			this.x=Main.WIDTH/2-this.width/2;
		}
		this.y=y;
		if(y==0){
			this.y=Main.HEIGHT/2-this.height/2;
		}
		this.text=text;
		this.color=Color.cyan;
		this.textColor=Color.BLACK;
	};
	
	public void draw(Graphics g){
		g.setColor(this.color);
		g.fillRoundRect(this.x, this.y, this.width, this.height, 10, 10);
		g.setColor(this.textColor);
		g.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 36));
		g.drawString(this.text, this.x+(this.width-this.text.length()*22)/2-this.height/2, this.y+35);
		if(this.observer){
			g.setColor(Color.GREEN);
		}
		else{
			g.setColor(Color.RED);
		}
		g.fillRoundRect(this.x+this.width-this.height, this.y, this.height, this.height, 10, 10);
	};
	
	public boolean checkClick(int X, int Y){
		if( X>this.x && X<this.x+this.width && Y>this.y && Y<this.y+this.height){
			return true;
		}
		return false;
	};
	
	public boolean change(){
		if(this.observer){
			this.observer=false;
			return false;
		}
		else{
			this.observer=true;
			return true;
		}
	}
}
