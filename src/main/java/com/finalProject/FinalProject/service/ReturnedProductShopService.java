package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.ReturnedProducShoptDTO;
import com.finalProject.FinalProject.entity.ReturnedProductShop;
import com.finalProject.FinalProject.repository.CompanyRepo;
import com.finalProject.FinalProject.repository.ProductRepo;
import com.finalProject.FinalProject.repository.ReturnedProductShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnedProductShopService {

    @Autowired
    private ReturnedProductShopRepo returnedProductShopRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CompanyRepo companyRepo;

    public List<ReturnedProducShoptDTO> getAll(){
        List<ReturnedProductShop> returnedProductShops = returnedProductShopRepo.findAll();
        List<ReturnedProducShoptDTO> returnedProducShoptDTOS = new ArrayList<>();
        for (ReturnedProductShop r: returnedProductShops) {
            ReturnedProducShoptDTO employeeDetailDTO = new ReturnedProducShoptDTO();
            maptoDTO(r,employeeDetailDTO);
            returnedProducShoptDTOS.add(employeeDetailDTO);
        }
        return returnedProducShoptDTOS;
    }

    public ReturnedProducShoptDTO addData(ReturnedProducShoptDTO returnedProducShoptDTO){
        ReturnedProductShop returnedProductShop = new ReturnedProductShop();
        maptoEntity(returnedProducShoptDTO,returnedProductShop);
        return maptoDTO(returnedProductShopRepo.save(returnedProductShop), new ReturnedProducShoptDTO());
    }


    public ReturnedProductShop updateData(Long id,ReturnedProducShoptDTO returnedProducShoptDTO){
        ReturnedProductShop returnedProductShop = new ReturnedProductShop();
        maptoEntity(returnedProducShoptDTO,returnedProductShop);
        return returnedProductShopRepo.save(returnedProductShop);
    }

    public ReturnedProducShoptDTO getById(Long id){
        ReturnedProductShop returnedProductShop = returnedProductShopRepo.findById(id).get();
        ReturnedProducShoptDTO returnedProducShoptDTO = new ReturnedProducShoptDTO();
        maptoDTO(returnedProductShop,returnedProducShoptDTO);
        return returnedProducShoptDTO;
    }

    public void deleteByID(Long id){
        returnedProductShopRepo.deleteById(id);
    }


    public ReturnedProductShop maptoEntity(ReturnedProducShoptDTO returnedProducShoptDTO,ReturnedProductShop returnedProductShop){
        if (returnedProductShop!=null){
            returnedProductShop.setId(returnedProducShoptDTO.getId());
        }
        returnedProductShop.setTradeName(productRepo.findById(returnedProducShoptDTO.getTradeName()).get());
        returnedProductShop.setCompanyName(companyRepo.findById(returnedProducShoptDTO.getCompanyName()).get());
        returnedProductShop.setPurchaseDate(returnedProducShoptDTO.getPurchaseDate());
        returnedProductShop.setReturnDate(returnedProducShoptDTO.getReturnDate());
        return returnedProductShop;
    }

    public ReturnedProducShoptDTO maptoDTO(ReturnedProductShop returnedProductShop,ReturnedProducShoptDTO returnedProducShoptDTO){
        if (returnedProducShoptDTO!=null){
            returnedProducShoptDTO.setId(returnedProductShop.getId());
        }
        returnedProducShoptDTO.setTradeName(returnedProductShop.getTradeName().getTradeName());
        returnedProducShoptDTO.setCompanyName(returnedProductShop.getCompanyName().getName());
        returnedProducShoptDTO.setPurchaseDate(returnedProductShop.getPurchaseDate());
        returnedProducShoptDTO.setReturnDate(returnedProductShop.getReturnDate());
        return returnedProducShoptDTO;
    }


}
