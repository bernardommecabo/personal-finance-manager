package com.finance.personal.controller;

import com.finance.personal.dto.response.CategoryDTOResponse;
import com.finance.personal.dto.response.MessageDTOResponse;
import com.finance.personal.model.CategoryEntity;
import com.finance.personal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finance.personal.dto.request.CategoryDTORequest;

import jakarta.validation.Valid;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CategoryDTOResponse>> getAllCategory() {
        List<CategoryDTOResponse> categoryDTOResponse = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDTOResponse,HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<CategoryDTOResponse> getCategoryById(@RequestBody CategoryDTORequest request) {
        CategoryDTOResponse categoryDTOResponse = categoryService.getCategoryById(request.getId());
        return new ResponseEntity<>(categoryDTOResponse, HttpStatus.OK);
    }

    @DeleteMapping("/id/deleteID")
    public ResponseEntity<MessageDTOResponse> deleteCategoryById(@RequestBody CategoryDTORequest request) {
        MessageDTOResponse messageDTOResponse = categoryService.deleteCategoryById(request.getId());
        return new ResponseEntity<>(messageDTOResponse, HttpStatus.OK);
    }
}