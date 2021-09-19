package com.example.server.service;

import com.example.server.entity.Order;
import com.example.server.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для класса {@link Order}
 */
@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Создание заказа
     *
     * @param s s
     */
    public boolean create(Order s) {
        orderRepository.save(s);
        return true;
    }

    /**
     * Получение всех записей заказов
     *
     * @return Все заказы
     */
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    /**
     * Получение одной записи заказа по id
     *
     * @param id id заказа
     * @return Запись заказа по id
     */
    public Optional<Order> getOne(Long id) {
        return orderRepository.findById(id);
    }

    /**
     * Удалние заказа по id
     *
     * @param id id заказа
     * @return true при успешном удалении или false при не найденном заказе по id
     */
    public boolean delete(Long id) {
        if (getOne(id).isPresent()) {
            orderRepository.deleteById(id);
            return true;
        }

        return false;
    }

    /**
     * Обновление информации о заказе по id
     *
     * @param s s
     * @return true при успешном обновлении или false при не найденном заказе по id
     */
    public boolean update(Order s) {
        if (getOne(s.getId()).isPresent()) {
            orderRepository.save(s);
            return true;
        }

        return false;
    }

}
