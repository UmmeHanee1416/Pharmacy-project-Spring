package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll(){
        return productService.getAll();
    }

    @PostMapping
    public ProductDTO addData(@RequestBody ProductDTO productDTO){
        return productService.addData(productDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO getByID(@PathVariable("id") String id){
        return productService.getById(id);
    }


    @PutMapping("/{id}")
    public Product putProduct(@PathVariable ("id") Long id, @RequestBody ProductDTO productDTO){
        return productService.updateData(id,productDTO);
    }

    @GetMapping("/tnameremQ")
    public List<Object[]> getTradeNameAndRemainedQuantity(){
    return productService.getallTradeNameAndRemainedQuantity();
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") String id){
        productService.deleteByID(id);
    }
}
