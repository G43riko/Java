package CSFLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCSVLoader {

	public static void main(String[] args) {
		String input = "Adam;2;5;1;5;3;4\n"
					  +"Fero;1;4;2;5;22;7\n"
					  +"Dusan;7;3;4;2;1;15\n"
					  +"Brano;2222;2;22;11;111\n";
		
		Arrays.stream(input.split("\n")).forEach(a -> {
			ArrayList<String> words = new ArrayList<String>(Arrays.asList(a.split(";")));
			String name = words.remove(0);
			
			Stream<String> s =  words.stream();
			IntSummaryStatistics result = s.collect(Collectors.summarizingInt(p -> Integer.valueOf(p)));
			//System.out.println("meno: " + name + " max: " + result.getMax() + " min: " + result.getMin());
		});

		System.out.println(otocRetazecPreGabka("gabriel"));
	}
	
	public static String otocRetazecPreGabka(String retazec){
        String opak="";
        int lenght=retazec.length();
        
        for(int i=lenght-1;i>=0;i--)
            opak=opak+retazec.charAt(i);
        
        return opak;
    }

}
