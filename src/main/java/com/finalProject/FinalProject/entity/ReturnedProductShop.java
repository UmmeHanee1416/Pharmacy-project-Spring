package com.finalProject.FinalProject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
public class ReturnedProductShop extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tradeName")
    private Product tradeName;
    @ManyToOne
    @JoinColumn(name = "companyName")
    private Company companyName;
    private LocalDate purchaseDate;
    private LocalDate returnDate;
}
