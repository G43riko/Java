package Tests;

import org.lwjgl.util.vector.Vector3f;

public class Particle {
	private Vector3f position;
	private Vector3f color;
	private Vector3f direction;
//	private float size = 100; //v percentách
	private float life = 0; //zaèína nulou
	private float maxLife; //nastaví sa na zaèiatku
	private float maxSize; //nastaví sa na zaèiatku
	private float speed; //nastaví sa na zaèiatku
	private float gravity;
	private float spl; //vypoèíta sa na zaèiatku// size per life
	private boolean isDwindle; //èi sa zmenšuje
	
	public Particle(){
		if(isDwindle){
			spl = maxSize/maxLife;
		}
	}
	
	public boolean move(){
		position.x += direction.x * speed;
		position.y += direction.y * speed - gravity;
		position.z += direction.z * speed;
		
		life++;
		
		float  size = maxSize;
		if(isDwindle){
			size -= life*spl;
		}
		
		if(life==maxLife){
			return true;
		}
		return false;
	}
	
	
}
