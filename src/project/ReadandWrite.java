package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadandWrite {
	static String studentFeedbackFilesPath = ("C:\\Users\\Mudasser Shahzad\\eclipse-workspace\\ICT-PROJECT\\Feedback-files\\Student-feedback\\");
	static final Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		File std = new File(studentFeedbackFilesPath);
		File[] listOfFiles = std.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {

			System.out.println(listOfFiles[i].getName().replace(".txt", ""));
		}
		System.out.println(listOfFiles[0].renameTo(std));
		System.out.println("Enter the File Name");
		String fileName = keyboard.nextLine();
		search(new File(studentFeedbackFilesPath + fileName + ".txt"));
		percentage(new File(studentFeedbackFilesPath + fileName + ".txt"));

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