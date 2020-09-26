**JPA EntityManager's methods (flush, commit, refresh, merge, contains, detach, clear)**

**Flush vs Commit**

`entityManager.flush()` - It saves the entity immediately to the database **within a transaction** to be used 
further and **it can be rolled back**.

`entityManager.getTransaction().commit()` - It marks the end of transaction and saves all the changes within the transaction 
into the database and it can't be rolled back.

**JPA EntityManager's refresh() method**

Refresh the state of the instance from the database, overwriting changes made to the entity, if any.

```java
entityManager.getTransaction().begin();
Student student = new Student("Student_name");
entityManager.persist(student);
entityManager.getTransaction().commit();
System.out.println(student.getName()); // Out: Student_name

student.setName("New_Student_name"); // update name

entityManager.getTransaction().begin();
entityManager.refresh(student); // Returns to the student entity the state the entity has in the database
entityManager.getTransaction().commit();
System.out.println(student.getName()); // Out: Student_name
```

**JPA EntityManager's contains() method**  
The contains() method checks if the entity exists in the persistence context.

```java
entityManager.getTransaction().begin();
Student student = new Student("Student_name");
entityManager.persist(student);
entityManager.getTransaction().commit();
System.out.println(entityManager.contains(student)); // Out: true

entityManager.getTransaction().begin();
entityManager.remove(student); // remove entity
entityManager.getTransaction().commit();
System.out.println(entityManager.contains(student)); // Out: false
```

**JPA EntityManager's detach() method**  
Detached entity objects are objects in a special state in which they are not managed by 
any EntityManager but still represent objects in the database.

```java
entityManager.getTransaction().begin();
Student student = new Student("Student_name");
entityManager.persist(student);
entityManager.getTransaction().commit();
System.out.println(entityManager.contains(student)); // Out: true

entityManager.detach(student); // detach student
System.out.println(entityManager.contains(student)); // Out: false
```

**JPA EntityManager's clear() method**  
Clear the persistence context, causing all managed entities to become detached. 
Changes made to entities that have not been flushed to the database will not be persisted.

```java
entityManager.getTransaction().begin();
Student student = new Student("Student_name");
entityManager.persist(student);
entityManager.getTransaction().commit();
System.out.println(entityManager.contains(student)); // Out: true

entityManager.clear(); // clear the persistence context
System.out.println(entityManager.contains(student)); // Out: false
```

**JPA EntityManager's merge() method**  
This is an operation that says that we already have this object in the database and 
we want to update all data from the object to the database.

```java
entityManager.getTransaction().begin();
Student student = new Student("Student_name");
entityManager.persist(student);
entityManager.getTransaction().commit();
System.out.println(entityManager.contains(student)); // Out: true

entityManager.clear(); // clear the persistence context
student.setName("New_Student_name");

entityManager.getTransaction().begin();
entityManager.merge(student); // merge call 
entityManager.getTransaction().commit();

System.out.println(entityManager.contains(student)); // Out: false
```