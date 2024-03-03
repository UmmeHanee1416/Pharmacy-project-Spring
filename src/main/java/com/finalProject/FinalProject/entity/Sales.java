package com.finalProject.FinalProject.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Sales extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;
    private  Integer totalQuantity;
    private Integer totalCharge;
    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee empId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchaseId", referencedColumnName = "id")
    private Set<SalesDetail> salesDetails;

//    public void setTotalQuantity(SalesDetail salesDetail) {
//        this.totalQuantity += salesDetail.getProductQuantity();
//    }

//    public void setTotalCharge(SalesDetail salesDetail) {
//        this.totalCharge += salesDetail.getTotalCharge();
//    }
}
