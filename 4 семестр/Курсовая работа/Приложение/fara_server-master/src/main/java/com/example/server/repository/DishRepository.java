package com.example.server.repository;

import com.example.server.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Dish}
 */
public interface DishRepository extends JpaRepository<Dish, Long> {

}
