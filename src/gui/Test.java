package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		File file1 = new File("nonvulnerable.txt");
		File file2 = new File("vulnerable.txt");
		
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(file1));
			BufferedReader br2 = new BufferedReader(new FileReader(file2));
			String strLine1;
			String strLine2;
			
			while (((strLine1 = br1.readLine())!=null) && ((strLine2 = br2.readLine())!=null)) {
				if(!strLine1.equals(strLine2)) {
					System.out.println(strLine1);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
