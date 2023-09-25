package com.microservice1.emp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice1.emp.enitity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
