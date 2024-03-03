package com.finalProject.FinalProject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PurchaseDTO {

    private Long id;
    private String productId;
    private Long genericId;
    private String companyId;
    private Long batchId;
    private Integer purchaseQuantity;
    private LocalDate purchaseDate;
    private Integer MRP;
}
