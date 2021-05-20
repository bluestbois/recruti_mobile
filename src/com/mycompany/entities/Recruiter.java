/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author asus
 */
public class Recruiter {
    
    private int id;
    private String name;
    private String email;
    private String image;
    private String description;
    private String phoneNumber;
    private String password;
     private String address;
     private String field;

    public Recruiter(String name, String email, String image, String description, String phoneNumber, String password, String address, String field) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Recruiter{" + "id=" + id + ", name=" + name + ", email=" + email + ", image=" + image + ", description=" + description + ", phoneNumber=" + phoneNumber + ", password=" + password + ", address=" + address + ", field=" + field + '}';
    }

    public Recruiter() {
    }

    public Recruiter(int id) {
        this.id = id;
    }

    public Recruiter(String name, String email) {
        this.name = name;
        this.email = email;
    }
    

  
    
    
}
