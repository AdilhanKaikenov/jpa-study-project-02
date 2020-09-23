package com.epam.adilkhan.jpa.lesson11.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class JpaExample_02 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(new Gamer("OldOrc", new Date(), CharacterRace.ORC));
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
