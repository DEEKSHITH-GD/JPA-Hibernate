package com.springboard.jpahibernate.JPAHibernate.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboard.jpahibernate.JPAHibernate.entity.Address;
import com.springboard.jpahibernate.JPAHibernate.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
class StudentRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EntityManager em;
	/*
	@Test
	@Transactional
	public void getStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("Student -> {}", student);
		logger.info("Passport ->{}",student.getPassport()); 
	}
	*/
	@Test
	@Transactional
	public void setAddress() {
		Student student = em.find(Student.class, 20001L);
		student.setAddress(new Address("#49/1", "Chruch St", "Bangalore"));
		em.flush();
		logger.info("Student -> {}", student);
	}	
}
