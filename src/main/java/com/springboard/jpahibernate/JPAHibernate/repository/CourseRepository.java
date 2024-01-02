package com.springboard.jpahibernate.JPAHibernate.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboard.jpahibernate.JPAHibernate.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class CourseRepository {
	
	@Autowired
	EntityManager em;
	
	public Course save(Course course) {
		if(course.getId() == null) {
			em.persist(course);
		}
		else {
			em.merge(course);
		}
		return course;
	}
	
	public Course findById(long id) {
		return em.find(Course.class, id);
	}
	
	public void deleteById(long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public void playWithEntityManager() {
		Course course1 = new Course("Angular");
		em.persist(course1);
		em.flush();
		Course course2 = new Course("Javascript");
		em.persist(course2);
		em.flush();
		//em.clear();
		//em.detach(course1);
		course1.setName("Angular - Updated");
		em.flush();
		course2.setName("Javascript - Updated");
		em.refresh(course2);
		em.flush();
	}
	
}
