package com.finalProject.FinalProject.repository;

import com.finalProject.FinalProject.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepo extends JpaRepository<Sales,Long> {

    @Query(value = " SELECT sd.product_quantity FROM sales_detail sd JOIN sales s ON s.id = sd.purchase_id WHERE s.id  =  :id ", nativeQuery = true)
    List<Integer> getAllProductQty(@Param("id") Long id);

    @Query(value = " SELECT sd.total_charge FROM sales_detail sd JOIN sales s ON s.id = sd.purchase_id WHERE s.id  =  :id ", nativeQuery = true)
    List<Integer> getAllProductChrg(@Param("id") Long id);

//    @Query(value = " select trade_name, remained_quantity from product ", nativeQuery = true)
//    List<Object[]> getTradeNameAndRemainedQuanity();

}
