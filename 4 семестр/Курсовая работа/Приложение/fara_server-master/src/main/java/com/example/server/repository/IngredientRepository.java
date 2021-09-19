package com.example.server.repository;

import com.example.server.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Ingredient}
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
