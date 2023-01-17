/**
 * @author Shane Hagan
 * Date: 01-13-2023
 * Project: SBA - Core Java/Hibernate/JUnit
 * Class: CourseService - contains our required methods for the user / student to obtain course data
 * Has the required method from the project description
 * In this case, only contains one method that will display all courses for the user
 */
package com.jpa.service;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.jpa.dao.CourseDAO;
import com.jpa.entitymodels.Course;
import com.jpa.util.SMSUtil;

public class CourseService implements CourseDAO {

	/**
	 * getAllCourses Method that connects with our database and uses HQL to get all courses in the database
	 * Returns a list of courses
	 */
	@Override
	public List<Course> getAllCourses() {
		Session session = SMSUtil.getSession();
		String hql = "FROM Course";
		TypedQuery<Course> query = session.createQuery(hql, Course.class);
		List<Course> courseResults = query.getResultList();
		
		System.out.println(String.format("%-30s %-30s %-30s", "--- Course ID ---", "--- Course Name ---", "--- Instructor ---"));
		
		for (Course c : courseResults) {
			System.out.println(String.format("%-30s %-30s %-30s", c.getcId(), c.getcName(), c.getcInstructorName()));
		}
		return courseResults;
	}

}
