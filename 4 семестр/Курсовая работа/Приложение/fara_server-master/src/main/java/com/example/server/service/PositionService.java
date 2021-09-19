package com.example.server.service;

import com.example.server.entity.Position;
import com.example.server.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для класса {@link Position}
 */
@Service
public class PositionService {

    private PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    /**
     * Создание должности
     * @param s s
     */
    public boolean create(Position s) {
        positionRepository.save(s);
        return true;
    }

    /**
     * Получение всех записей должностей
     * @return Все должности
     */
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    /**
     * Получение одной записи должности по id
     * @param id id должности
     * @return Запись должности по id
     */
    public Optional<Position> getOne(Long id) {
        return positionRepository.findById(id);
    }

    /**
     * Удалние должности по id
     * @param id id должности
     * @return true при успешном удалении или false при не найденной должности по id
     */
    public boolean delete(Long id) {
        if (getOne(id).isPresent()) {
            positionRepository.deleteById(id);
            return true;
        }

        return false;
    }

    /**
     * Обновление информации о должности по id
     * @param s s
     * @return true при успешном обновлении или false при не найденной должности по id
     */
    public boolean update(Position s) {
        if (getOne(s.getId()).isPresent()) {
            positionRepository.save(s);
            return true;
        }

        return false;
    }

}