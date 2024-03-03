package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.CompanyDTO;
import com.finalProject.FinalProject.entity.Company;
import com.finalProject.FinalProject.repository.CompanyRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    public List<CompanyDTO> getAll(){
        List<Company> companies = companyRepo.findAll();
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        for (Company c: companies) {
            CompanyDTO companyDTO = new CompanyDTO();
            BeanUtils.copyProperties(c,companyDTO);
            companyDTOS.add(companyDTO);
        }
        return companyDTOS;
    }

    public Company addData(CompanyDTO companyDTO){
        Company company = new Company();
        BeanUtils.copyProperties(companyDTO,company);
        return companyRepo.save(company);
    }

    public Company updateData(Long id,CompanyDTO companyDTO){
        Company company = new Company();
        BeanUtils.copyProperties(companyDTO,company);
        return companyRepo.save(company);
    }

    public CompanyDTO getById(String id){
        Company company = companyRepo.findById(id).get();
        CompanyDTO companyDTO = new CompanyDTO();
        BeanUtils.copyProperties(company,companyDTO);
        return companyDTO;
    }

    public void deleteByID(String id){
        companyRepo.deleteById(id);
    }
}
