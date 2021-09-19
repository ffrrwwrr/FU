package com.example.server.controller;

import com.example.server.entity.Unit;
import com.example.server.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UnitController {

    private UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    /**
     * Получение списка единиц измерения
     * @return OK при полном списке или NOT_FOUND при пустом списке
     */
    @GetMapping(value = "units")
    public ResponseEntity<List<Unit>> getAll() {
        List<Unit> units = unitService.getAll();
        return units != null ? new ResponseEntity<>(units, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Добавление единицы измерения в список
     * @param unit единица измерения
     * @return добавленная единицы измерения
     */
    @PostMapping(value = "units")
    public ResponseEntity<?> create(@RequestBody Unit unit) {
        unitService.create(unit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Обновление информации о единице измерения по его id
     * @param id id единицы измерения
     * @param unit единица измерения
     * @return OK при обновлении данных или NOT_FOUND при не найденной единицы измерения с таким id
     */
    @PutMapping(value = "units/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Unit unit) {
        unit.setId(id);
        return unitService.update(unit) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Удаление сотрудника по его id
     * @param id id единицы измерения
     * @return OK при успешном удалении единицы измерения или NOT_FOUND при не найденной единицы измерения с таким id
     */
    @DeleteMapping(value = "units/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return unitService.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
