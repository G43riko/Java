package Components;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Main.Main;


public class Warning {
	
	private String text;
	private Color color;
	private int live,size;
	private int Y,X;
	public Warning(String text){
		this.size=20;
		this.text=text;
		this.color=Color.red;
		this.live=100;
		this.Y = 400;
		if(Main.oznamenia.size()!=0){
			int lastY = Main.oznamenia.get((Main.oznamenia.size()-1)).Y;
			if(lastY>=this.Y-this.size){
				this.Y=lastY+this.size;
			}
		}
		this.X = 300;
		if(Main.gameIs==1){
			this.X=(int)(Main.players.x-Main.players.offsetX);
		}
	}
	
	public void draw(Graphics2D g2) {
		Font newFont = new Font("Dialog", Font.PLAIN, this.size);
		Font oldFont = g2.getFont();
		g2.setFont(newFont);
		g2.setColor(this.color);
		g2.drawString(this.text, this.X,this.Y);
		g2.setFont(oldFont);
		this.live--;
		this.Y--;
		if(this.live<=0){
			Main.oznamenia.remove(this);
		}
	}

}
