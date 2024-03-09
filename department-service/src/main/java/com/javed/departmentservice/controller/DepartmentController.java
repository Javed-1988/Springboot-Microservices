package com.javed.departmentservice.controller;

import com.javed.departmentservice.entity.Department;
import com.javed.departmentservice.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RefreshScope
@Slf4j
@RequestMapping(value = "/api/department")
public class DepartmentController {

    @Value("${spring.boot.message}")
    private String message;
    private final DepartmentService service;
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/message")
    public String getMessage()
    {
       return message;

    }

    @PostMapping("/savedepartment")
    public ResponseEntity<Object> saveDepartment(@RequestBody Department department)
    {
        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.set("springboot-microservice-example","Value-ResponseEntityBuilderWithHttpHeaders");
        Department department1=service.saveDepartment(department);
        return ResponseEntity.ok().headers(httpHeaders).body(department1);
        //return new ResponseEntity<>(department1, HttpStatus.CREATED);

    }
    @GetMapping("/getdepartment")
    public ResponseEntity<Object> getDepartment()
    {
        List<Department> list=service.getDepartment();
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);

    }
    @GetMapping("/getdepartmentByCode/{code}")
    public ResponseEntity<Object> getDepartmentByCode(@PathVariable String code)
    {
        Department department=service.getDepartmentByCode(code);
        return new ResponseEntity<>(department, HttpStatus.ACCEPTED);

    }
}
