package com.finance.personal.controller;

import com.finance.personal.dto.response.CategoryDTOResponse;
import com.finance.personal.model.CategoryEntity;
import com.finance.personal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.personal.dto.request.CategoryDTORequest;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTOResponse> createNewCategory(@RequestBody @Valid CategoryDTORequest request) {
        CategoryEntity category = categoryService.createNewCategory(request);
        CategoryDTOResponse categoryDTOResponse = new CategoryDTOResponse();
        categoryDTOResponse.setId(category.getId());
        categoryDTOResponse.setName(category.getName());
        return new ResponseEntity<>(categoryDTOResponse, HttpStatus.CREATED);
    }

    //getMapping findAll
    //retornaria a lista recebida do service

    //getMapping findById

    //deleteMapping deletar categoria
}
