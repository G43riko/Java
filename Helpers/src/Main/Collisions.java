package Main;

public class Collisions {
	public static boolean collisionRectRect(int ax,int ay, int awidth, int aheight, int bx,int by, int bwidth, int bheight){
		  if((bx+bwidth>ax)&&
		    (by+bheight>ay)&&
		    (bx<ax+awidth)&&
		    (by<ay+aheight)){
		    return true;
		  }  
		return false;
	};

	public static boolean collisionCircleCircle(double ax, double ay, int asize, double bx, double by, int bsize){
		double distX=ax-bx;
		double distY=ay-by;
		int rad_a=asize;
		int rad_b=bsize;
		double dist = Math.sqrt((distX * distX) + (distY * distY));
		if(dist <=(rad_a+rad_b)){
			return false;
		}
		return false;
	};
	
	public static boolean collisionPointRec(double ax, double ay, int awidth, int aheight, double bx,double by){
		if(bx>ax&&bx<ax+awidth&&by>ay&&by<ay+aheight){
			return true;
		}
		return false;
	};
	
//	public static boolean collisionPointCircle(double ax, double ay, double bx, double by, double bradius){
//		if(Distances.getDist2f(ax,ay,bx,by)<bradius){
//			return true;
//		}
//		return false;
//	};
}
