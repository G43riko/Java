package week.two;

public class Cestujuci {
	private static int numOfInstances=0;
	
	public Cestujuci(){
		Cestujuci.numOfInstances++;
	};
	
	public static int getNumOfInstances(){
		return Cestujuci.numOfInstances;
	};
}
