package com.example.server.service;

import com.example.server.entity.Dish;
import com.example.server.repository.DishRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class DishServiceTest {

    @Autowired
    private DishService service;

    @MockBean
    private DishRepository repository;

    @Test
    void create() {
        Dish dish = new Dish();
        Assertions.assertTrue(service.create(dish));
    }

    @Test
    void getAll() {
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish());
        Mockito.doReturn(dishes).when(repository).findAll();
        Assertions.assertEquals(dishes, service.getAll());
    }

    @Test
    void getOne() {
        Dish dish = new Dish();
        Optional<Dish> optionalDish = Optional.of(dish);
        Mockito.doReturn(optionalDish).when(repository).findById(0L);
        Assertions.assertEquals(optionalDish, service.getOne(0L));
    }

    @Test
    void delete() {
        Dish d = new Dish();
        Optional<Dish> dish = Optional.of(d);
        Mockito.doReturn(dish).when(repository).findById(0L);
        Assertions.assertTrue(service.delete(0L));
    }

    @Test
    void update() {
        Dish dish = new Dish();
        dish.setId(0L);
        Optional optional = Optional.of(dish);
        Mockito.doReturn(optional).when(repository).findById(0L);
        Assertions.assertTrue(service.update(dish));
    }
}