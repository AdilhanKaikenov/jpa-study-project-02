package com.epam.adilkhan.jpa.lesson18.table_per_class_inheritance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "blog_post")
public class BlogPost extends Publication {

    @Column
    private String url;

    public BlogPost() {
    }

    public BlogPost(String title, Date publishingDate, String url) {
        super(title, publishingDate);
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
