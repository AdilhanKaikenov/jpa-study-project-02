package com.epam.adilkhan.jpa.lesson18.mapped_superclass;

import com.epam.adilkhan.jpa.lesson18.mapped_superclass.entity.BlogPost;
import com.epam.adilkhan.jpa.lesson18.mapped_superclass.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class MappedSuperclassExample {
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

        // You can’t use the inheritance structure for polymorphic queries or to define relationships.
        // But you can, of course, query the entites as any other entity.
        entityManager.getTransaction().begin();
        // The Book entity and all its attributes are mapped to the book table. This makes the generated query simple and efficient.
        // It just has to select all columns of the book table.
        String query = "SELECT b FROM com.epam.adilkhan.jpa.lesson18.mapped_superclass.entity.Book b";
        List<Book> books = entityManager.createQuery(query, Book.class).getResultList();
        System.out.println(books.size());
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
