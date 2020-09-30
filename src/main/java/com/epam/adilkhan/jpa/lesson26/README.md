**Entity Life Cycle**  

When an entity is instantiated (with the new operator), it is just seen as a regular POJO by 
the JVM (i.e., detached) and can be used as a regular object by the application. Then, 
when the entity is persisted by the entity manager, it is said to be managed. When an entity 
is managed, the entity manager will automatically synchronize the value of its attributes 
with the underlying database (e.g., if you change the value of an attribute by using a set 
method while the entity is managed, this new value will be automatically synchronized with the database).

![entity-life-cycle-01](https://raw.githubusercontent.com/AdilhanKaikenov/jpa-study-project-02/master/src/main/java/com/epam/adilkhan/jpa/lesson26/etc/entity-life-cycle-01.jpg)

To create an instance of the Customer entity, you use the new operator. This object exists in memory, although JPA 
knows nothing about it. If you do nothing with this object, it will go out of scope and will end up being garbage collected, 
and that will be the end of its life cycle. What you can do next is persist an instance of Customer with the 
EntityManager.persist() method. At that moment, the entity becomes managed, and its state is synchronized with the 
database. During this managed state, you can update attributes using the setter methods (e.g., customer.setFirstName()) 
or refresh the content with anEntityManager.refresh() method. All these changes will be synchronized between the 
entity and the database. During this state, if you call the EntityManager.contains(customer) method, it will return true 
because customer is contained in the persistence context (i.e., managed).  
Another way for an entity to be managed is when it is loaded from the database. When you use the 
EntityManager.find() method, or create a JPQL query to retrieve a list of entities, all are automatically managed, 
and you can start updating or removing their attributes.  
In the managed state, you can call the EntityManager.remove() method, and the entity is deleted from the 
database and not managed anymore. But the Java object continues living in memory, and you can still use it until the 
garbage collector gets rid of it.
EntityManager.clear() or EntityManager.detach(customer) methods will clear the entity from the persistence 
context; it becomes detached. But there is also another, more subtle, way to detach an entity: when it’s serialized. 
If they need to cross a network to be invoked remotely or cross layers to be displayed in a presentation tier, they 
need to implement the java.io.Serializable interface. This is not a JPA restriction but a Java restriction. 
When a managed entity is serialized, crosses the network, and gets deserialized, it is seen as a detached object. 
To reattach an entity, you need to call the EntityManager.merge() method.

**Callback** methods and **listeners** allow you to add your own business logic when certain life-cycle events occur on
an entity, or broadly whenever a life-cycle event occurs on any entity.

**Callbacks**  

The life cycle of an entity falls into four categories: persisting, updating, removing, and loading, which correspond to 
the database operations of inserting, updating, deleting, and selecting, respectively. Each life cycle has a “pre” and 
“post” event that can be intercepted by the entity manager to invoke a business method.  

- `@PrePersist` - Marks a method to be invoked before EntityManager.persist() is executed.
- `@PostPersist` - Marks a method to be invoked after the entity has been persisted. If the entity autogenerates its 
primary key (with @GeneratedValue), the value is available in the method.
- `@PreUpdate` - Marks a method to be invoked before a database update operation is performed (calling the
entity setters or the EntityManager.merge() method).
- `@PostUpdate` - Marks a method to be invoked after a database update operation is performed.
- `@PreRemove` - Marks a method to be invoked before EntityManager.remove() is executed.
- `@PostRemove` - Marks a method to be invoked after the entity has been removed.
- `@PostLoad` - Marks a method to be invoked after an entity is loaded (with a JPQL query or an
EntityManager.find()) or refreshed from the underlying database.  
There is no @PreLoad annotation, as it doesn’t make sense to preload data on an entity that is not built yet.

![entity-life-cycle-02](https://raw.githubusercontent.com/AdilhanKaikenov/jpa-study-project-02/master/src/main/java/com/epam/adilkhan/jpa/lesson26/etc/entity-life-cycle-02.jpg)


**Listeners**  

Callback methods in an entity work well when you have business logic that is only related to that entity. Entity 
listeners are used to extract the business logic to a separate class and share it between other entities.  
An entity listener is just a POJO on which you can define one or more life-cycle callback methods. 
To register a listener, the entity needs to use the **@EntityListeners** annotation. 
The class must have a public no-arg constructor. This can be useful in cases where the listener provides more 
general logic that many entities can benefit from.

```java 
@EntityListeners({DataValidationListener.class, AgeCalculationListener.class})
@Entity
public class Customer {...}
```

A Debug Listener Usable by Any Entity  
```java 
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
```

Note that each method takes an Object as a parameter, meaning that any type of entity could use this listener by
adding the DebugListener class to its @EntityListeners annotation. To have every single entity of your application
use this listener, you would have to go through each one and add it manually to the annotation. For this case, JPA has
a notion of **default listeners that can cover all entities in a persistence unit**. As there is no annotation 
targeted for the entire scope of the persistence unit, **the default listeners can only be declared in an XML mapping file.**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<entity-mappings xmlns="http://xmlns.jcp.org/xml/ns/persistence/orm"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
                 xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence/orm
                 http://xmlns.jcp.org/xml/ns/persistence/orm_2_1.xsd"
                 version="2.1">

     <persistence-unit-metadata>
         <persistence-unit-defaults>
             <entity-listeners>
                <entity-listener class="org.agoncal.book.javaee7.chapter06.DebugListener"/>
             </entity-listeners>
         </persistence-unit-defaults>
    </persistence-unit-metadata>

</entity-mappings>
```

In this file, the **<persistence-unit-metadata>** tag defines all the metadata that don’t have any annotation
equivalent. The **<persistence-unit-defaults>** tag defines all the defaults of the persistence unit, and the
**<entity-listener>** tag defines the default listener. This file needs to be referred in the persistence.xml and
deployed with the application. The DebugListener will then be automatically invoked for every single entity.

Default entity listeners always get invoked before any of the entity listeners listed in the 
**@EntityListeners** annotation. **If an entity doesn’t want to have the default entity listeners applied to it**, 
it can use the **@ExcludeDefaultListeners** annotation.

```java
@ExcludeDefaultListeners
@Entity
public class Customer {...}
```

When an event is raised, the listeners are executed in the following order:
1. @EntityListeners for a given entity or superclass in the array order,
2. Entity listeners for the superclasses (highest first),
3. Entity listeners for the entity,
4. Callbacks of the superclasses (highest first), and
5. Callbacks of the entity.