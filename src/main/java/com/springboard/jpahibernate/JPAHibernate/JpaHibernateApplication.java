package com.springboard.jpahibernate.JPAHibernate;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboard.jpahibernate.JPAHibernate.entity.Course;
import com.springboard.jpahibernate.JPAHibernate.entity.Review;
import com.springboard.jpahibernate.JPAHibernate.entity.ReviewRating;
import com.springboard.jpahibernate.JPAHibernate.repository.CourseRepository;
import com.springboard.jpahibernate.JPAHibernate.repository.EmployeeRepository;
import com.springboard.jpahibernate.JPAHibernate.repository.StudentRepository;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		courseRepository.save(new Course("Microservices"));
		courseRepository.save(new Course("JPA"));
		courseRepository.save(new Course("Hibernate"));
		courseRepository.save(new Course("J2EE"));
		courseRepository.save(new Course("JS"));
		courseRepository.save(new Course("Spring"));
		
		studentRepository.saveStudentWithPassport();
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review(ReviewRating.FIVE, "Great Course"));
		reviews.add(new Review(ReviewRating.FIVE, "Excellent"));
		/*
		courseRepository.addReviewForCourse(1L, reviews);
		studentRepository.insertStudentAndCourse();
		
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
		employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		logger.info("All Employees ->{}", employeeRepository.retrieveAllEmployees());
		logger.info("Part Time Employees ->{}", employeeRepository.retrieveAllPartTimeEmployees());
		logger.info("Full Time Employees ->{}", employeeRepository.retrieveAllFullTimeEmployees());
		*/
	}
}
