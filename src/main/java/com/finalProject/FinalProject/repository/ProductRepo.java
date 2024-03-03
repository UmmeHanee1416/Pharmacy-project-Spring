package com.finalProject.FinalProject.repository;


import com.finalProject.FinalProject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,String> {

    @Query(value = " select trade_name, remained_quantity from product ", nativeQuery = true)
    List<Object[]> getTradeNameAndRemainedQuanity();
}
