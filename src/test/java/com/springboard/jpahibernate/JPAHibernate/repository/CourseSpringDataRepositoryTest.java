package com.springboard.jpahibernate.JPAHibernate.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.springboard.jpahibernate.JPAHibernate.entity.Course;

@SpringBootTest
class CourseSpringDataRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository courseSpringDataRepository;

	@Test
	public void findCourse() {
		Optional<Course> courseOptional = courseSpringDataRepository.findById(1L);
		logger.info("{}", courseOptional.isPresent());
	}
	
	@Test
	public void courseNotPresent() {
		Optional<Course> courseOptional = courseSpringDataRepository.findById(10L);
		assertFalse(courseOptional.isPresent());
	}
	
	@Test
	public void saveAndUpdateCourse() {
		Course course = new Course("Python");
		courseSpringDataRepository.save(course); //saving course
		course.setName("Python full stack");
		courseSpringDataRepository.save(course); //updates the course
		logger.info("Courses ->{}", courseSpringDataRepository.findAll());
		logger.info("Count ->{}", courseSpringDataRepository.count());
	}
	
	@Test
	public void pagination() {
		PageRequest pr = PageRequest.of(0, 2);
		Page<Course> page1 = courseSpringDataRepository.findAll(pr);
		logger.info("First Page->{}", page1.getContent());
		
		Pageable nextPageable = page1.nextPageable();
		Page<Course> page2 = courseSpringDataRepository.findAll(nextPageable);
		logger.info("Second page->{}", page2.getContent());
	}
	
	@Test
	public void findCourseByName() {
		logger.info("Find by name->{}", courseSpringDataRepository.findByName("JS"));
	}
}
