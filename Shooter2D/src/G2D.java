import java.awt.Color;
import java.awt.Graphics;


public class G2D {
	public Buttons newGame,exit,mainMenu,options,toPause,resume;
	public Checkbox sound,debbuging,moveUpDown,toCenterAfterGetDemage,bulletAreBouncing;
	G2D(){
		this.newGame=new Buttons(0,Main.HEIGHT/2-25,400,50,"Nov· hra");
		this.exit=new Buttons(0,Main.HEIGHT/2+75,400,50,"UkonËiù hru");
		this.mainMenu=new Buttons(0,Main.HEIGHT/2+175,400,50,"HlavnÈ menu");
		this.options=new Buttons(0,Main.HEIGHT/2+75,400,50,"Nastavenia");
		this.toPause=new Buttons(0,Main.HEIGHT/2+225,400,50,"Nasp‰ù");
		this.resume=new Buttons(0,Main.HEIGHT/2-125,400,50,"PokraËovaù");
		
		this.sound=new Checkbox(0,Main.HEIGHT/2-275,400,50,"Zvuky",Main.sound);
		this.toCenterAfterGetDemage=new Checkbox(0,Main.HEIGHT/2-175,400,50,"Zarovnanie",Main.toCenterAfterGetDemage);
		this.debbuging=new Checkbox(0,Main.HEIGHT/2-75,400,50,"⁄daje",Main.debbuging);
		this.moveUpDown=new Checkbox(0,Main.HEIGHT/2+25,400,50,"Hore a dole",Main.moveUpDown);
		this.bulletAreBouncing=new Checkbox(0,Main.HEIGHT/2+125,400,50,"Gumene strely",Main.bulletAreBouncing);
	}
	public void clickIn(int X, int Y){
		if(this.newGame.checkClick(X, Y)&& (Main.gameIs==0||Main.gameIs==2)){
			Main.newGameInit();
			for(int i=0 ; i<Main.EnemyNum ; i++){
				Main.enemies[i]=Enemy.createEnemy();
			}
			Main.player=new Player(390,530);
			Main.gameIs=1;
		}
		else if(this.exit.checkClick(X, Y)&&Main.gameIs==0){
			Main.display.stop();
			System.exit(0);
		}
		else if(this.mainMenu.checkClick(X, Y)&&Main.gameIs==2){
			Main.gameIs=0;
		}
		else if(this.options.checkClick(X, Y)&&Main.gameIs==2){
			Main.gameIs=3;
		}
		else if(this.toPause.checkClick(X, Y)&&Main.gameIs==3){
			Main.gameIs=2;
		}
		else if(this.resume.checkClick(X, Y)&&Main.gameIs==2){
			Main.gameIs=1;
		}
		else if(this.sound.checkClick(X, Y)&&Main.gameIs==3){
			Main.sound=this.sound.change();
		}
		else if(this.debbuging.checkClick(X, Y)&&Main.gameIs==3){
			Main.debbuging=this.debbuging.change();
		}
		else if(this.moveUpDown.checkClick(X, Y)&&Main.gameIs==3){
			Main.moveUpDown=this.moveUpDown.change();
		}
		else if(this.toCenterAfterGetDemage.checkClick(X, Y)&&Main.gameIs==3){
			Main.toCenterAfterGetDemage=this.toCenterAfterGetDemage.change();
		}
		else if(this.bulletAreBouncing.checkClick(X, Y)&&Main.gameIs==3){
			Main.bulletAreBouncing=this.bulletAreBouncing.change();
		}
	};
	
	public void drawMenu(Graphics g){
		g.setColor(Color.darkGray);
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
		
		this.newGame.draw(g);
		this.exit.draw(g);
	};

	public void drawGame(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
		
		for( int i=0 ; i<Main.EnemyNum ; i++){
			if(Main.enemies[i]!=null){
				Main.enemies[i].draw(g);
			}
		};
		
		Main.player.draw(g);
		Main.bullets.draw(g);
		
		g.setColor(Color.white);
		if(Main.debbuging){
			g.drawString("Score: "+Integer.toString(Main.score), 0, 10);
			g.drawString("Healt: "+Integer.toString(Main.player.healt), 0, 20);
			g.drawString("Number of enemies: "+Main.EnemyNum, 0, 30);
			g.drawString("Max bullets: "+Main.BulletNum, 0, 40);
			g.drawString("Number of bullets: "+Main.bullets.getNum(), 0, 50);
			
			
			int num=0;
			for(int i=0 ; i<Main.EnemyNum ; i++){
				if(Main.enemies[i].isExploding){
					num+=Main.enemies[i].particles.length;
				}
			};
			num+=Main.bullets.getNumPart();
			g.drawString("Number of particles: "+num, 0, 60);
		}
	};

	public void drawPause(Graphics g) {
		this.drawGame(g);
		g.setColor(new Color(35,35,35,200));
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
		this.resume.draw(g);
		this.newGame.draw(g);
		this.mainMenu.draw(g);
		this.options.draw(g);
	};
	
	public void drawOptions(Graphics g){
		this.drawGame(g);
		g.setColor(new Color(35,35,35,200));
		g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);
		this.sound.draw(g);
		this.debbuging.draw(g);
		this.moveUpDown.draw(g);
		this.toCenterAfterGetDemage.draw(g);
		this.bulletAreBouncing.draw(g);
		this.toPause.draw(g);
		
	};
}
