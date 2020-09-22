package com.epam.adilkhan.jpa.lesson10.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private int id;

    private String firstname;

    private String lastname;

    @Transient
    private int age;

    public Person() {
    }

    public Person(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }
}
