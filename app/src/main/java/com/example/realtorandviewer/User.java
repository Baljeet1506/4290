package com.example.realtorandviewer;

public class User {

    public String firstName, lastName, email, phNumber;
    public Integer userType;

    public User() {

    }

    //Constructor for Realtor user type
    public User(String firstName, String lastName, String email, String phNumber, int userType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phNumber = phNumber;
        this.userType = userType;
    }

    //Constructor for Viewer user type without phone number
    public User(String firstName, String lastName, String email, Integer userType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }

}
