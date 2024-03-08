package com.javed.departmentservice.service;

import com.javed.departmentservice.entity.Department;
import com.javed.departmentservice.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department)
    {
        return  departmentRepository.save(department);

    }
    public List<Department> getDepartment()
    {
        return  departmentRepository.findAll();

    }
    public Department getDepartmentByCode(String code)
    {
        return  departmentRepository.findDepartmentByCode(code);

    }
}
