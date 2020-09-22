package com.epam.adilkhan.jpa.lesson08.embedded_id_example;

import com.epam.adilkhan.jpa.lesson08.embedded_id_example.entity.BookId;
import com.epam.adilkhan.jpa.lesson08.embedded_id_example.entity.MyBook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Embedded_Id_Example {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(new MyBook(new BookId("Book_Title_1", "Rus"), 2010));
        entityManager.persist(new MyBook(new BookId("Book_Title_2", "Kz"), 2012));
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
