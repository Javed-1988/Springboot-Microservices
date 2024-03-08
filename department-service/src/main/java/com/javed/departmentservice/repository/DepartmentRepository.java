package com.javed.departmentservice.repository;

import com.javed.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    @Query(value = "from Department where dept_code=?1")
    public Department findDepartmentByCode(String code);
}
