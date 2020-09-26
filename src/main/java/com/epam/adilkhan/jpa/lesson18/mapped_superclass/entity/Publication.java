package com.epam.adilkhan.jpa.lesson18.mapped_superclass.entity;

import javax.persistence.*;
import java.util.Date;

// Publication has no @Entity annotation and will not be managed by the persistence provider.
@MappedSuperclass
public abstract class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Column
    protected String title;

    @Column
    @Temporal(TemporalType.DATE)
    private Date publishingDate;

    public Publication() {
    }

    public Publication(String title, Date publishingDate) {
        this.title = title;
        this.publishingDate = publishingDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishingDate() {
        return this.publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }
}