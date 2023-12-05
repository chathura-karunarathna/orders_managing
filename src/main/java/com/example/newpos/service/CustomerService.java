package com.example.newpos.service;

import com.example.newpos.dto.CustomerDTO;
import com.example.newpos.dto.paginated.PaginatedResponseCustomerDTO;
import com.example.newpos.dto.request.CustomerUpdateDTO;

public interface CustomerService {

    String SaveCustomer(CustomerDTO customerDTO);

    PaginatedResponseCustomerDTO getAllCustomers(int page, int size);

    CustomerDTO UpdateCustomer(CustomerUpdateDTO customerUpdateDTO);

    CustomerDTO getCustomerById(int customerId);

    String deleteCustomer(int customerId);

    PaginatedResponseCustomerDTO getAllCustomersByActiveStatus(boolean status, int page, int size);

    PaginatedResponseCustomerDTO getAllCustomersByName(String name, int page, int size);
}
