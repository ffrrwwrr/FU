package sample.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class Ingredient {

    @Expose
    @SerializedName("id")
    private Long id;
    @Expose
    @SerializedName("ingredient")
    private String ingredient;
    @Expose
    @SerializedName("unitPrice")
    private Double unitPrice;
    @Expose
    @SerializedName("unit")
    private Unit unit;
    @Expose
    @SerializedName("dishes")
    private Set<Dish> dishes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }
}
