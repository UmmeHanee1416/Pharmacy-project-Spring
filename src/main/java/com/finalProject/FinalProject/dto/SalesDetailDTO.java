package com.finalProject.FinalProject.dto;
import lombok.Data;

@Data
public class SalesDetailDTO {

    private Long id;
    private Long purchaseId;
    private String productId;
    private Integer productQuantity;

    public void setProductQuantity(Integer productQuantity) {
        int abc = 0;
        abc += productQuantity==null?0:productQuantity;
        this.productQuantity = abc ;
    }
}
