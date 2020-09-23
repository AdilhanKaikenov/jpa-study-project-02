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

**@Enumerated**
 If we put the **@Enumerated(EnumType.ORDINAL)** annotation on the enum field, JPA will use 
 the Enum.ordinal() value when persisting a given entity in the database.
A problem with this kind of mapping arises when we need to modify our enum. **If we add a 
new value in the middle or rearrange the enum's order, we'll break the existing data model.**

JPA will use the Enum.name() value when storing an entity if we annotate the enum field 
with **@Enumerated(EnumType.STRING)**.
With @Enumerated(EnumType.STRING), we can safely add new enum values or change our enum's 
order. **However, renaming an enum value will still break the database data.**

**@Access**
@Access is used to specify how JPA must access (get and set) mapped properties of the entity. 
It has 2 modes: **AccessType.FIELD, AccessType.PROPERTY**;

If access type is set to **FIELD**, the values will directly be read/set on the field, 
bypassing getters and setters. If set to **PROPERTY**, the getters and setters are used 
to access the field value.