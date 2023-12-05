package com.example.newpos.service;

import com.example.newpos.dto.ItemDTO;
import com.example.newpos.dto.paginated.PaginatedResponseItemDTO;
import com.example.newpos.dto.request.ItemUpdateDTO;

public interface ItemService {
    String SaveItem(ItemDTO itemDTO);

    PaginatedResponseItemDTO getAllItems(int page, int size);

    ItemDTO UpdateItem(ItemUpdateDTO itemUpdateDTO);

    ItemDTO getItemById(int id);

    String deleteById(int id);

    PaginatedResponseItemDTO getItemByActiveState(boolean status, int page, int size);

    PaginatedResponseItemDTO getItemByName(String name, int page, int size);
}
