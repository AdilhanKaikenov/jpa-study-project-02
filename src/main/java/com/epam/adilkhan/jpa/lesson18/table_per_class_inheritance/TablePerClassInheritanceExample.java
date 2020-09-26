package com.epam.adilkhan.jpa.lesson18.table_per_class_inheritance;

import com.epam.adilkhan.jpa.lesson18.table_per_class_inheritance.entity.BlogPost;
import com.epam.adilkhan.jpa.lesson18.table_per_class_inheritance.entity.Book;
import com.epam.adilkhan.jpa.lesson18.table_per_class_inheritance.entity.Publication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class TablePerClassInheritanceExample {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(new Book("book_title_1", new Date(), 300));
        entityManager.persist(new Book("book_title_2", new Date(), 400));
        entityManager.persist(new Book("book_title_3", new Date(), 500));
        entityManager.persist(new BlogPost("blogpost_title_3", new Date(), "https://blogpost.com/blog/1"));
        entityManager.persist(new BlogPost("blogpost_title_3", new Date(), "https://blogpost.com/blog/2"));
        entityManager.persist(new BlogPost("blogpost_title_3", new Date(), "https://blogpost.com/blog/3"));
        entityManager.getTransaction().commit();

        // The superclass is now also an entity
        entityManager.getTransaction().begin();
        String query = "SELECT p FROM com.epam.adilkhan.jpa.lesson18.table_per_class_inheritance.entity.Publication p";
        List<Publication> publications = entityManager.createQuery(query, Publication.class).getResultList();

        for (Publication publication : publications) {
            if (publication instanceof Book) {
                System.out.println("Book: " + publication.getTitle());
            }
            if (publication instanceof BlogPost) {
                System.out.println("BlogPost: " + publication.getTitle());
            }
        }

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
