package com.example.ss3.service;

import com.example.ss3.dto.ItemDto;
import com.example.ss3.entity.DishEntity;
import com.example.ss3.entity.ItemEntity;
import com.example.ss3.repository.CartRepo;
import com.example.ss3.repository.DishRepo;
import com.example.ss3.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    CustomService customService;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    DishRepo dishRepo;
    @Autowired
    UserCustomService userCustomService;
    @Autowired
    CartService cartService;

    @Override
    public List<ItemEntity> getAll() {
        return itemRepo.findAll();
    }

    @Override
    public Page<ItemEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.itemRepo.findAll(pageable);
    }

    @Override
    public ItemEntity findByID(Integer id) {
        return itemRepo.findById(id).get();
    }

    @Override
    public void add(ItemDto itemDto) {
        ItemEntity itemEntity = new ItemEntity(itemDto.getQuantity(),
                itemDto.getProduct_id(), itemDto.getCart_id());
        itemRepo.save(itemEntity);
    }

    @Override
    public ItemEntity save(ItemDto itemDto) {
        ItemEntity itemEntity = findByID(itemDto.getId());
        itemEntity.setQuantity(itemDto.getQuantity());
        return itemRepo.save(itemEntity);
    }

    @Override
    public void delete(Integer id) {
        ItemEntity itemEntity = findByID(id);
        if(itemEntity !=null)
            itemRepo.delete(itemEntity);

    }

    @Override
    public String removeFromCart(Integer id) {
        Integer userId = customService.getUserId();
        ItemEntity itemEntity = findByID(id);
        if(itemEntity.getCart().getUser_id()!=userId){
            return "remove failed. no permission";
        }
        itemRepo.delete(itemEntity);
        return "remove successful";
    }

    @Override
    public ItemEntity addToCart(ItemDto itemDto) {
        Integer userId = customService.getUserId();
        Integer cartId = cartService.getCartIdByUserId(userId);
        Integer itemId = cartService.checkTempCartByDishId(itemDto.getProduct_id());
        if(itemId != null){
            ItemEntity itemEntity = findByID(itemId);
                    itemEntity.setQuantity(itemEntity.getQuantity() + 1);
            return  itemEntity;
        }
        else {
            ItemEntity itemEntity = new ItemEntity(1,
                    itemDto.getProduct_id(), cartId);
            return  itemRepo.save(itemEntity);
        }
    }

    @Override
    public ItemEntity updateItemFromCart(ItemDto itemDto) {
        ItemEntity itemEntity = itemRepo.findById(itemDto.getId()).get();
        itemEntity.setQuantity(itemDto.getQuantity());
        return itemRepo.save(itemEntity);
    }
}
