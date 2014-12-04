package Main;

public class Main {
	public static boolean vypisy = true;
	public static void main(String[] args){
		int i = 0;
		City city = new City("Gabos town");
		city.initCity();
		
		while(i<360){
			city.day();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.println(e);
				System.exit(1);
			}
			i++;
		}
	}
	public static void napis(String string) {
		if(!Main.vypisy){
			return;
		}
		System.out.println(string);
	}
}
