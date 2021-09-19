package com.example.server.service;

import com.example.server.entity.Ingredient;
import com.example.server.entity.Order;
import com.example.server.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService service;

    @MockBean
    private OrderRepository repository;

    @Test
    void create() {
        Order order = new Order();
        Assertions.assertTrue(service.create(order));
    }

    @Test
    void getAll() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order());
        Mockito.doReturn(orders).when(repository).findAll();
        Assertions.assertEquals(orders, service.getAll());
    }

    @Test
    void getOne() {
        Order order = new Order();
        Optional<Order> optionalDish = Optional.of(order);
        Mockito.doReturn(optionalDish).when(repository).findById(0L);
        Assertions.assertEquals(optionalDish, service.getOne(0L));
    }

    @Test
    void delete() {
        Order o = new Order();
        Optional<Order> order = Optional.of(o);
        Mockito.doReturn(order).when(repository).findById(0L);
        Assertions.assertTrue(service.delete(0L));
    }

    @Test
    void update() {
        Order order = new Order();
        order.setId(0L);
        Optional optional = Optional.of(order);
        Mockito.doReturn(optional).when(repository).findById(0L);
        Assertions.assertTrue(service.update(order));
    }
}