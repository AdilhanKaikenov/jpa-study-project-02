**First level cache**  

The first level cache is always associated with the Session object, more specifically with the persistence context. 
When placing an object in the persistence context, when loading it from the database or saving, the object will 
also be automatically placed in the first-level cache and this cannot be disabled. Accordingly, when the same 
object is requested several times within the same persistence context, the request to the database will be 
executed once, and all other downloads will be performed from the cache.

```java
Session session = sessionFactory.openSession();
session.beginTransaction();
 
// Database will be queried
System.out.println(session.get(Person.class, 123456L));
 
// Cached object will be returned
System.out.println(session.get(Person.class, 123456L));
 
session.getTransaction().commit();
session.close();
```

The entity manager is a first-level cache used to process data for the database and to cache short-lived entities. 
This first-level cache is used on a per-transaction basis to reduce the number of SQL queries within a given transaction. 
For example, if an object is modified several times within the same transaction, the entity manager will generate only 
one UPDATE statement at the end of the transaction. A first-level cache is not a performance cache. 

**Second-level cache**  

If the first-level cache exists only at the session and persistence context level, then the second-level cache is 
located higher - at the SessionFactory level and, therefore, the same cache is available simultaneously in several 
persistence contexts. **The L2 cache requires some configuration and is therefore not enabled by default.** Setting up 
a cache is about configuring the cache implementation and allowing entities to be cached.

All JPA implementations use a performance cache (a second-level cache) to optimize database access, queries, 
joins, and so on. As seen in Figure 6-3, the second-level cache sits between the entity manager and the database to 
reduce database traffic by keeping objects loaded in memory and available to the whole application.

![second-level-cache](https://raw.githubusercontent.com/AdilhanKaikenov/jpa-study-project-02/master/src/main/java/com/epam/adilkhan/jpa/lesson24/etc/second-level-cache.jpg)

JPA 2.0 acknowledged that second-level cache was needed and has added caching operations to the standard API:
```java
public interface Cache {
 // Whether the cache contains the given entity
 public boolean contains(Class cls, Object id);
 // Removes the given entity from the cache
 public void evict(Class cls, Object id);
 // Removes entities of the specified class (and its subclasses) from the cache
 public void evict(Class cls);
// Clears the cache.
 public void evictAll();
 // Returns the provider-specific cache implementation
 public <T> T unwrap(Class<T> cls);
}
```
Like EntityManager, the javax.persistence.Cache is an interface implemented by the persistence provider caching system.

You can use this API to check if a specific entity is in the second-level cache or not, remove it from the cache, or 
clear the entire cache. Combined with this API, you can explicitly inform the provider that an entity is cacheable or 
not by using the @Cacheable annotation.

```java
@Entity
@Cacheable(true)
public class Customer {
 @Id @GeneratedValue
 private Long id;
 private String firstName;
 private String lastName;
 private String email;
 // Constructors, getters, setters
}
```

The **@Cacheable** annotation takes a Boolean value. Once you’ve decided which entity should be cacheable or 
not, you need to inform the provider which caching mechanism to use. The way to do this with JPA is to set the 
shared-cache-mode attribute in the persistence.xml file. 
The second-level cache for a persistence unit may be configured to one of several second-level cache modes:

- `ALL`: All entities and entity-related state and data are cached.
- `DISABLE_SELECTIVE`: Caching is enabled for all entities except those annotated with
@Cacheable(false).
- `ENABLE_SELECTIVE`: Caching is enabled for all entities annotated with @Cacheable(true).
- `NONE`: Caching is disabled for the persistence unit.
- `UNSPECIFIED`: Caching behavior is undefined (the provider-specific defaults may apply).

Not setting one of these values leaves it up to the provider to decide which caching mechanism to use. 
Because Customer is cacheable, it should be in the second-level cache.

The **@Cache** is an annotation that configures object caching in the Hibernate second-level cache. 
The **@Cacheable** annotation is sufficient for the object to be cached with its default settings. 
At the same time, **@Cache** used without **@Cacheable** will not allow object caching. 

@Cache takes three parameters: include, region, usage.  
**`usage` - specifies the concurrent access strategy for objects.**

The problem is that **the second-level cache is accessible from multiple sessions at once and multiple 
streams of program can simultaneously work in different transactions with the same entity**. Therefore, 
it is necessary to somehow provide them with the same representation of this object.

- `translactional` - complete transaction separation. Every session and every transaction sees objects as 
if only they were working with it sequentially, one transaction after another. The pay for this is 
blockages and lost productivity.
- `read-write` - full access to one specific record and sharing its state between transactions. 
However, the summarized state of several objects in different transactions may differ.
- `nonstrict-read-write` - Similar to read-write, but changes to objects can be delayed and transactions 
can see older versions of objects. It is recommended to use it in cases where simultaneous updating 
of objects is unlikely and cannot lead to problems.
- `read-only` - objects are cached read-only and change removes them from the cache.

The list above is sorted by performance gain, transactional strategy is slowest, read-only strategy is fastest. 
The disadvantage of the read-only strategy is its uselessness if objects are constantly changing, since 
in this case they will not linger in the cache.

```java
Customer customer = new Customer("Patricia", "Jane", "plecomte@mail.com");
tx.begin();
em.persist(customer);
tx.commit();

// Uses the EntityManagerFactory to get the Cache
Cache cache = emf.getCache();
// Customer should be in the cache
assertTrue(cache.contains(Customer.class, customer.getId()));
// Removes the Customer entity from the cache
cache.evict(Customer.class);
// Customer should not be in the cache anymore
assertFalse(cache.contains(Customer.class, customer.getId()));
```