**@Temporal** annotation must be specified for persistent fields or properties of type java.util.Date 
and java.util.Calendar. It may only be specified for fields or properties of these types.
The Temporal annotation may be used in conjunction with the **Basic** annotation, the **Id** annotation, 
or the **ElementCollection** annotation (when the element collection value is of such a temporal type.

```java
@Temporal(TemporalType.DATE)
private java.util.Date endDate;
```

For example, If Calendar is used as an Entity property then in plain java it prints a lot of information 
which is not necessary when it is saved to database. In that case, it can be applied on your Calendar property 
in the entity to store timestamp to a database or just date or just time by changing to 
@Temporal(TemporalType.DATE) or @Temporal(TemporalType.TIME) . 

```java
@Temporal(TemporalType.TIMESTAMP)
private Calendar calendar;
```
 