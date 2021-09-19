package com.example.server.service;

import com.example.server.entity.Employee;
import com.example.server.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для класса {@link Employee}
 */
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Создание сотрудника
     * @param s s
     */
    public boolean create(Employee s) {
        employeeRepository.save(s);
        return true;
    }

    /**
     * Получение всех записей сотрудников
     * @return Все сотрудники
     */
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    /**
     * Получение одной записим сотрудника по id
     * @param id id сотрудника
     * @return Запись сотрудника по id
     */
    public Optional<Employee> getOne(Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Удалние сотрудника по id
     * @param id id сотрудника
     * @return true при успешном удалении или false при не найденном сотруднике по id
     */
    public boolean delete(Long id) {
        if (getOne(id).isPresent()) {
            employeeRepository.deleteById(id);
            return true;
        }

        return false;
    }

    /**
     * Обновление информации о сотруднике по id
     * @param s s
     * @return true при успешном обновлении или false при не найденном сотруднике по id
     */
    public boolean update(Employee s) {
        if (getOne(s.getId()).isPresent()) {
            employeeRepository.save(s);
            return true;
        }

        return false;
    }
}
