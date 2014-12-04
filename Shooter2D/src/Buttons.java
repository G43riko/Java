import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Buttons {
	private int x,y,width,height;
	private String text;
	private Color color,textColor;
	
	Buttons(int x,int y, int width, int height, String text){
		this.width=width;
		this.height=height;
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
		g.drawString(this.text, this.x+(this.width-this.text.length()*22)/2, this.y+35);
	};
	
	public boolean checkClick(int X, int Y){
		if( X>this.x && X<this.x+this.width && Y>this.y && Y<this.y+this.height){
			return true;
		}
		return false;
	};
}
