package com.example.newpos.repo;

import com.example.newpos.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerRepo extends JpaRepository <Customer, Integer> {
    Page<Customer> findCustomersByActiveStatusEquals(boolean status, Pageable pageable);
    long countCustomerByActiveStatusEquals(boolean status);

    Page<Customer> findCustomersByCustomerNameContains(String name, Pageable pageable);
    long countAllByCustomerNameContains(String name);


}
