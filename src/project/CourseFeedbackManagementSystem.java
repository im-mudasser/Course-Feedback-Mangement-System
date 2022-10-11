package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader("./StudentsID.txt"));
			String line;

			while ((line = reader.readLine()) != null) {
				int splitLocation = line.indexOf(",");
				String stdUserName = line.substring(0, splitLocation);
				String stdPassword = line.substring(splitLocation + 1);
				if (inputStdUserName.equals(stdUserName) && inputStdPassword.equals(stdPassword)) {
					found = true;
					break;

				} else {
					found = false;
					continue;
				}
			}

		} catch (

		FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("finally is excuted");
			if (reader != null) {
				System.out.println("reader is null now");
				try {
					reader.close();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
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
			keyboard.close();
			break;
		default:
			invalidChoice(choice);

		}
	}
}
