/**
 * @author Shane Hagan
 * Date: 01-11-2023
 * Project: SBA - Core Java/Hibernate/JUnit
 * Interface: Student - contains our method declarations for the student implementation
 */

package com.jpa.dao;

import java.util.List;

import com.jpa.entitymodels.Course;
import com.jpa.entitymodels.Student;

public interface StudentDAO {
	/**
	 * getAllStudents Method that will connect with the database to get every student
	 * Returns a list of students
	 */
	List<Student> getAllStudents();
	
	/**
	 * getStudentByEmail Method that will connect with the database to get student information based on the
	 * email parameter that is passed in
	 * Returns a single student object
	 */
	Student getStudentByEmail(String email);
	
	/**
	 * validateStudent Method that will connect with the database to check the username and password that are
	 * passed in are valid
	 * Returns a true / false boolean
	 */
	boolean validateStudent(String username, String password);
	
	/**
	 * registerStudentToCourse Method that will connect with the database to register the passed in student
	 * to the courses he / she selects
	 * Uses SQL insert statements to register the student to their selected course(s)
	 * Takes in a student object as parameter
	 */
	void registerStudentToCourse(Student student);
	
	/**
	 * getStudentCourses Method that will connect with the database to get a given student's course list
	 * Takes in a student's email as the parameter
	 * Returns a list of courses
	 */
	List<Course> getStudentCourses(String email);
	
}
