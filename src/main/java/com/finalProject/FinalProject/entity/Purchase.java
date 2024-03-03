package com.finalProject.FinalProject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
public class Purchase extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product productId;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "purchases")
    @ToString.Exclude
    private List<Generic> generics;
    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company companyId;
    private Long batchId;
    private Integer purchaseQuantity;
    private LocalDate purchaseDate;
    private Integer MRP;
}
