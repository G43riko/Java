package GameEngine;

public class Camera {
	public final static Vector3f yAxis = new Vector3f(0, 1, 0);
	
	private Vector3f pos;
	private Vector3f forward;
	private Vector3f up;
	private Vector2f centerPosition = new Vector2f(Window.getWidth()/2,Window.getHeight()/2);
	private boolean mouseLocked = false;
	
	public Camera(){
		this(new Vector3f(5,0,5), new Vector3f(0,0,1), new Vector3f(0,1,0));
	}
	
	public Camera(Vector3f pos, Vector3f forward, Vector3f up){
		this.up = up;
		this.forward = forward;
		this.pos = pos;
		forward.Normalized();
		pos.Normalized();
	}
	
	public void rotateY(float angle){
		Vector3f Haxis = yAxis.Cross(forward).Normalized();
		forward.rotate(angle, yAxis).Normalized();
		up = forward.Cross(Haxis).Normalized();
	}
	
	public void rotateX(float angle){
		Vector3f Haxis = yAxis.Cross(forward).Normalized();
		forward.rotate(angle, Haxis).Normalized();
		up = forward.Cross(Haxis).Normalized();
	}

	public void move(Vector3f dir, float amt){
		pos = pos.Add(dir.Mul(amt));
	}
	
	public void input(){
		float sensitivity = 0.3f;
		float movAmt = (float)(10 * Time.getDelta());
		float rotAmt = (float)(100 * Time.getDelta());
		
		if(Input.getKey(Input.KEY_ESCAPE)){
			Input.SetCursor(true);
			mouseLocked = false;
		}
		
		if(Input.GetMouse(0)){
			Input.SetMousePosition(centerPosition);
			Input.SetCursor(false);
			mouseLocked = true;
		}
		
		
		if(Input.getKey(Input.KEY_W)){
			move(getForward(),movAmt);
		}
		if(Input.getKey(Input.KEY_S)){
			move(getForward(),-movAmt);
		}
		if(Input.getKey(Input.KEY_A)){
			move(getLeft(),movAmt);
		}
		if(Input.getKey(Input.KEY_D)){
			move(getRight(),movAmt);
		}
		if(Input.getKey(Input.KEY_UP)){
			rotateX(-rotAmt);
		}
		if(Input.getKey(Input.KEY_DOWN)){
			rotateX(rotAmt);
		}
		if(Input.getKey(Input.KEY_LEFT)){
			rotateY(-rotAmt);
		}
		if(Input.getKey(Input.KEY_RIGHT)){
			rotateY(rotAmt);
		}
		if(Input.getKey(Input.KEY_SPACE)){
			move(new Vector3f(0,1,0),movAmt);
		}
		if(Input.getKey(Input.KEY_LSHIFT)){
			move(new Vector3f(0,1,0),-movAmt);
		}
		
		if(mouseLocked){
			Vector2f deltaPos = Input.GetMousePosition().Sub(centerPosition);
			
			boolean rotY =deltaPos.GetX() !=0;
			boolean rotX =deltaPos.GetY() !=0;

			if(rotY){
				rotateY(deltaPos.GetX() * sensitivity);
			}
			if(rotX){
				rotateX(-deltaPos.GetY() * sensitivity);
			}
			
			if(rotY || rotX){
				Input.SetMousePosition(centerPosition);
			}
		}
	}
	
	public Vector3f getRight(){
		return up.Cross(forward).Normalized();
	}
	
	public Vector3f getLeft(){
		return forward.Cross(up).Normalized();
	}
	
	public Vector3f getTop(){
		return getLeft().Cross(forward).Normalized();
	}
	
	public Vector3f getDown(){
		return getRight().Cross(forward).Normalized();
	}
	
	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getForward() {
		return forward;
	}

	public void setForward(Vector3f forward) {
		this.forward = forward;
	}

	public Vector3f getUp() {
		return up;
	}

	public void setUp(Vector3f up) {
		this.up = up;
	}
}
