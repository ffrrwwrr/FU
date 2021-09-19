package sample.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Unit {

    @Expose
    @SerializedName("id")
    private Long id;
    @Expose
    @SerializedName("unit")
    private String unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
