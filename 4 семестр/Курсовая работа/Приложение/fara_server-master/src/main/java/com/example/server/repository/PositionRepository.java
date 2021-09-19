package com.example.server.repository;

import com.example.server.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Position}
 */
public interface PositionRepository extends JpaRepository<Position, Long> {
}
