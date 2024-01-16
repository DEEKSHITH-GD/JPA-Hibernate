package com.springboard.jpahibernate.JPAHibernate.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboard.jpahibernate.JPAHibernate.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@SpringBootTest
public class NativeQueriesTest {
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	public void nativeQueryTest() {
		repository.save(new Course("Microservices"));
		repository.save(new Course("JPA"));
		repository.save(new Course("Hibernate"));
		Query query1 = em.createNativeQuery("select * from course");
		List<Course> resultList = query1.getResultList();
		logger.info("select * from course -> {}", resultList);
	}
	@Test
	public void nativeQuery2Test() {
		repository.save(new Course("Microservices"));
		repository.save(new Course("JPA"));
		repository.save(new Course("Hibernate"));
		Query query2 = em.createNativeQuery("select * from course where id=?", Course.class);
		query2.setParameter(1, 3);
		List<Course> resultList = query2.getResultList();
		logger.info("select * from course -> {}", resultList);
	}
	
	@Test
	public void nativeQueryWithNamedParaTest() {
		repository.save(new Course("Microservices"));
		repository.save(new Course("JPA"));
		repository.save(new Course("Hibernate"));
		Query query2 = em.createNativeQuery("select * from course where id=:id", Course.class);
		query2.setParameter("id", 2);
		List<Course> resultList = query2.getResultList();
		logger.info("select * from course -> {}", resultList);
	}
	
	@Test
	@Transactional
	public void nativeQueryToUpdate() {
		repository.save(new Course("Microservices"));
		repository.save(new Course("JPA"));
		repository.save(new Course("Hibernate"));
		Query query3 = em.createNativeQuery("Update COURSE set last_updated_date=now()", Course.class);
		int updatedRows = query3.executeUpdate();
		logger.info("select * from course -> {}", updatedRows);
	}
}
