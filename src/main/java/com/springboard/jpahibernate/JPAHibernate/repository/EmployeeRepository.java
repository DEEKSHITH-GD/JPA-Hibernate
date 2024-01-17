package com.springboard.jpahibernate.JPAHibernate.repository;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboard.jpahibernate.JPAHibernate.entity.Employee;
import com.springboard.jpahibernate.JPAHibernate.entity.FullTimeEmployee;
import com.springboard.jpahibernate.JPAHibernate.entity.PartTimeEmployee;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class EmployeeRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public void insert(Employee employee) {
		em.persist(employee); //insert employee
	}
	
	public List<Employee> retrieveAllEmployees(){
		return em.createQuery("select e from Employee e", Employee.class).getResultList(); //Retrieves all employees
	}
	public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
		return em.createQuery("select p from PartTimeEmployees p", PartTimeEmployee.class).getResultList(); //Retrieves all part time employees
	}
	public List<FullTimeEmployee> retrieveAllFullTimeEmployees(){
		return em.createQuery("select f from FullTimeEmployees f", FullTimeEmployee.class).getResultList(); //Retrieves all full time employees
	}
}