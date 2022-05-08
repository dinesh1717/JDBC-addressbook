package com.bridgelabz.addressbookjdbc;
import java.time.LocalDate;


public class Person {
    // variable declaration

    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String zip;
    String phonenumber;
    String email;


    // paarmeterised constructor
    public Person(String firstName, String lastName, String address, String city, String state, String zip,
                  String phonenumber, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phonenumber = phonenumber;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    //toString
    @Override
    public String toString() {
        return "Person [lastName=" + lastName + ", address=" + address + ", city=" + city + ", state=" + state
                + ", zip=" + zip + ", phonenumber=" + phonenumber + ", email=" + email
                + ", firstName=" + firstName + "]";
    }

}
