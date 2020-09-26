JPA and Hibernate support 4 inheritance strategies which map the domain objects to different table structures.

**Mapped Superclass**  

The mapped superclass strategy is the simplest approach to mapping an inheritance structure to database tables. 
It maps each concrete class to its own table.

That allows you to share the attribute definition between multiple entities. But it also has a huge drawback. 
**A mapped superclass is not an entity, and there is no table for it.**

If you just want to share state and mapping information between your entities, the mapped superclass strategy 
is a good fit and easy to implement. You just have to set up your inheritance structure, annotate the mapping 
information for all attributes and add the **@MappedSuperclass** annotation to your superclass. Without the 
**@MappedSuperclass** annotation, Jpa provider (Hibernate) will ignore the mapping information of your superclass.

**Pros**  
- Allows inheritance of non-entity classes

**Cons**  
- No base entity to form queries across hierarchy (unlike TABLE_PER_CLASS)
- Tables not normalized (like TABLE_PER_CLASS)
- Parent columns repeated in each subclass table

**Table per Class**  
The table per class strategy is similar to the mapped superclass strategy. The main difference is that 
the superclass is now also an entity. Each of the concrete classes gets still mapped to its own database table. 
This mapping allows you to use polymorphic queries and to define relationships to the superclass. 
**But the table structure adds a lot of complexity to polymorphic queries, and you should, therefore, avoid them.**

The table per class strategy maps each entity to its own table which contains a column for each entity attribute.

**Pros**
- The strategy offers good performance when querying single entities – we have all the data in one table, 
we don’t need any joins. No joins when accessing a single concrete type.
- Having individual table per entity allows us to define specific constraints at the database level 
to control data integrity.

**Cons**
- The main drawback of the strategy is the fact we can’t use polimorphic queries efficiently. 
When querying parent entities, we have to use UNION statements to gather data from all tables.
- Another aspect is that data in tables are not normalized – the same information can be stored 
in different tables. Controlling the same logic of data integrity together in many tables can be error prone.
- Especially defining relationships of the entities can be very confusing – imagine our application has the following entity:
```java
@Entity
public class Customer extends Product {
    @Id
    @GeneratedValue
    private Long id;
    private Product product;
} 
```
Furthermore, although the Table Per Class strategy is part of the JPA , the support for this startegy 
is optional – providers aren’t forced to implement this solution. As a consequence, the solution based 
on this strategy isn’t portable.

**Summary**  

As we can see, the Table Per Class strategy have more disadvantages than advantages. 
It makes it difficult for us to control data integrity at the database level, performance 
of polymorphic queries can be really low and our solution isn’t portable across different 
JPA providers. so, when we develop application that is changing dynamically, the Table Per 
Class should’t be chosen.

But there can be some cases, when this strategy is the best choice – when the Java model isn’t 
complicated (for example subclasses doesn’t share common fields and have different characteristics) 
and we are sure the will be no modifications of the entities hierarchy, we can choose this strategy 
as the most efficient.

**Single Table**  
The single table strategy maps all entities of the inheritance structure to the same database table. 
This approach makes polymorphic queries very efficient and provides the best performance.

But it also has some drawbacks. The attributes of all entities are mapped to the same database table. 
Each record uses only a subset of the available columns and sets the rest of them to null. You can, 
therefore, not use not null constraints on any column that isn’t mapped to all entities. That can 
create data integrity issues, and your database administrator might not be too happy about it.

The **@DiscriminatorColumn** is commonly used in SINGLE_TABLE inheritance because you need a column 
to identify the type of the record.  
The **@DiscriminatorValue** annotation is optional. It defines which discriminator value shall be 
mapped to this class. 

The previously discussed inheritance strategies had their issues with polymorphic queries. 
They were either not supported or required complex union and join operations. 
That’s not the case if you use the single table strategy. All entities of the inheritance hierarchy are 
mapped to the same table and can be selected with a simple query. This query is much easier than the one 
created by the table per class strategy, and you don’t need to worry about performance problems.

**Pros**  
- Persisting all entities in one table gives us a good performance, when using polymorphic queries 
(for both writes and reads). Executing queries that operate on different levels of entities hierarchy, 
the query runs only on one table, without any joins. Of course, there is also better to control the table, 
instead if we had many tables – all defined constraints are kept in one place.
- Besides, using Single Table strategy, we can use only one Primary Key generation strategy – common for 
all subclasses – definition of the primary key should be defined only at the root entity level.

**Cons**  
- Due to the fact we one table to store all entities, we have to allow to store null values for all fields of 
subclasses – we can only have not null constraints for fields in the root entity. By using Single Table strategy 
most of the constraints according data (all checks like ) have to be moved from the database to the application 
level.
- When we have to persist very complicated hierarchy in one table (with most fields in subclasses), the amount 
of columns with null values can significantly rise. The readability of such rows can be weak.

**Summary**  
- Single Table strategy provides the best performance for writes and reads, because queries run only on one table. 
If we have to disable most of data integrity checks at the database level and move them to our application, the 
strategy can be very efficient solution.

**Joined Table**  
The joined table approach maps each class of the inheritance hierarchy to its own database table. 
This sounds similar to the table per class strategy. But this time, also the abstract superclass 
gets mapped to a database table. This table contains columns for all shared entity attributes. 
The tables of the subclasses are much smaller than in the table per class strategy. They hold only 
the columns specific for the mapped entity class and a primary key with the same value as the record 
in the table of the superclass.

Each query of a subclass requires a join of the 2 tables to select the columns of all entity attributes. 
That increases the complexity of each query, but it also allows you to use not null constraints on subclass 
attributes and to ensure data integrity.

**Pros**  
- From the point of view of the database, the Joined inheritance strategy is is the most consistent of all 
three strategies – there are no redundancy in data persistence. We can define not null checks and other 
constraints on all the columns to control data integrity at the database level.
- The fact that we don’t have nullable columns while persisting entity (like in Single Table strategy ) 
has the positive impact on the amount of memory used by the database – the database use the minimum amount 
of memory needed to store entity.

**Cons**  
- Due to the fact, that entity is stored in few tables, we have to use joins to read the whole object. 
In our example, while querying the Car entity, the query would look like:
```java
SELECT p.ID, p.NAME, c.BRAND, c.MODEL 
FROM PRODUCT p 
    LEFT JOIN CAR c 
        ON p.ID = c.ID
WHERE p.TYPE = 'Car';
```
The amount of joins in query grows proportionally to the amount of classes in the hierarchy of the entity. 
This can have a negative impact on the performance of reading entities.
- Requires access to multiple tables when using (insert, select, update, and delete) an entity

**Summary**  

The Joined strategy should be chosen, when data integrity is more important than performance, when there is 
a strong need to control data at the database level.

But when you expect to have many polymorphic queries, this strategy isn’t a good choice.

**Choosing a Strategy**  
Choosing the right inheritance strategy is not an easy task. As so often, you have to decide which advantages you need and which drawback you can accept for your application. Here are a few recommendations:

* If you require the best performance and need to use polymorphic queries and relationships, you should choose the single table strategy. But be aware, that you can’t use not null constraints on subclass attributes which increase the risk of data inconsistencies.
* If data consistency is more important than performance and you need polymorphic queries and relationships, the joined strategy is probably your best option.
* If you don’t need polymorphic queries or relationships, the table per class strategy is most likely the best fit. It allows you to use constraints to ensure data consistency and provides an option of polymorphic queries. But keep in mind, that polymorphic queries are very complex for this table structure and that you should avoid them.