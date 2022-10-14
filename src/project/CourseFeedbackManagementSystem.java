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
		System.out.println("4.Feedback");
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
		case 4:
			String feedbackMenu = "1.Create Feedbaack\n2.Add Questions\n3.Edit Questions in feedback\n4.delete feedback ";
			System.out.println(feedbackMenu);
			System.out.println("Enter the choice");
			choice = keyboard.nextInt();
			keyboard.nextLine();
			switch (choice) {
			case 1:

				System.out.println("Enter the filename for ex : feedbackcourseName");
				String fileName = keyboard.nextLine();
				createFeedback(fileName);
				break;
			case 2:
				System.out.println("Enter the feedback file name to add the questions");
				fileName = keyboard.nextLine();
				do {
					System.out.println("Enter the question");
					String question = keyboard.nextLine();
					writetoFile(new File(fileName), question);
					System.out.println("Do you want to enter more question press y other wise n : y/n");
				} while (keyboard.nextLine().equals("y"));
				System.out.println("Questions has be added succesfully!");
				break;
			case 3:
				editFeedback();
				break;
			case 4:
				String deleteMenu = "1.Delete the entire feedback\n2.Delete the questions within the feedback";
				System.out.println(deleteMenu);
				System.out.println("Enter the choice");
				choice = keyboard.nextInt();
				switch (choice) {
				case 1:
					keyboard.nextLine();
					System.out.println("Enter the file name");
					fileName = keyboard.nextLine();
					deleteEntireFeedback(new File(fileName));
					break;
				case 2:
					deleteFeedbackQuestion();
					break;
				default:
					invalidChoice(choice);
				}

				break;

			default:
				invalidChoice(choice);
			} // end of inner switch
			break; // outer switch
		default:
			invalidChoice(choice);
		}// end of switch
	} // end of method

	private static void deleteEntireFeedback(File fileName) {
		String filePath = "C:\\Users\\Mudasser Shahzad\\eclipse-workspace\\ICT-PROJECT\\Feedback-files\\";
		File file = new File(filePath + fileName + ".txt");

		if (file.exists()) {
			file.delete();
			System.out.println("successfully deleted!");
		} else {
			System.out.println("File could not be deleted!");
		}

	}

	private static void createFeedback(String fileName) {

		File feedBackFile = new File(
				"C:\\Users\\Mudasser Shahzad\\eclipse-workspace\\ICT-PROJECT\\Feedback-files\\" + fileName + ".txt");
		try {
			if (feedBackFile.createNewFile())
				System.out.println("File created successfully!" + feedBackFile.getName());
			else

				System.out.println(fileName + "File already exist");

		}

		catch (IOException e) {
			System.out.println("Error occurs while creating File");
			e.printStackTrace();
		}
	}

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

	private static void deleteFeedbackQuestion() {
		Scanner keyboard = new Scanner(System.in);
		String filePath = "C:\\Users\\Mudasser Shahzad\\eclipse-workspace\\ICT-PROJECT\\Feedback-files\\";
		File tempFile = new File(filePath + "temp.txt");
		System.out.println("Enter the file name");
		String editFileName = keyboard.nextLine();
		File oldFile = new File(filePath + editFileName + ".txt");
		String getdataFromFile = readFromFile(oldFile);
		System.out.println(getdataFromFile);
		System.out.println("Which Question do you want to delete");
		String deleteQuestion = keyboard.nextLine();
		String[] line = getdataFromFile.split("\\r?\\n");
		for (int i = 0; i < line.length; i++) {
			String question = null;
			int splitLocation = line[i].indexOf(".");
			int endOfQueston = line[i].indexOf(":");
			String questionName = line[i].substring(0, splitLocation);
			String questionData = line[i].substring(splitLocation + 1, endOfQueston);
			String feedbackPart = line[i].substring(endOfQueston);
			if (deleteQuestion.equals(questionName)) {
				continue;
			} else {
				question = questionName + "." + questionData + feedbackPart;
				writetoFile(tempFile, question);
			}

		}

		File newFile = new File(filePath.trim() + editFileName.trim() + ".txt");
		oldFile.delete();
		if (tempFile.exists()) {
			tempFile.renameTo(newFile);
		} else {
			System.out.println("Error occur while renamming the file!");
		}
		System.out.println("Delete sucessfully!");
		keyboard.close();
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

	private static void editFeedback() {
		Scanner keyboard = new Scanner(System.in);
		String filePath = "C:\\Users\\Mudasser Shahzad\\eclipse-workspace\\ICT-PROJECT\\Feedback-files\\";
		File tempFile = new File(filePath + "temp.txt");
		System.out.println("Enter the file name");
		String editFileName = keyboard.nextLine();
		File oldFile = new File(filePath + editFileName + ".txt");
		String getdataFromFile = readFromFile(oldFile);
		System.out.println(getdataFromFile);
		System.out.println("Which Question do you want to edit");
		String editQuestion = keyboard.nextLine();
		String[] line = getdataFromFile.split("\\r?\\n");
		for (int i = 0; i < line.length; i++) {
			String question = null;
			int splitLocation = line[i].indexOf(".");
			int endOfQueston = line[i].indexOf(":");
			String questionName = line[i].substring(0, splitLocation);
			String questionData = line[i].substring(splitLocation + 1, endOfQueston);
			String feedbackPart = line[i].substring(endOfQueston);
			if (editQuestion.equals(questionName)) {
				System.out.println("Type question ");
				String editQuestionData = keyboard.nextLine();
				question = questionName + "." + editQuestionData + feedbackPart;
				writetoFile(tempFile, question + feedbackPart);
			} else {
				question = questionName + "." + questionData + feedbackPart;
				writetoFile(tempFile, question);
			}

		}

		File newFile = new File(filePath.trim() + editFileName.trim() + ".txt");
		oldFile.delete();
		if (tempFile.exists()) {
			tempFile.renameTo(newFile);
		} else {
			System.out.println("Error occur while renamming the file!");
		}
		System.out.println("Edit sucessfully!");
		keyboard.close();
	}

}