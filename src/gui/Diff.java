package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Diff {
	public static void readersAsText(Reader r1, String name1, Reader r2,
			String name2, List<String> diffs) throws IOException {
		LineNumberReader reader1 = new LineNumberReader(r1);
		LineNumberReader reader2 = new LineNumberReader(r2);
		String line1 = reader1.readLine();
		String line2 = reader2.readLine();
		while ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
			System.out.println("line1: " + line1 + "\nline2: " + line2);
			if (!line1.equals(line2)) {
				diffs.add("File \"" + name1 + "\" and file \"" + name2
						+ "\" differ at line " + reader1.getLineNumber() + ":"
						+ "\n" + line1 + "\n" + line2);
				break;
			}
			line1 = reader1.readLine();
			line2 = reader2.readLine();
		}
		if ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null)
			diffs.add("File \"" + name2 + "\" has extra lines at line "
					+ reader2.getLineNumber() + ":\n" + line2);
		if ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null)
			diffs.add("File \"" + name1 + "\" has extra lines at line "
					+ reader1.getLineNumber() + ":\n" + line1);
	}
	
	public static void main(String[] args) {
		File file1 = new File("nonvulnerable.txt");
		File file2 = new File("vulnerable.txt");
		
		try {
			
			Reader fr1 = new FileReader(file1);
			Reader fr2 = new FileReader(file2);
			List<String> list = new ArrayList<>();
			BufferedReader br1 = new BufferedReader(fr1);
			BufferedReader br2 = new BufferedReader(fr2);
					
			try {
//				readersAsText(fr1, file1.getName(), fr2, file2.getName(), list);
				String strLine1;
				String strLine2;
				
				while (((strLine1 = br1.readLine())!=null) && ((strLine2 = br2.readLine())!=null)) {
					if(!strLine1.equals(strLine2)) {
						System.out.println(strLine1);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
}