package com.epam.adilkhan.jpa.lesson15.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    /**
     * The orphanRemoval feature can make it very comfortable to remove a child entity.
     * You can use it for parent-child relationships in which a child entity can’t exist
     * without its parent entity.
     * An PostComment entity can’t exist without a Post entity. So, any PostComment entity
     * that’s not associated to a Post entity, needs to be removed.
     * orphanRemoval=true means child entity should be removed automatically by the ORM
     * if it's no longer referenced by a parent entity, eg. we have a collection of items
     * and we remove one of them. That item does not have a reference now and it will be removed.
     * Do not mix orphanRemoval up with cascadeType.REMOVE which are database level operations.
     *
     * A good practice is to use cascade in the parent entity so that we can propagate
     * the changes and apply them into children.
     *
     * fetchType=LAZY, retrieves entity, only when we really need it.
     */
    @OneToMany(mappedBy = "post", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostComment> comments;

    public Post() {
    }

    public Post(String title, List<PostComment> comments) {
        this.title = title;
        this.comments = comments;
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

    public List<PostComment> getComments() {
        return this.comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }
}
