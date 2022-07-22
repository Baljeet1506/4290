package com.example.scrollview;

public class MainModel {
    private String houseNumber;
    private String street;
    private String city;
    private String price;
    private String type;
    private String landSize;
    public MainModel() {
    }
    public MainModel(String houseNumber, String street, String city, String price, String type, String landSize) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.price = price;
        this.type = type;
        this.landSize = landSize;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getLandSize() { return landSize; }

    public void setLandSize(String landSize) { this.landSize = landSize; }
}
