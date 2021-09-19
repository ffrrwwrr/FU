package sample.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Set;

public class Order {

    @Expose
    @SerializedName("id")
    private Long id;
    @Expose
    @SerializedName("locator")
    private String locator;
    @Expose
    @SerializedName("employee")
    private Employee employee;
    @Expose
    @SerializedName("dishes")
    private Set<Dish> dishes;
    @Expose
    @SerializedName("status")
    private Integer status;
    @Expose
    @SerializedName("orderTime")
    private String orderTime;
    @Expose
    @SerializedName("counts")
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

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public HashMap<Long, Long> getCounts() {
        return counts;
    }

    public void setCounts(HashMap<Long, Long> counts) {
        this.counts = counts;
    }
}
