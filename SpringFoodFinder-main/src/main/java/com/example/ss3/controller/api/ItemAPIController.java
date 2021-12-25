package com.example.ss3.controller.api;

import com.example.ss3.dto.ItemDto;
import com.example.ss3.entity.UserEntity;
import com.example.ss3.model.BaseResponse;
import com.example.ss3.service.CartService;
import com.example.ss3.service.ItemService;
import com.example.ss3.service.UserCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemAPIController {
    @Autowired
    ItemService itemService;
    @Autowired
    UserCustomService userCustomService;
    @Autowired
    CartService cartService;
    @GetMapping()
    public ResponseEntity getOrdersById(@RequestParam(value = "id", required = true) Integer id) {
        BaseResponse res = new BaseResponse();
        res.data = itemService.findByID(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/remove")
    public ResponseEntity removeItemFromCart(@RequestParam(value = "id", required = true) Integer id) {
        BaseResponse res = new BaseResponse();
        res.data = itemService.removeFromCart(id);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/update")
    public ResponseEntity updateItemFromCart(@RequestBody ItemDto itemDto) {
        BaseResponse res = new BaseResponse();
        res.data = itemService.updateItemFromCart(itemDto);
        return ResponseEntity.ok(res);
    }



    @PostMapping()
    public  ResponseEntity postItem(@RequestBody ItemDto itemDto){
        BaseResponse res = new BaseResponse();
        res.data = itemService.addToCart(itemDto);
        return ResponseEntity.ok(res);
    }

}
