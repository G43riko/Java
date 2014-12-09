package Buildings.Houses;

public class Villa extends House{

	public Villa() {
		super(5,20);
		najom = 300;
	}
	
	public void zvacsNajom(){
		najom *= 1.3;
	}

}
