**persistence.xml** is a settings file that allows you to configure the work with the database.

Traditionally, the **persistence.xml** is located in a **META-INF** folder, 
which should be at the root of the Java classpath.

Persistent units can be packaged as part of a WAR or EJB JAR file or can be packaged 
as a JAR file that can then be included in an WAR or EAR file.

* If you package the persistent unit as a set of classes in an EJB JAR file, persistence.xml 
should be put in the EJB JAR's **META-INF** directory: **src/main/resources/META-INF/persistence.xml**
* If you package the persistence unit as a set of classes in a WAR file, persistence.xml 
should be located in the WAR file's **WEB-INF/classes/META-INF** directory.
* If you package the persistence unit in a JAR file that will be included in a WAR or EAR file, 
the JAR file should be located in either The **WEB-INF/lib** directory of a WAR or the EAR file's library directory

The **persistence tag** is the root XML element, and it defines the JPA version and the 
XML schema used to validate the persistence.xml configuration file.

This file contains the configuration for the **EntityManager**. 
When you use this configuration, you configure a **persistence unit** with a specific name.

The **persistence-unit element** defines the name of the associated **JPA Persistence Unit**, 
which you can later use to reference it when using the **@PersistenceUnit** JPA annotation 
to inject the associated **EntityManagerFactory** instance:
```java
@PersistenceUnit(name = "examplePersistenceUnit")
private EntityManagerFactory entityManagerFactory;
```
Roughly speaking, a persistent unit is a setting for connecting to a specific database.