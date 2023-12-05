package com.example.newpos.service.impl;

import com.example.newpos.dto.CustomerDTO;
import com.example.newpos.dto.paginated.PaginatedResponseCustomerDTO;
import com.example.newpos.dto.request.CustomerUpdateDTO;
import com.example.newpos.entity.Customer;
import com.example.newpos.exception.NotFoundException;
import com.example.newpos.repo.CustomerRepo;
import com.example.newpos.service.CustomerService;
import com.example.newpos.util.mappers.CustomerMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String SaveCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customerRepo.save(customer);
        return customer.getCustomerName()+" Saved successfully in newpos database.";
    }

    @Override
    public PaginatedResponseCustomerDTO getAllCustomers(int page, int size) {
        Page<Customer> customers = customerRepo.findAll(PageRequest.of(page,size));

        if (customers.getSize() > 0) {
            return new PaginatedResponseCustomerDTO(customerMapper.PageToCustomerDTO(customers),customerRepo.count());
        }else {
            throw new NotFoundException("No data found.....");
        }
    }

    @Override
    public CustomerDTO UpdateCustomer(CustomerUpdateDTO customerUpdateDTO) {

        if(customerRepo.existsById(customerUpdateDTO.getCustomerId())){
            Customer customerUpdate = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());
            modelMapper.map(customerUpdateDTO,customerUpdate);
            customerRepo.save(customerUpdate);

            CustomerDTO customerDTO = modelMapper.map(customerUpdate,CustomerDTO.class);
            return customerDTO;
        }else {
            throw new NotFoundException("No data found....");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if(customerRepo.existsById(customerId)){
            Customer customer = customerRepo.getReferenceById(customerId);
            CustomerDTO customerDTO = modelMapper.map(customer,CustomerDTO.class);
            return customerDTO;
        } else {
            throw new NotFoundException("No data found..!!!");
        }
    }

    @Override
    public String deleteCustomer(int customerId) {
       if(customerRepo.existsById(customerId)){
           customerRepo.deleteById(customerId);
           return "Deleted Successfully " + customerId;
       }else {
           throw new NotFoundException("Data not found");
       }
    }

    @Override
    public PaginatedResponseCustomerDTO getAllCustomersByActiveStatus(boolean status, int page, int size) {
        Page<Customer> customers = customerRepo.findCustomersByActiveStatusEquals(status, PageRequest.of(page, size));
        if(customers.getSize()>0){
            return new PaginatedResponseCustomerDTO(customerMapper.PageToCustomerDTO(customers),customerRepo.countCustomerByActiveStatusEquals(status));
        }else {
            throw new NotFoundException("Data not found");
        }
    }

    @Override
    public PaginatedResponseCustomerDTO getAllCustomersByName(String name, int page, int size) {
        Page<Customer> customers = customerRepo.findCustomersByCustomerNameContains(name, PageRequest.of(page,size));
        if(customers.getSize()>0){
            return new PaginatedResponseCustomerDTO(customerMapper.PageToCustomerDTO(customers),customerRepo.countAllByCustomerNameContains(name));
        }else{
         throw new NotFoundException("Data not found");
        }
    }


}
