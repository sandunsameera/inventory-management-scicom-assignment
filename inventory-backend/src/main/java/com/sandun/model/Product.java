package com.sandun.model;

import java.util.Date;

public class Product {

    private int id;
    private String brand;
    private String type;
    private Double price;
    private Date expireDate;
    private String description;

    public Product(){}

    public Product(int id, String brand, String type, Double price, Date expireDate, String description) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.expireDate = expireDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
