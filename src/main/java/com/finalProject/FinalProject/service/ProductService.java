package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.ProductDTO;
import com.finalProject.FinalProject.entity.Company;
import com.finalProject.FinalProject.entity.Product;
import com.finalProject.FinalProject.repository.CompanyRepo;
import com.finalProject.FinalProject.repository.ProductRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CompanyRepo companyRepo;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepo.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product p: products) {
            ProductDTO productDTO = new ProductDTO();
            maptoDTO(p,productDTO);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    public ProductDTO addData(ProductDTO productDTO){
        Product product = new Product();
        maptoEntity(productDTO,product);
        product.setRemainedQuantity(productDTO.getRegsrtQuantity() - productDTO.getSoldQuantity());
        ProductDTO productDTO1 = new ProductDTO();
        return maptoDTO(productRepo.save(product),productDTO1);
    }


    public Product updateData(Long id,ProductDTO productDTO){
        Product product = new Product();
        maptoEntity(productDTO,product);
        return productRepo.save(product);
    }

    public ProductDTO getById(String id){
        Product product = productRepo.findById(id).get();
        ProductDTO productDTO = new ProductDTO();
        maptoDTO(product,productDTO);
        return productDTO;
    }

    public void deleteByID(String id){
        productRepo.deleteById(id);
    }

    public Company maptoCompany(ProductDTO productDTO){
        Company company = companyRepo.findById(productDTO.getCompanyName()).get();
        return company;
    }

    public String maptoCompanyName(Product product){
        String productDTO = product.getCompanyName().getName();
        return productDTO;
    }

    public Product maptoEntity(ProductDTO productDTO,Product product){
        product.setTradeName(productDTO.getTradeName());
        product.setCompanyName(maptoCompany(productDTO));
        product.setRegsrtQuantity(productDTO.getRegsrtQuantity());
        product.setSoldQuantity(productDTO.getSoldQuantity());
        product.setMfd(productDTO.getMfd());
        product.setExp(productDTO.getExp());
        product.setSellPrice(productDTO.getSellPrice());
        return product;
    }

    public ProductDTO maptoDTO(Product product,ProductDTO productDTO){
        productDTO.setTradeName(product.getTradeName());
        productDTO.setCompanyName(maptoCompanyName(product));
        productDTO.setRegsrtQuantity(product.getRegsrtQuantity());
        productDTO.setSoldQuantity(product.getSoldQuantity());
        productDTO.setRemainedQuantity(product.getRemainedQuantity());
        productDTO.setMfd(product.getMfd());
        productDTO.setExp(product.getExp());
        productDTO.setSellPrice(product.getSellPrice());
        return productDTO;
    }

    public List<Object[]> getallTradeNameAndRemainedQuantity() {
       return productRepo.getTradeNameAndRemainedQuanity();
    }
}
