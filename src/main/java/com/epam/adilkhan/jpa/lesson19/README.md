**Persistence context**  

A persistence context is a set of managed entity instances at a given time for a given user’s transaction: 
only one entity instance with the same persistent identity can exist in a persistence context. 

For example, if a Book instance with an ID of 12 exists in the persistence context, no other book with this ID can exist within that same persistence context. Only
entities that are contained in the persistence context are managed by the entity manager, meaning that changes will
be reflected in the database.  
The entity manager updates or refers to the persistence context whenever the **javax.persistence.EntityManager** 
interface method is called.  For example, when a **persist()** method is called, the entity passed as an argument 
will be added to the persistence context if it doesn’t already exist. Similarly, when an entity is found by its 
primary key, the entity manager first checks whether the requested entity is already present in the persistence 
context. The persistence context can be seen as a **first-level cache**. It’s a short, live space where the entity 
manager stores entities before flushing the content to the database. By default, objects just live in the 
persistent context for the duration of the transaction.

To summarize, let’s look at Figure 6-1 where two users need to access entities whose data are stored in the
database. Each user has his own persistence context that lasts for the duration of his own transaction. User 1 gets the
Book entities with IDs equal to 12 and 56 from the database, so both get stored in his persistence context. User 2 gets
the entities 12 and 34. As you can see, the entity with ID = 12 is stored in each user’s persistence context. While the
transaction runs, the persistence context acts like a first-level cache storing the entities that can be managed by the
EntityManager. Once the transaction ends, the persistence context ends and the entities are cleared.

![persistence-context](https://raw.githubusercontent.com/AdilhanKaikenov/jpa-study-project-02/tree/master/src/main/java/com/epam/adilkhan/jpa/lesson19/etc/persistence-context.jpg)

The configuration for an entity manager is bound to the factory that created it. Whether application or container
managed, the factory needs a persistence unit from which to create an entity manager. A persistence unit dictates
the settings to connect to the database and the list of entities that can be managed in a persistence context. The
**persistence.xml** file located in the **META-INF** directory defines the persistence unit. The persistence
unit has a name and a set of attributes.


