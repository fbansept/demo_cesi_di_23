package edu.cesi.demo.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int quantity;

    @ManyToOne
    private User supplier;

    @ManyToMany
    @JoinTable(
            name = "family_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "family_id")
    )
    List<Family> familyList = new ArrayList<>();
}
