Options to generate primary keys:  
1. GenerationType.AUTO
2. GenerationType.IDENTITY
3. GenerationType.SEQUENCE
4. GenerationType.TABLE

These generators are used to generate primary key while inserting rows in the database.

**GenerationType.AUTO**

The `GenerationType.AUTO` is the default generation type and lets 
the persistence provider choose an appropriate generation strategy 
for the particular database. 

For example, If Hibernate is used as a persistence provider, 
it selects a generation strategy based on the database-specific dialect. 
For most popular databases, it selects `GenerationType.SEQUENCE`.

**GenerationType.IDENTITY**

The GenerationType.IDENTITY is the easiest to use but not the best one from 
a performance point of view. It relies on an auto-incremented database column 
and lets the database generate a new value with each insert operation. 

For example, when using Mysql, you can specify auto_increment in a table definition 
to make it self-incrementing, and then use 
@GeneratedValue(strategy = GenerationType.IDENTITY) in Java code to indicate 
that you are also using this strategy on the database server side.

GenerationType.IDENTITY, works with databases that have special IDENTITY fields, 
such as MySQL or DB2. In this case, the table would have to be created as:

```sql
CREATE TABLE example_table_identity (
  ID BIGINT PRIMARY KEY AUTO_INCREMENT
);
```

This approach has a significant drawback if you use Hibernate. 
Hibernate requires a primary key value for each managed entity 
and therefore has to perform the insert statement immediately. 
This prevents it from using different optimization technologies 
like JDBC batching.

**GenerationType.SEQUENCE**

GenerationType.SEQUENCE, uses the built-in sequence mechanism in databases such as 
PostgreSQL or Oracle. Using this generator requires both creating a separate 
sequence in the database:

```sql
CREATE TABLE example_table_sequence (
  ID BIGINT PRIMARY KEY
);
 
CREATE SEQUENCE JPA_SEQUENCE START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
```

Then set the name of this sequence in the key description:

```java
@Id
@SequenceGenerator( name = "jpaSequence", sequenceName = "JPA_SEQUENCE", 
allocationSize = 1, initialValue = 1 )
@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
@Getter
@Setter
@Column(name = "id", nullable = false, updatable = false)
private Long rowId;
```

In sequence, we first ask database for the next set 
of the sequence then we insert row with return sequence id. 

It requires additional select statement to get the next value from a database 
sequence. But this has no performance impact for most applications. 
And if an application has to persist a huge number of new entities, 
Hibernate specific optimizations to reduce the number of statements can be used. 

If any additional information is't provided, Hibernate will request the next value 
from its default sequence. It can be changed by referencing the name of a 
@SequenceGenerator the generator attribute of the @GeneratedValue annotation. 
The @SequenceGenerator annotation lets you define the name of the generator, 
the name, and schema of the database sequence and the allocation size of the sequence.

**GenerationType.TABLE**

GenerationType.TABLE is independent of specific database support and stores 
the value counters in a separate table. On the one hand, it is a more flexible 
and customizable solution, on the other hand, it is slower and requires more 
customization. First, you need to create (manually!) And initialize (!) A table 
for key values:

```sql
CREATE TABLE example_table_table
(
    ID BIGINT PRIMARY KEY
);
 
CREATE TABLE SEQ_STORE
(
 SEQ_NAME VARCHAR(255) PRIMARY KEY,
 SEQ_VALUE BIGINT NOT NULL
);
 
INSERT INTO SEQ_STORE VALUES ('example_table_table.ID.PK', 0);
```

Then create a generator and associate it with an id:

```java
@Id
@TableGenerator( name = "seqStore", table = "SEQ_STORE", pkColumnName = "SEQ_NAME", 
pkColumnValue = "example_table_table.ID.PK", valueColumnName = "SEQ_VALUE", 
initialValue = 1, allocationSize = 1 )
@GeneratedValue( strategy = GenerationType.TABLE, generator = "seqStore" )
@Getter
@Setter
@Column(name = "id", nullable = false, updatable = false)
private Long rowId
```

The GenerationType.TABLE gets only rarely used nowadays. The disadvantage of 
this method is that it does not scale well and can negatively impact performance. 
It simulates a sequence by storing and updating its current value in 
a database table which requires the use of pessimistic locks which put 
all transactions into a sequential order. This slows down an application, 
and it is more preferable to use GenerationType.SEQUENCE, if a database 
supports sequences, which most databases do. 

While IDENTITY maps to an auto-incremented column (e.g. IDENTITY in SQL Server 
or AUTO_INCREMENT in MySQL) and SEQUENCE is used for delegating the identifier 
generation to a database sequence, the TABLE generator has no direct implementation 
in relational databases.

