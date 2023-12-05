package com.example.newpos.service.impl;

import com.example.newpos.dto.ItemDTO;
import com.example.newpos.dto.paginated.PaginatedResponseItemDTO;
import com.example.newpos.dto.request.ItemUpdateDTO;
import com.example.newpos.entity.Item;
import com.example.newpos.exception.NotFoundException;
import com.example.newpos.repo.ItemRepo;
import com.example.newpos.service.ItemService;
import com.example.newpos.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceIMPl implements ItemService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String SaveItem(ItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO,Item.class);
        itemRepo.save(item);
        return item.getItemName()+" saved successfully";
    }

    @Override
    public PaginatedResponseItemDTO getAllItems(int page, int size) {
        Page<Item> items = itemRepo.findAll(PageRequest.of(page,size));
        if(items.getSize()>0){
            return new PaginatedResponseItemDTO(itemMapper.PageToItemDTO(items), itemRepo.count());
        }else {
            throw  new NotFoundException("No data Found.....!!");
        }
    }

    @Override
    public ItemDTO UpdateItem(ItemUpdateDTO itemUpdateDTO) {
        if(itemRepo.existsById(itemUpdateDTO.getItemId())){
            Item item1 = itemRepo.getReferenceById(itemUpdateDTO.getItemId());
            modelMapper.map(itemUpdateDTO,item1);
            itemRepo.save(item1);
            return modelMapper.map(item1,ItemDTO.class);
        } else {
            throw new NotFoundException("No data found for that id");
        }

    }

    @Override
    public ItemDTO getItemById(int id) {
        if(itemRepo.existsById(id)){
            Item item = itemRepo.getReferenceById(id);
            return modelMapper.map(item,ItemDTO.class);
        }else{
            throw new NotFoundException("Data not found");
        }
    }

    @Override
    public String deleteById(int id) {
        if(itemRepo.existsById(id)){
            itemRepo.deleteById(id);
            return "Deleted successfully";
        }else {
            throw new NotFoundException("No data found ");
        }
    }

    @Override
    public PaginatedResponseItemDTO getItemByActiveState(boolean status,int page, int size) {
        Page<Item> items = itemRepo.getItemByActiveStateEquals(status, PageRequest.of(page, size));
        if(items.getSize()>0){
            return new PaginatedResponseItemDTO(itemMapper.PageToItemDTO(items),itemRepo.countItemByActiveStateEquals(status));
        } else {
            throw  new NotFoundException("No data found");
        }

    }

    @Override
    public PaginatedResponseItemDTO getItemByName(String name, int page, int size) {
        Page<Item> items = itemRepo.getItemByItemNameContains(name,PageRequest.of(page, size));
        if(items.getSize()>0){
          return new PaginatedResponseItemDTO(itemMapper.PageToItemDTO(items),itemRepo.countItemByItemNameContains(name));
        }else {
            throw new NotFoundException("No data found");
        }
    }

}
