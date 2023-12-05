package com.example.newpos.controller;

import com.example.newpos.dto.ItemDTO;
import com.example.newpos.dto.paginated.PaginatedResponseOrderDetails;
import com.example.newpos.dto.request.RequestOrderSaveDTO;
import com.example.newpos.service.OrderService;
import com.example.newpos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> SaveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO){
        String id = orderService.addOrder(requestOrderSaveDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"Item saved successfully",id), HttpStatus.CREATED);
    }

    @GetMapping(value = {"/get-order-details"},params = {"stateType","page","size"})
    public ResponseEntity<StandardResponse> getAllOrderDetais(
            @RequestParam(value = "stateType")String statetype,
            @RequestParam(value = "page")int page,
            @RequestParam(value = "size")int size){

        PaginatedResponseOrderDetails p = null;
        return p;
    }
}
