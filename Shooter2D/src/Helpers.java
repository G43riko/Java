
public class Helpers {
	
	public static int choose(int[] argc){
		int rand=argc[(int)Math.floor(Math.random()*argc.length)];
		return rand;
	};
	
	public static double average(double[] argc){
		double sum=0;
		for(int i=0 ; i<argc.length ; i++){
			sum+=argc[i];
		}
		return sum/argc.length;
	};
	
	public static double getDist(double ax, double ay, double bx, double by){
		double dist;
		dist=(ax-bx)*(ax-bx)+(ay-by)*(ay-by);
		dist=Math.sqrt(dist);
		return dist;
	};

	public static boolean collisionRectRect(int ax,int ay, int awidth, int aheight, int bx,int by, int bwidth, int bheight){
		  if((bx+bwidth>ax)&&
		    (by+bheight>ay)&&
		    (bx<ax+awidth)&&
		    (by<ay+aheight)){
		    return true;
		  }  
		return false;
	};
	
	public static boolean jePriesecnik(double a1x, double a1y, double a2x, double a2y, double bx, double by, double bradius){
		double r,k,q,a,b,c,x,y;
		r=bradius;
		x=a1x;
		y=a1y;
		a=(a1y-a2y)*-1;
		b=a1x-a2x;
		c=-a*x-b*y;
		if(b!=0){
			a/=b;
			c/=b;
			b=1;
		}
		q=c;
		k=a;
		r*=r;
		k*=k;
		q*=q;
		if(((4*(r*(1+k))-q))>=0){
			return true;
		}
		return false;
	}
	public static boolean isPriesecnik(double a1x, double a1y, double a2x, double a2y, double bx, double by, double bradius){
		double baX = a1x - a2x;
        double baY = a1y - a2y;
        double caX = bx - a2x;
        double caY = by - a2y;

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - bradius * bradius;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;
        
        if(disc >=1){
        	return true;
        }
        return false;
        /*
        double tmpSqrt = Math.sqrt(disc);
        double abScalingFactor1 = -pBy2 + tmpSqrt;
        double abScalingFactor2 = -pBy2 - tmpSqrt;

        double p1x=a1x-baX * abScalingFactor1;
        double p1y=a1y-baY * abScalingFactor1;
        if (disc > 0) {
        	double p2x=a1x-baX * abScalingFactor2;
            double p2y=a1y-baY * abScalingFactor2;
        }
        */
	}
	
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
	
	public static boolean collisionPointCircle(double ax, double ay, double bx, double by, double bradius){
		if(getDist(ax,ay,bx,by)<bradius){
			return true;
		}
		return false;
	};
}

