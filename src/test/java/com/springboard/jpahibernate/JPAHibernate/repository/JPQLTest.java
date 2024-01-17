package com.springboard.jpahibernate.JPAHibernate.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboard.jpahibernate.JPAHibernate.entity.Course;
import com.springboard.jpahibernate.JPAHibernate.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@SpringBootTest
class JPQLTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	public void getCoursesWithoutStudents() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Result ->{}",resultList); 
	}	
	
	@Test
	public void getCoursesWithAtleast_2Students() {
		TypedQuery<Course> query2 = em.createQuery("Select c from Course c where size(c.students) >= 2 ", Course.class);
		List<Course> resultList2 = query2.getResultList();
		logger.info("Result ->{}",resultList2); 
	}
	
	@Test
	public void jpqlCoursesOrderByStudents() {
		TypedQuery<Course> query2 = em.createQuery("Select c from Course c where size(c.students) >= 2 ", Course.class);
		List<Course> resultList2 = query2.getResultList();
		logger.info("Result ->{}",resultList2); 
	}
	
	@Test
	public void jpqlStudentsWithPassportWithPattern() {
		TypedQuery<Student> query3 = em.createQuery("Select s from Student s where s.passport.number like '%652%'", Student.class);
		List<Student> resultList3 = query3.getResultList();
		logger.info("Result ->{}",resultList3); 
	}
	
	@Test
	public void jpqlJoin() {
		Query query4 = em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList4 = query4.getResultList();
		logger.info("Result Size ->{}",resultList4.size());
		for(Object[] result:resultList4) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	@Test
	public void jpqlLeftJoin() {
		Query query5 = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList5 = query5.getResultList();
		logger.info("Result Size ->{}",resultList5.size());
		for(Object[] result:resultList5) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	@Test
	public void jpqlCrossJoin() {
		Query query5 = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList5 = query5.getResultList();
		logger.info("Result Size ->{}",resultList5.size());
		for(Object[] result:resultList5) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
}
