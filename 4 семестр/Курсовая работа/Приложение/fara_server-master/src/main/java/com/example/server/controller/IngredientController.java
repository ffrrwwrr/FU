package com.example.server.controller;

import com.example.server.entity.Employee;
import com.example.server.entity.Ingredient;
import com.example.server.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для класса {@link Ingredient}
 */
@RestController
public class IngredientController {

    private IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    /**
     * Получение списка ингредиентов
     * @return OK при полном списке или NOT_FOUND при пустом списке
     */
    @GetMapping(value = "ingredients")
    public ResponseEntity<List<Ingredient>> getAll() {
        List<Ingredient> ingredients = ingredientService.getAll();
        return ingredients != null ? new ResponseEntity<>(ingredients, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Добавление ингредиента в список
     * @param ingredient ингредиент
     * @return добавленный ингредиент
     */
    @PostMapping(value = "ingredients")
    public ResponseEntity<?> create(@RequestBody Ingredient ingredient) {
        ingredientService.create(ingredient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Обновление информации об ингредиенту по его id
     * @param id id ингредиента
     * @param ingredient ингредиент
     * @return OK при обновлении данных или NOT_FOUND при не найденном ингредиенте с таким id
     */
    @PutMapping(value = "ingredients/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Ingredient ingredient) {
        ingredient.setId(id);
        return ingredientService.update(ingredient) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Удаление ингредиента по его id
     * @param id id ингредиента
     * @return OK при успешном удалении ингредиента или NOT_FOUND при не найденном ингредиенте с таким id
     */
    @DeleteMapping(value = "ingredients/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ingredientService.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
