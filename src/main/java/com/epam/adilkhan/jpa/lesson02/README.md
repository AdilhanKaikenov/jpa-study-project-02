The foundation of JPA is represented by Entity classes. 
Entity - POJO class associated with the database using the annotation (**@Entity**) or 
via "XML" - the description file.  

This class has the following requirements:
* The class must have an empty constructor (public or protected)
* Class cannot be nested, interface or enum
* It cannot be final and cannot contain final fields / properties
* Must contain at least one field (property) annotated with **@Id** - identifier

~~~~
@Entity
public class Student {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    private Date birth;
}
~~~~