package TpMavena.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class  File {

	static String text="";
	
	public static String readFile() {
		 try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
	         String currentLine;
	         
	         while ((currentLine = br.readLine()) != null) {
	            text+=currentLine;
	         }
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		 return text;
	}
	
}
