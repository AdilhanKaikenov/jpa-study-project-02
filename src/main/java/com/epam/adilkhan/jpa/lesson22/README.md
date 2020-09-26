**Java Persistence Query language**  

**JPQL** is **Java Persistence Query Language** defined in **JPA** specification. 
It is used to create queries against entities to store in a relational database. 
**JPQL** is developed based on **SQL** syntax.  
**JPQL** can retrieve information or data using **SELECT** clause, can do bulk updates using 
**UPDATE** clause and **DELETE** clause. `EntityManager.createQuery()` API will support 
for querying language.

**Query Structure**  
**JPQL** syntax is very similar to the syntax of **SQL**. But **SQL** works directly against relational 
database tables, records and fields, whereas **JPQL** works with Java classes and instances.

```jpaql
SELECT ... FROM ...
[WHERE ...]
[GROUP BY ... [HAVING ...]]
[ORDER BY ...]
```

```jpaql
DELETE FROM ... [WHERE ...]
 
UPDATE ... SET ... [WHERE ...]
```

**JPQL** uses the entity object model instead of database tables to define a query. 
That makes it very comfortable for us Java developers, but the database still uses **SQL**. 
Hibernate, or any other **JPA** implementation, has to transform the **JPQL** query into **SQL**.

**Selection – The FROM clause**  
```jpaql
SELECT a FROM Author a
```

**Polymorphism and Downcasting**  
When you choose an inheritance strategy that supports polymorphic queries, 
your query selects all instances of the specified class and its subclasses. 
Select all Publication entities, which are either Book or BlogPost entities.

```jpaql
SELECT p FROM Publication p
```
Or select a specific subtype of a Publication, like a BlogPost.
```jpaql
SELECT b FROM BlogPost b
```

Since JPA 2.1, you can also use the **TREAT** operator for downcasting in FROM and WHERE clauses.
```jpaql
SELECT a, p FROM Author a JOIN treat (a.publications AS Book) p
```

**Restriction – The WHERE clause**  
They can be combined with the logical operators AND, OR and NOT into more complex expressions.

Operators for single-valued expressions:  

- Equal: author.id = 10
- Not equal: author.id <> 10
- Greater than: author.id > 10
- Greater or equal then: author.id => 10
- Smaller than: author.id < 10
Smaller or equal then: author.id <= 10
- Between: author.id BETWEEN 5 and 10
- Like: author.firstName LIKE ‘%and%’
The % character represents any character sequence. This example restricts the query result to all Authors with a firstName that contains the String ‘and’, like Alexander or Sandra. You can use an _ instead of % as a single character wildcard. You can also negate the operator with NOT to exclude all Authors with a matching firstName.
- Is null: author.firstName IS NULL
You can negate the operator with NOT to restrict the query result to all Authors who’s firstName IS NOT NULL.
- In: author.firstName IN (‘John’, ‘Jane’)
Restricts the query result to all Authors with the first name John or Jane.

Operators for collection expressions:  

- Is empty: author.books IS EMPTY
Restricts the query result to all Authors that don’t have any associated Book entities. You can negate the operator (IS NOT EMPTY) to restrict the query result to all Authors with associated Book entities.
- Size: size(author.books) > 2
Restricts the query result to all Authors who are associated with more than 2 Book entities.
- Member of: :myBook member of author.books
Restricts the query result to all Authors who are associated with a specific Book entity.
```jpaql
TypedQuery<EntityA> queryMemberOf = em.createQuery(
    "SELECT a FROM EntityA a WHERE :value MEMBER OF a.listOfValues", EntityA.class);
queryMemberOf.setParameter("value", 7);
List<EntityA> resultMemberOf = queryMemberOf.getResultList();
```

**Projection – The SELECT clause**  
The projection of your query defines which information you want to retrieve from the database.

**Entities**  
```jpaql
SELECT a FROM Author a
```

**Scalar values**  
```jpaql
SELECT a.firstName, a.lastName FROM Author a
```

**Constructor references**
```jpaql
SELECT new org.model.AuthorValueObject(a.id, a.firstName, a.lastName) FROM Author a
```

**Distinct query results**  
JPQL supports Distinct operator which removes duplicates from a projection.  
```jpaql
SELECT DISTINCT a.lastName FROM Author a
```

**Functions**  
It allows you to perform basic operations in the WHERE and SELECT clause.  
- upper(String s): transforms String s to upper case
- lower(String s): transforms String s to lower case
- current_date(): returns the current date of the database
- current_time(): returns the current time of the database
- current_timestamp(): returns a timestamp of the current date and time of the database
- substring(String s, int offset, int length): returns a substring of the given String s
- trim(String s): removes leading and trailing whitespaces from the given String s
- length(String s): returns the length of the given String s
- locate(String search, String s, int offset): returns the position of the String search in s. The search starts at the position offset
- abs(Numeric n): returns the absolute value of the given number
- sqrt(Numeric n): returns the square root of the given number
- mod(Numeric dividend, Numeric divisor): returns the remainder of a division
- treat(x as Type): downcasts x to the given Type
- size(c): returns the size of a given Collection c
- index(orderedCollection): returns the index of the given value in an ordered Collection

**Grouping – The GROUP BY and HAVING clause**  
```jpaql
SELECT a.lastName, COUNT(a) FROM Author a GROUP BY a.lastName
```
The HAVING clause is similar to the WHERE clause and allows you to define additional 
restrictions for your query. The main difference is that the restrictions specified 
in a HAVING clause are applied to a group and not to a row.  
```jpaql
SELECT a.lastName, COUNT(a) AS cnt FROM Author a GROUP BY a.lastName HAVING a.lastName LIKE ‘B%’
```

**Ordering – The ORDER BY clause**  
```jpaql
SELECT a FROM Author a ORDER BY a.lastName ASC, a.firstName DESC
```

**Subselects**  
A subselect is a query embedded into another query. 
Unfortunately, JPQL supports it only in the WHERE clause and not in the SELECT or FROM clause.
```jpaql
SELECT a FROM Author a WHERE (SELECT count(b) FROM Book b WHERE a MEMBER OF b.authors ) > 1
```

SQL supports a lot of advanced features that you can’t use with JPQL. 
If you need one or more of them for a specific use case, you should use a **native SQL query**.