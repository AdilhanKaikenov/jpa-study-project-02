package com.epam.adilkhan.jpa.lesson15.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post_comment")
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private PostComment parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<PostComment> replies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = true)
    private Post post;

    public PostComment() {
    }

    public PostComment(String message) {
        this.message = message;
    }

    public PostComment(String message, PostComment parent, List<PostComment> replies) {
        this.message = message;
        this.parent = parent;
        this.replies = replies;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PostComment getParent() {
        return this.parent;
    }

    public void setParent(PostComment parent) {
        this.parent = parent;
    }

    public List<PostComment> getReplies() {
        return this.replies;
    }

    public void setReplies(List<PostComment> replies) {
        this.replies = replies;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    //  it’s good practice to override equals and hashCode for the child entity in a bidirectional association.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostComment that = (PostComment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
