**Many-To-Many Relationship**

A relationship is a connection between two types of entities. It can only be bidirectional.
In case of a many-to-many relationship, both sides can relate to multiple instances of the other side.  
For example, when the students mark the courses they like: a student can like many courses, and many students can like the same course.

Note, that it's possible for entity types to be in a relationship with themselves. 
For example, when we model family trees: every node is a person, so if we talk about the parent-child relationship, 
both participants will be a person.

With **@ManyToMany**, we should create a third table so that we can map both entities. 
This third table will have two FK pointing to their parent tables.

**@JoinTable** annotations to indicate that the name of the table to join **table_join_A_B**, 
consisting of columns **id_A** and **id_B**, foreign keys, respectively, referring to the id of 
the column in table **EntityA** and in table **EntityB**; **(id_A, id_B)** will be the composite 
primary key for **table_join_A_B**.