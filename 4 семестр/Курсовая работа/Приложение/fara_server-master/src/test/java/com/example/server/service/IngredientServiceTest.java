package com.example.server.service;

import com.example.server.entity.Employee;
import com.example.server.entity.Ingredient;
import com.example.server.repository.IngredientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class IngredientServiceTest {

    @Autowired
    private IngredientService service;

    @MockBean
    private IngredientRepository repository;

    @Test
    void create() {
        Ingredient ingredient = new Ingredient();
        Assertions.assertTrue(service.create(ingredient));
    }

    @Test
    void getAll() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient());
        Mockito.doReturn(ingredients).when(repository).findAll();
        Assertions.assertEquals(ingredients, service.getAll());
    }

    @Test
    void getOne() {
        Ingredient ingredient = new Ingredient();
        Optional<Ingredient> optionalDish = Optional.of(ingredient);
        Mockito.doReturn(optionalDish).when(repository).findById(0L);
        Assertions.assertEquals(optionalDish, service.getOne(0L));
    }

    @Test
    void delete() {
        Ingredient i = new Ingredient();
        Optional<Ingredient> ingredient = Optional.of(i);
        Mockito.doReturn(ingredient).when(repository).findById(0L);
        Assertions.assertTrue(service.delete(0L));
    }

    @Test
    void update() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(0L);
        Optional optional = Optional.of(ingredient);
        Mockito.doReturn(optional).when(repository).findById(0L);
        Assertions.assertTrue(service.update(ingredient));
    }
}