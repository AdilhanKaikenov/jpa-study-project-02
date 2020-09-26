package com.epam.adilkhan.jpa.lesson18.single_table_inheritance.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "blog_post")
@DiscriminatorValue("Blog")
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
