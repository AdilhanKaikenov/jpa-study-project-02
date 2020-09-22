package com.epam.adilkhan.jpa.lesson08.embedded_id_example.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class MyBook {

    @EmbeddedId
    private BookId bookId;

    private int publishYear;

    public MyBook() {
    }

    public MyBook(BookId bookId, int publishYear) {
        this.bookId = bookId;
        this.publishYear = publishYear;
    }

    public BookId getBookId() {
        return this.bookId;
    }

    public void setBookId(BookId bookId) {
        this.bookId = bookId;
    }

    public int getPublishYear() {
        return this.publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
}
