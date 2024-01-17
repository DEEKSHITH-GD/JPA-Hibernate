package com.springboard.jpahibernate.JPAHibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;

@Entity
@NamedQuery(name= "get_the_course_list", query="select c from Course c")
@NamedQueries(value= {@NamedQuery(name= "get_the_course_list", query="select c from Course c"),
			  @NamedQuery(name= "get_the_course_where", query="Select c from Course c where name like '%H%'")})
@Cacheable
@SQLDelete(sql= "update course set is_deleted=true where id=?")
@Where(clause = "is_deleted=false")
public class Course {
	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<Review>();
	
	@ManyToMany//(mappedBy = "course")
	@JsonIgnore
	private List<Student> students = new ArrayList<Student>();
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	private boolean isDeleted;
	
	@PreRemove
	private void preRemove() {
		LOGGER.info("Setting isDeleted to true");
		this.isDeleted = true;
	}
	
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review reviews) {
		this.reviews.add(reviews);
	}
	
	public void removeReview(Review reviews) {
		this.reviews.remove(reviews);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudents(Student student) {
		this.students.add(student);
	}
	
	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}
	
}
