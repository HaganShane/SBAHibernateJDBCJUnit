/**
 * @author Shane Hagan
 * Date: 01-11-2023
 * Project: SBA - Core Java/Hibernate/JUnit
 * Interface: Course - contains our method declaration for the course implementation
 */

package com.jpa.dao;

import java.util.List;

import com.jpa.entitymodels.Course;

public interface CourseDAO {
	/**
	 * getAllCourses Method declaration
	 * Returns a list of courses
	 */
	List<Course> getAllCourses();
}
