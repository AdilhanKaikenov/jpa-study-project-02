**Named Queries**  

Named query is the way you define a query by giving it a name.  
Named queries are compiled when SessionFactory is instantiated (so, essentially, 
when your application starts up). The obvious advantage, therefore, is that all 
your named queries are validated at that time rather than failing upon execution.
**(validated when the session factory is created, thus making the application to fail fast in case of an error)**

- The Named Query allows you to collect multiple queries in one place and then invoke them in any class.
- The Named Query syntax is checked when the session factory is created, which allows you to notice the error early, rather than when the application is running and the query is executed.
- The Named Query are compiled to the native syntax once by persistence provider

Named queries are one of the core concepts in JPA. They enable you to declare a query in 
your persistence layer and reference it in your business code. That makes it easy to 
reuse an existing query. It also enables you to separate the definition of your query 
from your business code.

You can define a named query using a **@NamedQuery** annotation on an entity class or 
using a **<named-query />** element in your XML mapping. 
It can be provided a JPQL query or a native SQL query.

Named queries provide a **significant performance benefit**, when the application requires 
repeated execution of the same query. The key concept to this is that every query, no 
matter what syntax you use to write them in your business tier (hibernate syntax for 
example), will end up (must end up) as a native query (i.e. a syntax that your database 
understands) in order to be executed. This transformation is called **compilation** and is 
considered a time consuming process. So the performance benefit comes from the fact that 
**Named Queries are compiled to their native syntax once** by hibernate and execute ever after, 
whereas dynamically created queries must be compiled every time to their native syntax, 
before their execution.

**Named Native Queries**  
The JPA specification defines its own query language. It’s called JPQL, and its syntax 
is similar to SQL. But there are 2 essential differences between these 2:

- You define your JPQL query based on your entity model. When you execute it, your 
persistence provider generates a SQL query based on your entity mappings and the 
provided JPQL statement. That enables you to define database-independent queries 
but also limits you to the features supported by your persistence provider.
- JPQL supports only a small subset of the SQL standard and almost no database-specific 
features.

The definition of a named JPQL query is pretty simple. You just have to annotate one 
of your entity classes with **@NamedQuery** and provide 2 Strings for the name and 
query attributes.  
The name of your query has to be unique within your persistence context.  
The value of the query attribute has to be a String that contains a valid JPQL statement. 

**JPA has its own query language but it also supports native SQL.** 
You can create these queries in a very similar way as JPQL queries and they can 
even return managed entities if you want. 
```java
Query q = em.createNativeQuery("SELECT a.id, a.version, a.firstname, a.lastname FROM Author a", Author.class);
List<Author> authors = q.getResultList();
 
for (Author a : authors) {
    System.out.println("Author "
            + a.getFirstName()
            + " "
            + a.getLastName());
}
```

**Stored procedures and functions**  
**@NamedStoredProcedureQuery** which can be used to define the stored procedure call. 
