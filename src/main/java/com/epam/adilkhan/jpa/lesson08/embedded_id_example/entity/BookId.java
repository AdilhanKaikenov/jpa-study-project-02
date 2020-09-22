package com.epam.adilkhan.jpa.lesson08.embedded_id_example.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookId implements Serializable {

    private String title;

    private String language;

    public BookId() {
    }

    public BookId(String title, String language) {
        this.title = title;
        this.language = language;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookId bookId = (BookId) o;

        if (title != null ? !title.equals(bookId.title) : bookId.title != null) return false;
        return language != null ? language.equals(bookId.language) : bookId.language == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}