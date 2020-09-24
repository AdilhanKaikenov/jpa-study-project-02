**One-To-One Relationship**

One to one relationship refers to the relationship between two entities/tables 
A and B in which one item/row of A may be linked with only one item/row of B, 
and vice versa.

**@OneToOne** defines a one-to-one relationship between 2 entities.  
**@JoinColumn** defines foreign key column and indicates the owner of the relationship. 
@JoinColumn is only needed on the owning side of the foreign key relationship. Simply put, whoever 
owns the foreign key column gets the @JoinColumn annotation.

The relationship between tables can be unidirectional and bidirectional.  
In a `unidirectional` relationship, only one entity has a relationship field or property that refers to the other.   
In a `bidirectional` relationship, each entity has a relationship field or property that refers to the other entity.

**JPA Cascade Types**  
The cascade types supported by the Java Persistence Architecture are as below:  

- CascadeType.PERSIST : cascade type presist means that save() or persist() operations cascade to related entities.  
- CascadeType.MERGE : cascade type merge means that related entities are merged when the owning entity is merged.  
- CascadeType.REFRESH : cascade type refresh does the same thing for the refresh() operation.  
- CascadeType.REMOVE : cascade type remove removes all related entities association with this setting when the owning entity is deleted.  
- CascadeType.DETACH : cascade type detach detaches all related entities if a “manual detach” occurs.  
- CascadeType.ALL : cascade type all is shorthand for all of the above cascade operations.  