package com.example.newpos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerUpdateDTO {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String nic;
    private int contactNumber;
}
