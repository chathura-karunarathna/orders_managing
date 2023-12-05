package com.example.newpos.controller;

import com.example.newpos.dto.CustomerDTO;
import com.example.newpos.dto.paginated.PaginatedResponseCustomerDTO;
import com.example.newpos.dto.request.CustomerUpdateDTO;
import com.example.newpos.service.CustomerService;
import com.example.newpos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> SaveCustomer(@RequestBody CustomerDTO customerDTO){
        String message = customerService.SaveCustomer(customerDTO);
        ResponseEntity<StandardResponse> responseEntity = new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Successfully saved",message), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping(value = "/get-all-customers", params = {"page","size"})
    public ResponseEntity<StandardResponse> GetAllCustomers(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size){
       PaginatedResponseCustomerDTO paginatedResponseCustomerDTO = customerService.getAllCustomers(page,size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"All Customers",paginatedResponseCustomerDTO), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
        CustomerDTO customerDTO = customerService.UpdateCustomer(customerUpdateDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(201,"Data updated successfully",customerDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/get-by-id", params = "id")
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam(value = "id")int customerId){
        CustomerDTO customer = customerService.getCustomerById(customerId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "Customer Found",customer),HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/delete-by-id",params = "id")
    public ResponseEntity<StandardResponse> deleteCustomer (@RequestParam(value = "id") int customerId){
        String deleted = customerService.deleteCustomer(customerId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"Customer deleted successfully..!!",deleted),HttpStatus.OK);
    }

    @GetMapping(value = "get-customer-by-activestate", params = {"status","page","size"})
    public ResponseEntity<StandardResponse> getAllcustomersByActiveState(@RequestParam(value ="status") boolean status,
                                                                         @RequestParam(value = "page") int page,
                                                                         @RequestParam(value = "size") int size){
        PaginatedResponseCustomerDTO customers = customerService.getAllCustomersByActiveStatus(status,page,size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"Data found",customers),HttpStatus.FOUND);
    }

    @GetMapping(value = "get-customer-by-name", params = {"name","page","size"})
    public ResponseEntity<StandardResponse> getAllCustomerByName(@RequestParam(value = "name") String name,
                                                                 @RequestParam(value = "page") int page,
                                                                 @RequestParam(value = "size") int size){
        PaginatedResponseCustomerDTO paginatedResponseCustomerDTO = customerService.getAllCustomersByName(name,page,size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"Data found",paginatedResponseCustomerDTO),HttpStatus.FOUND);
    }

}
