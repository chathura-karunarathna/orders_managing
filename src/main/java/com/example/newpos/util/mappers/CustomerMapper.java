package com.example.newpos.util.mappers;

import com.example.newpos.dto.CustomerDTO;
import com.example.newpos.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    List<CustomerDTO> PageToCustomerDTO (Page<Customer> customers);
}
