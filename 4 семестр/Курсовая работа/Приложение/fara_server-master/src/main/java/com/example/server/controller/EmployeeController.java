package com.example.server.controller;

import com.example.server.entity.Dish;
import com.example.server.entity.Employee;
import com.example.server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для класса {@link Employee}
 */
@RestController
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    /**
     * Получение списка сотрудников
     * @return OK при полном списке или NOT_FOUND при пустом списке
     */
    @GetMapping(value = "employees")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.getAll();
        return employees != null ? new ResponseEntity<>(employees, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Добавление блюда в список
     * @param employee сотрудник
     * @return добавленное сотрудника
     */
    @PostMapping(value = "employees")
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        employeeService.create(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Обновление информации о сотруднике по его id
     * @param id id сотрудника
     * @param employee сотрудник
     * @return OK при обновлении данных или NOT_FOUND при не найденном сотруднике с таким id
     */
    @PutMapping(value = "employees/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeService.update(employee) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Удаление сотрудника по его id
     * @param id id сотрудника
     * @return OK при успешном удалении сотрудника или NOT_FOUND при не найденном сотруднике с таким id
     */
    @DeleteMapping(value = "employees/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return employeeService.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
