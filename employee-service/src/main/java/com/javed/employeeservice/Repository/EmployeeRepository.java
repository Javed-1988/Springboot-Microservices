package com.javed.employeeservice.Repository;

import com.javed.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    public Employee findByName(String name);
}
