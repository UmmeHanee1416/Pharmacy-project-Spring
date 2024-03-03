package com.finalProject.FinalProject.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product extends Base{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Id
    private String tradeName;
    @ManyToOne
    @JoinColumn(name = "companyName")
    private Company companyName;
    private Integer regsrtQuantity;
    private Integer soldQuantity;
    private Integer remainedQuantity;
    private LocalDate mfd;
    private LocalDate exp;
    private Integer sellPrice;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "products")
    @ToString.Exclude
    private List<Generic> generics;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "tradeName")
    private Set<SalesDetail> salesDetails;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tradeName", referencedColumnName = "tradeName")
    private Set<ReturnedProductShop> returnedProductShops;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tradeName", referencedColumnName = "tradeName")
    private Set<ReturnedtoCompany> returnedtoCompanies;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "tradeName")
    private Set<Purchase> purchases;
}
