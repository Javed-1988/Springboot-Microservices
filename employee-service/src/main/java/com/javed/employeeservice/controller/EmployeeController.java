package com.javed.employeeservice.controller;

import com.javed.employeeservice.entity.DepartmentDto;
import com.javed.employeeservice.entity.Employee;
import com.javed.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    private final EmployeeService service;
    private final RestTemplate restTemplate;
    private final WebClient webClient;
    @PostMapping("/saveemp")
    public ResponseEntity<Object> saveEmp(@RequestBody Employee employee)
    {
        ResponseEntity<String> response1= restTemplate.getForEntity("http://localhost:8080/api/department/getdepartment",String.class);
        log.info("Dept-cod:---------------------"+response1.getBody());
        ResponseEntity<DepartmentDto> response= restTemplate.getForEntity("http://localhost:8080/api/department/getdepartmentByCode/IT001",DepartmentDto.class);
        //DepartmentDto response=webClient.get().uri("http://localhost:8080/getdepartmentByCode/IT001").retrieve().bodyToMono(DepartmentDto.class).block();
        //employee.setDept_code(response.getBody().getDept_code());
        //log.info("Dept-cod:--"+response.getBody().getDept_code());
        employee.setDept_code(response.getBody().getDept_code());
        log.info("Dept-cod:--"+response.getBody().getDept_code());
        Employee emp=service.saveEmployee(employee);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    @GetMapping("/getemp")
    public ResponseEntity<Object> getEmp()
    {
        List<Employee> emp=service.getEmployee();
        return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
    }
    @GetMapping("/getempbyid/{id}")
    public ResponseEntity<Object> getEmpById(@PathVariable int id)
    {
        Optional<Employee> emp=service.getEmployeeById(id);
        if(emp.isPresent()) {
            return new ResponseEntity<>(emp.get(), HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>("Id not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getempbyname")
    public ResponseEntity<Object> getEmpById(@RequestParam String name)
    {
       Employee emp=service.getEmployeeByName(name);

            return new ResponseEntity<>(emp, HttpStatus.FOUND);

    }
}
