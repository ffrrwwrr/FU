package com.example.server.repository;

import com.example.server.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс для класса {@link Order}
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
