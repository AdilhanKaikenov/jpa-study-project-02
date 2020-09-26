package com.epam.adilkhan.jpa.lesson18.single_table_inheritance.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "book")
@DiscriminatorValue("Book")
public class Book extends Publication {

    @Column
    private int pages;

    public Book() {
    }

    public Book(String title, Date publishingDate, int pages) {
        super(title, publishingDate);
        this.pages = pages;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}