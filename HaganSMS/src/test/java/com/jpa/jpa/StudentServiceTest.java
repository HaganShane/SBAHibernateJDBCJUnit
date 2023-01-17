package com.jpa.jpa;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jpa.entitymodels.Course;
import com.jpa.entitymodels.Student;
import com.jpa.service.StudentService;

public class StudentServiceTest {
	private static StudentService studentService;
	
	@Test
	public void testGetStudentByEmail() {
		studentService = new StudentService();
		Student expectedStudent = new Student();
		List<Course> courseList = new ArrayList<Course>();
		
		expectedStudent.setsEmail("sbowden1@yellowbook.com");
		expectedStudent.setsName("Sonnnie Bowden");
		expectedStudent.setsPass("SJc4aWSU");
		expectedStudent.setsCourses(courseList);
		
		Student actualStudent = studentService.getStudentByEmail("sbowden1@yellowbook.com");
		
		Assert.assertEquals(expectedStudent.getsEmail(), actualStudent.getsEmail());
		
	}
}
