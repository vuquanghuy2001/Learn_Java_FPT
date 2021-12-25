package com.example.ss3.service;

import com.example.ss3.dto.CartDto;
import com.example.ss3.entity.CartEntity;
import com.example.ss3.entity.ItemEntity;
import com.example.ss3.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    CartRepo cartRepo;
    @Autowired
    CustomService customService;

    @Override
    public List<CartEntity> getAll() {
        return cartRepo.findAll();
    }

    @Override
    public Page<CartEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.cartRepo.findAll(pageable);
    }

    @Override
    public CartEntity findByID(Integer id) {
        return cartRepo.findById(id).get();
    }

    @Override
    public void updateStatus(int cart_id,int status_id,String comment) {
        CartEntity cart = findByID(cart_id);
        cart.setStatus_id(status_id);
        cart.setComment(comment);
        cartRepo.save(cart);
    }

    @Override
    public String add(CartDto cartDto) {
        CartEntity cartEntity = new CartEntity(
                cartDto.getUser_id(), cartDto.getPhone(),cartDto.getAddress(),cartDto.getComment(),cartDto.getStatus_id()
        );
        cartRepo.save(cartEntity);
        return "Add successfully";
    }

    @Override
    public List<CartEntity> findByUser(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<CartEntity> list = cartRepo.findCartByUser(customService.getUserId(),pageable).getContent();
        list.remove(list.size()-1);
        return list;
    }

    @Override
    public List<CartEntity> findAllByUser() {
        List<CartEntity> cartEntityList = cartRepo.findALlCartByUser(customService.getUserId());
        cartEntityList.remove(cartEntityList.size()-1);
//        Collections.sort(cartEntityList,Collections.reverseOrder());
//        cartEntityList.stream().skip(1).collect(Collectors.toList());
//        Collections.sort(cartEntityList,Collections.reverseOrder());
        return  cartEntityList ;
    }

    @Override
    public Page<CartEntity> findByStatus(Integer id, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return cartRepo.findByStatus_id(id,pageable);
    }

    @Override
    public long getTotal() {
        return cartRepo.count();
    }

    @Override
    public Integer getCartId() {
        Pageable pageable = PageRequest.of(0,1, Sort.Direction.DESC,"id");
        Integer id = cartRepo.findAll(pageable).toList().get(0).getId();
        return id;
    }

    @Override
    public Integer getCartIdByUserId(Integer userId) {
        List<CartEntity> cartEntityList = cartRepo.findALlCartByUser(userId);
        CartEntity cart = cartEntityList.get(cartEntityList.size() -1);
        return cart.getId();
    }

    @Override
    public CartEntity getTempCart() {
        return cartRepo.findById(getCartIdByUserId(customService.getUserId())).get();
    }

    @Override
    public CartEntity addCartUser(CartDto cartDto) {
        CartEntity cartEntity = getTempCart();
        cartEntity.setAddress(cartDto.getAddress());
        cartEntity.setPhone(cartDto.getPhone());
        add(new CartDto(customService.getUserId()));
        return  cartRepo.save(cartEntity);
    }

    @Override
    public Integer checkTempCartByDishId(Integer id) {
        CartEntity cartEntity = getTempCart();
        for (ItemEntity i : cartEntity.getItems()
             ) {
            if(i.getProduct_id() == id){
                return i.getId();
            }
        }
        return null;
    }
}
