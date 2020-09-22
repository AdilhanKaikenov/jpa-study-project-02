package com.epam.adilkhan.jpa.lesson08.id_class_example;

import com.epam.adilkhan.jpa.lesson08.id_class_example.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Id_Class_Example {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(new Account("№1010", "Private"));
        entityManager.persist(new Account("№1011", "Public"));
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
