package Files;

public class Main {
	private GFile subor;
	
	public static void main(String[] args) {
		Main index = new Main();
		index.subor = new GFile("test.txt");
		
		String riadok = index.subor.readLine(3);//za��na sa 0
		System.out.println("hladan� riadok je: "+riadok);
		
		String subor = index.subor.readFile();
		System.out.println("Obsah suboru je: \n"+ subor);
	}

}
