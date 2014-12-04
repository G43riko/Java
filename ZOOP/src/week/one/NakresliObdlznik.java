package week.one;

public class NakresliObdlznik {
	public static void main(String args[]){
		NakresliObdlznik.drawRect(10, 5, '*','X');
	}
	
	public static void drawRect(int w, int h,char ram,char medz){
		int i,j;
		for(i=0 ; i<w ; i++){
			System.out.print(ram);
		}
		System.out.println("");
		for(i=0 ; i<h-2 ; i++){
			System.out.print(ram);
			for(j=0 ; j<w-2 ; j++){
				System.out.print(medz);
			}
			System.out.println(ram);
		}
		for(i=0 ; i<w ; i++){
			System.out.print(ram);
		}
	}
}
