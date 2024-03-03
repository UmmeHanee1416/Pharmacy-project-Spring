package com.finalProject.FinalProject.controller;


import com.finalProject.FinalProject.dto.ReturnedProducShoptDTO;
import com.finalProject.FinalProject.entity.ReturnedProductShop;
import com.finalProject.FinalProject.service.ReturnedProductShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retpro")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ReturnedProductShopController {

    @Autowired
    private ReturnedProductShopService returnedProductShopService;

    @GetMapping
    public List<ReturnedProducShoptDTO> getAll(){
        return returnedProductShopService.getAll();
    }

    @PostMapping
    public ReturnedProducShoptDTO addData(@RequestBody ReturnedProducShoptDTO returnedProducShoptDTO){
        return returnedProductShopService.addData(returnedProducShoptDTO);
    }

    @GetMapping("/{id}")
    public ReturnedProducShoptDTO getByID(@PathVariable("id") Long id){
        return returnedProductShopService.getById(id);
    }


    @PutMapping("/{id}")
    public ReturnedProductShop putProduct(@PathVariable ("id") Long id, @RequestBody ReturnedProducShoptDTO returnedProducShoptDTO){
        return returnedProductShopService.updateData(id,returnedProducShoptDTO);
    }

}
