ORM (Object-Relational Mapping, объектно-реляционное отображение)  
This is usually a ORM-library (Hibernate, EclipseLink, TopLink etc.)  

ORM is technology that allows you to work with database tables as objects.  
ORM is designed to convert objects into a form for saving them in files or databases, 
as well as their further retrieval in the program.  

JavaEE is a set of specifications that can interact with each other. 
All technologies and frameworks in Java work on specifications (standards, rules). 
If there is no standard, they first come up with it (JSR) so that there is no chaos. 

JPA  
In Java, it was decided to standardize all ORM librarians.  
A set of interfaces was created that can work with ORM.   
  
JPA (Java Persistence API) – specification, a document that describes rules and 
API for implementing ORM principles for Java (annotations, settings, approach)  

JPA has many implementations:
 - OpenJPA (Apache) 
 - Hibernate 
 - TopLink (Oracle ) 
 - EclipseLink  
 ...

JPA provides a set of interfaces that allow us to work with the database.
Thus, we get a universal code that can work with any persistence provider.