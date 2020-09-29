**Understanding Optimistic Locking**  

In order to use optimistic locking, **we need to have an entity including a property with @Version annotation**. 
While using it, each transaction that reads data holds the value of the version property.

Before the transaction wants to make an update, it checks the version property again.

If the value has changed in the meantime an **OptimisticLockException** is thrown. Otherwise, the transaction 
commits the update and increments a value version property.

Version attributes are properties with **@Version** annotation. 
**We should know that we can retrieve a value of the version attribute via entity, but we mustn't update or increment it.**
They are necessary for enabling optimistic locking.
```java
@Entity
public class Student {
 
    @Id
    private Long id;
 
    private String name;
 
    private String lastName;
 
    @Version
    private Integer version;
 
    // getters and setters
 
}
```

JPA provides us with two different optimistic lock modes (and two aliases):

- `OPTIMISTIC` – it obtains an optimistic read lock for all entities containing a version attribute
- `OPTIMISTIC_FORCE_INCREMENT` – it obtains an optimistic lock the same as OPTIMISTIC and additionally increments the version attribute value
- `READ` – it's a synonym for OPTIMISTIC
- `WRITE` – it's a synonym for OPTIMISTIC_FORCE_INCREMENT

When the persistence provider discovers optimistic locking conflicts on entities, it throws OptimisticLockException. 

**Understanding Pessimistic Locking**  

We can use a pessimistic lock to ensure that no other transactions can modify or delete reserved data.
Setting the proper isolation level is not enough to cope with concurrent transactions, 
JPA gives us pessimistic locking. It enables us to isolate and orchestrate different transactions 
so they don't access the same resource at the same time. 
Pessimistic locking uses database mechanisms for exclusive access to the data.

JPA specification defines three pessimistic lock modes which we're going to discuss:

- `PESSIMISTIC_READ` – allows us to obtain a shared lock and prevent the data from being updated or deleted
- `PESSIMISTIC_WRITE` – allows us to obtain an exclusive lock and prevent the data from being read, updated or deleted
- `PESSIMISTIC_FORCE_INCREMENT` – works like PESSIMISTIC_WRITE and it additionally increments a version attribute of a versioned entity

