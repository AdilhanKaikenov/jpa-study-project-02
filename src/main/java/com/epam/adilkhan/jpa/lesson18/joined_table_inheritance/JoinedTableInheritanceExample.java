package com.epam.adilkhan.jpa.lesson18.joined_table_inheritance;

import com.epam.adilkhan.jpa.lesson18.joined_table_inheritance.entity.BlogPost;
import com.epam.adilkhan.jpa.lesson18.joined_table_inheritance.entity.Book;
import com.epam.adilkhan.jpa.lesson18.joined_table_inheritance.entity.Publication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class JoinedTableInheritanceExample {
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

        //  The columns mapped by each subclass are stored in 2 different database tables.
        //  The publication table contains all columns mapped by the superclass Publication and the book
        //  table all columns mapped by the Book entity. Hibernate needs to join these 2 tables by their
        //  primary keys to select all attributes of the Book entity. This is an overhead that makes these
        //  queries slightly slower than the simpler queries generated for the single table strategy.
        entityManager.getTransaction().begin();
        String query = "SELECT b FROM com.epam.adilkhan.jpa.lesson18.joined_table_inheritance.entity.Book b";
        List<Publication> publications = entityManager.createQuery(query, Publication.class).getResultList();
        System.out.println(publications.size());
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
