package com.epam.adilkhan.jpa.lesson26.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * Note that each method takes an Object as a parameter, meaning that any type of entity could use this listener by
 * adding the DebugListener class to its @EntityListeners annotation.
 */
public class DebugListener {

    @PrePersist
    void prePersist(Object object) {
        System.out.println("prePersist");
    }
    @PreUpdate
    void preUpdate(Object object) {
        System.out.println("preUpdate");
    }
    @PreRemove
    void preRemove(Object object) {
        System.out.println("preRemove");
    }
}
