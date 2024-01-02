package com.springboard.jpahibernate.JPAHibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

	private String name;
	
	protected Course() {
	}
	
	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
}