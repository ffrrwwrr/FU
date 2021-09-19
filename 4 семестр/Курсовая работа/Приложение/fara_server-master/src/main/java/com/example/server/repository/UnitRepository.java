package com.example.server.repository;

import com.example.server.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Unit}
 */
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
