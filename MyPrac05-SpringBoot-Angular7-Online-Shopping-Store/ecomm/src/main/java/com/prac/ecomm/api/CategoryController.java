package com.prac.ecomm.api;

import com.prac.ecomm.vo.response.CategoryPage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CategoryController {

    @GetMapping("/category/{type}")
    public CategoryPage showOne(){
        return null;
    }
}
