/**
 * @author Shane Hagan
 * Date: 01-11-2023
 * Project: SBA - Core Java/Hibernate/JUnit
 * Class: Course - contains our POJO - getters / setters, constructors, declarations, toString method
 */

package com.jpa.entitymodels;

import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course {
	@Id
	@Column(name = "id", nullable = false, unique = true)
	private int cId;
	
	@Column(name = "name", length = 50, nullable = false)
	private String cName;
	
	@Column(name = "instructor", length = 50, nullable = false)
	private String cInstructorName;
	
	public Course() {}

	public Course(int cId, String cName, String cInstructorName) {
		this.cId = cId;
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}

	@Override
	public String toString() {
		return "Course [cId=" + cId + ", cName=" + cName + ", cInstructorName=" + cInstructorName + "]";
	}
}
