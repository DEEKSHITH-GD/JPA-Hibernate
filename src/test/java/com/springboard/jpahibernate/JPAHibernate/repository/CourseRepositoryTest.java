package com.springboard.jpahibernate.JPAHibernate.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.springboard.jpahibernate.JPAHibernate.entity.Course;
import com.springboard.jpahibernate.JPAHibernate.entity.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@SpringBootTest
class CourseRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	/*
	@Test
	public void findByIdBasicTest() {
		Course course = repository.findById(1);
		assertEquals("Microservices", course.getName());
	}
	
	@Test
	@DirtiesContext
	public void DeleteByIdTest() {
		repository.deleteById(1);
		assertNull(repository.findById(1));
	}
	
	@Test
	@DirtiesContext
	public void saveCourseTest() {
		Course course = repository.findById(1);
		assertEquals("Microservices", course.getName());
		//update
		course.setName("Microservices - updated");
		repository.save(course);
		
		//check the value
		Course course1 = repository.findById(1);
		assertEquals("Microservices - updated", course1.getName());
	}
	
	@Test
	@DirtiesContext
	public void entityManagerTest() {
		repository.playWithEntityManager();
	}
	
	@Test
	public void queryTest(){
		repository.save(new Course("Microservices"));
		repository.save(new Course("JPA"));
		repository.save(new Course("Hibernate"));
		Query Query = em.createQuery("Select c from Course c");
		List resultList1 = Query.getResultList();
		logger.info("Select c from Course c -> {}", resultList1);
	}
	
	@Test
	public void TypedQueryTest(){
		repository.save(new Course("Microservices"));
		repository.save(new Course("JPA"));
		repository.save(new Course("Hibernate"));
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c", Course.class);
		List<Course> resultList2 = typedQuery.getResultList();
		logger.info("Select c from Course c -> {}", resultList2);
	}
	
	@Test
	public void conditionalQueryTest(){
		repository.save(new Course("Microservices"));
		repository.save(new Course("JPA"));
		repository.save(new Course("Hibernate"));
		TypedQuery<Course> typedQuery = em.createQuery("Select c from Course c where name like '%H%'", Course.class);
		List<Course> resultList3 = typedQuery.getResultList();
		logger.info("Select c from Course c where name like '%H%' -> {}", resultList3);
	}
	
	@Test
	public void namedQueryTest(){
		repository.save(new Course("Microservices"));
		repository.save(new Course("JPA"));
		repository.save(new Course("Hibernate"));
		Query query = em.createNamedQuery("get_the_course_list");
		List<Course> resultList4 = query.getResultList();
		logger.info("Select c from Course c -> {}", resultList4);
	}
	
	@Test
	public void namedQueriesTest(){
		repository.save(new Course("Microservices"));
		repository.save(new Course("JPA"));
		repository.save(new Course("Hibernate"));
		Query query2 = em.createNamedQuery("get_the_course_where");
		List<Course> resultList5 = query2.getResultList();
		logger.info("Select c from Course c where name like '%H%' -> {}", resultList5);
	}
	*/
	@Test
	@Transactional
	public void retrieveReviews() {
		Course course = repository.findById(1L);
		logger.info("Reviews-> {}", course.getReviews());
	}
	
	@Test
	@Transactional
	public void retrieveCourseForReview() {
		Review review = em.find(Review.class, 1L);
		logger.info("{}", review.getCourse());
	}
}
