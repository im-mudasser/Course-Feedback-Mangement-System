package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadandWrite {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String filePath = "C:\\Users\\Mudasser Shahzad\\eclipse-workspace\\ICT-PROJECT\\Feedback-files\\";
		File f = new File(filePath + "temp.txt");
		File r = new File(filePath + "fuck.txt");
		if (f.exists()) {
			f.renameTo(r);

		}

	}

	private static String readFromFile(File fileName) {
		BufferedReader reader = null;
		String line = null;
		String appendLine = "";
		try {
			reader = new BufferedReader(new FileReader(fileName));
			while ((line = reader.readLine()) != null) {
				if (appendLine == "") {
					appendLine = appendLine + line;
				} else {
					appendLine = appendLine + "\n" + line;
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("File is not found");
		} catch (IOException e) {
			System.out.println("Check Input/Output Please ");
		} finally {

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("Check Input/Output Please");
				}
			}
		}

		return appendLine;
	}
}
