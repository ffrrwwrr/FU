package com.example.server.service;

import com.example.server.entity.Dish;
import com.example.server.entity.Employee;
import com.example.server.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService service;

    @MockBean
    private EmployeeRepository repository;

    @Test
    void create() {
        Employee employee = new Employee();
        Assertions.assertTrue(service.create(employee));
    }

    @Test
    void getAll() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        Mockito.doReturn(employees).when(repository).findAll();
        Assertions.assertEquals(employees, service.getAll());
    }

    @Test
    void getOne() {
        Employee employee = new Employee();
        Optional<Employee> optionalDish = Optional.of(employee);
        Mockito.doReturn(optionalDish).when(repository).findById(0L);
        Assertions.assertEquals(optionalDish, service.getOne(0L));
    }

    @Test
    void delete() {
        Employee e = new Employee();
        Optional<Employee> employee = Optional.of(e);
        Mockito.doReturn(employee).when(repository).findById(0L);
        Assertions.assertTrue(service.delete(0L));
    }

    @Test
    void update() {
        Employee employee = new Employee();
        employee.setId(0L);
        Optional optional = Optional.of(employee);
        Mockito.doReturn(optional).when(repository).findById(0L);
        Assertions.assertTrue(service.update(employee));
    }
}