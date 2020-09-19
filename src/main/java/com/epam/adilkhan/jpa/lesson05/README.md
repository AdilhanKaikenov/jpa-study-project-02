Exclude all classes that are not in the list of settings 
(to explicitly specify which managed classes shall be part of the persistence-unit):  
**`<exclude-unlisted-classes>true</exclude-unlisted-classes>`**

```
<persistence-unit name="examplePersistenceUnit">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <!-- Specify entity classes -->
    <class>com.epam.adilkhan.jpa.lesson04.entity.Book</class>
    <exclude-unlisted-classes>true</exclude-unlisted-classes>
    <properties>
        ...
    </properties>
</persistence-unit>
```
Include all managed classes:  
**`<exclude-unlisted-classes>false</exclude-unlisted-classes>`**

```
<persistence-unit name="examplePersistenceUnit">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <exclude-unlisted-classes>false</exclude-unlisted-classes>
    <properties>
        ...
    </properties>
</persistence-unit>
```




