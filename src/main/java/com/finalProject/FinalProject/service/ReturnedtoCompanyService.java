package com.finalProject.FinalProject.service;


import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.dto.ReturnedtoCompanyDTO;
import com.finalProject.FinalProject.entity.Company;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.entity.ReturnedtoCompany;
import com.finalProject.FinalProject.repository.CompanyRepo;
import com.finalProject.FinalProject.repository.ProductRepo;
import com.finalProject.FinalProject.repository.ReturnedtoCompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnedtoCompanyService {

    @Autowired
    private ReturnedtoCompanyRepo returnedtoCompanyRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<ReturnedtoCompanyDTO> getAll(){
        List<ReturnedtoCompany> products = returnedtoCompanyRepo.findAll();
        List<ReturnedtoCompanyDTO> productDTOS = new ArrayList<>();
        for (ReturnedtoCompany p: products) {
            ReturnedtoCompanyDTO productDTO = new ReturnedtoCompanyDTO();
            maptoDTO(p,productDTO);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public ReturnedtoCompany addData(ReturnedtoCompanyDTO productDTO){
        ReturnedtoCompany product = new ReturnedtoCompany();
        maptoEntity(productDTO,product);
        return returnedtoCompanyRepo.save(product);
    }


    public ReturnedtoCompany updateData(Long id,ReturnedtoCompanyDTO productDTO){
        ReturnedtoCompany product = new ReturnedtoCompany();
        maptoEntity(productDTO,product);
        return returnedtoCompanyRepo.save(product);
    }

    public ReturnedtoCompanyDTO getById(Long id){
        ReturnedtoCompany product = returnedtoCompanyRepo.findById(id).get();
        ReturnedtoCompanyDTO productDTO = new ReturnedtoCompanyDTO();
        maptoDTO(product,productDTO);
        return productDTO;
    }

    public void deleteByID(Long id){
        returnedtoCompanyRepo.deleteById(id);
    }

    public Company maptoCompany(ReturnedtoCompanyDTO productDTO){
        Company company = companyRepo.findById(productDTO.getCompanyName()).get();
        return company;
    }

    public String maptoCompanyName(ReturnedtoCompany product){
        String productDTO = companyRepo.findById(product.getCompanyName().getName()).get().getName();
        return productDTO;
    }

    public ReturnedtoCompany maptoEntity(ReturnedtoCompanyDTO productDTO,ReturnedtoCompany product){
        product.setTradeName(productRepo.findById(productDTO.getTradeName()).get());
        product.setCompanyName(maptoCompany(productDTO));
        product.setPurchaseDate(productDTO.getPurchaseDate());
        product.setPurchaseAmount(productDTO.getPurchaseAmount());
        product.setReturnedDate(productDTO.getReturnedDate());
        product.setReturnedAmount(productDTO.getReturnedAmount());
        return product;
    }

    public ReturnedtoCompanyDTO maptoDTO(ReturnedtoCompany product,ReturnedtoCompanyDTO productDTO){
        productDTO.setTradeName(productRepo.findById(product.getTradeName().getTradeName()).get().getTradeName());
        productDTO.setCompanyName(maptoCompanyName(product));
        productDTO.setPurchaseDate(product.getPurchaseDate());
        productDTO.setPurchaseAmount(product.getPurchaseAmount());
        productDTO.setReturnedDate(product.getReturnedDate());
        productDTO.setReturnedAmount(product.getReturnedAmount());
        return productDTO;
    }

}
