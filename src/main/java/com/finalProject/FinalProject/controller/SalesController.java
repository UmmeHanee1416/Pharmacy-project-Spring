package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.SalesDTO;
import com.finalProject.FinalProject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class SalesController {

    @Autowired
    private CustomerService salesService;

    @GetMapping
    public List<SalesDTO> getAll(){
        return salesService.getAllSales();
    }

    @PostMapping
    public SalesDTO addData(@RequestBody SalesDTO salesDTO){
        return salesService.addData(salesDTO);
    }

    @GetMapping("/{id}")
    public SalesDTO getByID(@PathVariable("id") Long id){
        return salesService.getById(id);
    }


//    @PutMapping("/{id}")
//    public SalesDTO putProduct(@PathVariable ("id") Long id, @RequestBody SalesDTO salesDTO){
//        return salesService.updateData(id,salesDTO);
//    }
}
