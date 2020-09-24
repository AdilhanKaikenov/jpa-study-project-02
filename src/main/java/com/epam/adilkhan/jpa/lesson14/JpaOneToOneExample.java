package com.epam.adilkhan.jpa.lesson14;

import com.epam.adilkhan.jpa.lesson14.entity.PlatformUser;
import com.epam.adilkhan.jpa.lesson14.entity.PlatformUserAddress;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The PlatformUser will have one PlatformUserAddress, and only one PlatformUser will be linked to the PlatformUserAddress.
 */
public class JpaOneToOneExample {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        PlatformUser platformUser = new PlatformUser("my_username");
        PlatformUserAddress platformUserAddress = new PlatformUserAddress("New-York", "Chinatown", platformUser);
        platformUser.setPlatformUserAddress(platformUserAddress);
        entityManager.persist(platformUser);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
