import java.awt.Graphics;

public class Units {
	private UnitA[] a;
	
	public Units(){
		this.a=new UnitA[Main.maxUnits];
		
		for(int i=0 ; i<Main.maxUnits ; i++){
			a[i]=null;
			a[i]=UnitA.createUnitA("gabriel");
		}
	};
	
	public void draw(Graphics g){
		for(int i=0 ; i<Main.maxUnits ; i++){
			if(a[i]!=null){
				a[i].draw(g);
			}
		}
	};
	
	public void move(){
		for(int i=0 ; i<Main.maxUnits ; i++){
			if(a[i]!=null){
				a[i].move();
			}
		}
	}
}