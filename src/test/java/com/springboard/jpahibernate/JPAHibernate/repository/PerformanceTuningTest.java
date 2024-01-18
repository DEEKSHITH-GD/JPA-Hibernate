package com.springboard.jpahibernate.JPAHibernate.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.springboard.jpahibernate.JPAHibernate.entity.Course;
import com.springboard.jpahibernate.JPAHibernate.entity.Review;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Subgraph;
import jakarta.persistence.TypedQuery;

@SpringBootTest
class PerformanceTuningTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	@Test
	@Transactional
	public void findByIdBasicTest() {
		List<Course> courses = em.createNamedQuery("get_the_course_list", Course.class).getResultList();
		for(Course course:courses) {
			logger.info("Courses ->{} Students ->{}", course, course.getStudents());
		}
	}
	
	@Test
	@Transactional
	public void solveNplusOneProblem_EntityGraph() {
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> addSubgraph = entityGraph.addSubgraph("students");
		List<Course> courses = em.createNamedQuery("get_the_course_list", Course.class).setHint("jakarta.persistance.loadgraph", entityGraph).getResultList();
		for(Course course:courses) {
			logger.info("Courses ->{} Students ->{}", course, course.getStudents());
		}
	}
	
	@Test
	@Transactional
	public void solveNplusOneProblem_JoinFetch() {
		EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
		Subgraph<Object> addSubgraph = entityGraph.addSubgraph("students");
		List<Course> courses = em.createNamedQuery("get_all_course_Join_fetch", Course.class).setHint("jakarta.persistance.loadgraph", entityGraph).getResultList();
		for(Course course:courses) {
			logger.info("Courses ->{} Students ->{}", course, course.getStudents());
		}
	}
}
