package com.example.ss3.service;

import com.example.ss3.entity.DishEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomServiceImpl implements CustomService {
    @Autowired
    UserCustomService userCustomService;
    @Override
    public Integer getUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        return userCustomService.getUserIdByUsername(username);
    }

    @Override
    public String filterDish(DishEntity dishEntity) {
//        ObjectMapper mapper = new ObjectMapper();
//        FilterProvider filters = new SimpleFilterProvider()
//                .addFilter("filterDish",
//                        SimpleBeanPropertyFilter.serializeAllExcept(
//                                "category_id","recipe_id","method","ingredient_des","category","recipe"));
//        ObjectWriter writer = mapper.writer(filters);
//        try {
//            return  writer.writeValueAsString(dishEntity);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

}
