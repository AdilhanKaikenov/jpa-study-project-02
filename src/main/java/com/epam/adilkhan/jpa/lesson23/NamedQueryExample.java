package com.epam.adilkhan.jpa.lesson23;

import com.epam.adilkhan.jpa.lesson23.entity.User;

import javax.persistence.*;
import java.util.List;

public class NamedQueryExample {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(new User("Chan Li", "chan_li@gmail.com", "Secret123"));
        entityManager.persist(new User("Patrik Brown", "patrik_brown@gmail.com", "Secret123"));
        entityManager.persist(new User("Jessica Alba", "jessica_alba@gmail.com", "Secret123"));
        entityManager.getTransaction().commit();

        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.findAll", User.class);
        List<User> users = namedQuery.getResultList();
        for (User user : users) {
            System.out.println("User: " + user.getFullname());
        }

        Query query = entityManager.createNamedQuery("User.count");
        long count = (Long) query.getSingleResult();
        System.out.println("User count: " + count);

        TypedQuery<User> nativeNamedQuery = entityManager.createNamedQuery("User.findAllUsers", User.class);
        List<User> usersList = nativeNamedQuery.getResultList();
        for (User user : usersList) {
            System.out.println("User: " + user.getFullname());
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
