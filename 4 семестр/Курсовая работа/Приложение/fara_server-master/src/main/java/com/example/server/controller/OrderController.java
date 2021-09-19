package com.example.server.controller;

import com.example.server.entity.Ingredient;
import com.example.server.entity.Order;
import com.example.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  Контроллер для класса {@link Order}
 */
@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Получение списка заказов
     * @return OK при полном списке или NOT_FOUND при пустом списке
     */
    @GetMapping(value = "orders")
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orders = orderService.getAll();
        return orders != null ? new ResponseEntity<>(orders, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Добавление заказа в список
     * @param order заказ
     * @return добавленный заказ
     */
    @PostMapping(value = "orders")
    public ResponseEntity<?> create(@RequestBody Order order) {
        orderService.create(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Обновление информации о заказе по его id
     * @param id id заказа
     * @param order заказ
     * @return OK при обновлении данных или NOT_FOUND при не найденном заказе с таким id
     */
    @PutMapping(value = "orders/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Order order) {
        order.setId(id);
        return orderService.update(order) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Удаление заказа по его id
     * @param id id заказ
     * @return OK при успешном удалении заказа или NOT_FOUND при не найденном заказе с таким id
     */
    @DeleteMapping(value = "orders/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return orderService.delete(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
