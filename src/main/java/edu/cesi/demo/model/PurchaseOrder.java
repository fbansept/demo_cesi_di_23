package edu.cesi.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime date;

    private String status;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order")
    private List<PurchaseOrderLine> lines;
}
