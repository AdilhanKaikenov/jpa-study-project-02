JPA: **@Basic** vs **@Column**

**@Basic** annotation

It is an optional annotation and It has two attributes, optional and fetch. 

The **optional** attribute is a boolean parameter that defines whether the marked field or property 
allows null. It defaults to true. So, if the field is not a primitive type, the underlying column 
is assumed to be nullable by default.

The **fetch** attribute accepts a member of the enumeration **Fetch**, which specifies whether 
the marked field or property should be lazily loaded or eagerly fetched. It defaults 
to FetchType.**EAGER**, but we can permit lazy loading by setting it to FetchType.**LAZY**.

FetchType.**EAGER** means that the field will be loaded immediately when accessing the database, 
while FetchType.**LAZY** does not load the value for the field immediately, as soon as a person 
requests this field, only then the value will be loaded into memory. This can be useful when 
we have huge fields (for example, a large byte array of image data or a collection), because 
we do not always use this field, and sometimes we may not use it at all. In general, when we 
have large amounts of data and we rarely use them, then we can use **LAZY**.

**@Column** annotation

It's used mainly in the DDL schema metadata generation. This means that if we let any JPA Provider 
generate the database schema automatically, it applies all the constraints to the particular 
database column.

- columnDefinition - The SQL fragment that is used when generating the DDL for the column.
- insertable - Whether the column is included in SQL INSERT statements generated by the persistence provider.
- updatable - Whether the column is included in SQL UPDATE statements generated by the persistence provider.
- length - The column length.
- name - The name of the column.
- nullable - Whether the database column is nullable.
- table - The name of the table that contains the column.
- unique - Whether the column is a unique key.
- precision - The precision for a decimal (exact numeric) column.
- scale - The scale for a decimal (exact numeric) column.

`insertable` and `updatable` work at the persistence provider level.

**@Basic vs @Column**  
Let's look at the differences between @Basic and @Column annotations:

* Attributes of the **@Basic** annotation are applied to JPA entities, whereas the attributes of 
**@Column** are applied to the database columns 
* **@Basic** annotation's optional attribute 
defines whether the entity field can be null or not; on the other hand, **@Column** annotation's 
nullable attribute specifies whether the corresponding database column can be null
* We can use **@Basic** to indicate that a field should be lazily loaded
* The **@Column** annotation allows us to specify the name of the mapped database column