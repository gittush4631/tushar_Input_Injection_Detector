package gui;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

	public void write(String sb) {
		FileWriter fw;
		try {
			fw = new FileWriter("vulnerable.txt", true);
			fw.write(sb.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        
	}
}
