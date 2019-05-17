package com.kev.Learner.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "weight")
    private float weight;

    @Column(name = "unit")
    private String unit;

    @Column(name = "batchnumber")
    private String batchNumber;

    @Column(name = "manufacturedate")
    private String manufactureDate;

    @Column(name = "expirydate")
    private String expiryDate;

    @Column(name = "status")
    private String status;

    public Product() {}

    public Product(
            String name, String description, float price, float weight, String unit,
            String batchNumber, String manufactureDate, String expiryDate, String status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.unit = unit;
        this.batchNumber = batchNumber;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
