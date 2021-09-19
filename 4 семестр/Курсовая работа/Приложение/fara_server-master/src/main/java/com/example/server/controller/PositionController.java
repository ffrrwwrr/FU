package com.example.server.controller;

import com.example.server.entity.Position;
import com.example.server.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для класса {@link Position}
 */
@RestController
public class PositionController {

    private PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    /**
     * Получение списка должностей
     * @return OK при полном списке или NOT_FOUND при пустом списке
     */
    @GetMapping(value = "positions")
    public ResponseEntity<List<Position>> getAll() {
        List<Position> positions = positionService.getAll();
        return positions != null ? new ResponseEntity<>(positions, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Добавление должности в список
     * @param position должность
     * @return добавленная должность
     */
    @PostMapping(value = "positions")
    public ResponseEntity<?> create(@RequestBody Position position) {
        positionService.create(position);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Обновление информации о должности по его id
     * @param id id должности
     * @param position должность
     * @return OK при обновлении данных или NOT_FOUND при не найденной должности с таким id
     */
    @PutMapping(value = "positions/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Position position) {
        position.setId(id);
        return positionService.update(position) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Удаление должности по его id
     * @param id id должности
     * @return OK при успешном удалении должности или NOT_FOUND при не найденной должности таким id
     */
    @DeleteMapping(value = "positions/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return positionService.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
