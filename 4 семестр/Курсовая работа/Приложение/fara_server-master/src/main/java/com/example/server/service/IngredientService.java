package com.example.server.service;

import com.example.server.entity.Ingredient;
import com.example.server.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для класса {@link Ingredient}
 */
@Service
public class IngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Создание ингредиента
     *
     * @param s s
     */
    public boolean create(Ingredient s) {
        ingredientRepository.save(s);
        return true;
    }

    /**
     * Получение всех записей ингредиентов
     *
     * @return Все ингредиенты
     */
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    /**
     * Получение одной записи ингредиента по id
     *
     * @param id id ингредиента
     * @return Запись ингредиента по id
     */
    public Optional<Ingredient> getOne(Long id) {
        return ingredientRepository.findById(id);
    }

    /**
     * Удалние ингредиента по id
     *
     * @param id id ингредиента
     * @return true при успешном удалении или false при не найденном ингредиенте по id
     */
    public boolean delete(Long id) {
        if (getOne(id).isPresent()) {
            ingredientRepository.deleteById(id);
            return true;
        }

        return false;
    }

    /**
     * Обновление информации об ингредиенте по id
     *
     * @param s s
     * @return true при успешном обновлении или false при не найденном ингредиенте по id
     */
    public boolean update(Ingredient s) {
        if (getOne(s.getId()).isPresent()) {
            ingredientRepository.save(s);
            return true;
        }

        return false;
    }
}
