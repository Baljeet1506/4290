package com.example.realtorandviewer;

public class Listings {
    private String houseNumber;
    private String street;
    private String city;
    private String price;

    public Listings(){}

    public Listings(String houseNumber, String street, String city, String price) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.price = price;
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
}
