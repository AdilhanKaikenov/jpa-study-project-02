package com.epam.adilkhan.jpa.lesson13;

import com.epam.adilkhan.jpa.lesson13.entity.ExampleEntity_01;
import com.epam.adilkhan.jpa.lesson13.entity.Types;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JpaElementCollectionExample {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Set<Types> reportTypesOne = new HashSet<Types>();
        reportTypesOne.add(Types.Type_ONE);
        reportTypesOne.add(Types.Type_TWO);
        reportTypesOne.add(Types.Type_THREE);

        Set<Types> reportTypesTwo = new HashSet<Types>();
        reportTypesTwo.add(Types.Type_THREE);
        reportTypesTwo.add(Types.Type_FOUR);

        Map<Integer, String> integerStringHashMapOne = new HashMap<Integer, String>();
        integerStringHashMapOne.put(1, "one");
        integerStringHashMapOne.put(2, "two");
        integerStringHashMapOne.put(3, "three");

        Map<Integer, String> integerStringHashMapTwo = new HashMap<Integer, String>();
        integerStringHashMapTwo.put(3, "three");
        integerStringHashMapTwo.put(4, "four");
        integerStringHashMapTwo.put(5, "five");

        entityManager.getTransaction().begin();
        entityManager.persist(new ExampleEntity_01("entity_1", reportTypesOne, integerStringHashMapOne));
        entityManager.persist(new ExampleEntity_01("entity_2", reportTypesTwo, integerStringHashMapTwo));
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
