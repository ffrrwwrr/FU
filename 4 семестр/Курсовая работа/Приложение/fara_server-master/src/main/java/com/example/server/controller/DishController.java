package com.example.server.controller;

import com.example.server.entity.Dish;
import com.example.server.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для класса {@link Dish}
 */
@RestController
public class DishController {

    private DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    /**
     * Получение списка блюд
     * @return OK при полном списке или NOT_FOUND при пустом списке
     */
    @GetMapping(value = "dishes")
    public ResponseEntity<List<Dish>> getAll() {
        List<Dish> dishes = dishService.getAll();
        return dishes != null ? new ResponseEntity<>(dishes, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Добавление блюда в список
     * @param dish блюдо
     * @return добавленное блюдо
     */
    @PostMapping(value = "dishes")
    public ResponseEntity<?> create(@RequestBody Dish dish) {
        dishService.create(dish);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Обновление информации о блюде по его id
     * @param id id блюда
     * @param dish блюдо
     * @return OK при обновлении данных или NOT_FOUND при не найденом блюде с таким id
     */
    @PutMapping(value = "dishes/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Dish dish) {
        dish.setId(id);
        return dishService.update(dish) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Удаление блюда по его id
     * @param id id блюда
     * @return OK при успешном удалении блюда или NOT_FOUND при не найденом блюде с таким id
     */
    @DeleteMapping(value = "dishes/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return dishService.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
