package Tests;

import org.lwjgl.util.vector.Vector3f;

public class Particle {
	private Vector3f position;
	private Vector3f color;
	private Vector3f direction;
//	private float size = 100; //v percent�ch
	private float life = 0; //za��na nulou
	private float maxLife; //nastav� sa na za�iatku
	private float maxSize; //nastav� sa na za�iatku
	private float speed; //nastav� sa na za�iatku
	private float gravity;
	private float spl; //vypo��ta sa na za�iatku// size per life
	private boolean isDwindle; //�i sa zmen�uje
	
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
