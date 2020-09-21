**@Table** specifies the primary table for the annotated entity. 
Additional tables may be specified using **@SecondaryTable** or **@SecondaryTables** annotation.
If no Table annotation is specified for an entity class, the default values apply.

Attribute String **name** (Optional) - The name of the table. 
Defaults to the entity name.

We can specify the name of the table in the attribute of the **@Entity** annotation, 
but it is more preferable to do this with the **@Table** annotation, 
because in the first case, the name in the table will be in upper case.