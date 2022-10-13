package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CourseFeedbackManagementSystem {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String adminPassword = "123";
		String adminUsername = "admin";
		System.out.println("\t Course Feedback Management System");
		String mainmenu = "\t1.Admin\t2.Student";
		System.out.println(mainmenu);

		int choice = keyboard.nextInt();

		switch (choice) {
		case 1:

			System.out.println("Enter username");
			String username = keyboard.next();
			System.out.println("Enter the password");
			String password = keyboard.next();
			if (username.equals(adminUsername) && password.equals(adminPassword))
				adminLogin(adminUsername);
			else if (!password.equals(adminPassword)) {
				System.out.println("Password is wrong");
			} else {
				System.out.println("Username is wrong");
			}
			break;
		case 2:
			System.out.println("Enter your username");
			String inputStdUserName = keyboard.next();
			System.out.println("Enter password");
			String inputStdPassword = keyboard.next();
			if (loginValidation(inputStdUserName, inputStdPassword)) {
				System.out.println("Welcome " + inputStdUserName);
			} else {
				System.out.println("Sorry password and username doesnot match");
			}

			break;

		default:
			invalidChoice(choice);

		}
		keyboard.close();

	}

	private static boolean loginValidation(String inputStdUserName, String inputStdPassword) {
		boolean found = false;
		String getuserNameAndPassword = readFromFile(new File("StudentsID.txt"));
		String line[] = getuserNameAndPassword.split("\\r?\\n");
		for (int i = 0; i < line.length; i++) {
			int splitLocation = line[i].indexOf(",");
			String stdUserName = line[i].substring(0, splitLocation);
			String stdPassword = line[i].substring(splitLocation + 1);
			if (inputStdUserName.equals(stdUserName) && inputStdPassword.equals(stdPassword)) {
				found = true;
				break;

			} else {
				found = false;
				continue;
			}

		}

		return found;

	}

	private static void invalidChoice(int choice) {
		throw new IllegalArgumentException("Unexpected value: " + choice);
	}

	private static void adminLogin(String adminUsername) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Welcome " + adminUsername);
		System.out.println("1.Add Student");
		System.out.println("2.Edit Students");
		System.out.println("3.Delete Students");
		int choice = keyboard.nextInt();
		switch (choice) {
		case 1:
			do {
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter("./StudentsID.txt", true));
					System.out.println("Enter the student-id");
					String studentUserName = keyboard.next();
					System.out.println("Enter the student password");
					String studentPassword = keyboard.next();
					writer.write(studentUserName + "," + studentPassword);
					writer.newLine();
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Do you want to enter more students y/n");

			} while (keyboard.next().equals("y"));
			break;
		case 2:
			System.out.println("Enter the user name ");
			String editUserName = keyboard.next();
			System.out.println("Enter the password");
			String editUserpassword = keyboard.next();
			editStudentData(editUserName, editUserpassword);
			keyboard.close();
			break;
		case 3:
			System.out.println("Enter the user name ");
			String delUserName = keyboard.next();
			System.out.println("Enter the password");
			String delUserpassword = keyboard.next();
			delStudentData(delUserName, delUserpassword);
			break;
		default:
			invalidChoice(choice);
		}// end of switch
	} // end of method

	private static void delStudentData(String delUserName, String delUserpassword) {
		Scanner keyboard = new Scanner(System.in);
		String fileName = "./StudentsID.txt";
		File oldfileName = new File(fileName);
		File tempFile = new File("./temp.txt");
		File newFile = new File(fileName);
		String getdataFromFile = readFromFile(oldfileName);
		String[] line = getdataFromFile.split("\\r?\\n");
		for (int i = 0; i < line.length; i++) {
			int splitLocation = line[i].indexOf(",");
			String stdUserName = line[i].substring(0, splitLocation);
			String stdPassword = line[i].substring(splitLocation + 1);

			if (delUserName.equals(stdUserName) && delUserpassword.equals(stdPassword)) {
				continue;

			} else {
				writetoFile(tempFile, stdUserName, stdPassword);
			}
		}

		oldfileName.delete();
		tempFile.renameTo(newFile);
		System.out.println("delete succefully!");
		keyboard.close();

	}

	private static void editStudentData(String editUserName, String editUserpassword) {
		Scanner keyboard = new Scanner(System.in);
		String fileName = "./StudentsID.txt";
		File oldfileName = new File(fileName);
		File tempFile = new File("./temp.txt");
		File newFile = new File(fileName);
		String getdataFromFile = readFromFile(oldfileName);
		String[] line = getdataFromFile.split("\\r?\\n");
		for (int i = 0; i < line.length; i++) {
			int splitLocation = line[i].indexOf(",");
			String stdUserName = line[i].substring(0, splitLocation);
			String stdPassword = line[i].substring(splitLocation + 1);

			if (editUserName.equals(stdUserName) && editUserpassword.equals(stdPassword)) {
				System.out.println("Enter the new user name");
				String newUserName = keyboard.next();
				System.out.println("Enter the new user password");
				String newUserPassword = keyboard.next();
				writetoFile(tempFile, newUserName, newUserPassword);

			} else {
				writetoFile(tempFile, stdUserName, stdPassword);
			}
		}

		oldfileName.delete();
		tempFile.renameTo(newFile);
		System.out.println("upated!");
		keyboard.close();

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

	private static void writetoFile(File fileName, String UserName, String Userpassword) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName, true));
			writer.write(UserName + "," + Userpassword);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}