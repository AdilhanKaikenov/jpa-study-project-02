In JPA, we have two options to define the composite keys: The **@IdClass** and **@EmbeddedId** annotations.

In order to define the composite primary keys, we should follow some rules:
- The composite primary key class must be public
- It must have a no-arg constructor
- It must define equals() and hashCode() methods
- It must be Serializable

The **IdClass** Annotation

Let's say we have a table called Account and it has two columns – accountNumber, accountType – 
that form the composite key. Now we have to map it in JPA.

```java 
public class AccountId implements Serializable {
    private String accountNumber;
 
    private String accountType;
 
    // default constructor
 
    public AccountId(String accountNumber, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }
 
    // equals() and hashCode()
}
```

Next, let's associate the AccountId class with the entity Account.

In order to do that, we need to annotate the entity with the **@IdClass** annotation. 
We must also declare the fields from the AccountId class in the entity 
Account and annotate them with **@Id**:

```java 
@Entity
@IdClass(AccountId.class)
public class Account {
    @Id
    private String accountNumber;
 
    @Id
    private String accountType;
 
    // other fields, getters and setters
}
```

The **EmbeddedId** Annotation

**@EmbeddedId** is an alternative to the **@IdClass** annotation.
Let's consider another example where we have to persist some information of a Book 
with title and language as the primary key fields.
In this case, the primary key class, BookId, must be annotated with **@Embeddable**:

```java 
@Embeddable
public class BookId implements Serializable {
    private String title;
    private String language;
 
    // default constructor
 
    public BookId(String title, String language) {
        this.title = title;
        this.language = language;
    }
 
    // getters, equals() and hashCode() methods
}
```

Then, we need to embed this class in the Book entity using **@EmbeddedId**:

```java 
@Entity
public class Book {
    @EmbeddedId
    private BookId bookId;
 
    // constructors, other fields, getters and setters
}
```

**@IdClass vs @EmbeddedId**  
As we just saw, the difference on the surface between these two is that with **@IdClass**, we had to specify 
the columns twice – once in AccountId and again in Account. But, with **@EmbeddedId** we didn't.

There are some other differences, though.

For example, these different structures affect **the JPQL queries** that we write.
For example, with **@IdClass**, the query is a bit simpler:
`SELECT account.accountNumber FROM Account account`  
With **@EmbeddedId**, we have to do one extra traversal:
`SELECT book.bookId.title FROM Book book`  

Also, **@IdClass can be quite useful in places where we are using a composite key class that we can't modify.**
(Class from some *.jar file)

Finally, if we're going to access parts of the composite key individually, we can make use of **@IdClass**, 
but in places where we frequently use the complete identifier as an object, **@EmbeddedId** is preferred.