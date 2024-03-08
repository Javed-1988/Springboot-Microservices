package com.javed.employeeservice.service;

import com.javed.employeeservice.Repository.EmployeeRepository;
import com.javed.employeeservice.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee saveEmployee(Employee employee)
    {
        return repository.save(employee);
    }
    public List<Employee> getEmployee()
    {
        return repository.findAll();
    }
    public Optional<Employee> getEmployeeById(int id) {
        return repository.findById(id);
    }
    public Employee getEmployeeByName(String name){
        return repository.findByName(name);
    }

}
