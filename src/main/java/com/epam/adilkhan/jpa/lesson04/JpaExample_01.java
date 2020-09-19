package com.epam.adilkhan.jpa.lesson04;

import com.epam.adilkhan.jpa.lesson04.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * To work, we need:
 * - An entity (Book.class);
 * - A file with settings (persistence.xml) ;
 * - A code in which we get an EntityManager.
 *
 */
public class JpaExample_01 {
    public static void main(String[] args) {

        // (Factory pattern) EntityManagerFactory is a factory that provides an EntityManager instance for the given persistence unit
        // Moreover, EntityManager can be implemented by different providers. (Hibernate, EclipseLink, etc.)
        // The instance of EntityManagerFactory is created by passing persistence-unit name of persistence.xml as arguments.
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        // The factory will return the EntityManager depending on the specified settings (persistence.xml)
        // JPA provides javax.persistence.EntityManager interface which is used to interact with database
        // All work happens through EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // The instance of EntityManager plays around persistence context.
        // Persistence context is the set of entity instances where for any persistence entity,
        // there is a unique entity instance. The lifecycle of entity instances are managed within
        // the persistence context using EntityManager.

        // To write something to the database, it is needed to get a transaction
        // EntityManager provides javax.persistence.EntityTransaction using which we can begin and commit transaction.
        entityManager.getTransaction().begin();
        entityManager.persist(new Book("Собачье сердце")); // persist
        entityManager.getTransaction().commit();

        // After using entity manager and entity manager factory, they should be closed.
        entityManager.close();
        entityManagerFactory.close();
    }
}
