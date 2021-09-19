package com.example.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

/**
 * Класс Order
 */
@Entity
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String locator;

    @ManyToOne
    @JoinColumn(name = "employees_id")
    private Employee employee;

    private Integer status;

    @ManyToMany(cascade = {CascadeType.MERGE})
    private Set<Dish> dishes;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime orderTime;

    private HashMap<Long, Long> counts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public HashMap<Long, Long> getCounts() {
        return counts;
    }

    public void setCounts(HashMap<Long, Long> counts) {
        this.counts = counts;
    }
}
