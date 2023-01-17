/**
 * @author Shane Hagan
 * Date: 01-13-2023
 * Project: SBA - Core Java/Hibernate/JUnit
 * Class: SMSMenu - contains our methods to run the menu for the student / user to operate
 */

package com.jpa.util;

import java.sql.SQLException;
import java.util.Scanner;

import com.jpa.entitymodels.Course;
import com.jpa.entitymodels.Student;
import com.jpa.service.CourseService;
import com.jpa.service.StudentService;

public class SMSMenu {
	private String email;
	private String password;
	private int status, selection = 0;
	public boolean test = true;
	
	Scanner input = new Scanner(System.in);
	StudentService studentObj = new StudentService();
	CourseService course = new CourseService();
	DBUtil databaseObj = new DBUtil();

	/**
	 * determineStatus method will prompt the user to enter their status in the SMS Program
	 * 1 is a student, where they will be prompted to enter the email and password to login to the SMS program
	 * 2 is an instructor, this was added as an extended feature / more realistic. Doesn't actually do anything
	 * 3 is an admin, this was added as an extended feature / more realistic. Doesn't actually do anything
	 * 4 will close out the program
	 * Uses switch cases to determine next steps.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void determineStatus() throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to the SMS Program!");
		
		while (true) {
			System.out.println("Please enter your status below: ");
			System.out.println("1. Student");
			System.out.println("2. Instructor");
			System.out.println("3. Administrator");
			System.out.println("4. Exit");
			System.out.println("9. Initialize");
			
			status = input.nextInt();
			input.nextLine();

			switch(status) {
			case 1:
				takeLogin();
				break;
			case 2:
				System.out.println("Service temporarily unavailable. Please try again later.\n");
				continue;
			case 3:
				System.out.println("Service temporarily unavailable. Please try again later.\n");
				continue;
			case 4:
				exitApp();
			case 9:
				databaseObj.initializeDB();
			default:
				System.out.println("Please enter a valid selection.");
				continue;
			}
		}
	}
	
	/**
	 * takeLogin method will prompt the student for their email and password.
	 * It calls the validateStudent method from our StudentService class to determine if the entered email / password
	 * are valid.
	 * If so, it will run the student menu where they can select from their options
	 */
	public void takeLogin() {
		System.out.println("Please enter your email: ");
		email = input.nextLine();
		System.out.println("Please enter your password: ");
		password = input.nextLine();
		
		if(studentObj.validateStudent(email, password)) {
			runStudentMenu(email);
		}
	}
	
	/**
	 * runStudentMenu method will show the student their options and prompt for input. Utilizes different methods
	 * based on the selection presented
	 * 1 will allow the student to register to a course. It will call our registerStudentToCourse method from
	 * the StudentService class
	 * 2 will show all possible courses - this was added as an extended feature / more realistic. It will call 
	 * our getAllCourses method from the CourseService class
	 * 3 will show the logged in student's courses - this was added as an extended feature / more realistic. It will  
	 * call our getStudentCourses method from the StudentService class
	 * 4 will log out the program
	 * Uses switch cases to determine next steps.
	 */
	public void runStudentMenu(String email) {
		Student student = studentObj.getStudentByEmail(email);

		studentObj.getStudentCourses(email);
		
		while(true) {
			System.out.println("Please enter your selection below: ");
			System.out.println("1. Register to Class");
			System.out.println("2. View All Courses");
			System.out.println("3. View your Courses");
			System.out.println("4. Logout");
			
			selection = input.nextInt();
			
			switch(selection) {
			case 1: 
				studentObj.registerStudentToCourse(student);
				continue;
			case 2:
				course.getAllCourses();
				continue;
			case 3:
				studentObj.getStudentCourses(email);
				continue;
			case 4:
				exitApp();
			default:
				System.out.println("Please enter a valid selection.");
				continue;
			}
		}
	}
	
	/**
	 * exitApp method will simply display a message and exit the app
	 */
	public void exitApp() {
		System.out.println("Thank you for using the SMS application!");
		input.close();
		System.exit(0);
	}
	
	
	
}
