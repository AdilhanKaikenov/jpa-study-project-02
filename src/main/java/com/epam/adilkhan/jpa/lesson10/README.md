**transient keyword vs JPA @Transient annotation**  
The **@Transient** annotation tells the JPA provider to not persist any non-transient) attribute. 
The other tells the serialization framework to not serialize an attribute. You might want 
to have a **@Transient** property and still serialize it. In general, one deals with 
serialization and one deals with persistence.