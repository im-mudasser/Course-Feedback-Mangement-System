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
	static String adminFeedbackFilesPath = ("C:\\Users\\Mudasser Shahzad\\eclipse-workspace\\ICT-PROJECT\\Feedback-files\\created-by-admin-files\\");
	static String studentFeedbackFilesPath = ("C:\\Users\\Mudasser Shahzad\\eclipse-workspace\\ICT-PROJECT\\Feedback-files\\Student-feedback\\");
	static final Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		mainmenu();
	}

	private static void StudentfeedbackPanel(String inputStdUserName) {

		System.out.println("Welcome" + inputStdUserName);
		System.out.print("1.FeedBack Panel\t2.Logout");
		System.out.println("\nEnter the choice");
		String choice = keyboard.nextLine();
		if (choice.equals("1")) {
			studentFeedBackPanelMenu(inputStdUserName);
		}

		else if (choice.equals("2"))

		{

			mainmenu();

		} else {
			invalidChoice(choice);
		}

	}

	private static void studentFeedBackPanelMenu(String inputStdUserName) {

		System.out.println("\t\t\t\t\t b. Back to main menu");
		File getAdminFeedbackFiles = new File(adminFeedbackFilesPath);
		File[] listOfFiles = getAdminFeedbackFiles.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			System.out.println(i + 1 + "." + listOfFiles[i].getName().replace(".txt", ""));

		}
		System.out.println("To open a file kindly enter the choice");
		String choice = keyboard.nextLine();
		switch (choice) {
		case "1":
			File fileName = listOfFiles[0];
			openFeedBack(fileName, inputStdUserName);

			break;
		case "2":
			fileName = listOfFiles[1];
			openFeedBack(fileName, inputStdUserName);
			break;
		case "3":
			fileName = listOfFiles[2];
			openFeedBack(fileName, inputStdUserName);
			break;
		case "4":
			fileName = listOfFiles[3];
			openFeedBack(fileName, inputStdUserName);
			break;
		case "b":
			mainmenu();
			break;
		default:
			invalidChoice(choice);
			mainmenu();
			break;

		}

	}

	private static void mainmenu() {

		String adminPassword = "123";
		String adminUsername = "admin";
		System.out.println("\t Course Feedback Management System");
		String mainmenu = "\t1.Admin\t2.Student";
		System.out.println(mainmenu);

		String choice = keyboard.nextLine();

		switch (choice) {
		case "1":

			System.out.println("Enter username");
			String username = keyboard.nextLine();
			System.out.println("Enter the password");
			String password = keyboard.nextLine();
			if (username.equals(adminUsername) && password.equals(adminPassword))
				adminLogin(adminUsername);
			else if (!password.equals(adminPassword)) {
				System.out.println("Password is wrong");
			} else {
				System.out.println("Username is wrong");
			}
			break;
		case "2":
			System.out.println("Enter your username");
			String inputStdUserName = keyboard.nextLine();
			System.out.println("Enter password");
			String inputStdPassword = keyboard.nextLine();
			if (loginValidation(inputStdUserName, inputStdPassword)) {
				StudentfeedbackPanel(inputStdUserName);

			} else {
				System.out.println("Sorry password and username doesnot match");
			}

			break;

		default:
			invalidChoice(choice);
			mainmenu();

		}

	}

	private static void openFeedBack(File fileName, String inputStdUserName) {

		File stdFeedBackFile = new File(studentFeedbackFilesPath + inputStdUserName + "-" + fileName.getName());
		String getData = readFromFile(fileName);
		String[] line = getData.split("\\r?\\n");
		String questionData = null;
		String answer = null;
		String exitButton = "e.Exit";
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t" + exitButton);
		if (stdFeedBackFile.exists()) {
			System.out.println("Already submitted the feedback for this course");
		} else {
			for (int i = 0; i < line.length; i++) {
				System.out.println(line[i]);
				int splitLocation = line[i].indexOf(":");
				questionData = line[i].substring(0, splitLocation + 1);
				String answers = line[i].substring(splitLocation + 2);
				String[] splitanswers = answers.split(" +");

				System.out.println("Enter the choice");
				String choice = keyboard.nextLine();

				if (choice.equals("1")) {
					answer = splitanswers[0];
					writetoFile(stdFeedBackFile, questionData + " " + answer);

				} else if (choice.equals("2")) {
					answer = splitanswers[1];
					writetoFile(stdFeedBackFile, questionData + " " + answer);

				} else if (choice.equals("3")) {
					answer = splitanswers[2];
					writetoFile(stdFeedBackFile, questionData + " " + answer);

				} else if (choice.equals("4")) {
					answer = splitanswers[3];
					writetoFile(stdFeedBackFile, questionData + " " + answer);

				} else if (choice.equals("5")) {
					answer = splitanswers[4];
					writetoFile(stdFeedBackFile, questionData + " " + answer);

				} else {
					invalidChoice(choice);

				}

			}
			System.out.println("Enter the comment about this course");

			String comment = keyboard.nextLine();

			writetoFile(stdFeedBackFile, "Student Comment : " + " " + inputStdUserName + "\n" + comment);
			System.out.println("Successfully submit! thanks");
		}

		while (true) {
			System.out.println("Press e for exit ");
			String choice = keyboard.nextLine();
			if (choice.equals("e")) {
				studentFeedBackPanelMenu(inputStdUserName);
				break;

			} else {
				invalidChoice(choice);

			}
		}

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

	private static void adminLogin(String adminUsername) {

		System.out.println("\t\t\tWelcome " + adminUsername);
		System.out.println("\t\t\t\t\t\t\t\t 5.Logout");
		System.out.println("1.Add Student");
		System.out.println("2.Edit Students");
		System.out.println("3.Delete Students");
		System.out.println("4.Feedback");
		String choice = keyboard.nextLine();
		switch (choice) {
		case "1":
			while (true) {
				System.out.println("Enter the student-id");
				String studentUserName = keyboard.nextLine();
				System.out.println("Enter the student password");
				String studentPassword = keyboard.nextLine();
				writetoFile(new File("./StudentsID.txt"), studentUserName, studentPassword);
				System.out.println("Added successfully!!");
				System.out.println("Do you want to add students y/n");
				if (keyboard.nextLine().equalsIgnoreCase("y")) {
					continue;
				} else {
					clearConsole();
					adminLogin(adminUsername);
					break;
				}
			}
			break;
		case "2":
			while (true) {
				System.out.println("Enter the user name ");
				String editUserName = keyboard.nextLine();
				System.out.println("Enter the password");
				String editUserpassword = keyboard.nextLine();
				if (editStudentData(editUserName, editUserpassword)) {
					System.out.println("Edited successfully!!");
					System.out.println("Do you wan to edit more students y/n");
					choice = keyboard.nextLine();
					if (choice.equalsIgnoreCase("y")) {
						continue;
					} else if (choice.equalsIgnoreCase("n")) {
						clearConsole();
						adminLogin(adminUsername);
						break;
					} else {
						clearConsole();
						invalidChoice(choice);
						System.out.println(" Please Enter the valid choice!");
						continue;
					}
				} else {
					clearConsole();
					System.out.println("There is an error kindly check the username and the password");
					adminLogin(adminUsername);
					break;
				}

			}

			break;

		case "3":
			while (true) {
				System.out.println("Enter the user name ");
				String delUserName = keyboard.nextLine();
				System.out.println("Enter the password");
				String delUserpassword = keyboard.nextLine();
				if (delStudentData(delUserName, delUserpassword)) {
					System.out.println("Deleted Successfully!!");
					System.out.println("Do you wan to delete more students y/n");
					choice = keyboard.nextLine();
					if (choice.equalsIgnoreCase("y")) {
						continue;
					} else if (choice.equalsIgnoreCase("n")) {
						clearConsole();
						adminLogin(adminUsername);
						break;
					} else {
						clearConsole();
						invalidChoice(choice);
						System.out.println(" Please Enter the valid choice!");
						continue;
					}
				} else {
					clearConsole();
					System.out.println("There is an error kindly check the username and the password");
					adminLogin(adminUsername);
					break;
				}

			}

			break;
		case "4":
			clearConsole();
			feedbackPanel();
			break;
		case "5":
			clearConsole();
			mainmenu();
			break;
		default:
			clearConsole();
			invalidChoice(choice);
			adminLogin(adminUsername);
		}// end of switch

	} // end of method

	private static void percentage(File fileName) {
		int countExcellent = 0;
		int countGood = 0;
		int countVeryGood = 0;
		int countVeryPoor = 0;
		int countPoor = 0;
		String getDataFromFile = readFromFile(fileName);
		if (getDataFromFile == null) {
			System.out.println("Enter the correct File name");
			feedbackPanel();
			System.exit(0);

		}
		String line[] = getDataFromFile.split("\\r?\\n");
		for (int i = 0; i < line.length; i++) {
			int splitLocation = line[i].indexOf(":");
			String feedbackAnswers = line[i].substring(splitLocation + 2).replaceAll("\\d\\.", "");
			if (feedbackAnswers.equals("Excellent")) {
				countExcellent++;
			} else if (feedbackAnswers.equals("Good")) {
				countGood++;

			} else if (feedbackAnswers.equals("VeryGood")) {
				countVeryGood++;

			}

			else if (feedbackAnswers.equals("VeryPoor")) {
				countVeryPoor++;

			} else if (feedbackAnswers.equals("Poor")) {
				countPoor++;
			}

		}
		System.out.println("Excellent" + " : " + countExcellent + " " + "Good" + " : " + countGood + " " + "VeryGood"
				+ " : " + countVeryGood + " " + "VeryPoor" + " : " + countVeryPoor + " " + "Poor" + " : " + countPoor);
	}

	private static void search(File fileName) {
		String getDataFromFile = readFromFile(fileName);
		if (getDataFromFile == null) {
			System.out.println("Enter the correct File name");
			feedbackPanel();
			System.exit(0);

		}
		System.out.println("Enter the search term");
		String searchTerm = keyboard.nextLine();

		String data = getDataFromFile.replaceAll("\\d\\.", "");
		int count = 0;
		String line[] = data.split("\\r?\\n");

		for (int i = 0; i < line.length; i++) {
			String[] split = line[i].split("\\s+");
			for (int j = 0; j < split.length; j++) {

				if (searchTerm.equalsIgnoreCase(split[j])) {
					count++;
				}

			}
		}
		if (count != 0) {
			System.out.println(searchTerm + " Found " + count + " time");
		} else {
			System.out.println(searchTerm + " is not found");
		}

	}

	private static void feedbackPanel() {
		String feedbackMenu = "1.Create Feedbaack\t2.Add Questions\t3.Edit Questions in feedback\t4.delete feedback\t5.view how many students "
				+ "gives feedback\t6.Calculate the percentage\t7.Search\tb.Back\n";

		System.out.print(feedbackMenu);
		System.out.println("Enter the choice");
		String choice = keyboard.nextLine();
		switch (choice) {
		case "1":
			while (true) {
				System.out.print("Enter the filename for ex : feedbackcourseName");
				String fileName = keyboard.nextLine();
				createFeedback(new File(fileName));
				System.out.println("Do you want to create more file y/n");
				choice = keyboard.nextLine();
				if (choice.equalsIgnoreCase("y")) {
					continue;
				} else {
					feedbackPanel();
					break;
				}
			}
			break;
		case "2":
			System.out.println("Kindly add the question like else it may cause errors");
			System.out.println("Question Name. Question Part: 1.FeedbackChoice 2.FeedbackChoice");
			System.out.print("Enter the feedback file name to add the questions");
			String fileName = keyboard.nextLine();
			while (true) {
				System.out.println("Enter the question");
				String question = keyboard.nextLine();
				System.out.println(adminFeedbackFilesPath + fileName);
				writetoFile(new File(adminFeedbackFilesPath + fileName + ".txt"), question);
				System.out.println("Do you want to add more file y/n");
				choice = keyboard.nextLine();
				if (choice.equalsIgnoreCase("y")) {
					continue;
				} else {
					feedbackPanel();
					break;
				}
			}

			break;
		case "3":
			while (true) {
				System.out.println("Enter the feedback file name to edit the questions");
				fileName = keyboard.nextLine();
				System.out.println(adminFeedbackFilesPath + fileName + ".txt");
				if (editFeedback(new File(adminFeedbackFilesPath + fileName + ".txt"))) {
					System.out.println("FeedBack Edit Successfully!");
					System.out.println("Do you want to edit more file y/n");
					choice = keyboard.nextLine();
					if (choice.equalsIgnoreCase("y")) {
						continue;
					} else {
						feedbackPanel();
						break;
					}

				} else {
					System.out.println("Error !!");
				}

			}

			break;
		case "4":
			deleteFeedbackPanel();
			break;
		case "5":
			System.out.println("Student are  : ");
			File viewStudents = new File(studentFeedbackFilesPath);
			File[] listOfFiles = viewStudents.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				System.out.println(listOfFiles[i].getName().replace(".txt", ""));
			}
			feedbackPanel();
			break;
		case "6":
			while (true) {
				System.out.println("Enter the FileName");
				fileName = keyboard.nextLine();
				percentage(new File(studentFeedbackFilesPath + fileName + ".txt"));
				System.out.println("Do you want search y/n");
				if (keyboard.nextLine().equalsIgnoreCase("y")) {
					continue;
				} else {
					clearConsole();
					feedbackPanel();
					break;
				}
			}

			break;
		case "7":
			while (true) {
				System.out.println("Enter the FileName");
				fileName = keyboard.nextLine();
				search(new File(studentFeedbackFilesPath + fileName + ".txt"));
				System.out.println("Do you want search y/n");
				choice = keyboard.nextLine();
				if (choice.equals("y")) {
					continue;
				} else {
					clearConsole();
					feedbackPanel();
					break;
				}
			}
			break;
		case "b":
			clearConsole();
			adminLogin("admin");
			break;
		default:
			clearConsole();
			invalidChoice(choice);
			feedbackPanel();
		}
	}

	private static void deleteFeedbackPanel() {
		String deleteMenu = "1.Delete the entire feedback\t2.Delete the questions within the feedback";
		System.out.print(deleteMenu);
		System.out.print("\t\t\t\t b.Back\n");
		System.out.println("Enter the choice");
		String choice = keyboard.nextLine();
		switch (choice) {
		case "1":
			String fileName = null;
			while (true) {
				System.out.println("Enter the file name");
				fileName = keyboard.nextLine();
				deleteEntireFeedback(new File(fileName));
				System.out.println("Do you want to delete more file y/n");
				choice = keyboard.nextLine();
				if (choice.equalsIgnoreCase("y")) {
					continue;
				} else {
					deleteFeedbackPanel();
					break;
				}
			}
			break;
		case "2":
			while (true) {
				System.out.println("Enter the feedback file name to delete the questions");
				fileName = keyboard.nextLine();
				deleteFeedbackQuestion(new File(adminFeedbackFilesPath + fileName + ".txt"));
				System.out.println("FeedBack Question delete Successfully!");
				System.out.println("Do you want to delete more file y/n");
				choice = keyboard.nextLine();
				if (choice.equalsIgnoreCase("y")) {
					continue;
				} else {
					deleteFeedbackPanel();
					break;
				}

			}
			break;
		case "b":
			clearConsole();
			feedbackPanel();
			break;
		default:
			clearConsole();
			invalidChoice(choice);
			deleteFeedbackPanel();
		}

	}

	private static void deleteEntireFeedback(File fileName) {

		File file = new File(adminFeedbackFilesPath + fileName + ".txt");

		if (file.exists()) {
			file.delete();
			System.out.println("Successfully deleted!");
		} else {
			clearConsole();
			System.out.println(" Not file found ! ");
			deleteFeedbackPanel();
		}

	}

	private static void createFeedback(File fileName) {

		File feedBackFile = new File(adminFeedbackFilesPath + fileName + ".txt");
		try {
			if (feedBackFile.createNewFile())
				System.out.println("File created successfully! " + feedBackFile.getName());
			else

				System.out.println("File already exist " + feedBackFile.getName());

		}

		catch (IOException e) {
			System.out.println("Error occurs while creating File");
			e.printStackTrace();
		}
	}

	private static boolean delStudentData(String delUserName, String delUserpassword) {
		boolean deleteStatus = false;
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
				deleteStatus = true;
				continue;

			} else {
				writetoFile(tempFile, stdUserName, stdPassword);
			}
		}

		oldfileName.delete();
		tempFile.renameTo(newFile);
		return deleteStatus;

	}

	private static boolean editStudentData(String editUserName, String editUserpassword) {
		boolean editStatus = false;
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
				String newUserName = keyboard.nextLine();
				System.out.println("Enter the new user password");
				String newUserPassword = keyboard.nextLine();
				writetoFile(tempFile, newUserName, newUserPassword);
				editStatus = true;

			} else {
				writetoFile(tempFile, stdUserName, stdPassword);
			}
		}

		oldfileName.delete();
		tempFile.renameTo(newFile);
		return editStatus;

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
			appendLine = null;
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

	private static void deleteFeedbackQuestion(File deleteFileName) {
		File newFile = deleteFileName;
		File tempFile = new File(adminFeedbackFilesPath + "temp.txt");
		File oldFile = deleteFileName;
		String getdataFromFile = readFromFile(oldFile);
		if (getdataFromFile == null) {
			System.out.println("Enter the correct File name");
			feedbackPanel();
			System.exit(0);

		}
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
		oldFile.delete();
		tempFile.renameTo(newFile);

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

	private static boolean editFeedback(File editFileName) {
		boolean feedbackEditStatus = false;
		File newFile = editFileName;
		File tempFile = new File(adminFeedbackFilesPath + "temp.txt");
		File oldFile = editFileName;
		System.out.println("old file is " + editFileName);
		String getdataFromFile = readFromFile(oldFile);
		if (getdataFromFile == null) {
			System.out.println("Enter the correct File name");
			feedbackPanel();
			System.exit(0);

		}
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
				feedbackEditStatus = true;
			} else {
				question = questionName + "." + questionData + feedbackPart;
				writetoFile(tempFile, question);
			}

		}

		oldFile.delete();

		tempFile.renameTo(newFile);

		return feedbackEditStatus;

	}

	private static void invalidChoice(String choice) {
		System.out.println("Unexpected value: " + choice);
	}

	// clear console method
	private static void clearConsole() {

		for (int i = 0; i < 100; i++) {
			System.out.println("");
		}
	}

}