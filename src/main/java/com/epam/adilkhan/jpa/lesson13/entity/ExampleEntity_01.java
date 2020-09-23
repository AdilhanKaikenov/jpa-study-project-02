package com.epam.adilkhan.jpa.lesson13.entity;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "example_table_12")
public class ExampleEntity_01 {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "types", joinColumns = @JoinColumn(name = "example_table_12_id"))
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Set<Types> types;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "map_table_example", joinColumns = @JoinColumn(name = "example_table_12_id"))
    @Column(name = "map_column")
    @MapKeyColumn(name = "map_key_column")
    private Map<Integer, String> map;

    public ExampleEntity_01() {
    }

    public ExampleEntity_01(String name, Set<Types> types, Map<Integer, String> map) {
        this.name = name;
        this.types = types;
        this.map = map;
    }
}