**JPA - Using @OrderBy Annotation**

The annotation **@OrderBy** Specifies the ordering of the elements of a collection valued 
association or element collection at the point when the association or collection 
is retrieved.

This annotation can be used with **@ElementCollection** or **@OneToMany/@ManyToMany** relationships.

**When @OrderBy used with @ElementCollection**

If the collection is of basic type, then ordering will be by the value of the basic 
objects. For example following will arrange phoneNumbers in their natural ordering:
```java
@ElementCollection
  @OrderBy
  private List<String> phoneNumbers;
```

If the collection is of **@Embeddable** type, the dot (".") notation is used to refer to 
an attribute within the embedded attribute. For example following will arrange 
addresses by country names.
```java
@ElementCollection
  @OrderBy("city.country DESC")
  private List<Address> addresses;
```

ASC | DESC can be used to specify whether ordering is ascending or descending. Default is ASC.

**When @OrderBy used with a relationship**

**@OrderBy** only works with direct properties if used with a relationship 
(**@OneToMany or @ManyToMany**). For example:
```java
 @ManyToMany
  @OrderBy("supervisor")
  private List<Task> tasks;
```

Where Task entity is:
```java
 @Entity
 public class Task {
    ....
    @OneToOne
    private Employee supervisor;
    ...
}
```

Dot (".") access doesn't work in case of relationships. Attempting to use a nested 
property e.g. **@OrderBy("supervisor.name")** will end up in a runtime exception.

If the ordering element is not specified for an entity association 
(i.e. the annotation is used without any value), ordering by the primary key of 
the associated entity is assumed. For example:
```java
@ManyToMany
  @OrderBy
  private List<Task> tasks;
```

In above case tasks collection will be ordered by **Task#id**.

**@OrderColumn annotation**  
@OrderColumn annotation specifies a column that should maintain the persistent order of a list.
The persistence provider maintains the order and also updates the ordering on insertion, 
deletion, or reordering of the list.
This annotation can be used on @OneToMany or @ManyToMany relationship or on @ElementCollection.
The OrderColumn annotation is specified on the side of the relationship that references 
the collection that is to be ordered. The order column is not visible as part of the 
state of the entity or embeddable class.
```java
@ElementCollection
  @OrderColumn
  private List<String> phoneNumbers;
```


**@OrderBy vs @OrderColumn**  
The order specified by @OrderBy is only applied during runtime when a query result 
is retrieved. Whereas, the usage of @OrderColumn (last tutorials) results in a 
permanent ordering of the related data. In this case a dedicated database column 
is used to maintain the ordering.




