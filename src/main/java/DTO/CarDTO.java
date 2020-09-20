/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Car;

/**
 *
 * @author Benjamin
 */
public class CarDTO {
    private long id;
    private String brand;
    private String model;
    private int year;
    private double price; 
    private String fuelType;
      public CarDTO() {
    }
    public CarDTO(Car car) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.year = car.getYear();
        this.price = car.getPrice();
        this.fuelType = car.getFuelType();
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "CarDTO{" + "id=" + id + ", brand=" + brand + ", model=" + model + ", year=" + year + ", price=" + price + ", fuelType=" + fuelType + '}';
    }


}
