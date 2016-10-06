package com.codekul.database.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by aniruddha on 6/10/16.
 */

@Entity
public class Car {

    @Id(autoincrement = true)
    private Long carId;

    @Property(nameInDb = "car_name")
    private String carName;

    @Property(nameInDb = "manufacturer")
    private String manufacturer;

    @Property(nameInDb = "model")
    private Integer model;

    @Property(nameInDb = "country")
    private String country;

    @Property(nameInDb = "price")
    private Double price;

    @Property(nameInDb = "is_second")
    private Boolean isSecond;

    @Property(nameInDb = "color")
    private Integer color;

    @Property(nameInDb = "date")
    private Long date;

    @Property(nameInDb = "car_num")
    private String carNum;

    @Property(nameInDb = "milage")
    private Float milage;

    @Generated(hash = 1765456602)
    public Car(Long carId, String carName, String manufacturer, Integer model,
            String country, Double price, Boolean isSecond, Integer color,
            Long date, String carNum, Float milage) {
        this.carId = carId;
        this.carName = carName;
        this.manufacturer = manufacturer;
        this.model = model;
        this.country = country;
        this.price = price;
        this.isSecond = isSecond;
        this.color = color;
        this.date = date;
        this.carNum = carNum;
        this.milage = milage;
    }

    @Generated(hash = 625572433)
    public Car() {
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public Float getMilage() {
        return milage;
    }

    public void setMilage(Float milage) {
        this.milage = milage;
    }

    public Long getCarId() {
        return this.carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Boolean getIsSecond() {
        return this.isSecond;
    }

    public void setIsSecond(Boolean isSecond) {
        this.isSecond = isSecond;
    }
}
