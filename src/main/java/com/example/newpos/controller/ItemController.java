package com.example.newpos.controller;

import com.example.newpos.dto.ItemDTO;
import com.example.newpos.dto.paginated.PaginatedResponseItemDTO;
import com.example.newpos.dto.request.ItemUpdateDTO;
import com.example.newpos.service.ItemService;
import com.example.newpos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> SaveItem(@RequestBody ItemDTO itemDTO){
        String message = itemService.SaveItem(itemDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"Item saved successfully",message), HttpStatus.CREATED);
    }

    @GetMapping(value = "get-all-items",params = {"page","size"})
    public ResponseEntity<StandardResponse> GetAllItems(@RequestParam(value = "page")int page,@RequestParam(value = "size")int size){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItems(page, size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"All items in Database", paginatedResponseItemDTO),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<StandardResponse> UpdateItem(@RequestBody ItemUpdateDTO itemUpdateDTO){
        ItemDTO itemDTO = itemService.UpdateItem(itemUpdateDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"Item updated successfully",itemDTO),HttpStatus.OK);
    }

    @GetMapping(value = "/get-item-by-id",params = {"id"})
    public ResponseEntity<StandardResponse> GetItemById(@RequestParam(value = "id")int id){
        ItemDTO itemDTO = itemService.getItemById(id);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"Item Found for id",itemDTO),HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/delete-by-id",params = {"id"})
    public ResponseEntity<StandardResponse> DeleteItem(@RequestParam(value = "id")int id){
        String deletedItem = itemService.deleteById(id);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"Item deleted successfully",deletedItem),HttpStatus.OK);
    }

    @GetMapping(value = "/get-item-by-activestate",params = {"status","page","size"})
    public ResponseEntity<StandardResponse> GetItemByActiveState(@RequestParam(value = "status")boolean status,
                                                                 @RequestParam(value = "page")int page,
                                                                 @RequestParam(value = "size")int size){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemByActiveState(status,page,size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"Data found",paginatedResponseItemDTO),HttpStatus.FOUND);
    }

    @GetMapping(value = "/get-item-by-name",params = {"name","page","size"})
    public ResponseEntity<StandardResponse> GetItemByName(@RequestParam(value = "name")String  name,
                                                          @RequestParam(value = "page")int page,
                                                          @RequestParam(value = "size")int size){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemByName(name,page,size);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200,"Data found",paginatedResponseItemDTO),HttpStatus.FOUND);
    }

}
