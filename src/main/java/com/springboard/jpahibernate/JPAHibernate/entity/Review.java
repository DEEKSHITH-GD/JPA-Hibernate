package com.springboard.jpahibernate.JPAHibernate.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private ReviewRating rating;
	
	@ManyToOne
	private Course course;
	
	protected Review() {
	}
	
	public Review(ReviewRating rating,String description) {
		this.rating = rating;
		this.description = description;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	
	public ReviewRating getRating() {
		return rating;
	}

	public void setRating(ReviewRating rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return String.format("Review[%s]", description);
	}
}
