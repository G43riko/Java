import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyEvents implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getKeyCode());
		if(Main.gameIs==1){
			switch(arg0.getKeyCode()){
				case /*87*/38:
					if(Main.moveUpDown){
						Main.player.dy=-1;
					}
					break;
				case /*65*/37:
					Main.player.dx=-1;
					break;
				case /*83*/40:
					if(Main.moveUpDown){
						Main.player.dy=1;
					}
					break;
				case /*68*/39:
					Main.player.dx=1;
					break;
				case 32:
					Main.player.isShooting=true;
					break;
				case 27:
					Main.gameIs=2;
					break;
				case 49:
					if(Main.weaponType!="a"){
						Bullets.bulletAnum=1;
						Main.weaponType="a";
					}
					else{
						Bullets.bulletAnum++;
						if(Bullets.bulletAnum==10){
							Bullets.bulletAnum=1;
						}
					}
					
					break;
				case 50:
					Main.weaponType="b";
					break;
				case 51:
					if(Main.weaponType!="c"){
						Bullets.bulletCnum=1;
						Main.weaponType="c";
					}
					else{
						Bullets.bulletCnum++;
						if(Bullets.bulletCnum==5){
							Bullets.bulletCnum=1;
						}
					}
					break;

			}
		}
		else{
			switch(arg0.getKeyCode()){
				case 27:
					if(Main.gameIs==2){
						Main.gameIs=1;
					}
					else if(Main.gameIs==3){
						Main.gameIs=2;
					}
					break;
			}
			
		};
	};

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getKeyCode()){
			case /*87*/38:
				if(Main.moveUpDown){
					Main.player.dy=0;
				}
				break;
			case /*65*/37:
				Main.player.dx=0;
				break;
			case /*83*/40:
				if(Main.moveUpDown){
					Main.player.dy=0;
				}
				break;
			case /*68*/39:
				Main.player.dx=0;
				break;
			case 32:
				Main.player.isShooting=false;
				break;
		}
	};

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	};

}
