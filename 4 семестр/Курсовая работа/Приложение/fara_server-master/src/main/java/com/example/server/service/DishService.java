package com.example.server.service;

import com.example.server.entity.Dish;
import com.example.server.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для класса {@link Dish}
 */
@Service
public class DishService {

    private DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    /**
     * Создание блюда
     *
     * @param s s
     */
    public boolean create(Dish s) {
        dishRepository.save(s);
        return true;
    }

    /**
     * Получение всех записей блюд
     *
     * @return Все блюда
     */
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    /**
     * Получение одной записи блюда по id
     *
     * @param id id блюда
     * @return Запись блюда по id
     */
    public Optional<Dish> getOne(Long id) {
        return dishRepository.findById(id);
    }

    /**
     * Удалние блюда по id
     *
     * @param id id блюда
     * @return true при успешном удалении или false при не найденном блюде по id
     */
    public boolean delete(Long id) {
        if (getOne(id).isPresent()) {
            dishRepository.deleteById(id);
            return true;
        }

        return false;
    }

    /**
     * Обновление информации о блюде по id
     *
     * @param s s
     * @return true при успешном обновлении или false при не найденном блюде по id
     */
    public boolean update(Dish s) {
        if (getOne(s.getId()).isPresent()) {
            dishRepository.save(s);
            return true;
        }

        return false;
    }
}