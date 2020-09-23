Since JPA 2.0, you can use an element collection to persist a Collection 
of value types. You just need to annotate the attribute with **@ElementCollection** 
and the persistence provider will persist the elements of the Collection in 
an additional database table. The **ElementCollection** allows you to specify 
mappings for collections: collections of **embeddable** objects, or collections 
of "**simple**" types (Integer, String, etc.). Also, this component is used in 
the definitions of relations with **Map**, the role of which is any kind of object, 
and the role of the value - embeddable or "simple" objects.

ElementCollection values are always stored in separate tables, which are specified 
by the **@CollectionTable** annotation. CollectionTable defines the table name and 
**@JoinColumn** or **@JoinColumns** in the case of a composite primary key.

```java
@Entity
public class Author {
 
    @ElementCollection
    private List<String> phoneNumbers = new ArrayList<String>();
 
    ...
}
```
The element collection might seem easier to use than an entity with a one-to-many 
association. But it has one major drawback: **The elements of the collection have 
no id and Hibernate can’t address them individually.**

When you add a new Object to the List or remove an existing one, for example, 
Hibernate deletes all elements and inserts a new record for each item in the List.

**Collections of embedded objects**

ElementCollection is used to define a collection of embedded objects. 
Declaring an embedded display is similar to **OneToMany**, except that the 
target table is an **Embeddable** and not an **entity**.

The difference between **ElementCollection** and **OneToMany** is that the target objects 
cannot be selected, saved, or merged directly, independently of the parent object. 
There is no cascading policy, the state of collection objects is synchronous with 
the state of the parent object. Well, in general, everything is the same as with 
embedded objects. However, ElementCollection can have a selection type, and by 
default it is **lazy**.

```java
import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
  @Id
  @Column(name="CUSTOMER_ID")
  private Long id;
 
  private String name;
 
  @ElementCollection
  @CollectionTable(
    name="CUST_ADDRESS",
    joinColumns=@JoinColumn(name="OWNER_ID")
  )
  private List<Address> phones;
 
  // getters and setters
}
```

```java
import javax.persistence.Column;
import javax.persistence.Embeddable;
 
@Embeddable
public class Address {
 
  private String city;
 
  private String street;
 
  @Column(name="ZIP_CODE")
  private String zip;
 
  // getters and setters
}
```

**Let’s take a quick look at an example.** 

```java
EntityManager em = emf.createEntityManager();
em.getTransaction().begin();
 
Author a = em.find(Author.class, 1L);
a.getPhoneNumbers().add("42424242");
 
em.getTransaction().commit();
em.close();
```
Hibernate performs 2 SELECT statements to read the Author entity and the 
associated phoneNumbers. Then I add the second phoneNumber to the element 
collection. And Hibernate updates the Author entity, drops all associated 
phoneNumbers and inserts a new record for each phoneNumber.

It is better to use it only for very small collections so that persistence provider 
does not perform too many SQL statements. In all other cases, a one-to-many 
association is the better approach.