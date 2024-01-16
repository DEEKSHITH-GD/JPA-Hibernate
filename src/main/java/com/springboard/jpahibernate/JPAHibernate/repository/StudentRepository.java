package com.springboard.jpahibernate.JPAHibernate.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboard.jpahibernate.JPAHibernate.entity.Course;
import com.springboard.jpahibernate.JPAHibernate.entity.Passport;
import com.springboard.jpahibernate.JPAHibernate.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class StudentRepository {
	
	@Autowired
	EntityManager em;
	
	public Student save(Student student) {
		if(student.getId() == null) {
			em.persist(student);
		}
		else {
			em.merge(student);
		}
		return student;
	}
	
	public Student findById(long id) {
		return em.find(Student.class, id);
	}
	
	public void deleteById(long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	public void saveStudentWithPassport() {
		Passport passport = new Passport("P76523");
		em.persist(passport);
		Student student = new Student("Micheal");
		em.persist(student);
		student.setPassport(passport);
		//em.flush();
	}
	
	public void insertStudentAndCourse() {
		Student student = new Student("Jack");
		Course course = new Course("Core Java");
		em.persist(student);
		em.persist(course);
		
		student.addCourse(course);
		course.addStudents(student);
		em.persist(student);
		
	}
}
