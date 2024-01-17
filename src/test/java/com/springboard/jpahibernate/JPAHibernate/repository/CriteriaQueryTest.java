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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@SpringBootTest
class CriteriaQueryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EntityManager em;
	
	@Test
	public void criteriaQuery() {
		/* Query : "Select c from Course c"
		1. Use Criteria Builder to create a Criteria Query returning the expected result Object
		2. Define roots for tables which are involved in the Query
		3. Define Predicates etc using Criteria Query
		4. Add Predicates etc to the Criteria Query
		5. Build the TypedQuery using the Entity Manager and Criteria Query
		*/
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		TypedQuery<Course> tq = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = tq.getResultList();
		logger.info("Result ->{}",resultList); 
	}	
	
	@Test
	public void criteriaQueryWithWhereClause() {
		/* Query : "Select c from Course c where name like '%100 steps'"
		1. Use Criteria Builder to create a Criteria Query returning the expected result Object
		2. Define roots for tables which are involved in the Query
		3. Define Predicates etc using Criteria Query
		4. Add Predicates etc to the Criteria Query
		5. Build the TypedQuery using the Entity Manager and Criteria Query
		*/
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		Predicate likePattern = cb.like(courseRoot.get("name"), "%100 steps");
		
		cq.where(likePattern);
		
		TypedQuery<Course> tq = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = tq.getResultList();
		logger.info("Result ->{}",resultList); 
	}
	
	@Test
	public void criteriaQueryForCourseWithoutStudents() {
		/* Query : "Select c from Course c where c.students is empty"
		1. Use Criteria Builder to create a Criteria Query returning the expected result Object
		2. Define roots for tables which are involved in the Query
		3. Define Predicates etc using Criteria Query
		4. Add Predicates etc to the Criteria Query
		5. Build the TypedQuery using the Entity Manager and Criteria Query
		*/
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		Predicate condition = cb.isEmpty(courseRoot.get("students"));
		
		cq.where(condition);
		
		TypedQuery<Course> tq = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = tq.getResultList();
		logger.info("Result ->{}",resultList); 
	}	
	
	@Test
	public void join() {
		/* Query : "Select c from Course c JOIN c.students s"
		1. Use Criteria Builder to create a Criteria Query returning the expected result Object
		2. Define roots for tables which are involved in the Query
		3. Define Predicates etc using Criteria Query
		4. Add Predicates etc to the Criteria Query
		5. Build the TypedQuery using the Entity Manager and Criteria Query
		*/
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		Join<Object, Object> join = courseRoot.join("students");
		
		TypedQuery<Course> tq = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = tq.getResultList();
		logger.info("Result ->{}",resultList); 
	}
	
	@Test
	public void leftJoin() {
		/* Query : "Select c from Course c LEFT JOIN c.students s"
		1. Use Criteria Builder to create a Criteria Query returning the expected result Object
		2. Define roots for tables which are involved in the Query
		3. Define Predicates etc using Criteria Query
		4. Add Predicates etc to the Criteria Query
		5. Build the TypedQuery using the Entity Manager and Criteria Query
		*/
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		Root<Course> courseRoot = cq.from(Course.class);
		
		Join<Object, Object> leftJoin = courseRoot.join("students", JoinType.LEFT);
		
		TypedQuery<Course> tq = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = tq.getResultList();
		logger.info("Result ->{}",resultList);
	}
}