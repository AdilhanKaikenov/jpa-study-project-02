**One-To-Mane and Many-To-One Relationship**

One-to-many mapping means that one row in a table is mapped to multiple rows in another table.

The owning side of these relationships is usually in the **@ManyToOne** and 
the **mappedBy** in the parent entity.

Add the **mappedBy** property to the **@OneToMany** property in the Parent class. 
This makes Child the side of ownership. The mappedBy required unless the relationship 
is not unidirectional.

In Child, add the **@JoinColumn** annotation to the parent field to declare the foreign 
key column name in the child table.

**Don’t use unidirectional one-to-many associations**
Bidirectional one-to-many and both many-to-one association mappings are fine. But you should 
avoid unidirectional one-to-many associations in your domain model. Otherwise, Hibernate 
might create unexpected tables and execute more SQL statements than you expected.