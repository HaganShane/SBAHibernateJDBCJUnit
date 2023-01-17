/**
 * @author Shane Hagan
 * Date: 01-11-2023
 * Project: SBA - Core Java/Hibernate/JUnit
 * Class: Student - contains our POJO - getters / setters, constructors, declarations, toString method
 */

package com.jpa.entitymodels;

import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Student {
	
	@Id
	@Column(name = "email", length = 50, nullable = false, unique = true)
	private String sEmail;
	
	@Column(name = "name", length = 50, nullable = false)
	private String sName;
	
	@Column(name = "password", length = 50, nullable = false)
	private String sPass;
	
	@ManyToMany(targetEntity = Course.class)
	private List<Course> sCourses;
	
	public Student() {}

	
	
	public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.sCourses = sCourses;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public List<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}

	@Override
	public String toString() {
		return "Student [sEmail=" + sEmail + ", sName=" + sName + ", sPass=" + sPass + ", sCourses=" + sCourses + "]";
	}
	
	
}
