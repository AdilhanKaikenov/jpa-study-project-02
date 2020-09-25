package com.epam.adilkhan.jpa.lesson15;

import com.epam.adilkhan.jpa.lesson15.entity.Post;
import com.epam.adilkhan.jpa.lesson15.entity.PostComment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JpaOneToManyExample {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        List<PostComment> comments = new ArrayList<PostComment>();
        Post post = new Post("Title_1", comments);
        PostComment parent = new PostComment("parent_message", null, null);
        PostComment comment = new PostComment("message", parent, null);
        comments.addAll(Arrays.asList(parent, comment));
        entityManager.persist(post);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
