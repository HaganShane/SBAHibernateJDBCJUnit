/**
 * @author Shane Hagan
 * Date: 01-13-2023
 * Project: SBA - Core Java/Hibernate/JUnit
 * Class: StudentService - contains our required methods for the user / student to obtain their data
 * Has the required methods from the project description
 */

package com.jpa.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.jpa.dao.StudentDAO;
import com.jpa.entitymodels.Course;
import com.jpa.entitymodels.Student;
import com.jpa.util.SMSUtil;

public class StudentService implements StudentDAO{
	
	/**
	 * getAllStudents Method declaration
	 * Returns a list of students
	 */
	@Override
	public List<Student> getAllStudents() {
		Session session = SMSUtil.getSession();
		String hql = "FROM Student";
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		List<Student> studentResults = query.getResultList();
		
		System.out.println(String.format("%-30s %-30s %-30s", "--- Student Name ---", "--- Email ---", "--- Password ---"));
		for (Student s : studentResults) {
			
			System.out.println(String.format("%-30s %-30s %-30s", s.getsName(), s.getsEmail(), s.getsPass()));
		}
		return studentResults;
	}

	/**
	 * getStudentByEmail Method declaration
	 * email parameter passed in
	 * Returns a single student object
	 */
	@Override
	public Student getStudentByEmail(String email) {
		Session session = SMSUtil.getSession();
		String hql = "FROM Student WHERE email = :email";
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		query.setParameter("email", email);
		
		Student studentEmailResult = query.getSingleResult();
		
		return studentEmailResult;
	}

	/**
	 * validateStudent Method declaration
	 * Takes in a student email and password (entered in the login process)
	 * Returns a true / false boolean
	 */
	@Override
	public boolean validateStudent(String email, String password) {		
		try{
			Session session = SMSUtil.getSession();
		
			String hql = "FROM Student where email = :email AND password = :password";
			TypedQuery<Student> query = session.createQuery(hql, Student.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			Student s = query.getSingleResult();
			
			if(s.getsEmail().equals(email)) {
				System.out.println("Welcome to the SMS Application, " + s.getsName());
				return true;
			}
		}
		catch(NoResultException e) {
			System.out.println("Email or password is incorrect. Please try again");
		}	return false;	
	}

	/**
	 * registerStudentToCourse Method declaration
	 * Takes in a student object as parameter
	 */
	@Override
	public void registerStudentToCourse(Student student) {
		int courseSelection = 0;
		String email = student.getsEmail();
		
		List<Integer> courseNumbers = new ArrayList<Integer>();
		CourseService courseObj = new CourseService();
		Scanner input = new Scanner(System.in);
		
		List<Course> studentCourseList = getStudentCourses(student.getsEmail());
		for (Course c : studentCourseList) {
			courseNumbers.add(c.getcId());
		}
			
		if(studentCourseList.isEmpty()) {
			System.out.println("You are not registered for any courses yet!");
			System.out.println("Use the below course list to pick a course to register for: ");
			courseObj.getAllCourses();
			System.out.println("Enter the Course ID you wish to register for: ");
			courseSelection = input.nextInt();
			String sqlInsert = "INSERT INTO student_course (Student_email, sCourses_Id) VALUES (?,?)";
			try {
				Connection connection = SMSUtil.setConnection();
				PreparedStatement stmt = connection.prepareStatement(sqlInsert);
				stmt.setString(1, email);
				stmt.setInt(2, courseSelection);
				stmt.executeUpdate();
				System.out.println("Successfully added course!");
				System.out.println("Updated course list for " + student.getsName());
				getStudentCourses(email);
			}
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}			
		}
		else {
			System.out.println("Use the below course list to pick a course to register for: ");
			courseObj.getAllCourses();
			System.out.println("Enter the Course ID you wish to register for: ");
			courseSelection = input.nextInt();
			if (courseNumbers.contains(courseSelection)) {
				System.out.println("Already registered to course!");
			}
			else {
				String sqlInsert = "INSERT INTO student_course (Student_email, sCourses_Id) VALUES (?,?)";
				try {
					Connection connection = SMSUtil.setConnection();
					PreparedStatement stmt = connection.prepareStatement(sqlInsert);
					stmt.setString(1, email);
					stmt.setInt(2, courseSelection);
					stmt.executeUpdate();
					System.out.println("Successfully added course!");
					System.out.println("Updated course list for " + student.getsName());
					getStudentCourses(email);
				}
				catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
}		

	/**
	 * getStudentCourses Method declaration
	 * Takes in a student's email as the parameter
	 * Returns a list of courses
	 */
	@Override
	public List<Course> getStudentCourses(String email) {
		List<Course> courseList = new ArrayList<Course>();
		Student student = getStudentByEmail(email);
		
		String sql = "SELECT c.id AS 'CourseID', c.name AS 'CourseName', c.instructor as 'Instructor' FROM course c INNER JOIN student_course sc on c.id = sc.sCourses_id INNER JOIN student s on s.email = sc.Student_email WHERE s.email = ?";
		
		try {
			Connection connection = SMSUtil.setConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int courseId = rs.getInt("CourseID");
				String courseName = rs.getString("CourseName");
				String instructorName = rs.getString("Instructor");
				
				Course c = new Course();
				c.setcId(courseId);
				c.setcName(courseName);
				c.setcInstructorName(instructorName);
				
				courseList.add(c);
			}
			System.out.println("Displaying information for student: " + student.getsName());
			System.out.println(String.format("%-30s %-30s %-30s", "--- Course ID ---", "--- Course Name ---", "--- Instructor ---"));
			if (courseList.isEmpty()){
				System.out.println("No Registered Courses yet! Use Option 1 to Register for your Courses!");
			}
			for (Course c : courseList) {
				System.out.println(String.format("%-30s %-30s %-30s", c.getcId(), c.getcName(), c.getcInstructorName()));
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return courseList;
	}

}
