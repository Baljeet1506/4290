package com.example.realtorandviewer;

public class Properties {

    String unitNumber, houseNumber, street, city, province, postal, price, beds, landSize, baths, floorSize, age, type, title, uID, listingImage;


    public Properties() {

    }

    public Properties(String unitNumber, String houseNumber, String street, String city, String province, String postal, String price, String beds, String landSize, String baths, String floorSize, String age, String type, String title, String listingImage) {
        this.unitNumber = unitNumber;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postal = postal;
        this.price = price;
        this.beds = beds;
        this.landSize = landSize;
        this.baths = baths;
        this.floorSize = floorSize;
        this.age = age;
        this.type = type;
        this.title = title;
        this.listingImage = listingImage;
    }
    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public String getLandSize() {
        return landSize;
    }

    public void setLandSize(String landSize) {
        this.landSize = landSize;
    }

    public String getBaths() {
        return baths;
    }

    public void setBaths(String baths) {
        this.baths = baths;
    }

    public String getFloorSize() {
        return floorSize;
    }

    public void setFloorSize(String floorSize) {
        this.floorSize = floorSize;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListingImage() {
        return listingImage;
    }

    public void setListingImage(String listingImage) {
        this.listingImage = listingImage;
    }
}
