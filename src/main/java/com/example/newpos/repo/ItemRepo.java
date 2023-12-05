package com.example.newpos.repo;

import com.example.newpos.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item,Integer> {

    Page<Item> getItemByActiveStateEquals(boolean status, Pageable pageable);
    long countItemByActiveStateEquals (boolean status);

    Page<Item> getItemByItemNameContains(String name, Pageable pageable);
    long countItemByItemNameContains(String name);
}
