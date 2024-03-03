package com.finalProject.FinalProject.service;


import com.finalProject.FinalProject.dto.GenericDTO;
import com.finalProject.FinalProject.entity.Generic;
import com.finalProject.FinalProject.repository.GenericRepo;
import com.finalProject.FinalProject.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenericService {

    @Autowired
    private GenericRepo genericRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<GenericDTO> getAll(){
        List<Generic> Saless = genericRepo.findAll();
        List<GenericDTO> SalesDTOS = new ArrayList<>();
        for (Generic s: Saless) {
            GenericDTO SalesDTO = new GenericDTO();
//            maptoDTO(s,SalesDTO);
            SalesDTOS.add(SalesDTO);
        }
        return SalesDTOS;
    }


    public Generic addData(GenericDTO salesDTO){
        Generic sales = new Generic();
//        maptoEntity(salesDTO,sales);
        return genericRepo.save(sales);
    }

    public Generic updateData(Long id, GenericDTO salesDTO){
        Generic sales = new Generic();
//        maptoEntity(salesDTO,sales);
        return genericRepo.save(sales);
    }

    public GenericDTO getById(Long id){
        Generic sales = genericRepo.findById(id).get();
        GenericDTO salesDTO = new GenericDTO();
//        maptoDTO(sales,salesDTO);
        return salesDTO;
    }

    public void deleteByID(Long id){
        genericRepo.deleteById(id);
    }

//    public GenericDTO maptoDTO(Generic sales, GenericDTO salesDTO){
//
//        if (salesDTO!=null){
//            salesDTO.setId(sales.getId());
//        }
//        salesDTO.setProductId(sales.getProducts().get());
//        salesDTO.setGenericName(sales.getGenericName());
//        salesDTO.setRegisteredQuantity(sales.getRegisteredQuantity());
//        return salesDTO;
//    }

//    public Generic maptoEntity(GenericDTO salesDTO, Generic sales){
//        if (sales!=null){
//            sales.setId(salesDTO.getId());
//        }
//        sales.setProductId(productRepo.findById(salesDTO.getProductId()).get());
//        sales.setGenericName(salesDTO.getGenericName());
//        sales.setRegisteredQuantity(salesDTO.getRegisteredQuantity());
//        return sales;
//    }

}
