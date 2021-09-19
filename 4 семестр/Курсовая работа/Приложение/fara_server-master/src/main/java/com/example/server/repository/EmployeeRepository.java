package com.example.server.repository;

import com.example.server.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Employee}
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
