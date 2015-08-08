package streams;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainStream {
	 private static SecureRandom random = new SecureRandom();
	 
	public static void main(String[] args) {

		double start = System.currentTimeMillis();
		
		ArrayList<String> array = new ArrayList<String>();
		
		for(int i=0 ; i<1E5 ; i++)
			array.add(new BigInteger(130, random).toString(32));
		
		long res = array.stream().filter(a->a.startsWith("e")).collect(Collectors.counting());
		
		System.out.println("poèet je: " + res + " z " + array.size());
		
		System.out.println(System.currentTimeMillis() - start);
	}

}
