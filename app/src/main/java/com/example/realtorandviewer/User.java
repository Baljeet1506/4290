package com.example.realtorandviewer;

public class User {

    public String firstName, lastName, email, phNumber, aboutMe;
    public Integer userType;

    public User() {

    }

    //Constructor for Realtor user type
    public User(String firstName, String lastName, String email, String phNumber, String aboutMe, Integer userType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phNumber = phNumber;
        this.userType = userType;
        this.aboutMe = aboutMe;
    }

    //Constructor for Viewer user type without phone number
    public User(String firstName, String lastName, String email, Integer userType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
}
