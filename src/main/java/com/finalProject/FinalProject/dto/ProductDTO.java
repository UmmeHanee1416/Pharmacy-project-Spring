package com.finalProject.FinalProject.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class ProductDTO {

    private String tradeName;
    private String companyName;
    private Integer regsrtQuantity;
    private Integer soldQuantity;
    private Integer remainedQuantity;
    private LocalDate mfd;
    private LocalDate exp;
    private Integer sellPrice;

}
