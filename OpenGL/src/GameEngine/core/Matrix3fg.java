package GameEngine.core;

import org.lwjgl.util.vector.Matrix3f;

public class Matrix3fg extends Matrix3f{

	public float get(int i, int j){
		if(i==0){
			if(j==0)
				return this.m00;
			else if(j==1)
				return this.m01;
			else if(j==2)
				return this.m02;
		}
		else if(i==1){
			if(j==0)
				return this.m10;
			else if(j==1)
				return this.m11;
			else if(j==2)
				return this.m12;
		}
		else if(i==0){
			if(j==2)
				return this.m20;
			else if(j==1)
				return this.m21;
			else if(j==2)
				return this.m22;
		}
		return 0;
	}
}
