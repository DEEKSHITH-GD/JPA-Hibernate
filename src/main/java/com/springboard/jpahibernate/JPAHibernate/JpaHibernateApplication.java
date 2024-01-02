package com.springboard.jpahibernate.JPAHibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboard.jpahibernate.JPAHibernate.entity.Course;
import com.springboard.jpahibernate.JPAHibernate.repository.CourseRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.save(new Course("Microservices"));
		repository.save(new Course("JPA"));
		repository.save(new Course("Hibernate"));
		
		//Course course = repository.findById(1);
		//logger.info("Course Details -> {}", course);
		
		//repository.deleteById(2);
		
		repository.playWithEntityManager();
	}
}
