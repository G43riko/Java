package glib.util.vector;

public class GMatrix4f
{
	private float[][] m;
	
	public GMatrix4f(){
		m = new float[4][4];
	}

	public GMatrix4f InitIdentity(){
		m[0][0] = 1;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = 1;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = 1;	m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;

		return this;
	}
	
	public GMatrix4f InitTranslation(float x, float y, float z){
		m[0][0] = 1;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = x;
		m[1][0] = 0;	m[1][1] = 1;	m[1][2] = 0;	m[1][3] = y;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = 1;	m[2][3] = z;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		
		return this;
	}
	
	public GMatrix4f InitRotation(float x, float y, float z){
		GMatrix4f rx = new GMatrix4f();
		GMatrix4f ry = new GMatrix4f();
		GMatrix4f rz = new GMatrix4f();
		
		x = (float)Math.toRadians(x);
		y = (float)Math.toRadians(y);
		z = (float)Math.toRadians(z);
		
		rz.m[0][0] = (float)Math.cos(z);rz.m[0][1] = -(float)Math.sin(z);rz.m[0][2] = 0;				 rz.m[0][3] = 0;
		rz.m[1][0] = (float)Math.sin(z);rz.m[1][1] = (float)Math.cos(z);rz.m[1][2] = 0;				 	 rz.m[1][3] = 0;
		rz.m[2][0] = 0;					rz.m[2][1] = 0;					rz.m[2][2] = 1;					 rz.m[2][3] = 0;
		rz.m[3][0] = 0;					rz.m[3][1] = 0;					rz.m[3][2] = 0;					 rz.m[3][3] = 1;
		
		rx.m[0][0] = 1;					rx.m[0][1] = 0;					rx.m[0][2] = 0;					 rx.m[0][3] = 0;
		rx.m[1][0] = 0;					rx.m[1][1] = (float)Math.cos(x);rx.m[1][2] = -(float)Math.sin(x);rx.m[1][3] = 0;
		rx.m[2][0] = 0;					rx.m[2][1] = (float)Math.sin(x);rx.m[2][2] = (float)Math.cos(x); rx.m[2][3] = 0;
		rx.m[3][0] = 0;					rx.m[3][1] = 0;					rx.m[3][2] = 0;					 rx.m[3][3] = 1;
		
		ry.m[0][0] = (float)Math.cos(y);ry.m[0][1] = 0;					ry.m[0][2] = -(float)Math.sin(y);ry.m[0][3] = 0;
		ry.m[1][0] = 0;					ry.m[1][1] = 1;					ry.m[1][2] = 0;					 ry.m[1][3] = 0;
		ry.m[2][0] = (float)Math.sin(y);ry.m[2][1] = 0;					ry.m[2][2] = (float)Math.cos(y); ry.m[2][3] = 0;
		ry.m[3][0] = 0;					ry.m[3][1] = 0;					ry.m[3][2] = 0;					 ry.m[3][3] = 1;
		
		m = rz.mul(ry.mul(rx)).GetM();
		
		return this;
	}
	
	public GMatrix4f InitScale(float x, float y, float z){
		m[0][0] = x;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = y;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = z;	m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		
		return this;
	}
	
	public GMatrix4f initPerspective(float fov, float aspectRatio, float zNear, float zFar){
		float tanHalfFOV = (float)Math.tan(fov / 2);
		float zRange = zNear - zFar;
		
		m[0][0]=1.0f/(tanHalfFOV * aspectRatio); m[0][1]=0;				 	m[0][2]=0;						m[0][3] = 0;
		m[1][0]=0;								 m[1][1]=1.0f/tanHalfFOV;	m[1][2]=0;						m[1][3]=0;
		m[2][0]=0;								 m[2][1]=0;				 	m[2][2]=(-zNear-zFar)/zRange;	m[2][3]=2*zFar*zNear/zRange;
		m[3][0]=0;								 m[3][1]=0;				 	m[3][2]=1;						m[3][3]=0;
		
		
		return this;
	}

	public GMatrix4f InitOrthographic(float left, float right, float bottom, float top, float near, float far){
		float width = right - left;
		float height = top - bottom;
		float depth = far - near;

		m[0][0] = 2/width;	m[0][1] = 0;		m[0][2] = 0;		m[0][3] = -(right + left)/width;
		m[1][0] = 0;		m[1][1] = 2/height;	m[1][2] = 0;		m[1][3] = -(top + bottom)/height;
		m[2][0] = 0;		m[2][1] = 0;		m[2][2] = -2/depth;	m[2][3] = -(far + near)/depth;
		m[3][0] = 0;		m[3][1] = 0;		m[3][2] = 0;		m[3][3] = 1;

		return this;
	}

	public GMatrix4f InitRotation(GVector3f forward, GVector3f up){
		GVector3f f = forward.normalize();
		
		GVector3f r = up.normalize();
		r = r.Cross(f);
		
		GVector3f u = f.Cross(r);

		return InitRotation(f, u, r);
	}

	public GMatrix4f InitRotation(GVector3f forward, GVector3f up, GVector3f right){
		GVector3f f = forward;
		GVector3f r = right;
		GVector3f u = up;

		m[0][0] = r.getX();	m[0][1] = r.getY();	m[0][2] = r.getZ();	m[0][3] = 0;
		m[1][0] = u.getX();	m[1][1] = u.getY();	m[1][2] = u.getZ();	m[1][3] = 0;
		m[2][0] = f.getX();	m[2][1] = f.getY();	m[2][2] = f.getZ();	m[2][3] = 0;
		m[3][0] = 0;		m[3][1] = 0;		m[3][2] = 0;		m[3][3] = 1;

		return this;
	}

	public GVector3f Transform(GVector3f r){
		return new GVector3f(m[0][0] * r.getX() + m[0][1] * r.getY() + m[0][2] * r.getZ() + m[0][3],
		                     m[1][0] * r.getX() + m[1][1] * r.getY() + m[1][2] * r.getZ() + m[1][3],
		                     m[2][0] * r.getX() + m[2][1] * r.getY() + m[2][2] * r.getZ() + m[2][3]);
	}
	
	public GMatrix4f mul(GMatrix4f r){
		GMatrix4f res = new GMatrix4f();
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				res.Set(i, j, m[i][0] * r.Get(0, j) +
							  m[i][1] * r.Get(1, j) +
							  m[i][2] * r.Get(2, j) +
							  m[i][3] * r.Get(3, j));
			}
		}
		
		return res;
	}
	
	public float[][] GetM(){
		float[][] res = new float[4][4];
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				res[i][j] = m[i][j];
		
		return res;
	}
	
	public float Get(int x, int y){return m[x][y];}

	public void SetM(float[][] m){this.m = m;}
	
	public void Set(int x, int y, float value){m[x][y] = value;}
}
