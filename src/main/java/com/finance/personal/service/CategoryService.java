package com.finance.personal.service;

import com.finance.personal.dto.response.CategoryDTOResponse;
import com.finance.personal.dto.response.MessageDTOResponse;
import com.finance.personal.exception.DuplicatedItemException;
import com.finance.personal.exception.NotFoundException;
import com.finance.personal.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.personal.dto.request.CategoryDTORequest;
import com.finance.personal.model.CategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity createNewCategory(CategoryDTORequest request){
        CategoryEntity exist = categoryRepository.findByName(request.getName());
        if(exist != null){
            throw new DuplicatedItemException("Category already exists");
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(request.getName());
        return categoryRepository.save(categoryEntity);
    }

    public List<CategoryDTOResponse> getAllCategory(){
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<CategoryDTOResponse> responses = categoryEntities.stream()
                .map(CategoryDTOResponse::new)
                .collect(Collectors.toList());
        return responses;
    }

    public CategoryDTOResponse getCategoryById(Long id){
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category ID:" + id + " not found"));
        CategoryDTOResponse response = new CategoryDTOResponse();
        response.setId(id);
        response.setName(categoryEntity.getName());
        return response;
    }

    public MessageDTOResponse deleteCategoryById(Long id){
        MessageDTOResponse messageDTOResponse = new MessageDTOResponse();
        if(!categoryRepository.existsById(id)){
            throw new NotFoundException("Category ID:" + id + " not found");
        }
        categoryRepository.deleteById(id);
        messageDTOResponse.setMessage("Category ID:" + id + " has been deleted");
        return messageDTOResponse;
    }
}
