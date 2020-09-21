package com.epam.adilkhan.jpa.lesson06.entity;

import javax.persistence.*;

@Entity
@Table(name = "universityStudent")
@SecondaryTables({
        @SecondaryTable(name = "student_address"),
        @SecondaryTable(name = "student_country")
})
public class Student {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int age;

    @Column(table = "student_address")
    private String city;

    @Column(table = "student_address")
    private String street;

    @Column(table = "student_country")
    private String country;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, String city, String street, String country) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.street = street;
        this.country = country;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
