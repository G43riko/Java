package Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GFile {
	private File file;
	private BufferedReader reader=null;
	
	public GFile(String name){
		this.file=new File(name);
	};
	
	public String readLine(int lineNum){
		try {
			this.reader = new BufferedReader(new FileReader(this.file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = null;
		int i=0;
		boolean found=false;
		try {
			while((line=this.reader.readLine())!=null){
				if(i==lineNum){
					found=true;
					break;
				}
				i++;
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		if(!found){
			line="žiadny";
		}
		return line;
	};
	
	public String readFile(){
		
		try {
			this.reader = new BufferedReader(new FileReader(this.file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String fileContent = "";
		String line;
		try {
			while((line=this.reader.readLine())!=null){
				fileContent=fileContent+line+"\n";
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileContent;
	}
}
