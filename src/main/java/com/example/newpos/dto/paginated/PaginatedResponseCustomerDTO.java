package com.example.newpos.dto.paginated;

import com.example.newpos.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseCustomerDTO {
    private List<CustomerDTO> list;
    private long count;

}
