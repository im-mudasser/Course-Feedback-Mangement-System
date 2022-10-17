package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ReadandWrite {
	static File filename = new File(
			"C:\\Users\\Mudasser Shahzad\\eclipse-workspace\\ICT-PROJECT\\Feedback-files\\Student-feedback\\");
	// static String filename = "C:\\Users\\Mudasser
	// Shahzad\\eclipse-workspace\\ICT-PROJECT\\Feedback-files\\Student-feedback\\";

	public static void main(String[] args) {
		File file = new File("./bc.txt");
		System.out.println(file);
		clearConsole();
	}

	private static void clearConsole() {

		for (int i = 0; i < 100; i++) {
			System.out.println("");
		}
	}
//	private static String readFromFile(File fileName) {
//		BufferedReader reader = null;
//		String line = null;
//		String appendLine = "";
//		try {
//			reader = new BufferedReader(new FileReader(fileName));
//			while ((line = reader.readLine()) != null) {
//				if (appendLine == "") {
//					appendLine = appendLine + line;
//				} else {
//					appendLine = appendLine + "\n" + line;
//				}
//
//			}
//
//		} catch (FileNotFoundException e) {
//			System.out.println("File is not found");
//		} catch (IOException e) {
//			System.out.println("Check Input/Output Please ");
//		} finally {
//
//			if (reader != null) {
//				try {
//					reader.close();
//				} catch (IOException e) {
//					System.out.println("Check Input/Output Please");
//				}
//			}
//		}
//
//		return appendLine;
//	}
//
//}

	private static void writetoFile(File fileName, String question) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName, true));
			writer.write(question);
			writer.newLine();
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found !!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("'Error in the IO exception");
			e.printStackTrace();
		}

	}
}