package com.example.server.service;

import com.example.server.entity.Unit;
import com.example.server.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для класса {@link Unit}
 */
@Service
public class UnitService {

    private UnitRepository positionRepository;

    @Autowired
    public UnitService(UnitRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    /**
     * Создание единицы измерения
     * @param s s
     */
    public boolean create(Unit s) {
        positionRepository.save(s);
        return true;
    }

    /**
     * Получение всех записей единиц измерения
     * @return Все единицы измерения
     */
    public List<Unit> getAll() {
        return positionRepository.findAll();
    }

    /**
     * Получение одной записи единицы измерения по id
     * @param id id единицы измерения
     * @return Запись единицы измерения по id
     */
    public Optional<Unit> getOne(Long id) {
        return positionRepository.findById(id);
    }

    /**
     * Удалние единицы измерения по id
     * @param id id единицы измерения
     * @return true при успешном удалении или false при не найденной единицы измерения по id
     */
    public boolean delete(Long id) {
        if (getOne(id).isPresent()) {
            positionRepository.deleteById(id);
            return true;
        }

        return false;
    }

    /**
     * Обновление информации об единице измерения по id
     * @param s s
     * @return true при успешном обновлении или false при не найденной единицы измерения по id
     */
    public boolean update(Unit s) {
        if (getOne(s.getId()).isPresent()) {
            positionRepository.save(s);
            return true;
        }

        return false;
    }

}
