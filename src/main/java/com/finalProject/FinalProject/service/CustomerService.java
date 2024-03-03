package com.finalProject.FinalProject.service;

import com.finalProject.FinalProject.dto.CustomerDTO;
import com.finalProject.FinalProject.dto.SalesDTO;
import com.finalProject.FinalProject.dto.SalesDetailDTO;
import com.finalProject.FinalProject.entity.*;
import com.finalProject.FinalProject.repository.*;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public CustomerDTO addData(CustomerDTO customerDTO){
        Customer customer = new Customer();
        SalesDTO salesDTO  = new SalesDTO();
        SalesDetailDTO salesDetailDTO = new SalesDetailDTO();
        maptoEntity(customerDTO,customer);
        maptoDTO(customerRepo.save(customer), new CustomerDTO());
        salesDTO.setCustomerId(customerRepo.findById(customerDTO.getContact()).get().getContact());
        salesDTO.setEmpId(employeeRepo.findById(customerDTO.getEmpId()).get().getId());
        Sales sales  = new Sales();
        sales.setCustomerId(customerRepo.findById(salesDTO.getCustomerId()).get());
        sales.setEmpId(employeeRepo.findById(salesDTO.getEmpId()).get());
        SalesDTO salesDTO1 = new SalesDTO();
        maptoDTO(salesRepo.save(sales), salesDTO1);
        salesDetailDTO.setPurchaseId(salesRepo.findById(salesDTO1.getId()).get().getId());
        for (SalesDetailDTO s: this.salesDetails) {
            SalesDetail salesDetail = new SalesDetail();
            salesDetail.setPurchaseId(salesRepo.findById(salesDetailDTO.getPurchaseId()).get());
            salesDetailRepo.save(maptoEntity(s,salesDetail));
        }
        salesDetails = new ArrayList<>();
        List<Integer> arr = salesRepo.getAllProductQty(sales.getId());
        int abc = 0;
        for (int i = 0; i < arr.size(); i++) {
            abc += arr.get(i);
        }
        salesDTO1.setTotalQuantity(abc);
//        updateData(sales.getId(), salesDTO, sales);
        List<Integer> arr1 = salesRepo.getAllProductChrg(salesDetailDTO.getPurchaseId());
        int a =  0;
        for (int i = 0; i < arr1.size(); i++) {
            a += arr1.get(i);
        }
        salesDTO1.setTotalCharge(a);
//        updateData(sales.getId(), salesDTO1, sales);
        return customerDTO;
    }

    public List<CustomerDTO> getAll(){
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer c: customers) {
            CustomerDTO customerDTO = new CustomerDTO();
            maptoDTO(c,customerDTO);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public CustomerDTO getById(String id){
        Customer customer = customerRepo.findById(id).get();
        CustomerDTO customerDTO = new CustomerDTO();
        maptoDTO(customer,customerDTO);
        return customerDTO;
    }

    public Customer updateData(Long id,CustomerDTO customerDTO){
        Customer customer = new Customer();
        maptoEntity(customerDTO,customer);
        return customerRepo.save(customer);
    }

    public void deleteByID(String id){
        customerRepo.deleteById(id);
    }

    public Customer maptoEntity(CustomerDTO customerDTO,Customer customer){
        if (customer!=null){
            customer.setId(customerDTO.getId());
        }
        customer.setName(customerDTO.getName());
        customer.setContact(customerDTO.getContact());
        customer.setPurchaseDate(customerDTO.getPurchaseDate());
        customer.setPayMethod(customerDTO.getPayMethod());
        customer.setEmpId(employeeRepo.findById(customerDTO.getEmpId()).get());
        return customer;
    }

    public CustomerDTO maptoDTO(Customer customer, CustomerDTO customerDTO){
        if (customerDTO!=null){
            customerDTO.setId(customer.getId());
        }
        customerDTO.setName(customer.getName());
        customerDTO.setContact(customer.getContact());
        customerDTO.setPurchaseDate(customer.getPurchaseDate());
        customerDTO.setPayMethod(customer.getPayMethod());
        customerDTO.setEmpId(employeeRepo.findById(customer.getEmpId().getId()).get().getId());
        return customerDTO;
    }

//    -----sales service------

    @Autowired
    private SalesRepo salesRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<SalesDTO> getAllSales(){
        List<Sales> Saless = salesRepo.findAll();
        List<SalesDTO> SalesDTOS = new ArrayList<>();
        for (Sales s: Saless) {
            SalesDTO SalesDTO = new SalesDTO();
            maptoDTO(s,SalesDTO);
            SalesDTOS.add(SalesDTO);
        }
        return SalesDTOS;
    }


    public SalesDTO addData(SalesDTO salesDTO){
        Sales sales = new Sales();
        maptoEntity(salesDTO,sales);
        return maptoDTO(salesRepo.save(sales), new SalesDTO());
    }

    public Sales updateData(Long id, SalesDTO salesDTO, Sales sales){
//        Sales sales = new Sales();
        maptoEntity(salesDTO,sales);
        return salesRepo.save(sales);
    }

    public SalesDTO getById(Long id){
        Sales sales = salesRepo.findById(id).get();
        Customer customer = sales.getCustomerId();
        SalesDTO salesDTO = new SalesDTO();
        maptoDTO(sales,salesDTO);
        return salesDTO;
    }

    public void deleteByID(Long id){
        salesRepo.deleteById(id);
    }

    public SalesDTO maptoDTO(Sales sales, SalesDTO salesDTO){

        if (salesDTO!=null){
            salesDTO.setId(sales.getId());
        }
        salesDTO.setCustomerId(customerRepo.findById(sales.getCustomerId().getContact()).get().getContact());
        salesDTO.setEmpId(employeeRepo.findById(sales.getEmpId().getId()).get().getId());
//        salesDTO.setTotalCharge(sales.getTotalCharge());
        return salesDTO;
    }

    public Sales maptoEntity(SalesDTO salesDTO, Sales sales){
        if (sales!=null){
            sales.setId(salesDTO.getId());
        }
        sales.setTotalQuantity(salesDTO.getTotalQuantity());
//        sales.setTotalCharge(salesDTO.getTotalCharge());
        return sales;
    }


//    ----------salesdetail----------

    @Autowired
    private SalesDetailRepo salesDetailRepo;

    @Autowired
    private ProductRepo productRepo;

    public List<SalesDetailDTO> getAllsd(){
        List<SalesDetail> salesDetails = salesDetailRepo.findAll();
        List<SalesDetailDTO> salesDetailDTOS = new ArrayList<>();
        for (SalesDetail s: salesDetails) {
            SalesDetailDTO salesDetailDTO = new SalesDetailDTO();
            maptoDTO(s,salesDetailDTO);
            salesDetailDTOS.add(salesDetailDTO);
        }
        return salesDetailDTOS;
    }
    private List<SalesDetailDTO> salesDetails = new ArrayList<>();

    public SalesDetailDTO addData(SalesDetailDTO salesDetailDTO){
        SalesDetail salesDetail = new SalesDetail();
        maptoEntity(salesDetailDTO,salesDetail);
        salesDetails.add(salesDetailDTO);
        return salesDetailDTO;
    }

    public SalesDetail updateData(Long id,SalesDetailDTO salesDetailDTO){
        SalesDetail salesDetail = new SalesDetail();
        maptoEntity(salesDetailDTO,salesDetail);
        return salesDetailRepo.save(salesDetail);
    }

    public SalesDetailDTO getByIdsd(Long id){
        SalesDetail salesDetail = salesDetailRepo.findById(id).get();
        SalesDetailDTO salesDetailDTO = new SalesDetailDTO();
        maptoDTO(salesDetail,salesDetailDTO);
        return salesDetailDTO;
    }

    public void deleteByIDsd(Long id){
        salesDetailRepo.deleteById(id);
    }

    public SalesDetailDTO maptoDTO(SalesDetail salesDetail,SalesDetailDTO salesDetailDTO){
        if (salesDetailDTO!=null){
            salesDetailDTO.setId(salesDetail.getId());
        }
        salesDetailDTO.setProductId(productRepo.findById(salesDetail.getProductId().getTradeName()).get().getTradeName());
        salesDetailDTO.setProductQuantity(salesDetail.getProductQuantity());
        salesDetailDTO.setProductQuantity(salesDetail.getTotalCharge());
        return salesDetailDTO;
    }

    public SalesDetail maptoEntity(SalesDetailDTO salesDetailDTO,SalesDetail salesDetail){
        if (salesDetail!=null){
            salesDetail.setId(salesDetailDTO.getId());
        }
        salesDetail.setProductId(productRepo.findById(salesDetailDTO.getProductId()).get());
        salesDetail.setProductQuantity(salesDetailDTO.getProductQuantity());
        salesDetail.setTotalCharge(productRepo.findById(salesDetailDTO.getProductId()).get(),salesDetailDTO.getProductQuantity());
        return salesDetail;
    }

}
