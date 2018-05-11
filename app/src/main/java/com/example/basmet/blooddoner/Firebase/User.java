package com.example.basmet.blooddoner.Firebase;

/**
 * Created by basmet on 5/2/2018.
 */

public class User {
    private String email, password, firstName, lastName, phone, DateOfBirth,
    gender, city, bloodGroup, location;
//String gender, String city, String bloodGroup,
 /*   this.gender = gender;
        this.city = city;
        this.bloodGroup = bloodGroup;   */
    public User(String firstName, String lastName, String email,  String phone, String password, String dateOfBirth, String location) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.DateOfBirth = dateOfBirth;
        this.location = location;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
