package com.example.newpos.util.mappers;

import com.example.newpos.dto.ItemDTO;
import com.example.newpos.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemDTO> PageToItemDTO (Page<Item> items);
}
