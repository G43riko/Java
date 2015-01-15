package Goniometry;

public class GeomTester {

	public static void main(String[] args) {
		Vector3f vec = new Vector3f(0,0,1);
		for(int i=0 ;i<2000000 ; i++){
			System.out.println(vec);
			vec = vec.Rotate(new Vector3f(0,1,0), (float)Math.toRadians(1));
			//vec = vec.Rotate(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(1)));
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


}
