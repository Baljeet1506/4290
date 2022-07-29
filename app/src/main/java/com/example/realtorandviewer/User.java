package com.example.realtorandviewer;

public class User {

    public String firstName, lastName, company, email, phNumber, aboutMe, pimage;
    public Integer userType;

    public User() {

    }

    //Constructor for Realtor user type
    public User(String firstName, String lastName, String company, String email, String phNumber, String aboutMe, String pimage, Integer userType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.email = email;
        this.phNumber = phNumber;
        this.userType = userType;
        this.aboutMe = aboutMe;
        this.pimage = pimage;
    }

    //Constructor for Viewer user type without phone number
    public User(String firstName, String lastName, String email, Integer userType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }


    public User(String pimage) {


        this.pimage = pimage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) { this.company = company; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }
}
