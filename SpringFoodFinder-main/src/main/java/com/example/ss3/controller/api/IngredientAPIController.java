package com.example.ss3.controller.api;

import com.example.ss3.model.BaseResponse;
import com.example.ss3.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientAPIController {
    @Autowired
    IngredientService ingredientService;
    @GetMapping()
    public ResponseEntity getIngredientPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "0") Integer limit) {
        BaseResponse res = new BaseResponse();
        if(limit==0){
            res.data = ingredientService.getAllIngredient();
        }
        else {
            res.data = ingredientService.findPaginated(page,limit).getContent();
        }

        return ResponseEntity.ok(res);
    }
    @GetMapping("/search")
    public ResponseEntity getIngredientSearchPage(
            @RequestParam(value = "name", required = false, defaultValue = "Apple") String name,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                            @RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit) {
        BaseResponse res = new BaseResponse();
        res.data = ingredientService.findByName(name,page,limit).getContent();
        return ResponseEntity.ok(res);
    }

}
