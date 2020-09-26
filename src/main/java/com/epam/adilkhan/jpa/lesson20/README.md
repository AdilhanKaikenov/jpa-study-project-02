**Orphan Removal vs Cascade REMOVE**

**CascadeType** defines the cascade operations that are applied in the cascade element of the relationship 
annotations.  
Example: a line item is part of an order; if the order is deleted, the line item also should be deleted. 
This is called a cascade delete relationship.

```java
@OneToMany(cascade=REMOVE) 
public Set<LineItems> getLineItems() { return lineItems; }
```
**Orphan Removal** – when a target entity in one-to-one or one-to-many relationship is removed from the relationship, 
it is often desirable to cascade the remove operation to the target entity.  
Example: if an order has many line items and one of them is removed from the order, the removed line item 
is considered an orphan. If orphanRemoval is set to true, the line item entity will be deleted when the 
line item is removed from the order.
```java
@OneToMany(orphanRemoval="true") 
public Set<LineItems> getLineItems() { return lineItems; }
```
**How does JPA orphanRemoval=true differ from the ON DELETE CASCADE?**  

**orphanRemoval** has nothing to do with `ON DELETE CASCADE`.
Orphan Removal removes corresponding child when you remove it from the relationships. 
So, if you delete 1 photo from user.getPhotos() collection, JPA automatically removes 
that photo from database too.

**orphanRemoval** is an entirely **ORM-specific thing**. It marks "child" entity to be removed when it's 
no longer referenced from the "parent" entity, e.g. when you remove the child entity from the corresponding 
collection of the parent entity.

Cascade Delete removes all children when parent is removed. So, If you delete user entity, JPA deletes all his photos too.
`ON DELETE CASCADE` is a **database-specific thing**, it deletes the "child" row in the database when 
the "parent" row is deleted.

And It does not have the same effect. `ON DELETE CASCADE` tells the DB to delete all child records when 
the parent is deleted. That is if I delete the INVOICE, then delete all of the ITEMS on that INVOICE. 
**OrphanRemoval** tells the ORM that if I remove an Item object from the collection of Items that belong to an 
Invoice object (in memory operation), and then "save" the Invoice, the removed Item should be deleted 
from the underlying DB.