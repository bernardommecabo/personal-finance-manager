package com.finance.personal.controller;

import com.finance.personal.dto.response.CategoryDTOResponse;
import com.finance.personal.dto.response.MessageDTOResponse;
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
        CategoryDTOResponse categoryDTOResponse = categoryService.createNewCategory(request);
        return new ResponseEntity<>(categoryDTOResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTOResponse>> getAllCategory() {
        List<CategoryDTOResponse> categoryDTOResponse = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDTOResponse,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTOResponse> getCategoryById(@PathVariable Long id) {
        CategoryDTOResponse categoryDTOResponse = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDTOResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTOResponse> updateCategory(@PathVariable Long id, @RequestBody @Valid String name) {
        CategoryDTOResponse categoryDTOResponse = categoryService.updateCategory(id, name);
        return new ResponseEntity<>(categoryDTOResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTOResponse> deleteCategoryById(@PathVariable Long id) {
        MessageDTOResponse messageDTOResponse = categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(messageDTOResponse, HttpStatus.OK);
    }
}