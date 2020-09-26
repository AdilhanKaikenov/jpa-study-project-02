package com.epam.adilkhan.jpa.lesson18.single_table_inheritance;

import com.epam.adilkhan.jpa.lesson18.single_table_inheritance.entity.BlogPost;
import com.epam.adilkhan.jpa.lesson18.single_table_inheritance.entity.Book;
import com.epam.adilkhan.jpa.lesson18.single_table_inheritance.entity.Publication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class SingleTableInheritanceExample {
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

        //  The single table strategy allows easy and efficient data access.
        //  All attributes of each entity are stored in one table, and the query doesn’t require any join statements.
        //  The only thing that Hibernate needs to add to the SQL query to fetch a particular entity class is a comparison
        //  of the discriminator value. In this example, it’s a simple expression that checks that the column publication_type
        //  contains the value ‘Book‘.
        entityManager.getTransaction().begin();
        String query = "SELECT b FROM com.epam.adilkhan.jpa.lesson18.single_table_inheritance.entity.Book b";
        List<Publication> resultList = entityManager.createQuery(query, Publication.class).getResultList();
        System.out.println(resultList.size());
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
