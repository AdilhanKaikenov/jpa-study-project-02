package com.epam.adilkhan.jpa.lesson06;

import com.epam.adilkhan.jpa.lesson06.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaExample_01 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(new Student("Chan Li", 22, "Boston", "Chinatown", "USA"));
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
